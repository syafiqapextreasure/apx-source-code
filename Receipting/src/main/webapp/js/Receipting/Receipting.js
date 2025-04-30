
$(document).ready(function() {

	$(".override").on("click", function (e) {
			
			var userLoginName = $("#userLoginName").val();
			//alert(userLoginName+"masuk userLoginName?");
			var programID =  $("#programID").val();
	        $.ajax({
	            url: 'UserAccessLevel?username='+userLoginName+'&programID='+programID+'',
	            async: false,
	            success: function(data) {
	            	//alert(data+"masuk data");
	        	    if (data >= 2) {
	        	    	$('#overrideModal').modal('hide');
	        	    		swal({   
	        	    			title: 'Current Access Level is not allowed to perform Overriding.',
	        	    			type: 'warning',   
	        	    			cancelButtonColor: '#3085d6',
	        	    			cancelButtonText: 'No, cancel !',
	        	    			confirmButtonClass: 'confirm-class',
	        	    			closeOnClickOutside: false,
	        	    			closeOnConfirm: true,
	        	    			closeOnCancel: true 
	        	    			}).then(function() {
									//alert("tekanOK")
	        	    				$(".in").removeClass("modal-backdrop fade in");
	        	    				document.getElementById("overrideModal").style.display="none";
	        	    			})
/*	        	    		
	        				title: title,
	        				text: text,
	        				type: type,   
	        				showCancelButton: true,
	        				confirmButtonColor: '#3085d6', 
	        				cancelButtonColor: '#d33',
	        				confirmButtonText: confirmButtonText,
	        				cancelButtonText: 'No, cancel !',
	        				confirmButtonClass: 'confirm-class',
	        				cancelButtonClass: 'cancel-class',
	        				closeOnConfirm: false,
	        				closeOnCancel: false 
	        				*/
	        	    }
	            }
	        });
	        
	 });
	
    var userLoginName = $("#userLoginName").val();
	
	var $patronSearchPanel = $("#PatronSearchPanel");
	var $searchPatronInput = $patronSearchPanel.find("input#PatronId");
	var $searchPatronNameContainer = $patronSearchPanel.find("div[patron-name]");
	var $searchPatronName = $searchPatronNameContainer.find("input#PatronName");
	var $searchPatronNew = $patronSearchPanel.find("a[action=New]");
	var $searchPatronSearch = $patronSearchPanel.find("button[action=search]");
	var $searchPatronAdvanceSearch = $patronSearchPanel.find("button[action=advanceSearch]");
	var $transactionReceiptTab = $("#transaction-receipt");
	var $receiptSearchPanel = $transactionReceiptTab.find("#receiptSearchPanel");
	
	
	$("#PatronId").off('keydown').on('keydown', function(e)
			{
				//keydown if tab or enter
			var code = e.keyCode || e.which;
			if (code == '9'||code == '13') {
				
				var patronid = $(this).val();
				console.log("patronid " + patronid);
				$.get("PatronDetails",{patronid:patronid},function(data){
					if(data.trim()=="fail"){
						alertMessage("","", "036");
					}else{
					$(".misc").removeClass("hide");
					$searchPatronNameContainer.removeClass("hide");
					$searchPatronName.val(data);
					$searchPatronInput.attr("disabled", true);
					$searchPatronAdvanceSearch.attr("disabled", true);
					$searchPatronSearch.attr("disabled", true);
					$receiptSearchPanel.removeClass("hide");
					//$receiptSearchPanel.removeClass("hide");
					getPaidList(patronid);
					getDueList(patronid);
					$.get("ListReceipt",{patronid:patronid},function(data){
						console.log("Done3");
						$("#pagingContainerReceipt").html(data);
					});
					}
					
				});
			}
					 
	});
	
/*
	$(".refresh").click(function(){

		//location.reload();
		var sPageURL = decodeURIComponent(window.parent.location),
        sURLVariables = sPageURL.split('?');
		var windowURL =sPageURL.replace("?"+sURLVariables, "");

		if(windowURL==null || windowURL==""){
			location.reload();
		}else{
			 history.pushState(null, null, windowURL);
		//	  location.reload();
		}

		 var varArray = (sURLVariables[1]).split("="); //eg. index.html?msg=1
			if((varArray[1]!=null)&& varArray[1]!=""){
			document.getElementById("PatronId").value = varArray[1];
			$("#PatronId").focus();
			}
		// var varArray = (sURLVariables[1]).split("="); //eg. index.html?msg=1
		 works
		 * var varArray1 = (sURLVariables[1]).split("&"); //eg. index.html?msg=1
		  var windowURL = sPageURL.replace("&"+varArray1[2], "");
		  alert(windowURL);
		  history.pushState(null, null, windowURL);
		  location.reload();

	})
	*/
		$(".refresh").off('click').on('click', function(){
		
		reload();
		
		});
			
	$("#Amount").focusout(function(){
		var amt = parseFloat($(this).val());
		var payment = parseFloat($("#Payment").text());

		if(amt<payment){
			swal({
				  title: 'Amount',
				  text: 'Total distribution amount exceeds received amount'
				})
				document.getElementById("PayBtn").disabled = true; 
		}else{
			document.getElementById("PayBtn").disabled = false; 
		}
		amt = amt - payment;
		$("#Change").text(amt.toFixed(2));
	})
	
	
	/*$("#selectTrxn").on('change', function(e)
	{ 	alert("sd");
		 var code = $(this).parents('tr:first').text(); 
		 alert(code);
					 
	});*/
	
	$(".misc").click(function(){
		$.get("miscellaneous",function(data){
			$(".modal_contentmisc").html(data);
		})
	})

	var getDueList = function (patronid) {
		$.get("ListPendingPayment",{patronid:patronid},function(data){
			console.log("Done2");
			$("#pendingtable").html(data);
		});
	}
	
	var getPaidList = function (patronid) {
	$.get("ListPaidPayment",{patronid:patronid},function(data){
		console.log("Done1");
		$("#transaction-paid").html(data);
	});
	}
	
	var getReceiptList = function (patronid) {
	$.get("ListReceipt",{patronid:patronid},function(data){
		$("#pagingContainerReceipt-paid").html(data);
	});
	}
	
	
});




