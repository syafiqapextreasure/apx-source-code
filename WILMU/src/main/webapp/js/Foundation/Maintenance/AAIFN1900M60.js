$(document).ready(function() {
	// TO CHANGE ALPHABET TO UPPERCASE
	$('input.upCaseLetter').on('input', function() {
		this.value = this.value.toUpperCase();
	})
	
	// MAKE THIS TAB TO BE DEFAULT WHEN LOAD PAGE
	$('a[href="#parameters-tab"]').tab('show')
	
	$('#marcSearchCriteria').on("click", function() {
	  	if($('#marcSearchCriteria').prop('checked')){
			$('#marcSearchSelect').prop('disabled',false)
		}else {
			$('#marcSearchSelect').prop('disabled',true)
		}
	});
	
	$('#btn_find').on("click", function() {
		// TABLE FOR SEARCH CRITERIA
		var t = $('#fndMMainttTable').DataTable({
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			deferRender: true,
			processing: true,
			pageLength: 10,
			ajax: {
				url: "getSearchTagParameter",
				type: "GET",
				data: {
					marcSearchCriteria : $('#marcSearchCriteria').prop('checked') == true ? 'Y' : 'N',
					marcSearchSelect : $('#marcSearchSelect').val(),
					tagNoSearchCriteria : $('input[name="choice"]:checked').val(),
					tagNdescription : $('#tagNdescription').val()
				},
				dataSrc: function(json) {
					var return_data = new Array();
					objectTagParameter = []
	
					$.each( json, function( key, value ) {
						objectTagParameter.push(json[key])
						
						return_data.push({
							'MARC': json[key].Marc,
							'Tag': json[key].Tag,
							'Description': json[key].Desc,
							'Action': '<button id="View" data-index = "' + key + '"  class="btn btn-primary btn-xs" data-target="#modaltagparameter" data-toggle="modal"  data-mode="3" title="View Tag Parameter"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modaltagparameter" data-mode="2" title="Edit Tag Parameter" data-index = "' + key + '"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" data-index = "' + key + '" data-mode="4" class="btn btn-dull btn-xs" title="Delete" data-toggle="modal" data-target="#deleteModalTagParameter"><span class="glyphicon glyphicon-trash"></span></button>'
						})
					});
					return return_data;
				},
			},
			columns: [
				{ 'data': 'MARC' },
				{ 'data': 'Tag' },
				{ 'data': 'Description' },
				{ 'data': 'Action' }
			]
		});
		
		
	});
	
	$('#tagNdescription').on("input", function() {
	  	if($('#tagNdescription').val().length > 0){
			$('#btn_find').prop('disabled',false)
		}else {
			$('#btn_find').prop('disabled',true)
		}
	});
	
	
	$('#searchTagParameter').on("click", function() {
		$('#marcSearchSelect').prop('disabled',true);
		$('#marcSearchCriteria').prop('checked', false);
		$('#tagNoSearchCriteria').prop('checked', true);
		$('#btn_find').prop('disabled',true);
		$('#tagNdescription').val('');
	});
	

	$("#indexLang, #tagNoSearchCriteria, #tagParameterNo, #fieldLength").on("keypress keyup blur", function(event) {
		$(this).val($(this).val().replace(/[^\d].+/, ""));
		if ((event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});

	$('#save, #marcSearchSelect').prop('disabled',true)
	$('#tagParameterNo, #revisedTagNo, #acronym').attr('maxLength', '3')
	$('#indexTable').attr('maxLength', '6')
	$('#indexLang').attr('maxLength', '1')
	$('#fieldLength').attr('maxLength', '5')
	$('#authGroup').attr('maxLength', '10')
	
	
	

	var objectTagParameter = new Array();
	var arrayOfClickedElementId = new Array();
	var condition = 0;
	var marc;
	var tag;
	var tagNoExist = false
	
	$('#tagParameterNo').on("blur", function(event) {
		$.ajax({
			type: "GET",
			url: "checkTagAvailable",
			data: {
				Marc: $('#fCodeH').val(),
				Tag: $('#tagParameterNo').val()
			},
			success: function(data) {
				if(data == true){
					tagNoExist = true
					swal("This tag already exists. Please enter a unique tag").then(() => {
			            $('#tagParameterNo').val("");
			            $('#tagParameterNo').focus();
						checkFormValidationMandatoryField()
						disabledInputIndiSubf()
						$('#formdataAddTagParameter').trigger("reset");
						$('a[href="#parameters-tab"]').tab('show')
			         });
				}
				if(data == false){
					tagNoExist = false
				}
			}
		})
		checkFormValidationMandatoryField()
	});

	
	$('#indexTable').on("blur", function() {
		if($('#indexTable').val().length > 0 && $('#indexTable').val().slice(0, 2) != 'CT'){
			swal("Invalid Index Table").then(() => {
				$('#indexTable').val("")
				$('#indexTable').focus();
				checkFormValidationMandatoryField()
				disabledInputIndiSubf()
				$('#formdataAddTagParameter').trigger("reset");
				$('a[href="#parameters-tab"]').tab('show')
			});
		}
	  	checkFormValidationMandatoryField()
	});
	
	
	$('#tagParameterNo, #indexTable').on("input", function() {
	  	checkFormValidationMandatoryField()
	});
	
	
	function checkFormValidationMandatoryField(){
		if($('#tagParameterNo').val().length > 0 && $('#indexTable').val().length > 0){
			$('#save').prop('disabled',false)
		}else{
			$('#save').prop('disabled',true)
		}
	}
	
	// TABLE
	var t = $('#fndMMainttTable').DataTable({
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			deferRender: true,
			processing: true,
			pageLength: 10,
			ajax: {
				url: "getAllTagParameter",
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					objectTagParameter = []
	
					$.each( json, function( key, value ) {
						objectTagParameter.push(json[key])
						
						return_data.push({
							'MARC': json[key].Marc,
							'Tag': json[key].Tag,
							'Description': json[key].Desc,
							'Action': '<button id="View" data-index = "' + key + '"  class="btn btn-primary btn-xs" data-target="#modaltagparameter" data-toggle="modal"  data-mode="3" title="View Tag Parameter"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modaltagparameter" data-mode="2" title="Edit Tag Parameter" data-index = "' + key + '"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" data-index = "' + key + '" data-mode="4" class="btn btn-dull btn-xs" title="Delete" data-toggle="modal" data-target="#deleteModalTagParameter"><span class="glyphicon glyphicon-trash"></span></button>'
						})
					});
					return return_data;
				},
			},
			columns: [
				{ 'data': 'MARC' },
				{ 'data': 'Tag' },
				{ 'data': 'Description' },
				{ 'data': 'Action' }
			]
		});
		
		
	function refreshTableWithData(){
		// TABLE
		var t = $('#fndMMainttTable').DataTable({
			destroy: true,
			searching: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			deferRender: true,
			processing: true,
			pageLength: 10,
			ajax: {
				url: "getAllTagParameter",
				type: "GET",
				dataSrc: function(json) {
					var return_data = new Array();
					objectTagParameter = []
	
					$.each( json, function( key, value ) {
						objectTagParameter.push(json[key])
						
						return_data.push({
							'MARC': json[key].Marc,
							'Tag': json[key].Tag,
							'Description': json[key].Desc,
							'Action': '<button id="View" data-index = "' + key + '"  class="btn btn-primary btn-xs" data-target="#modaltagparameter" data-toggle="modal"  data-mode="3" title="View Tag Parameter"><span class="glyphicon glyphicon-eye-open"></span></button> <button id="Edit" class="btn btn-info btn-xs" data-toggle="modal" data-target="#modaltagparameter" data-mode="2" title="Edit Tag Parameter" data-index = "' + key + '"><span class="glyphicon glyphicon-pencil"></span></button> <button id="Delete" data-index = "' + key + '" data-mode="4" class="btn btn-dull btn-xs" title="Delete" data-toggle="modal" data-target="#deleteModalTagParameter"><span class="glyphicon glyphicon-trash"></span></button>'
						})
					});
					return return_data;
				},
			},
			columns: [
				{ 'data': 'MARC' },
				{ 'data': 'Tag' },
				{ 'data': 'Description' },
				{ 'data': 'Action' }
			]
		});
	}

	

	//****************************************** AREA FOR MODAL *****************************************//
	$('#modaltagparameter').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var recipient = button.data('mode'); // Extract info from data-* attributes
		var i = button.data('index');
		$('a[href="#parameters-tab"]').tab('show')
		
		switch (recipient) {
			case state = 1:// add new tag parameter 
				$('#modalTagParameter').text("Add Tag Parameter");
				enable()
				$('#save').show();
				disabledInputIndiSubf()
				condition = 1;
				$('#indicator-value-1').empty()
				$('#indicator-value-1').append('<jsp:include page="../../../../module/Indicator_Value.jsp"></jsp:include>')
				$('#indicator-value-2').empty()
				$('#indicator-value-2').append('<jsp:include page="../../../../module/Indicator_Value.jsp"></jsp:include>')
				break;
			case state = 2:// update/ edit
				enable()
				getIndicatorLevelEdit(objectTagParameter[i].Marc, objectTagParameter[i].Tag)
				$('#save').show();
				$('.read-input-only input').prop('readonly', true);
				$('#modalTagParameter').text("Edit Tag Parameter");
				$('#fCodeH').val(objectTagParameter[i].Marc).attr('disabled', true);;
				$('#tagParameterNo').val(objectTagParameter[i].Tag).attr('disabled', true);;
				$('#description').val(objectTagParameter[i].Desc);
				$('#abbreviatedDesc').val(objectTagParameter[i].AbbreviateDesc);
				$('#indexTable').val(objectTagParameter[i].IndexTable);
				$('#acronym').val(objectTagParameter[i].Acronym);
				$('#authGroup').val(objectTagParameter[i].AuthorityGroup);
				$('#fieldLength').val(objectTagParameter[i].FieldLength);
				$('#indexLang').val(objectTagParameter[i].IndexLanguage);
				$('#defaultValue').val(objectTagParameter[i].DefaultValue);
				$('#remark').val(objectTagParameter[i].Remark);
				$('#revisedTagNo').val(objectTagParameter[i].RevisedTag);
				condition = 2;

				if (objectTagParameter[i].Repeat == 'Y') {
					$('#checkboxRepeatable').prop('checked', true)
				}
				if (objectTagParameter[i].AuthFlag == 'Y') {
					$('#checkboxAuthFlag').prop('checked', true)
				}
				if (objectTagParameter[i].Mandatory == 'Y') {
					$('#checkboxMandatory').prop('checked', true)
				}
				if (objectTagParameter[i].CpFlag == 'Y') {
					$('#checkboxCopyPaste').prop('checked', true)
				}
				if (objectTagParameter[i].IdxFlag == 'Y') {
					$('#checkboxIndFlag').prop('checked', true)
				}
				if (objectTagParameter[i].Paramips == 'Y') {
					$('#checkboxParamLink').prop('checked', true)
				}
				if (objectTagParameter[i].Keyword == 'Y') {
					$('#checkboxKeyword').prop('checked', true)
				}
				if (objectTagParameter[i].Media == 'Y') {
					$('#checkboxMultimediaTag').prop('checked', true)
				}
				if (objectTagParameter[i].DuplicateCheck == 'Y') {
					$('#checkboxDuplicateCheck').prop('checked', true)
				}

				getIndiSubfieldsEdit(objectTagParameter[i].Marc, objectTagParameter[i].Tag)
				checkFormValidationMandatoryField()
				break;
			case state = 3: // view only
				//getIndicatorLevelView(objectTagParameter[i].Marc, objectTagParameter[i].Tag)
				$('#save').hide();
				$('#modalTagParameter').text("View Tag Parameter");
				disabled();
				$('#fCodeH').val(objectTagParameter[i].Marc);
				$('#tagParameterNo').val(objectTagParameter[i].Tag);
				$('#description').val(objectTagParameter[i].Desc);
				$('#abbreviatedDesc').val(objectTagParameter[i].AbbreviateDesc);
				$('#indexTable').val(objectTagParameter[i].IndexTable);
				$('#acronym').val(objectTagParameter[i].Acronym);
				$('#authGroup').val(objectTagParameter[i].AuthorityGroup);
				$('#fieldLength').val(objectTagParameter[i].FieldLength);
				$('#indexLang').val(objectTagParameter[i].IndexLanguage);
				$('#defaultValue').val(objectTagParameter[i].DefaultValue);
				$('#remark').val(objectTagParameter[i].Remark);
				$('#revisedTagNo').val(objectTagParameter[i].RevisedTag);

				if (objectTagParameter[i].Repeat == 'Y') {
					$('#checkboxRepeatable').prop('checked', true)
				}
				if (objectTagParameter[i].AuthFlag == 'Y') {
					$('#checkboxAuthFlag').prop('checked', true)
				}
				if (objectTagParameter[i].Mandatory == 'Y') {
					$('#checkboxMandatory').prop('checked', true)
				}
				if (objectTagParameter[i].CpFlag == 'Y') {
					$('#checkboxCopyPaste').prop('checked', true)
				}
				if (objectTagParameter[i].IdxFlag == 'Y') {
					$('#checkboxIndFlag').prop('checked', true)
				}
				if (objectTagParameter[i].Paramips == 'Y') {
					$('#checkboxParamLink').prop('checked', true)
				}
				if (objectTagParameter[i].Keyword == 'Y') {
					$('#checkboxKeyword').prop('checked', true)
				}
				if (objectTagParameter[i].Media == 'Y') {
					$('#checkboxMultimediaTag').prop('checked', true)
				}
				if (objectTagParameter[i].DuplicateCheck == 'Y') {
					$('#checkboxDuplicateCheck').prop('checked', true)
				}
				
				$('#marcSearchSelect').prop('disabled',true);
				$('#marcSearchCriteria').prop('checked', false);
				$('#tagNoSearchCriteria').prop('checked', true);
				$('#btn_find').prop('disabled',true);
				$('#tagNdescription').val('').removeAttr('disabled');
				$('input[name="choice"]').removeAttr('disabled');
				$('#marcSearchCriteria').removeAttr('disabled');
				
				getIndicatorLevelEdit(objectTagParameter[i].Marc, objectTagParameter[i].Tag)
				getIndiSubfieldsView(objectTagParameter[i].Marc, objectTagParameter[i].Tag)
				break;
		}
	});

	$('#deleteModalTagParameter').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var recipient = button.data('mode'); // Extract info from data-* attributes
		var i = button.data('index');
		
		switch (recipient) {
		case state = 4:
			marc = objectTagParameter[i].Marc
			tag = objectTagParameter[i].Tag
		}
		$('#deleteModalTagParameter').css({"opacity":"0"})
		
		swal({
		  text: 'Do you wish to delete this record?',
		  showCancelButton: true,
		  showConfirmButton: true,
		  confirmButtonColor: '#3085d6', 
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes',
		  cancelButtonText: 'No'
		}).then(function() {
			var tgh = false
			$.ajax({
				type: "GET",
				url: "checkTagUsedDelete",
				data: {
					Marc: marc,
					Tag: tag
				},
				success: function(data) {
					if(data == false){
						swal("Successfully Deleted!",'The record has been successfully removed.');	
						$('#deleteModalTagParameter').modal('hide');
						$.ajax({
							type: "POST",
							url: "deleteTagParameter",
							data: {
								Marc: marc,
								Tag: tag
							},
							success: function() {
								refreshTableWithData()
							}
						})
					} else if(data == true){
						swal("",'The record cannot be deleted.');	
						$('#deleteModalTagParameter').modal('hide');
					}
				}
			})
		},function(dismiss) {
			swal("","Cancelled");	
			$('#deleteModalTagParameter').modal('hide');	  
		})
	});
	
	$('#cancelButton').click(function() {
		$('#deleteModalTagParameter').modal('hide');
	})
	
	// TO GET INDI LEVEL FOR EDIT MODE IN PARAMETER TAB
	function getIndicatorLevelEdit(marc, tag) {
		$.get('getIndicatorLevel', {
			Marc: marc,
			Tag: tag
		}, function(responseJson) {
			if (responseJson.length == 0) {
				$('#indicator-value-1').empty()
				$('#indicator-value-1').append('<option value=" "> </option>')
				$('#indicator-value-2').empty()
				$('#indicator-value-2').append('<option value=" "> </option>')
			}
			if (responseJson == '') {
			} else {
				$.each(responseJson, function(key, value) {
					if(responseJson.length == 2){
						if(value['GL18INDILVL'] == 1){
							var indi1AllLvl = ''
							
							$.each(value['GL18INDILVLSUM'].toString().split(","),function(key, val) {
								if(value['GL17DEFAULT'].substring(0, 1) == val){
									indi1AllLvl +="<option selected value='"+val+"'>"+val+"</option>"
								}else {
									indi1AllLvl +="<option value='"+val+"'>"+val+"</option>"
								}
							});
							$('#indicator-value-1').empty()
							$('#indicator-value-1').append(indi1AllLvl)
						}else if(value['GL18INDILVL'] == 2){
							var indi2AllLvl = ''
							
							$.each(value['GL18INDILVLSUM'].toString().split(","),function(key, val) {
							  	if(value['GL17DEFAULT'].substring(1, 2) == val){
									indi2AllLvl +="<option selected value='"+val+"'>"+val+"</option>"
								}else {
									indi2AllLvl +="<option value='"+val+"'>"+val+"</option>"
								}
							});
							$('#indicator-value-2').empty()
							$('#indicator-value-2').append(indi2AllLvl)
						}
					}
					if(responseJson.length == 1){
						if(value['GL18INDILVL'] == 1){
							var indi1AllLvl = ''
							
							$.each(value['GL18INDILVLSUM'].toString().split(","),function(key, val) {
							  	if(value['GL17DEFAULT'].substring(0, 1) == val){
									indi2AllLvl +="<option selected value='"+val+"'>"+val+"</option>"
								}else {
									indi2AllLvl +="<option value='"+val+"'>"+val+"</option>"
								}
							});
							$('#indicator-value-1').empty()
							$('#indicator-value-1').append(indi1AllLvl)
							$('#indicator-value-2').empty()
							$('#indicator-value-2').append('<option value=" "> </option>')
						}else if(value['GL18INDILVL'] == 2){
							var indi2AllLvl = ''
							$.each(value['GL18INDILVLSUM'].toString().split(","),function(key, val) {
							  	if(value['GL17DEFAULT'].substring(1, 2) == val){
									indi2AllLvl +="<option selected value='"+val+"'>"+val+"</option>"
								}else {
									indi2AllLvl +="<option value='"+val+"'>"+val+"</option>"
								}
							});
							$('#indicator-value-2').empty()
							$('#indicator-value-2').append(indi2AllLvl)
							$('#indicator-value-1').empty()
							$('#indicator-value-1').append('<option value=" "> </option>')
						}
					}
				});
			}
		});
	}

	// TO GET INDI & SUBFIELDS FOR VIEW MODE IN INDI TAB AND SUBFIELD TAB
	function getIndiSubfieldsView(marc, tag) {
		$.get('indisubfServlet', {
			Marc: marc,
			Tag: tag
		}, function(responseJson) {
			if (responseJson == '') {
			} else {
				$.each(responseJson[0].concat(responseJson[1]), function(key, value) {
					if (value['GL18INDILVL'] == 1) {
						if(isNaN(value['GL18INDI']) === true){
							$(".indi1 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(".indi101").val(value['GL18DESC1']); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
							$(".indi101").prop('readonly', false); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
						} else if(isNaN(value['GL18INDI']) === false){
							var getindi = ".indi1" + value['GL18INDI'];
							$(".indi1 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(getindi).val(value['GL18DESC1']);
							$(getindi).prop('readonly', false);
						}
					} else if (value['GL18INDILVL'] == 2) {
						if(isNaN(value['GL18INDI']) === true){
							$(".indi2 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(".indi202").val(value['GL18DESC1']); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
							$(".indi202").prop('readonly', false); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
						}else if(isNaN(value['GL18INDI']) === false){
							var getindi = ".indi2" + value['GL18INDI'];
							$(".indi2 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(getindi).val(value['GL18DESC2']);
							$(getindi).prop('readonly', false);
						}
					} else if (value['GL23TYPE'] == 'SUBFIELD') {
						var getsub = ".sub" + value['GL23SUBF'];
						var getManda = ".sub-m-" + value['GL23SUBF'];
						var getRepeat = ".sub-r-" + value['GL23SUBF'];
						
						$(".subfield input[value='" + value['GL23SUBF'] + "']").prop('checked', 'checked');
						
						if(value['GL23MANDA'] == 'Y'){
							$(getManda+" input").prop('checked', 'checked');
							$(getManda).val(value['GL23MANDA']);
							$(getManda).removeAttr('readonly');
						}
						
						if(value['GL23REPEAT'] == 'Y'){
							$(getRepeat+" input").prop('checked', 'checked');
							$(getRepeat).val(value['GL23REPEAT']);
							$(getRepeat).removeAttr('readonly');
						}
						
						$(getsub).val(value['GL23DESC']);
						$(getsub).removeAttr('readonly');
						arrayOfClickedElementId.push($(".subfield input[value='" + value['GL23SUBF'] + "']").prop('checked', 'checked').prop("id"))
					}
					//$(getindi).prop('readonly', false);
				});
			}
		});
	}


	// TO GET INDI & SUBFIELDS FOR EDIT MODE IN INDI TAB AND SUBFIELD TAB
	function getIndiSubfieldsEdit(marc, tag) {
		$.get('indisubfServlet', {
			Marc: marc,
			Tag: tag
		}, function(responseJson) {
			if (responseJson == '') {
			} else {
				arrayOfClickedElementId = []
				$.each(responseJson[0].concat(responseJson[1]), function(key, value) {
					if (value['GL18INDILVL'] == 1) {
						if(isNaN(value['GL18INDI']) === true){
							$(".indi1 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(".indi101").val(value['GL18DESC1']); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
							$(".indi101").prop('readonly', false); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
							arrayOfClickedElementId.push($(".indi1 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked').prop("id"))
						} else if(isNaN(value['GL18INDI']) === false){
							var getindi1 = ".indi1" + value['GL18INDI'];
							$(".indi1 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(getindi1).val(value['GL18DESC1']);
							$(getindi1).removeAttr('readonly');
							arrayOfClickedElementId.push($(".indi1 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked').prop("id"))
						}
					} else if (value['GL18INDILVL'] == 2) {
						if(isNaN(value['GL18INDI']) === true){
							$(".indi2 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(".indi202").val(value['GL18DESC1']); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
							$(".indi202").prop('readonly', false); // THIS IS FOR THE UNDEFINED # INDICATOR TAG
							arrayOfClickedElementId.push($(".indi2 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked').prop("id"))
						} else if(isNaN(value['GL18INDI']) === false){
							var getindi2 = ".indi2" + value['GL18INDI'];
							$(".indi2 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked');
							$(getindi2).val(value['GL18DESC2']);
							$(getindi2).removeAttr('readonly');
							arrayOfClickedElementId.push($(".indi2 input[value='" + value['GL18INDI'] + "']").prop('checked', 'checked').prop("id"))
						}
					} else if (value['GL23TYPE'] == 'SUBFIELD') {
						var getsub = ".sub" + value['GL23SUBF'];
						var getManda = ".sub-m-" + value['GL23SUBF'];
						var getRepeat = ".sub-r-" + value['GL23SUBF'];
						
						$(".subfield input[value='" + value['GL23SUBF'] + "']").prop('checked', 'checked');
						
						if(value['GL23MANDA'] == 'Y'){
							$(getManda+" input").prop('checked', 'checked');
							$(getManda).val(value['GL23MANDA']);
						}
						
						if(value['GL23REPEAT'] == 'Y'){
							$(getRepeat+" input").prop('checked', 'checked');
							$(getRepeat).val(value['GL23REPEAT']);
						}
						
						$(getManda+" input").attr('disabled', false);
						$(getRepeat+" input").attr('disabled', false);
						$(getsub).val(value['GL23DESC']);
						$(getsub).removeAttr('readonly');
						arrayOfClickedElementId.push($(".subfield input[value='" + value['GL23SUBF'] + "']").prop('checked', 'checked').prop("id"))
					}
				});
			}
		});
	}

	// TO RESET THE FORM
	$('#close, #closeModalAddTagParameter').click(function() {
		$('#formdataAddTagParameter').trigger("reset");
		$('a[href="#parameters-tab"]').tab('show')
	})

	// TO ENABLE INPUT
	function enable() {
		$('input, select').attr('disabled', false);
		$('.read-input-only-manda-repeat > input').attr('disabled', true);
	}

	// TO DISABLE INPUT
	function disabled() {
		$("input,select").attr('disabled', true);
		$('input[type="search"]').removeAttr('disabled');
	}

	// TO DISABLE INPUT INDI SUBF
	function disabledInputIndiSubf() {
		$('#formdataAddTagParameter').trigger("reset");
		$("#indicator-value-1").attr('disabled', 'disabled');
		$("#indicator-value-2").attr('disabled', 'disabled');
		$('.read-input-only input').prop('readonly', true);
		$('.read-input-only-manda-repeat > input').attr('disabled', true);
	}
	//***************************************END AREA FOR MODAL *****************************************//
	
	// THIS CODE IS TO DETECT AND TAKE INPUT AS CHECKBOX CLICKED OR NOT
	$('.selected input:checkbox').click(function(e) {
		var clickedElementId = e.target.id // get id of clicked element checkbox chkb
		var clickedElementIdCombine = "#" + clickedElementId + "1" // chkbSubf-tab1-1
		var clickedElementIdCombineManda = "#" + clickedElementId + "m" // chkbSubf-tab1-1m
		var clickedElementIdCombineRepeat = "#" + clickedElementId + "r" // chkbSubf-tab1-1r
		
		if($("#" + clickedElementId).prop('checked') && !(clickedElementId.endsWith('m')) && !(clickedElementId.endsWith('r'))){
			$(clickedElementIdCombineManda).removeAttr("disabled")
			$(clickedElementIdCombineRepeat).removeAttr("disabled")
			$(clickedElementIdCombine).removeAttr("readonly")
			arrayOfClickedElementId.push(clickedElementId)
		} else if($("#" + clickedElementId).prop('checked') === false && !(clickedElementId.endsWith('m')) && !(clickedElementId.endsWith('r'))){
			arrayOfClickedElementId.splice(arrayOfClickedElementId.indexOf(clickedElementId),1)
			$(clickedElementIdCombine).prop("readonly", true);
			$(clickedElementIdCombineManda).attr('disabled', true);
			$(clickedElementIdCombineRepeat).attr('disabled', true);
		}
	});


	// SAVE BUTTON ON CLICK
	$("#save").click(function() {
		formDataObject = {};
		data_array = $("form").serializeArray();
		for (const key of data_array) { formDataObject[key.name] = key.value; }
		var allIndiSubf = new Array();
		var type = ""
		
		for (let q = 0; q < 58; q++) {
			if(typeof arrayOfClickedElementId[q] == 'undefined'){
			} else if(typeof arrayOfClickedElementId[q] != 'undefined'){
				if (arrayOfClickedElementId[q].substring(0, arrayOfClickedElementId[q].indexOf('-')) == 'chkbIndi1') {
					type = "indi1"
				} if (arrayOfClickedElementId[q].substring(0, arrayOfClickedElementId[q].indexOf('-')) == 'chkbIndi2') {
					type = "indi2"
				} if (arrayOfClickedElementId[q].substring(0, arrayOfClickedElementId[q].indexOf('-')) == 'chkbSubf') {
					type = "subfield"
				}
		
				if ($("#" + arrayOfClickedElementId[q]).prop('checked') === true) {
					if(arrayOfClickedElementId[q].slice(0, 8) == 'chkbIndi'){
						let j = {
							type: type,
							value: $("#" + arrayOfClickedElementId[q]).val(),
							desc: $("#" + arrayOfClickedElementId[q] + "1").val()
						}
						allIndiSubf.push(j)
					}
					if(arrayOfClickedElementId[q].slice(0, 8) == 'chkbSubf'){
						let j = {
							type: type,
							value: $("#" + arrayOfClickedElementId[q]).val(),
							desc: $("#" + arrayOfClickedElementId[q] + "1").val(),
							mandatory: $("#" + arrayOfClickedElementId[q] + "m").prop('checked') === true ? 'Y':'N',
							repeatable: $("#" + arrayOfClickedElementId[q] + "r").prop('checked') === true ? 'Y':'N'
						}
						allIndiSubf.push(j)
					}
				}
			}
		}
		
		if(condition == 2){
			if($('#indexTable').val().length > 0 && $('#indexTable').val().slice(0, 2) === 'CT'){
				sendData(formDataObject, allIndiSubf)
			}
		}
		
		if(condition == 1){
			$.ajax({
				type: "GET",
				url: "checkTagAvailable",
				data: {
					Marc: $('#fCodeH').val(),
					Tag: $('#tagParameterNo').val()
				},
				success: function(data) {
					if(data == true){
						tagNoExist = true
						swal("This tag already exists. Please enter a unique tag").then(() => {
				            $('#tagParameterNo').val("");
				            $('#tagParameterNo').focus();
							checkFormValidationMandatoryField()
				          });
					}
					if(data == false){
						tagNoExist = false
						if($('#tagParameterNo').val().length > 0 && tagNoExist == false && $('#indexTable').val().length > 0 && $('#indexTable').val().slice(0, 2) === 'CT'){
							sendData(formDataObject, allIndiSubf)
						}
					}
				}
			})
		}
	});
	

	function sendData(i, k) {
		$.ajax({
			type: "POST",
			url: condition == 1 ? "addTagParameter" : "updateTagParameter",
			data: {
				Marc: condition == 1 ? i.fCodeH :  $('#fCodeH').prop('value'),
				Tag: condition == 1 ? i.tagParameterNo : $('#tagParameterNo').prop('value'),
				Desc: i.description,
				Truc: i.abbreviatedDesc,
				Acro: i.acronym,
				TabName: i.indexTable,
				AutGroup: i.authGroup,
				RevTag: i.revisedTagNo,
				IdxLang: i.indexLang,
				Len: i.fieldLength == ''? '0':i.fieldLength,
				DefaultIndi1: condition == 1 ? " " : $('#indicator-value-1').val(),
				DefaultIndi2: condition == 1 ? " " : $('#indicator-value-2').val(),
				DefaultValue: i.defaultValue.trim(),
				Remark: i.remark,
				Repeat: $('#checkboxRepeatable').prop('checked') == true ? i.checkboxRepeatable = "Y" : i.checkboxRepeatable = "N",
				Manda: $('#checkboxMandatory').prop('checked') == true ? i.checkboxMandatory = "Y" : i.checkboxMandatory = "N",
				Keyword: $('#checkboxKeyword').prop('checked') == true ? i.checkboxKeyword = "Y" : i.checkboxKeyword = "N",
				Stop: $('#checkboxDuplicateCheck').prop('checked') == true ? i.checkboxDuplicateCheck = "Y" : i.checkboxDuplicateCheck = "N",
				AutFlag: $('#checkboxAuthFlag').prop('checked') == true ? i.checkboxAuthFlag = "Y" : i.checkboxAuthFlag = "N",
				Paramips: $('#checkboxParamLink').prop('checked') == true ? i.checkboxParamLink = "Y" : i.checkboxParamLink = "N",
				CpfFlag: $('#checkboxCopyPaste').prop('checked') == true ? i.checkboxCopyPaste = "Y" : i.checkboxCopyPaste = "N",
				IdxFlag: $('#checkboxIndFlag').prop('checked') == true ? i.checkboxIndFlag = "Y" : i.checkboxIndFlag = "N",
				Media: $('#checkboxMultimediaTag').prop('checked') == true ? i.checkboxMultimediaTag = "Y" : i.checkboxMultimediaTag = "N",
				IndiSubf: k,
				LengthOfIndiSubf: k.length,
				CreDate: moment(new Date()).format('YYYYMMDD'),
				CreTime: new Date().toLocaleTimeString([],{hour:'2-digit',minute:'2-digit',second:'2-digit',hour12: false}).replaceAll(':',''),
				RecBy: $("#liferayLogin").val()
			},
			success: function() {
				$('#modaltagparameter').modal('hide');
				if(condition == 1){
					swal("Successfully Add")
				} else {
					swal("Successfully Update")
				}
				
				$(".swal2-confirm").click(function(){
					$('#formdataAddTagParameter').trigger("reset");
					$('a[href="#parameters-tab"]').tab('show')
				});
			}
		}).then(function(data) {
			refreshTableWithData()
			checkFormValidationMandatoryField()
		})
	}
});