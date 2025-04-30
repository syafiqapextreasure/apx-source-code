$(document).ready(function() {
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////setup page//////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//$('#print').prop('disabled', true);
	/////$("#patronCate, #branch, #smd, #icat").prop("selectedIndex",-1);	
	$('#patronCate, #smd, #branch, #icat').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$("#minBorrow").val("0");
	
	$.get('GetLibName', {
	 	}, function(responseJson) {
		if(responseJson != null){
			$.each(responseJson, function(key,value) {
				$("#libname").val(value['Name']);
			});
		}
	});
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
		//startDate : today,
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#borrowerReterive').prop('disabled', false);
    });
	
	///////////////////// input-endDate set Current Date  ////////////////////
	
	$('#input-endDate').val(moment(today).format("DD/MM/YYYY"));
	$('#input-endDate').datepicker({
		format: "dd/mm/yyyy",
		todayBtn: true,
		autoclose: true,
		todayHighlight: true,
	}).on('changeDate', function(e) {
        // Revalidate the date field
		$('#borrowerReterive').prop('disabled', false);
    });
	
	//////////////Rate key in number only//////////////////////////////////////
	$("#minBorrow").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
    });
	
	//////table setup
	$('#borrower').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	
	 /////checkbox checked
	$("#checkboxpatcat").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
	    	$('#borrowerReterive').prop('disabled', true);
	    } else {
	    	$('#borrowerReterive').prop('disabled', false);
	    	$("#patronCate").prop("selectedIndex",-1);
	    }
	});
	
	$("#checkboxbrnc").on('change', function () {
	    var self = $(this);
	    if (self.is(":checked")) {
	    	$('#borrowerReterive').prop('disabled', true);
	    } else {
	    	$('#borrowerReterive').prop('disabled', false);
	    	$("#patronCate").prop("selectedIndex",-1);
	    }
	});

	 //select
	 $("#patronCate").change(function(){
		 $('#borrowerReterive').prop('disabled', false);
		 $("#checkboxpatcat").prop( "checked", true);
	});
	 
	 $("#checkboxbrnc").change(function(){
		 $('#borrowerReterive').prop('disabled', false);
		 $("#checkboxbrnc").prop( "checked", true);
	});


	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Borrower Reterive/////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#borrowerReterive').click(function(){
		$('#borrower').dataTable().fnClearTable();
		
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		
		var checkpatcat = $('#checkboxpatcat:checked').val();
    	if(checkpatcat == "input-patcat"){
    		checkpatcat = "Y";
    	}else{
    		checkpatcat = "N";
    	}
    	
    	var patcate = $("#patronCate").val();
    	
    	var checkbrnch = $('#checkboxbrnc:checked').val();
    	if(checkbrnch == "input-patcat"){
    		checkbrnch = "Y";
    	}else{
    		checkbrnch = "N";
    	}
    	
    	var brnch = $("#branch").val();
    	
    	var minBorrow = $("#minBorrow").val();
    	
    	//$('#print').prop('disabled', false);
    	
    	$('#borrower').DataTable( {
    		dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
                {
                extend: 'csv',
                filename: 'WILMU_AARE0850',
                title: 'Top Borrower Enquiry',
                messageTop: 'Minimum Borrowing = ' +minBorrow ,
               
                
            },
             {
            	 	text: '<i class="fa fa-file-pdf-o"></i> PDF',
					extend: 'pdfHtml5',
					filename: 'WILMU_AARE0850',
					orientation: 'landscape', //portrait
					pageSize: 'A4', //A3 , A5 , A6 , legal , letter
					messageTop: 'Minimum Borrowing = ' +minBorrow ,
					exportOptions: {
						columns: ':visible',
						search: 'applied',
						order: 'applied'
					},
					customize: function (doc) {
						//Remove the title created by datatTables
						doc.content.splice(0, 1, {
	                        text: [
	                                   { text: 'Top Borrower Enquiry \n',bold:true,fontSize:13,alignment: 'center'},
	                                   { text: $("#libname").val() +'\n',bold:true,fontSize:13,alignment: 'center'},
	                        ],
	                        margin: [0, 0, 0, 12],
	                    });
					
						//doc.pageMargins = [20,60,20,30];
						// Set the font size fot the entire document
						//doc.defaultStyle.fontSize = 7;
						// Set the fontsize for the table header
						//doc.styles.tableHeader.fontSize = 7;
						// Create a header object with 3 columns
						// Left side: Logo
						// Middle: brandname
						// Right side: A document title
						/*doc['header']=(function() {
							return {
								columns: [
									{
										image: logo,
										width: 24
									},
									{
										alignment: 'left',
										italics: true,
										text: 'dataTables',
										fontSize: 18,
										margin: [10,0]
									},
									{
										alignment: 'center',
										fontSize: 14,
										text: 'PpUNISZA \n Top Borrower Enquiry'
									}
								],
								margin: 20
							}
						});*/
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
                extend: 'print',
                text: '<i class="glyphicon glyphicon-print" aria-hidden="true"></i> Print',
                title: '',
                messageTop: 'Minimum Borrowing = ' +minBorrow,
                //For repeating heading.
                customize: function ( win ) {
                	$(win.document.body).css('margin-top','50px'); /*.css({"font-size": "13pt"})*/
                    $(win.document.body).prepend( "<div style='float: left'></div><div style='float: right'>"
                    		+moment(today).format("DD/MM/YYYY") +'<br>' 
                    		+moment(today).format("hh:mm:ss A")+"</div><div style='margin: 0 auto; width: 100px;'><b>"
                    		+$("#libname").val()+"</b></div>");
                    $(win.document.body).prepend( "<div class='center'><b>Top Borrower Enquiry</b></div>");
 
                    $(win.document.body).find( 'table' ).addClass( 'compact' ).css( 'font-size', 'inherit' );
                },
            }
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			//processing: true,
			order: [[ 4, 'desc' ]],
			//info: false,
		    ajax: {
		    	url: "resultBorrower",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate, 
		        	checkpatcat : checkpatcat, patcate : patcate, checkbrnch : checkbrnch,
		        	brnch : brnch, minBorrow : minBorrow},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            
		            for(var i=0;i< json.length; i++){

		              return_data.push({
		                'Patron ID': json[i].PatronID,
		                'Name' : json[i].Name, 
		                'Department' : json[i].DepartmentCode +" - "+json[i].Department,
		                'Designation' : json[i].DesignationCode +" - "+json[i].Designation,
		                'Borrowing Transaction' : json[i].Borrowing,
		                'No of Lost Item' : json[i].LostItem,
		            })
		            }
					
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'Patron ID'},
					{'data': 'Name'},
					{'data': 'Department'},
					{'data': 'Designation'},
					{'data': 'Borrowing Transaction'},
					{'data': 'No of Lost Item'},
				],
		});

	});
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
});