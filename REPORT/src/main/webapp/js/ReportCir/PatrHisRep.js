$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	$('#Reterive').prop('disabled', true);
	$('input[name=chkStatus]').attr("disabled",true);
	
	$('#patronCate, #smd, #branch, #icat, #loca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#branch").multiselect("disable");
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	});
	
	//////////////checked branch
	$("#chkBranch").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			$('#branch').multiselect("enable");
	    } else {
			$('#branch').multiselect("disable");
	    }
	});
	
	$('#branch').change(function() {
		if($("#branch").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	});
	
	$("#chkStatuslabel").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
			///$('#Reterive').prop('disabled', true);
			$('input[name=chkStatus]').attr("disabled",false);
	    } else {
			///$('#Reterive').prop('disabled', false);
			$('input[name=chkStatus]').attr("disabled",true);
	    }
	});
	
	///on click searc patron 
	$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";

		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});
	
	///keyup
	$("#lblPatronID").keyup(function(e){
		var id = $("#lblPatronID").val();
		if(id.length==0){
			$('#Reterive').prop('disabled', true);
		}else{
			$('#Reterive').prop('disabled', false);
		}

		$(".lblName").empty();
		////display vendor name
		$.get('GetPatronName', {
        	id : id
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".lblName").text(value['Name']);
				});
			}
		});
	});
	
	
	//clear title when paron id keydown backspace
	$("#lblPatronID").keydown(function(e){ 
		var code = e.keyCode || e.which;
		if(code == '8' ||code == '46'){
			$("#.lblName").empty();
		}
		
		if(code == 13) {
		    e.preventDefault();
		    return false;
		 }
	});
	
	
	
	
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function() {
		switch ($(this).val()) {
			 case 'patrID':
				$('#Reterive').prop('disabled', false);
		      break;
		    case 'pateCate':
				$('#patronCate').multiselect("enable");
			  	$("#smd, #icat, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#patronCate').change(function() {
						if($("#patronCate").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					 });
		      break;
		    case 'itemcate':
				$('#icat').multiselect("enable");
			  	$("#smd, #patronCate, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
					$('#icat').change(function() {
						if($("#icat").val() != ""){
							$('#Reterive').prop('disabled', false);
						}else{
							$('#Reterive').prop('disabled', true);
						}
					});
		      break;
			case 'smd':
				$('#smd').multiselect("enable");
			  	$("#patronCate, #icat, #loca").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#smd').change(function() {
					if($("#smd").val() != ""){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
				});
		      break;
			case 'loca':
				$('#loca').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");
				$('#Reterive').prop('disabled', true);
				$('#loca').change(function() {
					if($("#loca").val() != ""){
						$('#Reterive').prop('disabled', false);
					}else{
						$('#Reterive').prop('disabled', true);
					}
				});
		      break;	
		  }
	});
	
	////////Branch mandatary
	/*$('#branch').change(function() {
		if($("#branch").val() != ""){
			$('#Reterive').prop('disabled', false);
		}else{
			$('#Reterive').prop('disabled', true);
		}
	 });*/

	///on click searc patron 
	/*$('.patronid').on('click',function(){
		var url = "Modal_PatrSearch";
		
		alert(url);
		$.get(url,function(data){
			$("#Modal_PatrSearchContent").html(data);
		});
	});*/


	
	
	
	//////table setup
	$('#reportTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	


	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTable').dataTable().fnClearTable();
		
		var dateSelection = $('input[name="chkDate"]:checked').val();
		//alert(dateSelection+"dateSelection");
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var sDate, eDate;   
		
		var startSentDate = $("#input-startDate").val();
		if(startSentDate == ''){
			startSentDate = '';
			sDate = '';
		}else{
			startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			sDate = "from " +$("#input-startDate").val();
		}
		
		var endSentDate = $("#input-endDate").val();
		if(endSentDate == ''){
			endSentDate = '';
			eDate = '';
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		}
		
		var checkStatusChecked =  $("input[name='chkStatuslabel']:checked").val();
		var statusVal;
		//alert(checkStatusChecked+"checkStatusChecked");
		
		if(checkStatusChecked=="status"){
			 statusVal = $('input[name="chkStatus"]:checked').val();
				if(statusVal==undefined){
					statusVal = "0";
				}
		}else{
			statusVal = "0";
		}
		//alert(statusVal+"statusVal");
		
		//chkStatus

		var brnch = $("#branch").val(); 
		if(brnch != ""){
		}else{
			brnch = 0;
		}
		
		var patron = $("#lblPatronID").val();
		
		
		$.get('GetPatronWithAddress', {
        	id : patron
		 	}, function(responseJson) {
			if(responseJson != null){
				$.each(responseJson, function(key,value) {
					$(".patrID").text(value['Id']);
					$(".patrName").text(value['Name']);
					$(".add1").text(value['Add1']);
					$(".add2").text(value['Add2']);
					$(".add3").text(value['Add3']);
					$(".patrStat").text(value['Status']);
					$(".patrCate").text(value['Category']);
					$(".memDate").text(value['MemberDate']);
					$(".expDate").text(value['Expiry']);
					
				});
			}
		});
		
		/////result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_PatrHisRep',
							title: '',
					        //title: 'Patron History Report',

					        //messageTop: 'Minimum Borrowing = ' ,
							customize: function (xlsx) {
                var sheet = xlsx.xl.worksheets['sheet1.xml'];
                var numrows = 7;
                var clR = $('row', sheet);

                //update Row
                clR.each(function () {
                    var attr = $(this).attr('r');
                    var ind = parseInt(attr);
                    ind = ind + numrows;
                    $(this).attr("r",ind);
                });

                // Create row before data
                $('row c ', sheet).each(function () {
                    var attr = $(this).attr('r');
                    var pre = attr.substring(0, 1);
                    var ind = parseInt(attr.substring(1, attr.length));
                    ind = ind + numrows;
                    $(this).attr("r", pre + ind);
                });

                function Addrow(index,data) {
                    msg='<row r="'+index+'">'
                    for(i=0;i<data.length;i++){
                        var key=data[i].key;
                        var value=data[i].value;
                        msg += '<c t="inlineStr" r="' + key + index + '">';
                        msg += '<is>';
                        msg +=  '<t>'+value+'</t>';
                        msg+=  '</is>';
                        msg+='</c>';
                    }
                    msg += '</row>';
                    return msg;
                }


                //insert
                var r1 = Addrow(1, [{ key: 'E', value: 'Patron History Report' }]);
				var r2 = Addrow(2, [{ key: 'A', value: '' }, { key: 'B', value: '' }]);
                var r3 = Addrow(3, [{ key: 'A', value: 'Patron Id : ' }, { key: 'B', value: $(".patrID").text() }]);
                var r4 = Addrow(4, [{ key: 'A', value: 'Patron Name :' }, { key: 'B', value: $(".patrName").text() }]);
				var r5 = Addrow(5, [{ key: 'A', value: 'Address : ' }, { key: 'B', value: $(".add1").text() }, { key: 'C', value: $(".add2").text() }, { key: 'D', value: $(".add3").text() }]);
			    var r6 = Addrow(6, [{ key: 'A', value: 'Patron Status : ' }, { key: 'B', value: $(".patrStat").text() }]);
				var r7 = Addrow(7, [{ key: 'A', value: 'Patron Category : ' }, { key: 'B', value: $(".patrCate").text() }]);

                sheet.childNodes[0].childNodes[1].innerHTML = r1 + r2 + r3 + r4 + r5 + r6 + r7 + sheet.childNodes[0].childNodes[1].innerHTML;
            }
					       /*	customize: function( xlsx ) {
						    	 var sheet = xlsx.xl.worksheets['sheet1.xml'];
						         // $('row:first c', sheet).attr( 's', '42' );
 								$('c[r=B3] t', sheet).text( 'Custom text' );
						     }*/
					                
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_PatrHisRep',
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							//messageTop: 'Income Details by Transaction Type' ,
							//messageBottom: "lllllll",
							exportOptions: {
								//columns: ':visible',
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
								
								 /*var rowCount = doc.content[1].table.body.length;
						           for (i = 0; i < rowCount; i++) {
										doc.content[1].table.body[i][2].alignment = 'right';
						               	doc.content[1].table.body[i][3].alignment = 'right';
										doc.content[1].table.body[i][4].alignment = 'right';
						           };*/
								
								//doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							//	doc.content[1].table.widths = [30,100,50,50,50];
							//doc.content[1].table.body[0][3].alignment = 'right';
							doc.content[1].table.widths = [ '3%', '10%', '18%', '4%', '5%', '5%','5%','6%','5%','7%','8%','8%','8%','8%'];
							 //doc.content[1].table.widths = [ '8%', '55%', '12%', '12%', '12%']; 
							 //doc.content[1].table.body[0][5].alignment = 'right';
						
						


											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
															{ text: 'Patron History Report '+'\n',bold:true,fontSize:13,alignment: 'center'},
						                                   { text: "Patron Id : " + $(".patrID").text() +"\n"
																  + "Patron Name : " + $(".patrName").text() +"\n"
																  + "Address : " + $(".add1").text() +" "+ $(".add2").text()+ " "+ $(".add3").text()+"\n"
																  + "Patron Status : " + $(".patrStat").text() +"\n"		
																  + "Patron Category : " + $(".patrCate").text() + "\n\n"
																
																
																,bold:true,fontSize:12,alignment: 'left'},
						                                   /////{ text: $("#libname").val() +'\n',bold:true,fontSize:13,alignment: 'center'},
						                        ],
						                        margin: [0, 0, 0, 12],
						                    });
					
											doc['footer']=(function(page, pages) {
												return {
													columns: [
														{
															alignment: 'left',
															text: ['\t\t\t\t  ', moment(today).format("DD/MM/YYYY") +'\t' +moment(today).format("hh:mm:ss A")]
														},
														{
															alignment: 'right',
															text: ['page ', { text: page.toString() },	' of ',	{ text: pages.toString() }]
														}
													],
													margin: 20
												}
											});
									}, 
					    },
					
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_PatrHisRep',
		                	title: 'Patron History Report ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										
										var split_csv = doc.split("\n");
										//alert(split_csv)
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										///alert(csv_cell_array[1]);
										//alert(csv_cell_array[1]);
										
										return "Patron History Report " +"\n\n" 
										  + "Patron Id : " + $(".patrID").text() +"\n"
										  + "Patron Name : " + $(".patrName").text() +"\n"
										  + "Address : " + $(".add1").text() +" "+ $(".add2").text()+ " "+ $(".add3").text()+"\n"
										  + "Patron Status : " + $(".patrStat").text() +"\n"		
										  + "Patron Category : " + $(".patrCate").text() + "\n\n"
										  + doc;
										//var split_csv = doc.split("\n");
										 
									 }
             			},
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress"
        	},
			//serverSide: true,
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultPatrHisRep",
		        data : {dateSelection : dateSelection, startSentDate : startSentDate, endSentDate : endSentDate,
					statusVal : statusVal,  brnch : JSON.stringify(brnch), patron : patron},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

					var callno = json[i].Column3;
					
					if(callno!=undefined){
						if(callno.charAt(0)=='*'){
							callno = callno.slice(1);
							callno = "<i>"+callno+"</i>";
						}	
					}else{
						callno = "";
					}
					
					var title = json[i].Column2;
					if(title!=undefined){
						if(title.charAt(0)=='*'){
							title = title.slice(1);
							title = "<i>"+title+"</i>";
						}	
					}else{
						title = "";
					}
						
					
		              return_data.push({
		                'No': (i+1),
		                'Accession No' : json[i].Column1,
		                'Title' : title,
		                'Call No' : callno,
						'Borrow Date/Time' : json[i].Column4,
						'Due Date/Time' : json[i].Column5,
						'Returned Date/Time' : json[i].Column6,
						'Status' : json[i].Column7,
		                'No of Renewal' : json[i].Column8,
						'Item Category' : json[i].Column9,
                        'Charge Officer' : json[i].Column10,	
						'Discharge Officer' : json[i].Column11,	
						'Location' : json[i].Column12,
						'Branch' : json[i].Column13,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Title'},
					{'data': 'Call No'},
					{'data': 'Borrow Date/Time'},
					{'data': 'Due Date/Time'},
					{'data': 'Returned Date/Time'},
					{'data': 'Status'},
					{'data': 'No of Renewal'},
					{'data': 'Item Category'},
					{'data': 'Charge Officer'},
					{'data': 'Discharge Officer'},
					{'data': 'Location'},
					{'data': 'Branch'},
					
										
				],

    });
		
		
		
		/*t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				t.cell(cell).invalidate('dom');
			 });
	   }).draw();*/
    	

    	
    });
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
});