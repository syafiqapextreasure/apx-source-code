$(document).ready(function() {
	$('#optionDisplay').empty()
	$('#optionDisplay').append('<div class="col-sm-2 col-md-2"><label><input type="radio" name="selectOptions" id="radioDate" value="DATE">&nbsp <b>Due Date Range</b></label></div><div class="col-sm-4"><div class="input-daterange input-group"><input type="text" class="input-sm form-control" name="startDate" id="input-startDate" autocomplete="off" /> <span class="input-group-addon">to</span> <input type="text" class="input-sm form-control" name="endDate" id="input-endDate" autocomplete="off" /></div></div><div class="col-sm-2 col-md-2" style="width: 2%;"><label><input type="radio" name="selectOptions" id="radioReminder" value="REMINDER">&nbsp</label></div><div class="col-sm-4"><div class="form-check"><div class="input-group"><input type="text" class="form-control" id="daysReminder" style="width: 30%;" /><label style="padding-top: 3%;">&nbsp days after third reminder</label></div></div></div>')
	$('#patronCate, #smd, #branch, #icat, #loca').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
	$('#daysReminder').attr('maxLength', '4')
	$("#daysReminder").on("keypress keyup blur", function(event) {
		$(this).val($(this).val().replace(/[^\d].+/, ""));
		if ((event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});
	
	$("#daysReminder").on("input", function() {
		if($('#radioReminder').is(':checked')) { 
			//checkConditionEnableButton()
			//checkConditionEnableButton3()
			checkConditionEnableButton2()
		}
	});
	
	///////////////////// input-startDate set Current Date  //////////////////
	var today = new Date();  
	$('#input-startDate').datepicker({
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
	
	$("#input-startDate, #input-endDate").on('change', function() {
		//checkConditionEnableButton()
		//checkConditionEnableButton3()
		checkConditionEnableButton2()
	})
	
	$('#Reterive').prop('disabled', true);

	////////////// radio button on click  ////////////////////
	$('input[type=radio][name=selectOptions]').click(function() {
		if($('#radioReminder').is(':checked')) { 
			//checkConditionEnableButton()
			//checkConditionEnableButton3()
			checkConditionEnableButton2()
		}
		if($('#radioDate').is(':checked')) { 
			//checkConditionEnableButton()
			//checkConditionEnableButton3()
			checkConditionEnableButton2()
		}
	});
	
	var allSelected = false;
	$('#branch').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true,
		onSelectAll: function(){
		    allSelected = true;
		  },
		  onChange: function(){
		    allSelected = false;
		  }
	}); 
	
	$('#icat, #patronCate').change(function(){
		//checkConditionEnableButton()
		//checkConditionEnableButton3()
		checkConditionEnableButton2()
	}); 
	
	$('#loca, #smd').change(function(){
		//checkConditionEnableButton3()
		checkConditionEnableButton2()
	});
	
	var gSmd = 0
	var hLoca = 0
	var d = 0
	$('#radioSmd, #radioLoca').click(function(){
		checkConditionEnableButton3()
		checkConditionEnableButton2()
	}); 
	
	function checkConditionEnableButton3(){
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		switch(chkBoxSearchCateria) {
		  case 'smd':
			hLoca = 0
			gSmd++
			if($('#radioSmd').hasClass("clickSmd") && gSmd%2 == 0){ // NO CHECK
				$('#radioSmd').prop("checked", false)
			}
			if($('#radioSmd').hasClass("clickSmd") && gSmd%2 != 0 && $('#radioSmd').prop("checked")){ // YES CHECK
				$('#radioSmd').prop("checked", true)
			}
		    break;
		  case 'loca':
			gSmd = 0
			hLoca++
			if($('#radioLoca').hasClass("clickLoca") && hLoca%2 == 0){ // NO CHECK
				$('#radioLoca').prop("checked", false)
			}
			if($('#radioLoca').hasClass("clickLoca") && hLoca%2 != 0 && $('#radioLoca').prop("checked")){ // YES CHECK
				$('#radioLoca').prop("checked", true)
			}
		    break;
		 case undefined:
		    break;
		} 
	}
	
	function checkConditionEnableButton(){
		if($('#radioReminder').prop("checked") && $('#daysReminder').val().length > 0){
			if($('#icat').val().length > 0 && $('#patronCate').val().length > 0){
				$('#Reterive').prop('disabled', false);
			}
			if($('#icat').val().length > 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length > 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
		}
		if($('#radioReminder').prop("checked") && $('#daysReminder').val().length == 0){
			$('#Reterive').prop('disabled', true);
		}
		if($('#radioDate').prop("checked") && ($('#input-startDate').val().length > 0 || $('#input-endDate').val().length > 0)){
			if($('#icat').val().length > 0 && $('#patronCate').val().length > 0){
				$('#Reterive').prop('disabled', false);
			}
			if($('#icat').val().length > 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length > 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
		}
		if($('#radioDate').prop("checked") && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0){
			$('#Reterive').prop('disabled', true);
		}
	}

	function checkConditionEnableButton2(){
		if($('#radioReminder').prop("checked") && $('#daysReminder').val().length > 0){
			if($('#icat').val().length > 0 && $('#patronCate').val().length > 0){
				if($('#radioSmd').prop("checked") && $('#smd').val().length > 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioSmd').prop("checked") && $('#smd').val().length == 0){
					$('#Reterive').prop('disabled', true);
				}
				if($('#radioLoca').prop("checked") && $('#loca').val().length > 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioLoca').prop("checked") && $('#loca').val().length == 0){
					$('#Reterive').prop('disabled', true);
				}
				if($('#radioLoca').prop("checked") != true && $('#loca').val().length == 0 && $('#radioSmd').prop("checked") != true && $('#smd').val().length == 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioLoca').prop("checked") != true && $('#loca').val().length > 0 && $('#radioSmd').prop("checked") != true && $('#smd').val().length == 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioLoca').prop("checked") != true && $('#loca').val().length == 0 && $('#radioSmd').prop("checked") != true && $('#smd').val().length > 0){
					$('#Reterive').prop('disabled', false);
				}
			}
			if($('#icat').val().length > 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length > 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
		}
		if($('#radioReminder').prop("checked") && $('#daysReminder').val().length == 0){
			$('#Reterive').prop('disabled', true);
		}
		if($('#radioSmd').prop("checked") && $('#smd').val().length == 0){
			$('#Reterive').prop('disabled', true);
		}
		if($('#radioLoca').prop("checked") && $('#loca').val().length == 0){
			$('#Reterive').prop('disabled', true);
		}
		if($('#radioDate').prop("checked") && ($('#input-startDate').val().length > 0 || $('#input-endDate').val().length > 0)){
			if($('#icat').val().length > 0 && $('#patronCate').val().length > 0){
				if($('#radioSmd').prop("checked") && $('#smd').val().length > 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioSmd').prop("checked") && $('#smd').val().length == 0){
					$('#Reterive').prop('disabled', true);
				}
				if($('#radioLoca').prop("checked") && $('#loca').val().length > 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioLoca').prop("checked") && $('#loca').val().length == 0){
					$('#Reterive').prop('disabled', true);
				}
				if($('#radioLoca').prop("checked") != true && $('#loca').val().length == 0 && $('#radioSmd').prop("checked") != true && $('#smd').val().length == 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioLoca').prop("checked") != true && $('#loca').val().length > 0 && $('#radioSmd').prop("checked") != true && $('#smd').val().length == 0){
					$('#Reterive').prop('disabled', false);
				}
				if($('#radioLoca').prop("checked") != true && $('#loca').val().length == 0 && $('#radioSmd').prop("checked") != true && $('#smd').val().length > 0){
					$('#Reterive').prop('disabled', false);
				}
			}
			if($('#icat').val().length > 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length > 0){
				$('#Reterive').prop('disabled', true);
			}
			if($('#icat').val().length == 0 && $('#patronCate').val().length == 0){
				$('#Reterive').prop('disabled', true);
			}
		}
		if($('#radioDate').prop("checked") && $('#input-startDate').val().length == 0 && $('#input-endDate').val().length == 0){
			$('#Reterive').prop('disabled', true);
		}
	}
	
	////// table setup
	$('#reportTable').DataTable({
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
	});
	
	$('#Reterive').click(function(){
		var itemCategory = $('#icat').val();
		var patronCategory = $('#patronCate').val();
		
		$('#reportTable').dataTable().fnClearTable();
		var titlePrint = ''
		var allbranch; 
		var brnch = $("#branch").val(); 
		if(brnch != ""){
			if(allSelected){
				allbranch = "Y";
			}else{
				allbranch = "N";
			}
		}else{
			brnch = 0;
			allbranch = "N";
		}
		
		var titleHeader;
		var selectOptions;			
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;
		var unReturn = $("input[name='unReturn']:checked").val();
		var daysRemind
		
		if($("input[name='unReturn']:checked").val() === 'Y'){
			unReturn = 'Y'
		}
		if($("input[name='unReturn']:checked").val() != 'Y'){
			unReturn = 'N'
		}
		
		switch(chkBoxSearchCateria) {
		  case 'smd':
			searchCateriaval = $('#smd').val();
			titleHeader = "SMD";
		    break;
		  case 'loca':
			searchCateriaval = $('#loca').val();
			titleHeader = "Item Location";
		    break;
		 case undefined:
			searchCateriaval = "";
			titleHeader = "All";
			chkBoxSearchCateria = "";
		    break;
		} 
		
		switch($("input[name='selectOptions']:checked").val()) {
		  case 'DATE':
			var sDate, eDate;
			var startSentDate = $("#input-startDate").val();
			if(startSentDate == ''){
				startSentDate = '';
				sDate = "";
			}else{
				startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
				sDate = $("#input-startDate").val();
			}
			
			var endSentDate = $("#input-endDate").val();
			if(endSentDate == ''){
				endSentDate = '';
				eDate = '';
			}else{
				endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
				eDate =  $("#input-endDate").val();
			}
			
			titlePrint = "Due Item Listing "+titleHeader +" from " +sDate + " until " +eDate
			selectOptions = 'Date'
		    break;
		  case 'REMINDER':
			daysRemind = $('#daysReminder').val()
			titlePrint = "Due "+$('#daysReminder').val()+" days after third reminder"
			selectOptions = 'Reminder'
		    break;
		} 
		
		
		var collectionType = $("input[name='collectionType']:checked").val();
		var type;
		
		switch(collectionType) {
		  case 'mono':
			type = "M";
		    break;
		  case 'irs':
			type = "I";
		    break;
		}
		
		///// result display
		var t = $('#reportTable').DataTable( {
			dom: 'Bfrtip',
            buttons: [
                /*'excel', 'pdf', 'print'*/
						{
					  		extend: 'excelHtml5',
					        filename: 'WILMU_DueItemListing',
					        title: titlePrint,    
					 	},
						{
					    	text: '<i class="fa fa-file-pdf-o"></i> PDF',
							extend: 'pdfHtml5',
							filename: 'WILMU_DueItemListing',
							charset: "utf-8",
							bom : "true",
							exportData : {decodeEntities:true},
							orientation: 'landscape', //portrait
							pageSize: 'A4', //A3 , A5 , A6 , legal , letter
							footer: true,
							exportOptions: {
								search: 'applied',
								order: 'applied'
							},
							customize: function (doc) {
											//Remove the title created by datatTables
											doc.content.splice(0, 1, {
						                        text: [
						                                   { text: titlePrint + ' \n',bold:true,fontSize:13,alignment: 'center'},
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
		                	filename: 'WILMU_DueItemListing',
		                	title: 'Due Item Listing ',
							charset: "utf-8",
							bom : "true",
							customize: function(doc){
										var split_csv = doc.split("\n");
										$.each(split_csv.slice(1), function (index, csv_row) {
											var csv_cell_array = csv_row.split('","');
											csv_cell_array[1].replace("", (index+1));
										});
										return titlePrint +"\n\n" + doc;
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
			deferRender: true,
			footer: true,
		    ajax: {
		    	url: "ResultDueItemListing",
		        data : {startSentDate : startSentDate, endSentDate : endSentDate,  brnch : JSON.stringify(brnch), itemCategory : JSON.stringify(itemCategory), patronCategory : JSON.stringify(patronCategory),
						chkBoxSearchCateria : chkBoxSearchCateria, searchCateriaval : JSON.stringify(searchCateriaval),
						type : type, unReturn : unReturn, allbranch : allbranch, selectOptions : selectOptions, daysRemind : daysRemind},
		        type: "GET",
		        dataSrc: function (json) {
		            var return_data = new Array();
		            for(var i=0;i< json.length; i++){
		              return_data.push({
		                'No': (i+1),
		                'Accession No' : json[i].Column1,
						'Charge Date' : json[i].Column2,
						'Due Date' : json[i].Column3,
		                'Title' : json[i].Column4,
		                'Call No' : json[i].Column5,
		                'Patron ID/Name' : json[i].Column6,
						'Item Category' : json[i].Column7,
						'Late By' : json[i].Column8,
						'Branch' : json[i].Column9,
						'Location' : json[i].Column10,
		            })
		            }
		            return return_data;
		          },
		     },//This is the end of the AJAX object from the example above
		     	columns    : [
					{'data': 'No'},
					{'data': 'Accession No'},
					{'data': 'Charge Date'},
					{'data': 'Due Date'},
					{'data': 'Title'},
					{'data': 'Call No'},
					{'data': 'Patron ID/Name'},
					{'data': 'Item Category'},
					{'data': 'Late By'},
					{'data': 'Branch'},
					{'data': 'Location'},					
				],
    });
    });
});