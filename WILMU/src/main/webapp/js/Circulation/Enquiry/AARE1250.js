$(document).ready(function() {
	
	$('.collType,.unReturn').empty()
	$('#cirActForm').append('<div class="form-group"><div class="col-sm-2 col-md-2"><label class="form-check-label"> Statistics</label></div><div class="col-sm-4"><div class="form-check"><select id="statistic" multiple="multiple" name="statistic"><option value="onloan">On-Loan Items</option><option value="overdue">Overdue Items</option><option value="loaned">Loaned Items</option><option value="discharge">Discharged Items</option></select></div></div></div>')
	
	$('#patronCate, #smd, #icat, #loca, #statistic').multiselect({
    	allSelectedText: 'All',
   		maxHeight: 200,
   		includeSelectAllOption: true
	});
	
		////// table setup
	$('#ttttttTable').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
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
	$('#Reterive').prop('disabled', true);
	$("#patronCate, #smd, #icat, #loca").multiselect("disable");
	
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
	
	////////////// radio button on change  ////////////////////
	$('input[type=radio][name=chkBoxSearchCateria]').on('change', function(){
		switch ($(this).val()) {
		    case 'pateCate':
				$('#patronCate').multiselect("enable");
			  	$("#smd, #icat, #loca").multiselect("disable");
		      break;
		    case 'itemcate':
				$('#icat').multiselect("enable");
			  	$("#smd, #patronCate, #loca").multiselect("disable");
		      break;
			case 'smd':
				$('#smd').multiselect("enable");
			  	$("#patronCate, #icat, #loca").multiselect("disable");
		      break;
			case 'loca':
				$('#loca').multiselect("enable");
			  	$("#patronCate, #icat, #smd").multiselect("disable");
		      break;	
		  }
	});
	

	
	// detect any changes happens to statistics multiselect
	var searchStatistics = ''
	$("#statistic").change(function() {
		$("#circulationDatabaseTreeMenu").empty()
		$("#display_TreeMenu").empty()
		searchStatistics = $(this).val()
		if(Array.from(searchStatistics).length <= 0){
			$('#Reterive').prop('disabled', true);
		}else{
			$('#Reterive').prop('disabled', false);
		}
    });
    // ----------------------------------------------------


	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#Reterive').click(function(){
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
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
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		}
		
		var yy = sendRetrieveData()
		
		/////result display
		var t = $('#ttttttTable').DataTable( {
			dom: 'Bfrtip',
			paging: false,
			info:     false,
			buttons: [
                {
							text: "Print",
							className : 'Print',
				                action: function ( e, dt, node, config ) {
									 var pageTitle = 'Ilmu Circulation Statistics',
							            stylesheet = '//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css',
							            win = window.open('', 'Print', '');
										win.document.write('<html><head><title>' + pageTitle + '</title>' +
							            '<link rel="stylesheet" href="' + stylesheet + '">' +
							            '</head><body>'+
										'<br> <b>' +sDate + eDate +
										'</b><br><br>'+
										'<table border="1"' + $('#ttttttTable')[0].outerHTML + '</table></body></html>')
								        win.document.close();
								        win.print();
								        win.close();
								        return false;
				            }
				},
            ],
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
			paging: false,
			language: {
             loadingRecords : "Please wait - loading...",
             processing :     "Please wait formatting in progress..."
        	},
			deferRender: true,
			footer: true,
			data: yy,
			columns: [
				{ "data": "main" },
				{ "data": "description" },
			    { "data": "subDescription" },
			    { "data": "code" },
				{ "data": "branch" },
			    { "data": "value" }
			  ],
			 columnDefs: [ {
		            targets: [ 0, 1 ],
		            visible: false
		        } ],
	
			drawCallback: function( settings ) {
		        var api = this.api();

				var last1 = null;
				var last2 = null;
				var rows1 = api.rows({ page: 'current' }).nodes();
				var rows2 = api.rows({ page: 'current' }).nodes();
				
				api.column(0, { page: 'current' }).data().each(function (group, i) {
                    if (last1 !== group) {
                        $(rows1).eq(i).before('<tr class="group"><td colspan="6">' + group + '</td></tr>');
                        last1 = group;
                    }
                });

				api.column(1, { page: 'current' }).data().each(function (group, i) {
                    if (last2 !== group) {
						$(rows2).eq(i).before('<tr class="group"><td colspan="6">' + group + '</td></tr>');
						
                        last2 = group;
                    }
                });
		    }
    		});

		t.rows( {page: 'current'} ).every( function ( rowIdx, row,tableLoop, rowLoop ) {
			      var data = this.data();
				  if(data.subDescription.includes('ITEMS')){
					var ida = t.column(2).index();
					var idb = t.column(3).index();
					var idc = t.column(4).index();
					$(t.cell(rowIdx, ida).node()).attr('colspan',3);
					$(t.cell(rowIdx, idb).node()).css('display', 'none');
       	 			$(t.cell(rowIdx, idc).node()).css('display', 'none');
				  }
		})
	});
	
	
	function sendRetrieveData(){
		var startSentDate = moment($("#input-startDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		var endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
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
		}else{
			endSentDate = moment($("#input-endDate").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			eDate =  $("#input-endDate").val();
		}
		
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
			
		var chkBoxSearchCateria = $("input[name='chkBoxSearchCateria']:checked").val();
		var searchCateriaval;
		var unReturn = $("input[name='unReturn']:checked").val();
		
		switch(chkBoxSearchCateria) {
		  case 'patrID':
			searchCateriaval = $('#lblPatronID').val();
			titleHeader = "Patron Id";
		    break;	
		  case 'pateCate':
			searchCateriaval = $('#patronCate').val();
			titleHeader = "Patron Category";
		    break;
		  case 'smd':
			searchCateriaval = $('#smd').val();
			titleHeader = "SMD";
		    break;
		  case 'itemcate':
			searchCateriaval = $('#icat').val();
			titleHeader = "Item Category";
		    break;
		  case 'loca':
			searchCateriaval = $('#loca').val();
			titleHeader = "Item Location";
		    break;
		  case undefined:
			searchCateriaval = "";
		    break;
		}
		var tt = new Array()
		$.get({
		  url: 'getCirculationStatistics',// mandatory
		  data: { startSentDate : startSentDate, endSentDate : endSentDate,  
		  brnch : JSON.stringify(brnch), chkBoxSearchCateria : chkBoxSearchCateria, 
		  searchCateriaval : JSON.stringify(searchCateriaval), searchStatistics : JSON.stringify(searchStatistics),
		  allbranch : allbranch },
		  async:false, // to make it synchronous
		  success: function(data) {
			$.each( data.split('nnmm').slice(0, -1), function( key, value ) {
				$.each( JSON.parse(JSON.parse(value).value), function( key, value ) {
					tt.push(value)
				});
			});
			listree()
		}
		});
		return tt
	}
});