/**
 * 
 */

//Update Indicators
function updateIndi(indi){
	var tag = $(indi).data('tag');
	var indilvl = $(indi).data('indilvl');
	var indi = $(indi).val();
	$.get("BO_AjaxHandler",{tag : tag, indilvl: indilvl, indi : indi},function(data){
		$(".errorMessage").html(data);
	});
}

//Get subfield description onkeyup
function getDesc(desc){
	var subfDesc = $(desc).data('desc');
	$('#showSubf').html(subfDesc);
}

//Delete subfield
function removeSubf(deleteSubf){
	 var i = deleteSubf.parentNode.parentNode.rowIndex;
	    document.getElementById("subfTable").deleteRow(i);
}

	//To get Control Tag values 
	function report() {

		var ctrlTagValue = document.getElementById("ctrlTagValue");
		var listValue = document.getElementById("allValue");
		var selector = "#tag_" + ctrlTagValue.value;
		$(selector).html(listValue.value);
		/* var ctrlTagID = $(".ctrlTagID").attr("id");
		var ctrlValue = document.getElementById(ctrlTagID);
		ctrlTagID = ctrlTagValue.value;		
		
			ctrlValue.value = listValue.value;
			alert(ctrlValue.value);
	*/
     }
	
	//Get control tag and media record values onchange
	function getValues(id, values){
		
		
		 var attr1 = $(".position").attr("id");
		 attr1 = id;

		 document.getElementById(attr1).innerHTML = values;
		 var table = document.getElementById('mytable');
		 var allValue = document.getElementById("allValue");
		 var cloneValue= '';
	        for (var r = 0, n = table.rows.length; r < n; r++) {
	            for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
	                /* alert(table.rows[r].cells[c].innerHTML); */
	                var tables = table.rows[r].cells[c].innerHTML;
	                tables = tables.split('&nbsp;').join('  ');
	                //alert(tables);
	                //alert(cloneValue);
	                 cloneValue = cloneValue + tables;
	            	//allValue.value = allValue.value + tables;  
	               
	            }
	        }
	        allValue.value = cloneValue;
	}