function reload(){
    console.log('reload button clicked1');
    
    executed = false;
    
    var $patronSearchPanel = $("#PatronSearchPanel");
	var $searchPatronNameContainer = $patronSearchPanel.find("div[patron-name]");
    
	var tableSearchByAccno =  $('#searchbyaccno').DataTable();
	tableSearchByAccno.rows().remove().draw();
	
	var tableSearchByPaid =  $('#searchbypaid').DataTable();
	tableSearchByPaid.rows().remove().draw();
	
	var tableSearchByReceipt =  $('#searchbyreceipt').DataTable();
	tableSearchByReceipt.rows().remove().draw();
	
	$('#transaction tbody').empty();
	
/*	var transactionTable =  $('#transaction').DataTable();
	transactionTable.rows().remove().draw();*/
	
//	$('#transaction').dataTable().fnClearTable();
	
	 $("#CT03DOCNO").val("");
		$("#PatronId").val("");
		$("#PatronId").prop('disabled', false);
		$searchPatronNameContainer.addClass("hide");

		$("#Payment").text("0.00");
		$("#Rounding").text("0.00");
		$("#Amount").val("0.00");
		$("#Change").text("0.00");
		
		$(".due").text("0.00");
		$("#distribution").text("0.00");
		$("#btn_add").prop('disabled', false);
		

}


	function selectTrxn(receipt){
		var trxn =  $(receipt).closest('tr').find('td:eq(0)').text();
		var code =  $(receipt).closest('tr').find('td:eq(2)').text();
		var due =  $(receipt).closest('tr').find('td:eq(6)').text();
		var distribution =  $(receipt).closest('tr').find('td:eq(6)').text();
		var docno =  $(receipt).closest('tr').find('td:eq(5)').text();
		if ($(receipt).is(':checked')) {
			$.get("DistributionModal",{action:"AddSelectedTrxn", trxn:trxn, due:due,distribution:distribution,code:code,docno:docno},function(data){
				$("#selectedTransaction").append(data);
				calculateTotal();
			});
		}else{
			$('.transaction tbody tr').each(function () {
				var currtrxn =  $(this).closest('tr').find('td:eq(1)').text();
			
				if(trxn==currtrxn){
					 $(this).closest('tr').remove();
				}
			});

			  calculateTotal();
		}
	
	}
	
	function calculateTotal(){
		var duetotal = 0.00;
		var distributiontotal = 0.00;
		$('#transaction tbody tr').each(function () {
			var due =  $(this).closest('tr').find('td:eq(3)').text();
			var distribution =  $(this).closest('tr').find('td:eq(4)').text();
			duetotal += parseFloat(due);
			distributiontotal +=  parseFloat(distribution);
	    });
		
		$("#Payment").html(distributiontotal.toFixed(2));
		$("#Total").html(distributiontotal);
		$(".due").html(duetotal.toFixed(2));
		$(".distribution").html(distributiontotal.toFixed(2));
	}
	
	function deleteTrxn(r) {
		  var i = r.parentNode.parentNode.rowIndex;
		  document.getElementById("transaction").deleteRow(i);
		  var trxn =  $(r).closest('tr').find('td:eq(1)').text();
		 // var due =  $(r).closest('tr').find('td:eq(1)').text();

		  $('#searchbyaccno tbody tr').each(function() {
			  var trxn1 =  $(this).closest('tr').find('td:eq(0)').text();
			  
			  if(trxn==trxn1){
				  $('#selectTrxn').attr('checked', false);
			  }
			  
			});
		  
		  calculateTotal();
		}
			
	function editlist(list)
	{ 
		var trxn =  $(list).closest('tr').find('td:eq(1)').text();
		var due =  $(list).closest('tr').find('td:eq(3)').text();
		var distribution =  $(list).closest('tr').find('span').text();
		//alert(list);
		$("#mosal").modal("show");
	$.get("DistributionModal",{action:"distributionModal",trxn:trxn, due:due,distribution:distribution},function(data){
			$("#data").html(data);
		});

	}
	
	function changeDistribution(currtrxn){
	
		var distribution = $("#deduction").val();
	
		$('.transaction tbody tr').each(function () {
			var trxn =  $(this).closest('tr').find('td:eq(1)').text();
		
			if(trxn==currtrxn){
				
					var editbutton = "<a title='Edit' class='btn btn-default btn-sm' data-toggle='modal' onclick='editlist(this)'>"+
								"<i class='glyphicon glyphicon-pencil'></i>"+
								"<span class='dist'>"+distribution+"</span></a>";
				 $(this).closest('tr').find('td:eq(4)').html(editbutton);
				 calculateTotal();
				 $("#mosal").modal("hide");
				 
			}
			/*var distribution =  $(this).closest('tr').find('td:eq(3)').text();
			duetotal += parseFloat(due);
			alert(duetotal);
			distributiontotal +=  parseFloat(distribution);*/
	    });
	}
