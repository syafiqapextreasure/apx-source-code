$(document).ready(function() {

	

	                 

	                 var json = '[' +
	                   '{' +
	                     '"text": "Node 1",' +
	                     '"nodes": [' +
	                       '{' +
	                         '"text": "Volumn",' +
	                         '"nodes": [' +
	                           '{' +
	                             '"text": "Issue",' +
	                             '"nodes": [' +
		                           '{' +
		                             '"text": "Copy",' +
		                             '"nodes": [' +
			                           '{' +
			                             '"text": "Status"' +
			                           '},' +
			                           '{' +
			                             '"text": "Frequency"' +
			                           '},' +
			                           '{' +
			                             '"text": "Date Expected"' +
			                           '},' +
			                           '{' +
			                             '"text": "Date Received"' +
			                           '},' +
			                           '{' +
			                             '"text": "Branch"' +
			                           '},' +
			                           '{' +
			                             '"text": "Location"' +
			                           '},' +
			                           '{' +
			                             '"text": "Item Category"' +
			                           '}' +
			                         ']' +
		                           '},' +
		                           '{' +
		                             '"text": "Grandchild 2"' +
		                           '}' +
		                         ']' +
	                           '},' +
	                           '{' +
	                             '"text": "Grandchild 2"' +
	                           '}' +
	                         ']' +
	                       '},' +
	                       '{' +
	                         '"text": "Child 2"' +
	                       '}' +
	                     ']' +
	                   '},' +

	                   '{' +
	                   '}' +
	                 ']';
	                 
	                 var $tree = $('#treeview12').treeview({
							data: json,
							levels: 1,
							 expandIcon: 'glyphicon glyphicon-plus',
							  collapseIcon: 'glyphicon glyphicon-minus',
							  emptyIcon: 'glyphicon',
							  nodeIcon: '',
							  selectedIcon: '',
							  checkedIcon: 'glyphicon glyphicon-check',
							  uncheckedIcon: 'glyphicon glyphicon-unchecked',

							  // colors
							  color: undefined, // '#000000',
							  backColor: undefined, // '#FFFFFF',
							  borderColor: undefined, // '#dddddd',
							  onhoverColor: '#F5F5F5',
							  selectedColor: '#FFFFFF',
							  selectedBackColor: '#428bca',
							  searchResultColor: '#D9534F',
							  searchResultBackColor: undefined, //'#FFFFFF',

							  // enables links
							  enableLinks: false,

							  // highlights selected items
							  highlightSelected: true,

							  // highlights search results
							  highlightSearchResults: true,

							  // shows borders
							  showBorder: true,

							  // shows icons
							  showIcon: true,

							  // shows checkboxes
							  showCheckbox: false,

							  // shows tags
							  showTags: false,

							  // enables multi select
							  multiSelect: false
					 });
	                 
	                 
	
	$("input[name=radioOption][value='issn']").prop("checked",true);
	$("#textctrlno").prop('disabled', true);
	$('#searchCtrl').attr('disabled', true);
	
	$("#textctrlno").attr('maxlength','10');
	
	$("input[name=radioOption]").change(function() {

		var value = $( 'input[name=radioOption]:checked' ).val();

		if(value == "issn"){
			$("#textisn").prop('disabled', false);
			$("#textctrlno").prop('disabled', true);
			$('#searchCtrl').attr('disabled', true);
		}else if(value == "ctrlno"){
			$("#textisn").prop('disabled', true);
			$("#textctrlno").prop('disabled', false);
			$('#searchCtrl').attr('disabled', false);
		}
	});
	
	
	var liveMemberData;
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Payment Reterive//////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	$('#serHolReterive').click(function(){

		var radioButton;
		var inputSearch;

		
		radioButton = $( 'input[name=radioOption]:checked' ).val();
		if(radioButton == "issn"){
			inputSearch = $("#textisn").val();
		}else if(radioButton == "ctrlno"){
			inputSearch = $("#textctrlno").val();
		}
		
		$.get('getSerialHolding', {
        	code : radioButton,
        	inputSearch : inputSearch,
		 	}, function(responseJson) {
		 		if(responseJson==''){
		 			messageBox('150',"", "");
			 	}else{//(responseJson != null){
					$.each(responseJson, function(key,value) {
						///start treeView
						
						$.get('GETSerialTitles', {
				        	code : value['value1'],
						 	}, function(responseJson) {
						 		if(responseJson != null){
									$.each(responseJson, function(key,value) {
										$(".title").val(value['value1']);
										//$('#treeview').treeview({data: value['value1']});
										//initTree(value['value1']);
									});
								}
			    		 });
					});
				}
			
		});
		
		function initTree(liveMemberData) {
			alert(liveMemberData +"ss");
		    $('#treeview').treeview({data: liveMemberData});
		}
	});

});