//Ajax/Jquery functions in BO Organisation
	$(document).ready(function() {
		
		//Open up control tag model
		$(".ctrlTags").click(function(){
			  var ctrlTag =  $(this).attr("value");
			  var ctrlID =  $(this).data("id");
		      var data = 'ctrlTag='+ ctrlTag +"&ctrlID=" + ctrlID;
		           $.ajax({
		              type: "GET",
		              url: "Control_Fields",
		              data: data,
		              success: function(msg){
		              	
		            	  /* $("#ctrlTagsForm").show(); */
		            	   $("#ctrlTagDisplay").html(msg);
		              }
		          }); 
		  });
		
		//Delete selected row with tag and details
		$('#BibRcrd').on('click', '.delete', function(e) {
			e.preventDefault();
		    $(this).closest('tbody').remove();
		});
		
	
		
	$('#btn-submit-title').click(function(){
			
			
			var title = $("#criteria").val();
			
			var tag = $("#tag").val();
			$.get("Table_TermSearch",{criteria:title,tag:tag},function(data_title){
				$("#display_title").html(data_title);
			   });	
			
		});
	
	//Select media record in the dropdown list
	 $("#ctrlTagSlct").change(function(){
    	 
			var ctrlTagCodes = document.getElementById("ctrlTagSlct").value;
			
           var data = 'ctrlTagCodes='+ ctrlTagCodes;
                $.ajax({
                   type: "GET",
                   url: "Media_Record",
                   data: data,
                   success: function(msg){
                 	
                 	  //$("#ctrlTagCode").show();
                        $("#ctrlTagCode").html(msg);

                   }
               }); 
           
       });
	 
	 $("a#addBORcrd").click(function() {

			  $('#BibRcrd').find("tbody").each(function(index){
			   var CT04MATNO = $(this).find("input.matno").val();
			   var CT04TAG = $(this).find("td.tagValue").html();
			   var CT04INDI1 = $(this).find("input.indiValue1").val();
			   var CT04INDI2 = $(this).find("input.indiValue2").val();
			   var CT04RAW = $(this).find("td.subfield").html();
			   var CT04COUNTER = $(this).find("input.counter").val();
			   
			   $.get("Handler_AddBO",{CT04MATNO:CT04MATNO,CT04TAG:CT04TAG,CT04INDI1:CT04INDI1,CT04INDI2:CT04INDI2,CT04RAW:CT04RAW,CT04COUNTER:CT04COUNTER},function(data_title){
					//$("#display_title").html(data_title);
				   });
			   //alert(tag + indi1 + indi2 + subfield);

			  });



				 /*var allvals = $('input[name^="tag_').map(function() { return  $(this).attr("id")+this.value; }).get().join('');
				  var no = this.name.replace("tag_", "");
				  var selector = "#tag_" + no;
				  //var newValue = subfield[0]+allvals;
				  //$(selector).val(allvals);
				  $(selector).html(allvals);*/
	
			});
	 
   	  $("#retrieveLink").click(function(){
   		 $('#tpl').prop('disabled', true);
   	  })
        /* $("#tpl").change(function(){
      
			var tplName1 = $('#tpl').val();
     //alert(tplName1);
             var data = 'tplName1='+ tplName1;
                  $.ajax({
                     type: "GET",
                     url: "Tester",
                     data: data,
                     success: function(msg){
                   	  $("#result").show();
                          $("#tags").html(msg);
                      

                     }
                 }); 
             
         });
   	  $('#addBORcrd').click(function(){
   		  $("#delBORcrd").css("cursor","pointer");
   			var inc = 0;
   			$(".tag div").each(function () {
   				  var txtCT04TAG = $(this).find(".txtCT04TAG");
   				    var txtCT04CONV = $(this).find(".newRawSubf");
   				    //var txtBOData = $(this).find(".txtBOData");
   				  var txtCT04INDI1 = $(this).find(".txtnewCT04INDI1");
   				  var txtCT04INDI2 = $(this).find(".txtnewCT04INDI2");

   				var url = "jsp/modules/Cataloging/BO_Record/Handler_AddNewBO.jsp?CT04MATNO=" + $('#txtCT04MATNO').val()+
   					"&CT04MARC=U" +
   					"&CT04TAG=" +txtCT04TAG.val()+
   					"&CT04RAW=" +txtCT04CONV.val() + 
   					"&CT04INDI1=" + encodeURIComponent(txtCT04INDI1.val())+
   					"&CT04INDI2=" + encodeURIComponent(txtCT04INDI2.val())+
   					"&CT04COUNTER=" + (inc++);
   			alert(url);
   			$.ajax({
   					url: url,
   					success: function(result) {
   						var updateRecord = clearSpace(result);
   						if(updateRecord == "ok"){
   							showPopupMsg("Update successful.","success");
   							$('#addAccMaint').modal('hide');
   							reloadRecord();
   						}else{
   							showPopupMsg("Please fill up the necessary details.","warning");
   						}
   					}
   				});
   			});
   		});
   	  
   		$('#indexBO').click(function() {
   			 var table=document.getElementById('worktbl');
   			 for(var i=0; i<table.rows.length;i++){

   			        // FIX THIS
   			        var row = 0;

   			        var str_wc=(table.rows[i].cells[0].value);
   			        var str_act=(table.rows[i].cells[1].innerHTML);
   			        var str_coh=(table.rows[i].cells[2].innerHTML);
   			        var str_mconf=(table.rows[i].cells[3].innerHTML);
   			        var str_mconf1=(table.rows[i].cells[4].value);
   			        var str_mconf2=(table.rows[i].cells[5].value);

   			        var string1=str_wc +row+str_act+row+str_coh+row+str_mconf+row;

   			        alert(string1);
   			     }
   			 var data = [];
   			 var tbl = document.getElementById("worktbl");
   			var cells = tbl.getElementsByTagName("tr");
   	        for (var i = 0; i < cells.length; i++) {
   	            data.push(cells[i].val());
   	        }
   			 
   		      alert(data);
   		 
   			var inc = 0;
   			$(".worktbl tr").each(function () {
   				 var matno = $(this).find(".matno");
   				  var tag = $(this).find(".tag");
   				    var indi1 = $(this).find(".indi1");
     				  var indi2 = $(this).find(".indi2");
   				  var rawdata = $(this).find(".rawdata");
   				  var tablename = $("#").find(".tablename");

   				var url = "jsp/modules/Cataloging/BO_Record/Handler_Indexing.jsp?matno=" + matno.val()+
   					"&tag=" +tag.val()+
   					"&rawdata=" +rawdata.val() + 
   					"&indi2=" + encodeURIComponent(indi1.val())+
   					"&indi1=" + encodeURIComponent(indi2.val())+
   					"&tablename=" + tablename.val();

   				$.get(url,function(data){
   					//$("#tags").html(data);
   				});	
   			});
   			
   		});	
         */
   	$('#retrieveBO input').on('change', function() {
		   var checkedValue  = $('input[name=searchin]:checked', '#retrieveBO').val(); 
		   if(checkedValue=="index"){
			   $("#buffer").hide();
			   $("#index").show();
		   }
		   else if(checkedValue=="buffer"){
			   document.getElementById("search_type").disabled = true;
			   $("#buffer").show();
			   $("#index").hide();
		   }
		});
   	  
   	$("input[type=radio][value=index]").prop("checked",true);
	$('#btn-submit-retrievemodal').click(function(){
		
		// Display loading bar
		//replaceLoader("#display_title");
		var newSearch = $("input[name='searchSelection']:checked").val();
		 var checkedValue  = $('input[name=searchin]:checked', '#retrieveBO').val(); 
		 if(checkedValue=="index"){
			 if(newSearch=="selection"){
				var title = $('#criteria').val();
				var searchType = $('#search_type').val();
				$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType},function(data_title){
					$("#display_title").html(data_title);
				   });	
				}
		else if(newSearch=="bufferNo"){
			var title = $('#criteria').val();
			
			$.get("jsp/modules/Cataloging/BO_Record/RetrieveBufferNo.jsp",{criteria:title},function(data_title){
				$("#display_title").html(data_title);
			   });	
			}
		else if(newSearch=="ctrlNo"){
			var title = $('#criteria').val();
			
			$.get("jsp/modules/Cataloging/BO_Record/RetrieveCtrlNo.jsp",{criteria:title},function(data_title){
				$("#display_title").html(data_title);
			   });	
			}
		   }
		   else{
			   if(newSearch=="bufferNo"){
					var title = $('#criteria').val();
					var searchType = "bufferno";
					$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType},function(data_title){
						$("#display_title").html(data_title);
					   });	
					}
		   }
	}) 
	
	$('.clickable-row').click(function() {
		var pointer = $(this).attr("data-value");
		var title = $(this).find(".title").text();
		var matno = $(this).data("matno");
	
		if(pointer!=null){
		$.get("ViewTitles",{pointer : pointer},function(data){
			$("#display_title").html(data);
		});	
			document.getElementById('controlNo').focus();
		}	
		
		
		else if(matno!=null){

		url = "Table_UnindexBO?matno="+matno+"&action=IndexUnindex";
		$("#tags").empty();
				$.get(url,function(data_title){
					$('#titleSearch').modal('hide');
					$('body').removeClass('modal-open');
					$('.modal-backdrop').remove();
					$('.collapse').show();
					$("#tags").html(data_title);
					/*document.getElementById("actionstatus").innerHTML = "Status: Record Retrieved";*/
					/*$("#actionstatus").html("Status: Record Retrieved");*/
				});	
			}
		});
	
	$('.cancel').click(function() {
		$('#product-options').modal('hide');
	});

	$('.unindex').click(function() {
		 $('#BibRcrd').find("tbody").each(function(index){
			   var CT04MATNO = $(this).find("input.matno").val();
			   var CT04TAG = $(this).find("td.tagValue").html();
			   var CT04INDI1 = $(this).find("input.indiValue1").val();
			   var CT04INDI2 = $(this).find("input.indiValue2").val();
			   var CT04RAW = $(this).find("td.subfield").html();
			   var CT04COUNTER = $(this).find("input.counter").val();
			   var credate = $(this).find(".credate").text();
			   var creby = $(this).find(".creby").text();
			   var cretime = $(this).find(".cretime").text();
			   var status = $(this).find(".status").text();
			   var newstring = CT04MATNO.substring(0,1);
			   var newstring2= newstring.replace(newstring,"A");
			   var newstring1 = CT04MATNO.substring(1);
			   var newMatno = newstring2+newstring1;
			   var url = "Handler_Unindex?matno=" +newMatno+
		     	"&tag=" + CT04TAG +
					"&indi1=" + CT04INDI1 + 
					"&indi2=" + CT04INDI2 +
					"&raw=" + CT04RAW +
					"&counter=" + CT04COUNTER +
					"&orimatno=" + CT04MATNO + 
					"&credate=" + credate +
					"&creby=" + creby +
					"&cretime=" + cretime +
					"&status=" + status;	
			
			$.get(url,function(data_title){
				$("#actionstatus").html("Status: Unindex");
					//$("#display_title").html(data_title);
				   });

			  });
	     	
		});
	
	  
	$('#indexBO').click(function() {
		 var table=document.getElementById('worktbl');
		 for(var i=0; i<table.rows.length;i++){

		        // FIX THIS
		        var row = 0;

		        var str_wc=(table.rows[i].cells[0].value);
		        var str_act=(table.rows[i].cells[1].innerHTML);
		        var str_coh=(table.rows[i].cells[2].innerHTML);
		        var str_mconf=(table.rows[i].cells[3].innerHTML);
		        var str_mconf1=(table.rows[i].cells[4].value);
		        var str_mconf2=(table.rows[i].cells[5].value);

		        var string1=str_wc +row+str_act+row+str_coh+row+str_mconf+row;

		        alert(string1);
		     }

	      alert(data);
	 
		var inc = 0;
		$(".worktbl tr").each(function () {
			 var matno = $(this).find(".matno");
			  var tag = $(this).find(".tag");
			    var indi1 = $(this).find(".indi1");
				  var indi2 = $(this).find(".indi2");
			  var rawdata = $(this).find(".rawdata");
			  var tablename = $("#").find(".tablename");

			var url = "jsp/modules/Cataloging/BO_Record/Handler_Indexing.jsp?matno=" + matno.val()+
				"&tag=" +tag.val()+
				"&rawdata=" +rawdata.val() + 
				"&indi2=" + encodeURIComponent(indi1.val())+
				"&indi1=" + encodeURIComponent(indi2.val())+
				"&tablename=" + tablename.val();

			$.get(url,function(data){
				//$("#tags").html(data);
			});	
		});
		
	});
	
	$("#enableSelect").click(function(){
        $('#tpl').prop('disabled', false);
     });


     });