/*	
    var table = $('#searchbyaccno').DataTable({
        destroy: true,
        searching: false,
        bLengthChange: false,
        autoWidth: false,
        responsive: true,
        aaSorting: [],
        processing: true,
    });
	*/
	function accept(){
		console.log("amount press");
		//alert("ssdsds");
		var distribution = $("#distribution").text();
		var amount = $("#Amount").val();
		var codes =  [];
		var amts =  [];
		var trxns = [];
		var docnos =  [];
		var patrid = $("#PatronId").val();
		var paymode = $("#PayMode").val();
		var paymentMode = $('#PayMode option:selected').text();
		//09012020
		var totalPayment = $("#Payment").text();
		/*var amt = $("#Amount").val();*/
		var reference =  $("#Reference").val();
		console.log('166 amount: '+ amount);
//		if(amount == null && amount == "null" && amount == " " && amount == 0.00 && amount == ""){
//			swal("", "Please put amount.", "");
//			return;
//		}
		if(paymode=="0"){
			swal("", "Please select a payment method.", "");
		}else{
			console.log('166 paymode: '+ paymode);
			console.log('166 paymode text: '+ paymentMode);
			console.log('347 docno: '+ docnos);
			document.getElementById("PayBtn").disabled = true;
			
			swal({   
				title: 'Accept payment?',
				html:
				    'Amount Received: '+ amount +'<br/> ' +
				    'Total Distribution: ' + distribution + '<br/><br/>Are you sure to proceed?',
				type: 'warning',  
				allowOutsideClick: false,
				showCancelButton: true,
				confirmButtonColor: '#3085d6', 
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes',
				cancelButtonText: 'No',
				confirmButtonClass: 'confirm-class',
				cancelButtonClass: 'cancel-class',
				closeOnConfirm: false,
				closeOnCancel: true
				}).then(function() {
					document.getElementById("PayBtn").disabled = true;
					$('#selectedTransaction tr').each(function () {
						var code =  $(this).closest('tr').find('td:eq(2)').text();
						var amt =  $(this).closest('tr').find('span.dist').text();
						//alert(amt);
						var trxn =  $(this).closest('tr').find('td:eq(1)').text();
						var docno =  $(this).closest('tr').find('td:eq(0)').text().trim();
						
						console.log('375 docno: '+ docno);
						
						//var patrid = $("#PatronId").val();
						/*var paymode = $("#PayMode").val();
						var amt = $("#Amount").val();*/
						code = code.substring(0,2);
						codes.push(code);
						amts.push(amt);
						trxns.push(trxn);
						docnos.push(docno);
					});		
	
					$.get("AddTransaction",{action:"paidamt", amt:amts, reference:reference, trxn:trxns, code:codes,
						patrid:patrid, paymode:paymode, docno:docnos, total:docnos.length, paymode:paymode},function(data){
							//alert(data.trim());
							var message = (data.trim()).split(",");
							if(message[0]=="Successful"){
								document.getElementById("PayBtn").disabled = false;
								//alert("message" +message[0]);
								//var url = "printReceipt?patronid="+patrid+"&receiptno=" + message[1];
								//14112019
								//alert("message[1]" +message[1]);
								//alert("amts"+amts);
								var url = "GeneratePreview?receiptno=" + message[1]+ 
										"&total=" + totalPayment+
										"&received=" + $("#Amount").val()+
										"&change=" + $("#Change").text()+
										"&PayMode=" + paymentMode+
										"&patronid=" + patrid;
								
								//alert(url);
								window.open(url,"", "width=283,height=500,toolbar=0").print();
								$("#selectedTransaction").empty();
								$(".due").text("0.00");
								$(".distribution").text("0.00");
								$("#Payment").text("0.00");
								$("#Change").text("0.00");
								$("#Amount").val("0.00");
								
								setTimeout(function(){ 
									$.get("ListPendingPayment",{patronid:patrid},function(data){
										$("#pendingtable").html(data);
									})
									$.get("ListPaidPayment",{patronid:patrid},function(data){
								
										$("#transaction-paid").html(data);
									})
									
									$.get("ListReceipt",{patronid:patrid},function(data){
								
										$("#pagingContainerReceipt").html(data);
									});
								}, 1000);
							}
					})
				
							},function(dismiss) {
								  if (dismiss === 'cancel') {
									document.getElementById("PayBtn").disabled = false;
								    swal(
								      'Cancelled',
								      'Transaction is cancelled',
								      'error'
								    );
								  }
								})
		}
	}
	
	function addTransc(){
		var code = $("#Code option:selected").val();
		var date = $("#Date").val();
		var amt = $("#MiscAmount").val();
		var patrid = $("#PatronId").val();
		var docno = $("#AccessionNumber").val();
		var reference =  $("#Reference").val();

//		alert('line 212 receipting.js addTranc() docno: '+ docno);
		
		if(amt=="" ||code=="0"||reference==""){
			swal("", "Please fill in mandatory fields", "");
		}else{
		$.get("AddTransaction",{action:"misc", amt:amt, reference:reference, code:code,date:date,
								patrid:patrid, docno:docno},function(data){
	
				if(data.trim()=="success"){
					$("#miscModal").modal("hide");
					$.get("ListPendingPayment",{patronid:patrid},function(data){
						$("#pendingtable").html(data);
					})
					$.get("ListPaidPayment",{patronid:patrid},function(data){
						$("#transaction-paid").html(data);
					})
					
					$.get("ListReceipt",{patronid:patrid},function(data){
						
						$("#pagingContainerReceipt").html(data);
					});
					swal("", "Successfully added", "");
					}
		})
		}
	}

