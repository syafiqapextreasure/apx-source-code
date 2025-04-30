$(document).ready(function() {
	var today = new Date();
	$("#input-contorlNo").prop( "disabled", true);
	$('.searchCtrlNo').prop('disabled', true);
	$(".searchCtrlNo").removeAttr('href');  
	
	/////////////////--- EDITOR ------/////////
	$('input[type=radio][name=selectionoption]').on('change', function() {
		switch ($(this).val()) {
			 case 'range':
				$("#rangeInput").prop( "disabled", false);
				$("#input-contorlNo").prop( "disabled", true);
		      break;
			case 'controlNo':
				$("#rangeInput").prop( "disabled", true);
				$("#input-contorlNo").prop( "disabled", false);
		      break;
		}
	});
	
	///Range Label
	$(".rangeLabel").html("<b>Order Number Range</b>");
	/////////////////--- EDITOR ------/////////

	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		$('#reportTable').dataTable().fnClearTable();
		
		 $.get('ResultGetLibName', {
			 	}, function(responseJson) {
				$(".libname").val(responseJson);
		 });
	
		var currencyFormat = $("#setupCurrency").val();
		var optionSelect =  $('input[name="selectionoption"]:checked').val();
		var searchval;

		switch (optionSelect) {
			 case 'range':
				searchval = $("#rangeInput").val();
		      break;
			 case 'controlNo':
				searchval = $("#input-contorlNo").val();
		      break;
		}
		
		 var chkBoxAcqPrint = $("#chkBoxAcqPrint").is(":checked");
         if (chkBoxAcqPrint) {
			chkBoxAcqPrint = 'y';
         } else {
            chkBoxAcqPrint = 0;
         }
		/////////////////--- EDITOR ------/////////		
		
		/////result display
		var t = $('#reportTable').DataTable({
			dom: 'Bfrtip',
            buttons: [
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_ReceivingSlip',
							title: 'Receiving Slip ',
					                
					 	},
               			{
		                	extend: 'csv',
		                	filename: 'WILMU_ReceivingSlip',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return "Receiving Slip \n\n" + doc;
										//return "Receiving Slip "+sDate + eDate +"\n\n" + doc;
									 }
             			},
						{	text: '<i class="fa fa-file-pdf-o"></i> PDF',
				            action: function ( e, dt, node, config ) {
								$.post("Global?type=Handler&name=GeneratePreviewDocumentAcqReportListing", {
									actionPrint : "ReceivingSlip",
									currencyFormat: currencyFormat,
									optionSelect: optionSelect,
									searchval: searchval,
									chkBoxAcqPrint: chkBoxAcqPrint
								},
									function(result){
										var w = window.open("_blank");
										w.document.write(result);
										w.print();	 
									});
				            }
					    }
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultReceivingSlip",
		        data : {currencyFormat :  currencyFormat, 
						optionSelect : optionSelect, 
						searchval : searchval, 
						chkBoxAcqPrint : chkBoxAcqPrint,
						},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'No': (i+1),
		                'Order No' : json[i].Column1,
						'Control No' : json[i].Column2,
						'Accession No' : json[i].Column3,
		                'Title' : json[i].Column4,
		                'Author' : json[i].Column5,
		                'Publisher' : json[i].Column6,
						'Location' : json[i].Column7,
						'Branch' : json[i].Column8,
						'Item Category' : json[i].Column9,
						'Condition' : json[i].Column10,
						'SMD' : json[i].Column11,
						'Volume' : json[i].Column12,
						'Price' : json[i].Column13,
						'Status' : json[i].Column14,
		            	})
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Order No', visible: false},
					{'data': 'Control No', visible: false},
					{'data': 'Accession No', class : 'accno'},
					{'data': 'Title', visible: false},
					{'data': 'Author', visible: false},
					{'data': 'Publisher', visible: false},
					{'data': 'Location'},
					{'data': 'Branch'},
					{'data': 'Item Category'},
					{'data': 'Condition'},
					{'data': 'SMD'},
					{'data': 'Volume'},
					{'data': 'Price', className: "text-right", render: $.fn.dataTable.render.number(',', '.', currencyFormat, '')},
					{'data': 'Status'},
				],
				drawCallback: function ( settings ) {
		            var api = this.api();
		            var rows = api.rows( {page:'current'} ).nodes();
		            var last=null;

					api.column(1, {page:'current'} ).data().each( function ( group, i ) {
					     if (last !== group) {
					         var rowData = api.row(i).data();
					         $(rows).eq(i).before(
							'<tr class="group"><td><b>Order No</b></td><td colspan="13">'+group+'</td></td></tr>'
							+'<tr class="group"><td><b>Control No</b></td><td colspan="13">'+rowData['Control No']+'</td></td></tr>'
							+'<tr class="group"><td><b>Title</b></td><td colspan="13">'+rowData['Title']+'</td></td></tr>'
							+'<tr class="group"><td><b>Author</b></td><td colspan="13">'+rowData['Author']+'</td></td></tr>'
							+'<tr class="group"><td><b>Publisher</b></td><td colspan="13">'+rowData['Publisher']+'</td></td></tr>'
					     );
					         last = group;
					     }
					});
		        },
    	});
    });
		function storeTblValues() {
            var TableData = new Array();
            $('#reportTable tbody tr').each(function (row, tr) {
                TableData.push($(tr).find('.accno').html());
            });

            var filtered = TableData.filter(function (el) {
			  return el != null;
			});
            return filtered;
        }
});