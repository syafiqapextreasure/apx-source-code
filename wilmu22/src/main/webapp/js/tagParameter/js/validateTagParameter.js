$(document).ready(
		function() {		
			
			var liferayLogin = $("#liferayLogin").val();

			$("#recordedBy").text(liferayLogin);
			
			var d = new Date();

			var month = d.getMonth()+1;
			var day = d.getDate();

			var currDate =  ((''+day).length<2 ? '0' : '') + day+ '/' +
			    ((''+month).length<2 ? '0' : '') + month + '/' +
			    d.getFullYear();
			
			$('#dateRecorded').text(currDate);
			
			var globalRowData;

			var table = $('#tagParameter_table').DataTable( {
			    select: true
		   } );
			
			table.on('select', function(e, dt, type, indexes) {
			    var rowData = table.rows(indexes).data().toArray();
			    globalRowData = rowData;
		    });
			
			 $(".authorityType").hide();

						$.ajax({
							url : 'GetMARCID',
							success : function(data) {
								if (data) {
									var count = 0;
						
									$.each(data, function(key, entry) {
										$('#patronCategoryId1').append(
												$("<option />").val(entry)
														.text(entry));
										count++;
									})
									
								$dropdown = $('#patronCategoryId1');
								$dropdown[0].selectedIndex = -1;
									
									$.ajax({

										url : 'GetMARCdesc',
										success : function(data1) {
											if (data1) {
												var count1 = 0;

												$.each(data1, function(key, entry) {
													$('#patronCategoryDesc1').append(
															$("<option />").val(entry)
																	.text(entry));
													count1++;
												})
											}
											$dropdown = $('#patronCategoryDesc1');
											$dropdown[0].selectedIndex = -1;
										}
									});
								}
							}
						});

			$(".patronCategoryId").change(function() {
				var id = $(this).data('id');
				var index = $(this).prop('selectedIndex');
				$("#patronCategoryDesc" + id).prop('selectedIndex', index);
			});

			$(".patronCategoryDesc").change(function() {
				var id = $(this).data('id');
				var index = $(this).prop('selectedIndex');
				$("#patronCategoryId" + id).prop('selectedIndex', index);
			});

			$.ajax({
				url : 'GetMARCID',
				success : function(data) {
					if (data) {
						var count = 0;
			
						$.each(data, function(key, entry) {
							$('#marcId1').append(
									$("<option />").val(entry)
											.text(entry));
							count++;
						})
						
					$dropdown = $('#marcId1');
					$dropdown[0].selectedIndex = -1;
						
						$.ajax({

							url : 'GetMARCdesc',
							success : function(data1) {
								if (data1) {
									var count1 = 0;

									$.each(data1, function(key, entry) {
										$('#marcDesc1').append(
												$("<option />").val(entry)
														.text(entry));
										count1++;
									})
								}
								$dropdown = $('#marcDesc1');
								$dropdown[0].selectedIndex = -1;
							}
						});
					}
				}
			});
		
			$(".marcId").change(function() {
				var id = $(this).data('id');
				var index = $(this).prop('selectedIndex');
				$("#marcDesc" + id).prop('selectedIndex', index);
			});

			$(".marcDesc").change(function() {
				var id = $(this).data('id');
				var index = $(this).prop('selectedIndex');
				$("#marcId" + id).prop('selectedIndex', index);
			});
			
		var value = $('#authorityType :selected').val();

	    $('#patronCategoryId1').change(function(){

		     var values = $('#patronCategoryId1 :selected').val();
		     
		     if(values =='I' || values=='U'){
		        $(".authorityType").hide();
		      }else if(values =='Y'){
		    	$(".authorityType").show();
		      }
		             
		 });
			
		var table=$('#tagParameter_table').DataTable({
			destroy: true,
			searching: false,
			bPaginate: true,
			bLengthChange: false,
			autoWidth: false,
			responsive: true,
			processing: true,
		    ajax: {
		    	url: "LoadAllTagParameter",
		        type: "GET",
		        dataSrc: function (data) {
		        	
		        	$.fn.dataTable.ext.errMode = 'none';
		   	
		            var return_data = new Array();

		            for( var i=1;i< data.length; i++) {
		            	var stat = data[i].status;
		       				return_data.push({
								'No': i,   
								'MARC' : data[i].marc,
								'Tag' : data[i].tag,
								'Description' : data[i].description,
								'Action' : /*'<button id="Add" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#addModal" data-whatever="Add Email" title="Add Tag" data-rowid="'+data[i].tag+'"><span class="glyphicon glyphicon-plus"></span></button></button> '+	*/			
											'<button id="Edit" class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#editModal" data-whatever="Edit Tag" title="Edit Tag"  data-marc="'+data[i].marc+'" data-tag="'+data[i].tag+'" data-desc="'+data[i].description+'" style="background-color: #1ab394"><span class="glyphicon glyphicon-pencil"></span></button></button>&nbsp'+
											'<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#viewModal" data-whatever="View Tag" title="View Tag" data-marc="'+data[i].marc+'" data-tag="'+data[i].tag+'" data-desc="'+data[i].description+'" style="background-color: #1ab394"><span class="glyphicon glyphicon-eye-open"></span></button></button> '+								
											'<button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+data[i].tag+'"><span class="glyphicon glyphicon-trash" title="Delete Tag"></span></button>',
							})
		            	}
		            return return_data;
		          },
		     },
	     	columns    : [
				{'data': 'No'},
				{'data': 'MARC'},
				{'data': 'Tag'},
				{'data': 'Description'},
				{'data': 'Action'},
			],
	        'dom': 'Rlfrtip',
	        'colReorder': {
	            'allowReorder': false
	        },   
		});
		
		$('#addInd1CBUndefine').click(function() {
		    $('#addInd1TFUndefine').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB0').click(function() {
		    $('#addInd1TF0').attr('disabled',! this.checked)
		});
		
		
		$('#addInd1CB1').click(function() {
		    $('#addInd1TF1').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB2').click(function() {
		    $('#addInd1TF2').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB3').click(function() {
		    $('#addInd1TF3').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB4').click(function() {
		    $('#addInd1TF4').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB5').click(function() {
		    $('#addInd1TF5').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB6').click(function() {
		    $('#addInd1TF6').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB7').click(function() {
		    $('#addInd1TF7').attr('disabled',! this.checked)
		});
		
		$('#addInd1CB8').click(function() {
		    $('#addInd1TF8').attr('disabled',! this.checked)
		});
		$('#addInd1CB9').click(function() {
		    $('#addInd1TF9').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB0').click(function() {
		    $('#addInd2TF0').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB1').click(function() {
		    $('#addInd2TF1').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB2').click(function() {
		    $('#addInd2TF2').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB3').click(function() {
		    $('#addInd2TF3').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB4').click(function() {
		    $('#addInd2TF4').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB5').click(function() {
		    $('#addInd2TF5').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB6').click(function() {
		    $('#addInd2TF6').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB7').click(function() {
		    $('#addInd2TF7').attr('disabled',! this.checked)
		});
		
		$('#addInd2CB8').click(function() {
		    $('#addInd2TF8').attr('disabled',! this.checked)
		});
		$('#addInd2CB9').click(function() {
		    $('#addInd2TF9').attr('disabled',! this.checked)
		});
		
		
		
		$('#addSFCBa').click(function() {
		    $('#addSFTFa').attr('disabled',! this.checked)
		});
		
		$('#addSFCBb').click(function() {
		    $('#addSFTFb').attr('disabled',! this.checked)
		});
		
		$('#addSFCBc').click(function() {
		    $('#addSFTFc').attr('disabled',! this.checked)
		});
		
		$('#addSFCBd').click(function() {
		    $('#addSFTFd').attr('disabled',! this.checked)
		});
		
		$('#addSFCBe').click(function() {
		    $('#addSFTFe').attr('disabled',! this.checked)
		});
		
		$('#addSFCBf').click(function() {
		    $('#addSFTFf').attr('disabled',! this.checked)
		});
		
		$('#addSFCBg').click(function() {
		    $('#addSFTFg').attr('disabled',! this.checked)
		});
		
		$('#addSFCBh').click(function() {
		    $('#addSFTFh').attr('disabled',! this.checked)
		});
		
		$('#addSFCBi').click(function() {
		    $('#addSFTFi').attr('disabled',! this.checked)
		});
		
		$('#addSFCBj').click(function() {
		    $('#addSFTFj').attr('disabled',! this.checked)
		});
		
		$('#addSFCBk').click(function() {
		    $('#addSFTFk').attr('disabled',! this.checked)
		});
		
		$('#addSFCBl').click(function() {
		    $('#addSFTFl').attr('disabled',! this.checked)
		});
		
		$('#addSFCBm').click(function() {
		    $('#addSFTFm').attr('disabled',! this.checked)
		});
		
		$('#addSFCBn').click(function() {
		    $('#addSFTFn').attr('disabled',! this.checked)
		});
		
		$('#addSFCBo').click(function() {
		    $('#addSFTFo').attr('disabled',! this.checked)
		});
		
		$('#addSFCBp').click(function() {
		    $('#addSFTFp').attr('disabled',! this.checked)
		});
		
		$('#addSFCBq').click(function() {
		    $('#addSFTFq').attr('disabled',! this.checked)
		});
		
		$('#addSFCBr').click(function() {
		    $('#addSFTFr').attr('disabled',! this.checked)
		});
		
		$('#addSFCBs').click(function() {
		    $('#addSFTFs').attr('disabled',! this.checked)
		});
			
		$('#addSFCBt').click(function() {
		    $('#addSFTFt').attr('disabled',! this.checked)
		});
		
		$('#addSFCBu').click(function() {
		    $('#addSFTFu').attr('disabled',! this.checked)
		});
		
		$('#addSFCBv').click(function() {
		    $('#addSFTFv').attr('disabled',! this.checked)
		});
		
		$('#addSFCBw').click(function() {
		    $('#addSFTFw').attr('disabled',! this.checked)
		});
		
		$('#addSFCBx').click(function() {
		    $('#addSFTFx').attr('disabled',! this.checked)
		});
		
		$('#addSFCBy').click(function() {
		    $('#addSFTFy').attr('disabled',! this.checked)
		});
		$('#addSFCBz').click(function() {
		    $('#addSFTFz').attr('disabled',! this.checked)
		});
		
		$('#addSFCB0').click(function() {
		    $('#addSFTF0').attr('disabled',! this.checked)
		});
		
		$('#addSFCB1').click(function() {
		    $('#addSFTF1').attr('disabled',! this.checked)
		});
		
		$('#addSFCB2').click(function() {
		    $('#addSFTF2').attr('disabled',! this.checked)
		});
		
		$('#addSFCB3').click(function() {
		    $('#addSFTF3').attr('disabled',! this.checked)
		});
		
		$('#addSFCB4').click(function() {
		    $('#addSFTF4').attr('disabled',! this.checked)
		});
		
		$('#addSFCB5').click(function() {
		    $('#addSFTF5').attr('disabled',! this.checked)
		});
		
		$('#addSFCB6').click(function() {
		    $('#addSFTF6').attr('disabled',! this.checked)
		});
		
		$('#addSFCB7').click(function() {
		    $('#addSFTF7').attr('disabled',! this.checked)
		});
		
		$('#addSFCB8').click(function() {
		    $('#addSFTF8').attr('disabled',! this.checked)
		});
		$('#addSFCB9').click(function() {
		    $('#addSFTF9').attr('disabled',! this.checked)
		});
		
		var testDataUrl = "https://raw.githubusercontent.com/chennighan/RapidlyPrototypeJavaScript/master/lesson4/data.json"
			
			$('#findBtn').click(function() {

				var table = $('#tagParameter_table').DataTable({
					destroy: true,
					searching: false,
					bPaginate: true,
					bLengthChange: false,
					autoWidth: false,
					responsive: true,
					processing: true,
				    ajax: {
				    	url: "FindTag",
				        data : {marcId: $('#marcId1').val(), marcDesc: $('#marcDesc1').val(), selectedRadio: $('#selectedRadio').val(), userInput: $('#userInput').val()}, 
				        type: "GET",
				        dataSrc: function (data) {
				        	
				        	$.fn.dataTable.ext.errMode = 'none';
				   	
				            var return_data = new Array();

				            var dataLength = Object.keys(data).length;

				            for( var i=0;i< dataLength; i++) {
				       				return_data.push({
										'No': i+1,   
										'MARC' : data[i].marc,
										'Tag' : data[i].tag,
										'Description' : data[i].description,
										'Action' : '<button id="Edit" class="btn btn-primary btn-xs"  data-toggle="modal" data-target="#editModal" data-whatever="Edit Tag" title="Edit Tag"  data-marc="'+data[i].marc+'" data-tag="'+data[i].tag+'" data-desc="'+data[i].description+'" ><span class="glyphicon glyphicon-pencil"></span></button></button>&nbsp'+
												   '<button id="View" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#viewModal" data-whatever="View Tag" title="View Tag" data-marc="'+data[i].marc+'" data-tag="'+data[i].tag+'" data-desc="'+data[i].description+'"><span class="glyphicon glyphicon-eye-open"></span></button></button> '+								
												   '<button id="Delete" class="btn btn-dull btn-xs Delete" data-id="'+data[i].tag+'"><span class="glyphicon glyphicon-trash" title="Delete Tag" ></span></button>',
							
										})
				            	}
				            return return_data;
				          },
				     },
				     	columns    : [
							{'data': 'No'},
							{'data': 'MARC'},
							{'data': 'Tag'},
							{'data': 'Description'},
							{'data': 'Action'},
						],
				        'dom': 'Rlfrtip',
				        'colReorder': {
				            'allowReorder': false
				        },   
				});
			});	

		  function populateDataTable(data) {
			    $("#tagParameter_table").DataTable().clear();
			    var length = Object.keys(data.customers).length;
			    for(var i = 1; i < length+1; i++) {
			      var customer = data.customers['customer'+i];

			      $('#tagParameter_table').dataTable().fnAddData( [
			        i,
			    	customer.first_name,
			        customer.last_name,
			        customer.occupation,
			        customer.email_address
			      ]);
			    }
			  }

	  $("#addOkBtn").click(function(){
		  
			 if($('#addInd1CBUndefine').is(':checked')){
					var ind1Undefine =  $('#addInd1TFUndefine').val();
			  }				
			 if($('#addInd1CB0').is(':checked')){
					var ind1Input0 =  $('#addInd1TF0').val();
			  }
			 if($('#addInd1CB1').is(':checked')){
					var ind1Input1 =  $('#addInd1TF1').val();
			  }
			 if($('#addInd1CB2').is(':checked')){
					var ind1Input2 =  $('#addInd1TF2').val();
			  }
			 if($('#addInd1CB3').is(':checked')){
					var ind1Input3 =  $('#addInd1TF3').val();
			  }
			 if($('#addInd1CB4').is(':checked')){
					var ind1Input4 =  $('#addInd1TF4').val();
			  }
			 if($('#addInd1CB5').is(':checked')){
					var ind1Input5 =  $('#addInd1TF5').val();
			  }
			 if($('#addInd1CB6').is(':checked')){
					var ind1Input6 =  $('#addInd1TF6').val();
			  }
			 if($('#addInd1CB7').is(':checked')){
					var ind1Input7 =  $('#addInd1TF7').val();
			  }
			 if($('#addInd1CB8').is(':checked')){
					var ind1Input8 =  $('#addInd1TF8').val();
			  }
			 if($('#addInd1CB9').is(':checked')){
					var ind1Input9 =  $('#addInd1TF9').val();
			  }
			 if($('#addInd2CBUndefine').is(':checked')){
					var ind2Undefine =  $('#addInd2TFUndefine').val();
			  }				
			 if($('#addInd2CB0').is(':checked')){
					var ind2Input0 =  $('#addInd2TF0').val();
			  }
			 if($('#addInd2CB1').is(':checked')){
					var ind2Input1 =  $('#addInd2TF1').val();
			  }
			 if($('#addInd2CB2').is(':checked')){
					var ind2Input2 =  $('#addInd2TF2').val();
			  }
			 if($('#addInd2CB3').is(':checked')){
					var ind2Input3 =  $('#addInd2TF3').val();
			  }
			 if($('#addInd2CB4').is(':checked')){
					var ind2Input4 =  $('#addInd2TF4').val();
			  }
			 if($('#addInd2CB5').is(':checked')){
					var ind2Input5 =  $('#addInd2TF5').val();
			  }
			 if($('#addInd2CB6').is(':checked')){
					var ind2Input6 =  $('#addInd2TF6').val();
			  }
			 if($('#addInd2CB7').is(':checked')){
					var ind2Input7 =  $('#addInd2TF7').val();
			  }
			 if($('#addInd2CB8').is(':checked')){
					var ind2Input8 =  $('#addInd2TF8').val();
			  }
			 if($('#addInd2CB9').is(':checked')){
					var ind2Input9 =  $('#addInd2TF9').val();
			  }
		  
		  if($('#addSFCBa').is(':checked')){
				var sfInputA =  $('#addSFTFa').val();
		  }
		  
		  if($('#addSFCBb').is(':checked')){
			  var sfInputB =  $('#addSFTFb').val();
		  }
		  if($('#addSFCBc').is(':checked')){
			  var sfInputC =  $('#addSFTFc').val();
		  }
		  
		  if($('#addSFCBd').is(':checked')){
			  var sfInputD =  $('#addSFTFd').val();
		  }
		  if($('#addSFCBe').is(':checked')){
			  var sfInputE =  $('#addSFTFe').val();
		  }
		  
		  if($('#addSFCBf').is(':checked')){
			  var sfInputF =  $('#addSFTFf').val();
		  }
		  if($('#addSFCBg').is(':checked')){
			  var sfInputG =  $('#addSFTFg').val();
		  }
		  
		  if($('#addSFCBh').is(':checked')){
			  var sfInputH =  $('#addSFTFh').val();
		  }
		  if($('#addSFCBi').is(':checked')){
			  var sfInputI =  $('#addSFTFi').val();
		  }
		  
		  if($('#addSFCBj').is(':checked')){
			  var sfInputJ =  $('#addSFTFj').val();
		  }
		  if($('#addSFCBk').is(':checked')){
			  var sfInputK =  $('#addSFTFk').val();
		  }
		  
		  if($('#addSFCBl').is(':checked')){
			  var sfInputL =  $('#addSFTFl').val();
		  }
		  if($('#addSFCBm').is(':checked')){
			  var sfInputM =  $('#addSFTFm').val();
		  }
		  
		  if($('#addSFCBn').is(':checked')){
			  var sfInputN =  $('#addSFTFn').val();
		  }
		  if($('#addSFCBo').is(':checked')){
			  var sfInputO =  $('#addSFTFo').val();
		  }
		  
		  if($('#addSFCBp').is(':checked')){
			  var sfInputP =  $('#addSFTFp').val();
		  }
		  if($('#addSFCBq').is(':checked')){
			  var sfInputQ =  $('#addSFTFq').val();
		  }
		  if($('#addSFCBr').is(':checked')){
			  var sfInputR =  $('#addSFTFr').val();
		  }
		  
		  if($('#addSFCBs').is(':checked')){
			  var sfInputS =  $('#addSFTFs').val();
		  }
		  if($('#addSFCBt').is(':checked')){
			  var sfInputT =  $('#addSFTFt').val();
		  }
		  
		  if($('#addSFCBu').is(':checked')){
			  var sfInputU =  $('#addSFTFu').val();
		  }
		  if($('#addSFCBv').is(':checked')){
			  var sfInputV =  $('#addSFTFv').val();
		  }
		  
		  if($('#addSFCBw').is(':checked')){
			  var sfInputW =  $('#addSFTFw').val();
		  }
		  if($('#addSFCBx').is(':checked')){
			  var sfInputX =  $('#addSFTFx').val();
		  }
		  
		  if($('#addSFCBy').is(':checked')){
			  var sfInputY =  $('#addSFTFy').val();
		  }
		  if($('#addSFCBz').is(':checked')){
			  var sfInputZ =  $('#addSFTFz').val();
		  }
		  
		  if($('#addSFCB0').is(':checked')){
			  var sfInput0 =  $('#addSFTF0').val();
		  }
		  
		  if($('#addSFCB1').is(':checked')){
			  var sfInput1 =  $('#addSFTF1').val();
		  }
		  if($('#addSFCB2').is(':checked')){
			  var sfInput2 =  $('#addSFTF2').val();
		  }
		  
		  if($('#addSFCB3').is(':checked')){
			  var sfInput3 =  $('#addSFTF3').val();
		  }
		  if($('#addSFCB4').is(':checked')){
			  var sfInput4 =  $('#addSFTF4').val();
		  }
		  
		  if($('#addSFCB5').is(':checked')){
			  var sfInput5 =  $('#addSFTF5').val();
		  }
		  if($('#addSFCB6').is(':checked')){
			  var sfInput6 =  $('#addSFTF6').val();
		  }
		  
		  if($('#addSFCB7').is(':checked')){
			  var sfInput7 =  $('#addSFTF7').val();
		  }
		  if($('#addSFCB8').is(':checked')){
			  var sfInput8 =  $('#addSFTF8').val();
		  }		
		  if($('#addSFCB9').is(':checked')){
			  var sfInput9 =  $('#addSFTF9').val();
		  }		
		  
			$.ajax({
				url : "AddNewTag",
				data: {marc: $("#patronCategoryId1").val(), tagNo: $("#addTag").val(), desc: $("#addDesc").val(), abbrDesc: $("#addAbbreDesc").val(), indexTable: $("#addIndexTable").val(),
					acronym: $("#addAcronym").val(), authorityGroup: $("#addAuthorityGroup").val(),fieldLength: $("#addFieldLength").val(), indexLanguage: $("#addIndexLanguage").val(), 
					indicator1: $("#addInd1").val(), indicator2: $("#addInd2").val(), defaultValue: $("#addDefaultValue").val(), remark: $("#addRemark").val(),
					repeatable: $('#addRepeatableCheck').prop('checked'), mandatory: $("#addMandatoryCheck").prop('checked'), indexFlag: $("#addIndexFlagCheck").prop('checked'),
					keyword: $("#addKeywordCheck").prop('checked'), duplicateCheck: $("#addDuplicateCheck").prop('checked'), authorityFlag: $("#addAuthorityFlagCheck").prop('checked'),
					copyPaste: $("#addCopyPasteCheck").prop('checked'), paramLink: $("#addParamLinkCheck").prop('checked'), multimediaTag: $("#addMultimediaTagCheck").prop('checked'),
					ind1Undefine: ind1Undefine , ind1Input0: ind1Input0, ind1Input1: ind1Input1, ind1Input2: ind1Input2, ind1Input3: ind1Input3,
					ind1Input4: ind1Input4, ind1Input5: ind1Input5, ind1Input6: ind1Input6, ind1Input7: ind1Input7, ind1Input8: ind1Input8,
					ind1Input9: ind1Input9, ind2Undefine: ind2Undefine, ind2Input0: ind2Input0, ind2Input1: ind2Input1, ind2Input2: ind2Input2, ind2Input3: ind2Input3,
					ind2Input4: ind2Input4, ind2Input5: ind2Input5, ind2Input6: ind2Input6, ind2Input7: ind2Input7, ind2Input8: ind2Input8,
					ind2Input9: ind2Input9, sfInputA: sfInputA, sfInputB: sfInputB, sfInputC: sfInputC, sfInputD: sfInputD, sfInputE: sfInputE, sfInputF: sfInputF, sfInputG: sfInputG, sfInputH: sfInputH,
					sfInputI: sfInputI, sfInputJ: sfInputJ, sfInputK: sfInputK, sfInputL: sfInputL, sfInputM: sfInputM, sfInputM: sfInputM, sfInputO: sfInputO, sfInputP: sfInputP, 
					sfInputQ: sfInputQ, sfInputR: sfInputR, sfInputS: sfInputS, sfInputT: sfInputT, sfInputU: sfInputU, sfInputV: sfInputV, sfInputW: sfInputW, sfInputX: sfInputX,
					sfInputY: sfInputY, sfInputZ: sfInputZ, sf0: sfInput0, sf1: sfInput1, sf2: sfInput2, sf3: sfInput3, sf4: sfInput4, sf5: sfInput5, sf6: sfInput6, sf7: sfInput7, 
					sf8: sfInput8, sf9: sfInput9},
				success : function(data) {
					if (data) {
						swal({
							text: "New Tag Added",
							showCancelButton : true,
							cancelButtonColor : '#d33',
							cancelButtonText : 'Ok',
							cancelButtonClass : 'cancel-class',
							closeOnCancel : false
						});
						$("#tagParameter_table").DataTable().ajax.reload();

						} else if (!data){
						}
					}
				});			  
		  });

		$('#editModal').on('show.bs.modal', function (event) {
			
			var button = $(event.relatedTarget) // Button that triggered the modal
			var modal = $(this);
			var recipient = button.data('whatever'); // Extract info from data-* attributes
			var marc = button.data('marc');
			var tagNo = button.data('tag');
			var desc = button.data('desc');	
			
			modal.find('.modal-title').text(recipient);
			
			$("#editMARC").val(marc);
			$("#editTag").val(tagNo);
			$("#editDesc").val(desc);
			$("#editAbbreDesc").val(desc);
			
			switch(recipient){
				case state = 'Add Tag':
				break;
				case state = 'Edit Tag':
					$.get('ViewTag', {
						marc : marc, tagNo : tagNo
					 	}, 
					 	function(data) {
							if(data != null) {
								
								var params = {};
								var indicator1 = {};
								var indicator2 = {};
								var subfields = {};

								data.params.forEach(function(item) {
									
									  $("#editIndexTable").val(item.indexTable);
									  $("#editAcronym").val(item.acronym);
									  $("#editAuthorityGroup").val(item.authorityGroup);
									  $("#editFieldLength").val(item.fieldLength);
									  $("#editIndexLanguage").val(item.indexLength);
									  $("#editDefaultValue").val(item.defaultValue); 
									  $("#editRemark").val(item.remark);
											
									  $("#editInd1").val(item.defaultIndicator1);
									  $("#editInd2").val(item.defaultIndicator2);
									  
									if(item.repeatable=='Y'){
										$("#editRepeatableCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editRepeatableCheck").prop('checked', false );
									}
									
									if(item.mandatory=='Y'){
										$("#editMandatoryCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editMandatoryCheck").prop('checked', false );
									}
									
									if(item.indexFlag=='Y'){
										$("#editIndexFlagCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editIndexFlagCheck").prop('checked', false );
									}
									
									if(item.keyword=='Y'){
										$("#editKeywordCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editKeywordCheck").prop('checked', false );
									}
									
									if(item.duplicateCheck=='Y'){
										$("#editDuplicateCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editDuplicateCheck").prop('checked', false );
									}
									
									if(item.authorityCheck=='Y'){
										$("#editAuthorityFlagCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editAuthorityFlagCheck").prop('checked', false );
									}
									
									if(item.copyPaste=='Y'){
										$("#editCopyPasteCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editCopyPasteCheck").prop('checked', false );
									}
									
									if(item.paraLink=='Y'){
										$("#editParamLinkCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editParamLinkCheck").prop('checked', false );
									}
									
									if(item.multimediaTag=='Y'){
										$("#editMultimediaTagCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#editMultimediaTagCheck").prop('checked', false );
									}
										});
										
								data.subfields.forEach(function(item) {
									subfields[item.code] = item.desc;
								});
							
								data.indicator1.forEach(function(item) {
									indicator1[item.code] = item.desc;
								});
								
								data.indicator2.forEach(function(item) {
									indicator2[item.code] = item.desc;
								});
								
								if (!indicator1['#']){
									$("#editInd1CBUndefine").prop('checked', false );	
								}else{
									$("#editInd1CBUndefine").prop('checked', true );
								}
								
								if (!indicator1['0']){
									$("#editInd1CB0").prop('checked', false );	
								}else{
									$("#editInd1CB0").prop('checked', true );
								}
								
								if (!indicator1['1']){
									$("#editInd1CB1").prop('checked', false );	
								}else{
									$("#editInd1CB1").prop('checked', true );
								}
								
								if (!indicator1['2']){
									$("#editInd1CB2").prop('checked', false );	
								}else{
									$("#editInd1CB2").prop('checked', true );
								}
								
								if (!indicator1['3']){
									$("#editInd1CB3").prop('checked', false );	
								}else{
									$("#editInd1CB3").prop('checked', true );
								}
								
								if (!indicator1['4']){
									$("#editInd1CB4").prop('checked', false );	
								}else{
									$("#editInd1CB4").prop('checked', true );
								}
								
								if (!indicator1['5']){
									$("#editInd1CB5").prop('checked', false );	
								}else{
									$("#editInd1CB5").prop('checked', true );
								}
								
								if (!indicator1['6']){
									$("#editInd1CB6").prop('checked', false );	
								}else{
									$("#editInd1CB6").prop('checked', true );
								}
								
								if (!indicator1['7']){
									$("#editInd1CB7").prop('checked', false );	
								}else{
									$("#editInd1CB7").prop('checked', true );
								}
								
								if (!indicator1['8']){
									$("#editInd1CB8").prop('checked', false );	
								}else{
									$("#editInd1CB8").prop('checked', true );
								}
								
								if (!indicator1['9']){
									$("#editInd1CB9").prop('checked', false );	
								}else{
									$("#editInd1CB9").prop('checked', true );
								}
								
								if (!indicator2['#']){
									$("#editInd2CBUndefine").prop('checked', false );	
								}else{
									$("#editInd2CBUndefine").prop('checked', true );
								}
								
								if (!indicator2['0']){
									$("#editInd2CB0").prop('checked', false );	
								}else{
									$("#editInd2CB0").prop('checked', true );
								}
								
								if (!indicator2['1']){
									$("#editInd2CB1").prop('checked', false );	
								}else{
									$("#editInd2CB1").prop('checked', true );
								}
								
								if (!indicator2['2']){
									$("#editInd2CB2").prop('checked', false );	
								}else{
									$("#editInd2CB2").prop('checked', true );
								}
								
								if (!indicator2['3']){
									$("#editInd2CB3").prop('checked', false );	
								}else{
									$("#editInd2CB3").prop('checked', true );
								}
								
								if (!indicator2['4']){
									$("#editInd2CB4").prop('checked', false );	
								}else{
									$("#editInd2CB4").prop('checked', true );
								}
								
								if (!indicator2['5']){
									$("#editInd2CB5").prop('checked', false );	
								}else{
									$("#editInd2CB5").prop('checked', true );
								}
								
								if (!indicator2['6']){
									$("#editInd2CB6").prop('checked', false );	
								}else{
									$("#editInd2CB6").prop('checked', true );
								}
								
								if (!indicator2['7']){
									$("#editInd2CB7").prop('checked', false );	
								}else{
									$("#editInd2CB7").prop('checked', true );
								}
								
								if (!indicator2['8']){
									$("#editInd2CB8").prop('checked', false );	
								}else{
									$("#editInd2CB8").prop('checked', true );
								}
								
								if (!indicator2['9']){
									$("#editInd2CB9").prop('checked', false );	
								}else{
									$("#editInd2CB9").prop('checked', true );
								}						
								
								
								if (!subfields.a){
									$("#editSFCBa").prop('checked', false );	
								}else{
									$("#editSFCBa").prop('checked', true );
								}
								
								if(!subfields.b){
									$("#editSFCBb").prop('checked', false );									
								}else {
									$("#editSFCBb").prop('checked', true );
								}
								
								if(!subfields.c){
									$("#editSFCBc").prop('checked', false );									
								}else {
									$("#editSFCBc").prop('checked', true );
								}
								
								if(!subfields.d){
									$("#editSFCBd").prop('checked', false );									
								}else {
									$("#editSFCBd").prop('checked', true );
								}
								
								if(!subfields.e){
									$("#editSFCBe").prop('checked', false );									
								}else {
									$("#editSFCBe").prop('checked', true );
								}
								
								if(!subfields.f){
									$("#editSFCBf").prop('checked', false );									
								}else {
									$("#editSFCBf").prop('checked', true );
								}

								if(!subfields.g){
									$("#editSFCBg").prop('checked', false );									
								}else {
									$("#editSFCBg").prop('checked', true );
								}
								
								if(!subfields.h){
									$("#editSFCBh").prop('checked', false );									
								}else {
									$("#editSFCBh").prop('checked', true );
								}
								
								if(!subfields.i){
									$("#editSFCBi").prop('checked', false );									
								}else {
									$("#editSFCBi").prop('checked', true );
								}
								
								if(!subfields.j){
									$("#editSFCBj").prop('checked', false );									
								}else {
									$("#editSFCBj").prop('checked', true );
								}
								
								if(!subfields.k){
									$("#editSFCBk").prop('checked', false );									
								}else {
									$("#editSFCBk").prop('checked', true );
								}
								
								if(!subfields.l){
									$("#editSFCBl").prop('checked', false );									
								}else {
									$("#editSFCBl").prop('checked', true );
								}
								
								if(!subfields.m){
									$("#editSFCBm").prop('checked', false );									
								}else {
									$("#editSFCBm").prop('checked', true );
								}
								
								if(!subfields.n){
									$("#editSFCBn").prop('checked', false );									
								}else {
									$("#editSFCBn").prop('checked', true );
								}
								
								if(!subfields.o){
									$("#editSFCBo").prop('checked', false );									
								}else {
									$("#editSFCBo").prop('checked', true );
								}
								
								if(!subfields.p){
									$("#editSFCBp").prop('checked', false );									
								}else {
									$("#editSFCBp").prop('checked', true );
								}
								
								if(!subfields.q){
									$("#editSFCBq").prop('checked', false );									
								}else {
									$("#editSFCBq").prop('checked', true );
								}
								
								if(!subfields.r){
									$("#editSFCBr").prop('checked', false );									
								}else {
									$("#editSFCBr").prop('checked', true );
								}
								
								if(!subfields.s){
									$("#editSFCBs").prop('checked', false );									
								}else {
									$("#editSFCBs").prop('checked', true );
								}
								
								if(!subfields.t){
									$("#editSFCBt").prop('checked', false );									
								}else {
									$("#editSFCBt").prop('checked', true );
								}
								
								if(!subfields.u){
									$("#editSFCBu").prop('checked', false );									
								}else {
									$("#editSFCBu").prop('checked', true );
								}
								
								if(!subfields.v){
									$("#editSFCBv").prop('checked', false );									
								}else {
									$("#editSFCBv").prop('checked', true );
								}
								
								if(!subfields.w){
									$("#editSFCBw").prop('checked', false );									
								}else {
									$("#editSFCBw").prop('checked', true );
								}
								
								if(!subfields.x){
									$("#editSFCBx").prop('checked', false );									
								}else {
									$("#editSFCBx").prop('checked', true );
								}
								
								if(!subfields.y){
									$("#editSFCBy").prop('checked', false );									
								}else {
									$("#editSFCBy").prop('checked', true );
								}
								
								if(!subfields.z){
									$("#editSFCBz").prop('checked', false );									
								}else {
									$("#editSFCBz").prop('checked', true );
								}
								
								if(!subfields['0']){
									$("#editSFCB0").prop('checked', false );									
								}else {
									$("#editSFCB0").prop('checked', true );
								}
								
								if(!subfields['1']){
									$("#editSFCB1").prop('checked', false );									
								}else {
									$("#editSFCB1").prop('checked', true );
								}
								
								if(!subfields['2']){
									$("#editSFCB2").prop('checked', false );									
								}else {
									$("#editSFCB2").prop('checked', true );
								}
								
								if(!subfields['3']){
									$("#editSFCB3").prop('checked', false );									
								}else {
									$("#editSFCB3").prop('checked', true );
								}
								
								if(!subfields['4']){
									$("#editSFCB4").prop('checked', false );									
								}else {
									$("#editSFCB4").prop('checked', true );
								}
								
								if(!subfields['5']){
									$("#editSFCB5").prop('checked', false );									
								}else {
									$("#editSFCB5").prop('checked', true );
								}
								
								if(!subfields['6']){
									$("#editSFCB6").prop('checked', false );									
								}else {
									$("#editSFCB6").prop('checked', true );
								}
								
								if(!subfields['7']){
									$("#editSFCB7").prop('checked', false );									
								}else {
									$("#editSFCB7").prop('checked', true );
								}
								
								if(!subfields['8']){
									$("#editSFCB8").prop('checked', false );									
								}else {
									$("#editSFCB8").prop('checked', true );
								}
								
								if(!subfields['9']){
									$("#editSFCB9").prop('checked', false );									
								}else {
									$("#editSFCB9").prop('checked', true );
								}			
								
								$("#editInd1TFUndefine").val(indicator1['#']);
								$("#editInd1TF0").val(indicator1['0']);
								$("#editInd1TF1").val(indicator1['1']);
								$("#editInd1TF2").val(indicator1['2']);
								$("#editInd1TF3").val(indicator1['3']);
								$("#editInd1TF4").val(indicator1['4']);
								$("#editInd1TF5").val(indicator1['5']);
								$("#editInd1TF6").val(indicator1['6']);
								$("#editInd1TF7").val(indicator1['7']);
								$("#editInd1TF8").val(indicator1['8']);
								$("#editInd1TF9").val(indicator1['9']);
								
								$("#editInd2TFUndefine").val(indicator2['#']);
								$("#editInd2TF0").val(indicator2['0']);
								$("#editInd2TF1").val(indicator2['1']);
								$("#editInd2TF2").val(indicator2['2']);
								$("#editInd2TF3").val(indicator2['3']);
								$("#editInd2TF4").val(indicator2['4']);
								$("#editInd2TF5").val(indicator2['5']);
								$("#editInd2TF6").val(indicator2['6']);
								$("#editInd2TF7").val(indicator2['7']);
								$("#editInd2TF8").val(indicator2['8']);
								$("#editInd2TF9").val(indicator2['9']);
								
								$("#editSFTFa").val(subfields.a);
								$("#editSFTFb").val(subfields.b);
								$("#editSFTFc").val(subfields.c);
								$("#editSFTFd").val(subfields.d);
								$("#editSFTFe").val(subfields.e);
								$("#editSFTFf").val(subfields.f);
								$("#editSFTFg").val(subfields.g);
								$("#editSFTFh").val(subfields.h);
								$("#editSFTFi").val(subfields.i);
								$("#editSFTFj").val(subfields.j);
								$("#editSFTFk").val(subfields.k);
								$("#editSFTFl").val(subfields.l);
								$("#editSFTFm").val(subfields.m);
								$("#editSFTFn").val(subfields.n);
								$("#editSFTFo").val(subfields.o);
								$("#editSFTFp").val(subfields.p);
								$("#editSFTFq").val(subfields.q);
								$("#editSFTFr").val(subfields.r);
								$("#editSFTFs").val(subfields.s);
								$("#editSFTFt").val(subfields.t);
								$("#editSFTFu").val(subfields.u);
								$("#editSFTFv").val(subfields.v);
								$("#editSFTFw").val(subfields.w);
								$("#editSFTFx").val(subfields.x);
								$("#editSFTFy").val(subfields.y);
								$("#editSFTFz").val(subfields.z);
								
								$("#editSFTF0").val(subfields['0']);
								$("#editSFTF1").val(subfields['1']);
								$("#editSFTF2").val(subfields['2']);
								$("#editSFTF3").val(subfields['3']);
								$("#editSFTF4").val(subfields['4']);
								$("#editSFTF5").val(subfields['5']);
								$("#editSFTF6").val(subfields['6']);
								$("#editSFTF7").val(subfields['7']);
								$("#editSFTF8").val(subfields['8']);
								$("#editSFTF9").val(subfields['9']);
													  
							}
					 	}
					 );
				break;
			}
			
		});
		
		$('#editInd1CBUndefine').click(function() {
		    $('#editInd1TFUndefine').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB0').click(function() {
		    $('#editInd1TF0').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB1').click(function() {
		    $('#editInd1TF1').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB2').click(function() {
		    $('#editInd1TF2').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB3').click(function() {
		    $('#editInd1TF3').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB4').click(function() {
		    $('#editInd1TF4').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB5').click(function() {
		    $('#editInd1TF5').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB6').click(function() {
		    $('#editInd1TF6').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB7').click(function() {
		    $('#editInd1TF7').attr('disabled',! this.checked)
		});
		
		$('#editInd1CB8').click(function() {
		    $('#editInd1TF8').attr('disabled',! this.checked)
		});
		$('#editInd1CB9').click(function() {
		    $('#editInd1TF9').attr('disabled',! this.checked)
		});
		
		$('#editInd2CBUndefine').click(function() {
		    $('#editInd2TFUndefine').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB0').click(function() {
		    $('#editInd2TF0').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB1').click(function() {
		    $('#editInd2TF1').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB2').click(function() {
		    $('#editInd2TF2').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB3').click(function() {
		    $('#editInd2TF3').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB4').click(function() {
		    $('#editInd2TF4').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB5').click(function() {
		    $('#editInd2TF5').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB6').click(function() {
		    $('#editInd2TF6').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB7').click(function() {
		    $('#editInd2TF7').attr('disabled',! this.checked)
		});
		
		$('#editInd2CB8').click(function() {
		    $('#editInd2TF8').attr('disabled',! this.checked)
		});
		$('#editInd2CB9').click(function() {
		    $('#editInd2TF9').attr('disabled',! this.checked)
		});
		
		$('#editSFCBa').click(function() {
		    $('#editSFTFa').attr('disabled',! this.checked)
		});
		
		$('#editSFCBb').click(function() {
		    $('#editSFTFb').attr('disabled',! this.checked)
		});
		
		$('#editSFCBc').click(function() {
		    $('#editSFTFc').attr('disabled',! this.checked)
		});
		
		$('#editSFCBd').click(function() {
		    $('#editSFTFd').attr('disabled',! this.checked)
		});
		
		$('#editSFCBe').click(function() {
		    $('#editSFTFe').attr('disabled',! this.checked)
		});
		
		$('#editSFCBf').click(function() {
		    $('#editSFTFf').attr('disabled',! this.checked)
		});
		
		$('#editSFCBg').click(function() {
		    $('#editSFTFg').attr('disabled',! this.checked)
		});
		
		$('#editSFCBh').click(function() {
		    $('#editSFTFh').attr('disabled',! this.checked)
		});
		
		$('#editSFCBi').click(function() {
		    $('#editSFTFi').attr('disabled',! this.checked)
		});
		
		$('#editSFCBj').click(function() {
		    $('#editSFTFj').attr('disabled',! this.checked)
		});
		
		$('#editSFCBk').click(function() {
		    $('#editSFTFk').attr('disabled',! this.checked)
		});
		
		$('#editSFCBl').click(function() {
		    $('#editSFTFl').attr('disabled',! this.checked)
		});
		
		$('#editSFCBm').click(function() {
		    $('#editSFTFm').attr('disabled',! this.checked)
		});
		
		$('#editSFCBn').click(function() {
		    $('#editSFTFn').attr('disabled',! this.checked)
		});
		
		$('#editSFCBo').click(function() {
		    $('#editSFTFo').attr('disabled',! this.checked)
		});
		
		$('#editSFCBp').click(function() {
		    $('#editSFTFp').attr('disabled',! this.checked)
		});
		
		$('#editSFCBq').click(function() {
		    $('#editSFTFq').attr('disabled',! this.checked)
		});
		
		$('#editSFCBr').click(function() {
		    $('#editSFTFr').attr('disabled',! this.checked)
		});
		
		$('#editSFCBs').click(function() {
		    $('#editSFTFs').attr('disabled',! this.checked)
		});
		
		$('#editSFCBt').click(function() {
		    $('#editSFTFt').attr('disabled',! this.checked)
		});
		
		$('#editSFCBu').click(function() {
		    $('#editSFTFu').attr('disabled',! this.checked)
		});
		
		$('#editSFCBv').click(function() {
		    $('#editSFTFv').attr('disabled',! this.checked)
		});
		
		$('#editSFCBw').click(function() {
		    $('#editSFTFw').attr('disabled',! this.checked)
		});
		
		$('#editSFCBx').click(function() {
		    $('#editSFTFx').attr('disabled',! this.checked)
		});
		
		$('#editSFCBy').click(function() {
		    $('#editSFTFy').attr('disabled',! this.checked)
		});
		$('#editSFCBz').click(function() {
		    $('#editSFTFz').attr('disabled',! this.checked)
		});
		
		$('#editSFCB0').click(function() {
		    $('#editSFTF0').attr('disabled',! this.checked)
		});
		
		$('#editSFCB1').click(function() {
		    $('#editSFTF1').attr('disabled',! this.checked)
		});
		
		$('#editSFCB2').click(function() {
		    $('#editSFTF2').attr('disabled',! this.checked)
		});
		
		$('#editSFCB3').click(function() {
		    $('#editSFTF3').attr('disabled',! this.checked)
		});
		
		$('#editSFCB4').click(function() {
		    $('#editSFTF4').attr('disabled',! this.checked)
		});
		
		$('#editSFCB5').click(function() {
		    $('#editSFTF5').attr('disabled',! this.checked)
		});
		
		$('#editSFCB6').click(function() {
		    $('#editSFTF6').attr('disabled',! this.checked)
		});
		
		$('#editSFCB7').click(function() {
		    $('#editSFTF7').attr('disabled',! this.checked)
		});
		
		$('#editSFCB8').click(function() {
		    $('#editSFTF8').attr('disabled',! this.checked)
		});
		$('#editSFCB9').click(function() {
		    $('#editSFTF9').attr('disabled',! this.checked)
		});
		
	  $("#editOkBtn").click(function(){	  
		  
			 if($('#editInd1CBUndefine').is(':checked')){
					var ind1Undefine =  $('#editInd1TFUndefine').val();
			  }				
			 if($('#editInd1CB0').is(':checked')){
					var ind1Input0 =  $('#editInd1TF0').val();
			  }
			 if($('#editInd1CB1').is(':checked')){
					var ind1Input1 =  $('#editInd1TF1').val();
			  }
			 if($('#editInd1CB2').is(':checked')){
					var ind1Input2 =  $('#editInd1TF2').val();
			  }
			 if($('#editInd1CB3').is(':checked')){
					var ind1Input3 =  $('#editInd1TF3').val();
			  }
			 if($('#editInd1CB4').is(':checked')){
					var ind1Input4 =  $('#editInd1TF4').val();
			  }
			 if($('#editInd1CB5').is(':checked')){
					var ind1Input5 =  $('#editInd1TF5').val();
			  }
			 if($('#editInd1CB6').is(':checked')){
					var ind1Input6 =  $('#editInd1TF6').val();
			  }
			 if($('#editInd1CB7').is(':checked')){
					var ind1Input7 =  $('#editInd1TF7').val();
			  }
			 if($('#editInd1CB8').is(':checked')){
					var ind1Input8 =  $('#editInd1TF8').val();
			  }
			 if($('#editInd1CB9').is(':checked')){
					var ind1Input9 =  $('#editInd1TF9').val();
			  }
			 if($('#editInd2CBUndefine').is(':checked')){
					var ind2Undefine =  $('#editInd2TFUndefine').val();
			  }				
			 if($('#editInd2CB0').is(':checked')){
					var ind2Input0 =  $('#editInd2TF0').val();
			  }
			 if($('#editInd2CB1').is(':checked')){
					var ind2Input1 =  $('#editInd2TF1').val();
			  }
			 if($('#editInd2CB2').is(':checked')){
					var ind2Input2 =  $('#editInd2TF2').val();
			  }
			 if($('#editInd2CB3').is(':checked')){
					var ind2Input3 =  $('#editInd2TF3').val();
			  }
			 if($('#editInd2CB4').is(':checked')){
					var ind2Input4 =  $('#editInd2TF4').val();
			  }
			 if($('#editInd2CB5').is(':checked')){
					var ind2Input5 =  $('#editInd2TF5').val();
			  }
			 if($('#editInd2CB6').is(':checked')){
					var ind2Input6 =  $('#editInd2TF6').val();
			  }
			 if($('#editInd2CB7').is(':checked')){
					var ind2Input7 =  $('#editInd2TF7').val();
			  }
			 if($('#editInd2CB8').is(':checked')){
					var ind2Input8 =  $('#editInd2TF8').val();
			  }
			 if($('#editInd2CB9').is(':checked')){
					var ind2Input9 =  $('#editInd2TF9').val();
			  }
		  
		  if($('#editSFCBa').is(':checked')){
				var sfInputA =  $('#editSFTFa').val();
		  }
		  
		  if($('#editSFCBb').is(':checked')){
			  var sfInputB =  $('#editSFTFb').val();
		  }
		  if($('#editSFCBc').is(':checked')){
			  var sfInputC =  $('#editSFTFc').val();
		  }
		  
		  if($('#editSFCBd').is(':checked')){
			  var sfInputD =  $('#editSFTFd').val();
		  }
		  if($('#editSFCBe').is(':checked')){
			  var sfInputE =  $('#editSFTFe').val();
		  }
		  
		  if($('#editSFCBf').is(':checked')){
			  var sfInputF =  $('#editSFTFf').val();
		  }
		  if($('#editSFCBg').is(':checked')){
			  var sfInputG =  $('#editSFTFg').val();
		  }
		  
		  if($('#editSFCBh').is(':checked')){
			  var sfInputH =  $('#editSFTFh').val();
		  }
		  if($('#editSFCBi').is(':checked')){
			  var sfInputI =  $('#editSFTFi').val();
		  }
		  
		  if($('#editSFCBj').is(':checked')){
			  var sfInputJ =  $('#editSFTFj').val();
		  }
		  if($('#editSFCBk').is(':checked')){
			  var sfInputK =  $('#editSFTFk').val();
		  }
		  
		  if($('#editSFCBl').is(':checked')){
			  var sfInputL =  $('#editSFTFl').val();
		  }
		  if($('#editSFCBm').is(':checked')){
			  var sfInputM =  $('#editSFTFm').val();
		  }
		  
		  if($('#editSFCBn').is(':checked')){
			  var sfInputN =  $('#editSFTFn').val();
		  }
		  if($('#editSFCBo').is(':checked')){
			  var sfInputO =  $('#editSFTFo').val();
		  }
		  
		  if($('#editSFCBp').is(':checked')){
			  var sfInputP =  $('#editSFTFp').val();
		  }
		  if($('#editSFCBq').is(':checked')){
			  var sfInputQ =  $('#editSFTFq').val();
		  }
		  if($('#editSFCBr').is(':checked')){
			  var sfInputR =  $('#editSFTFr').val();
		  }
		  
		  if($('#editSFCBs').is(':checked')){
			  var sfInputS =  $('#editSFTFs').val();
		  }
		  if($('#editSFCBt').is(':checked')){
			  var sfInputT =  $('#editSFTFt').val();
		  }
		  
		  if($('#editSFCBu').is(':checked')){
			  var sfInputU =  $('#editSFTFu').val();
		  }
		  if($('#editSFCBv').is(':checked')){
			  var sfInputV =  $('#editSFTFv').val();
		  }
		  
		  if($('#editSFCBw').is(':checked')){
			  var sfInputW =  $('#editSFTFw').val();
		  }
		  if($('#editSFCBx').is(':checked')){
			  var sfInputX =  $('#editSFTFx').val();
		  }
		  
		  if($('#editSFCBy').is(':checked')){
			  var sfInputY =  $('#editSFTFy').val();
		  }
		  if($('#editSFCBz').is(':checked')){
			  var sfInputZ =  $('#editSFTFz').val();
		  }		  

		  if($('#editSFCB0').is(':checked')){
			  var sfInput0 =  $('#editSFTF0').val();
		  }
		  
		  if($('#editSFCB1').is(':checked')){
			  var sfInput1 =  $('#editSFTF1').val();
		  }
		  if($('#editSFCB2').is(':checked')){
			  var sfInput2 =  $('#editSFTF2').val();
		  }
		  
		  if($('#editSFCB3').is(':checked')){
			  var sfInput3 =  $('#editSFTF3').val();
		  }
		  if($('#editSFCB4').is(':checked')){
			  var sfInput4 =  $('#editSFTF4').val();
		  }
		  
		  if($('#editSFCB5').is(':checked')){
			  var sfInput5 =  $('#editSFTF5').val();
		  }
		  if($('#editSFCB6').is(':checked')){
			  var sfInput6 =  $('#editSFTF6').val();
		  }
		  
		  if($('#editSFCB7').is(':checked')){
			  var sfInput7 =  $('#editSFTF7').val();
		  }
		  if($('#editSFCB8').is(':checked')){
			  var sfInput8 =  $('#editSFTF8').val();
		  }		
		  if($('#editSFCB9').is(':checked')){
			  var sfInput9 =  $('#editSFTF9').val();
		  }		
			$.ajax({
				url : "EditTag",
				data: {marc: $("#editMARC").val(), tagNo: $("#editTag").val(), desc: $("#editDesc").val(), abbreDesc: $("#editAbbreDesc").val(),
				acronym: $("#editAcronym").val(), authorityGroup: $("#editAuthorityGroup").val(),fieldLength: $("#editFieldLength").val(),
				indexLanguage: $("#editIndexLanguage").val(), indicator1: $("#editInd1").val(), indicator2: $("#editInd2").val(), defaultValue: $("#editDefaultValue").val(), remark: $("#editRemark").val(),
				repeatable: $('#editRepeatableCheck').prop('checked'), mandatory: $("#editMandatoryCheck").prop('checked'), indexFlag: $("#editIndexFlagCheck").prop('checked'),
				keyword: $("#editKeywordCheck").prop('checked'), duplicateCheck: $("#editDuplicateCheck").prop('checked'), authorityFlag: $("#editAuthorityFlagCheck").prop('checked'),
				copyPaste: $("#editCopyPasteCheck").prop('checked'), paramLink: $("#editParamLinkCheck").prop('checked'), multimediaTag: $("#editMultimediaTagCheck").prop('checked'),
				ind1Undefine: ind1Undefine , ind1Input0: ind1Input0, ind1Input1: ind1Input1, ind1Input2: ind1Input2, ind1Input3: ind1Input3,
				ind1Input4: ind1Input4, ind1Input5: ind1Input5, ind1Input6: ind1Input6, ind1Input7: ind1Input7, ind1Input8: ind1Input8,
				ind1Input9: ind1Input9, ind2Undefine: ind2Undefine, ind2Input0: ind2Input0, ind2Input1: ind2Input1, ind2Input2: ind2Input2, ind2Input3: ind2Input3,
				ind2Input4: ind2Input4, ind2Input5: ind2Input5, ind2Input6: ind2Input6, ind2Input7: ind2Input7, ind2Input8: ind2Input8,
				ind2Input9: ind2Input9, sfA: sfInputA, sfB: sfInputB, sfC: sfInputC, sfD: sfInputD, sfE: sfInputE, sfF: sfInputF, sfG: sfInputG, sfH: sfInputH, sfI: sfInputI, sfJ: sfInputJ, 
				sfK: sfInputK, sfL: sfInputL, sfM: sfInputM, sfN: sfInputN, sfO: sfInputO, sfP: sfInputP, sfQ: sfInputQ, sfR: sfInputR, sfS: sfInputS, sfT: sfInputT, sfU: sfInputU, sfV: sfInputV, 
				sfW: sfInputW, sfX: sfInputX, sfY: sfInputY, sfZ: sfInputZ, sf0: sfInput0, sf1: sfInput1, sf2: sfInput2, sf3: sfInput3, sf4: sfInput4, sf5: sfInput5, sf6: sfInput6, sf7: sfInput7, sf8: sfInput8, sf9: sfInput9},
				success : function(data) {
					swal({
						text: "Edit Successfully",
						showCancelButton : true,
						cancelButtonColor : '#d33',
						cancelButtonText : 'Ok',
						cancelButtonClass : 'cancel-class',
						closeOnCancel : false
					});
					
					$("#tagParameter_table").DataTable().ajax.reload();

					}
				});			

	  });    

	$('#viewModal').on('show.bs.modal', function (event) {
			
			var button = $(event.relatedTarget) // Button that triggered the modal
			var modal = $(this);
			var recipient = button.data('whatever'); // Extract info from data-* attributes
			var marc = button.data('marc');
			var tagNo = button.data('tag');
			var desc = button.data('desc');	
			
			modal.find('.modal-title').text(recipient);
			
			$("#viewMARC").val(marc);
			$("#viewTag").val(tagNo);
			$("#viewDesc").val(desc);
			$("#viewAbbreDesc").val(desc);
			
			switch(recipient){
				case state = 'Add Tag':
				break;
				case state = 'View Tag':
					$.get('ViewTag', {
						marc : marc, tagNo : tagNo
					 	}, 
					 	function(data) {
							if(data != null) {
								
								var params = {};
								var indicator1 ={};
								var indicator2 ={};
								var subfields = {};

								data.params.forEach(function(item) {
									
									  $("#viewIndexTable").val(item.indexTable);
									  $("#viewAcronym").val(item.acronym);
									  $("#viewAuthorityGroup").val(item.authorityGroup);
									  $("#viewFieldLength").val(item.fieldLength);
									  $("#viewIndexLanguage").val(item.indexLength);
									  $("#viewDefaultValue").val(item.defaultValue); 
									  $("#viewRemark").val(item.remark);
											
									  
									  $("#viewInd1").val(item.defaultIndicator1);
									  $("#viewInd2").val(item.defaultIndicator2);
									  
									if(item.repeatable=='Y'){
										$("#viewRepeatableCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewRepeatableCheck").prop('checked', false );
									}
									
									if(item.mandatory=='Y'){
										$("#viewMandatoryCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewMandatoryCheck").prop('checked', false );
									}
									
									if(item.indexFlag=='Y'){
										$("#viewIndexFlagCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewIndexFlagCheck").prop('checked', false );
									}
									
									if(item.keyword=='Y'){
										$("#viewKeywordCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewKeywordCheck").prop('checked', false );
									}
									
									if(item.duplicateCheck=='Y'){
										$("#viewDuplicateCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewDuplicateCheck").prop('checked', false );
									}
									
									if(item.authorityCheck=='Y'){
										$("#viewAuthorityFlagCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewAuthorityFlagCheck").prop('checked', false );
									}
									
									if(item.copyPaste=='Y'){
										$("#viewCopyPasteCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewCopyPasteCheck").prop('checked', false );
									}
									
									if(item.paraLink=='Y'){
										$("#viewParamLinkCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewParamLinkCheck").prop('checked', false );
									}
									
									if(item.multimediaTag=='Y'){
										$("#viewMultimediaTagCheck").prop('checked', true );
									}else if (item.repeatable=='N'){
										$("#viewMultimediaTagCheck").prop('checked', false );
									}
										});
								
								data.indicator1.forEach(function(item) {
									indicator1[item.code] = item.desc;
								});
								
								data.indicator2.forEach(function(item) {
									indicator2[item.code] = item.desc;
								});
										
								data.subfields.forEach(function(item) {
									subfields[item.code] = item.desc;
								});
							
								if (!indicator1['#']){
									$("#viewInd1CBUndefine").prop('checked', false );	
								}else{
									$("#viewInd1CBUndefine").prop('checked', true );
								}
								
								if (!indicator1['0']){
									$("#viewInd1CB0").prop('checked', false );	
								}else{
									$("#viewInd1CB0").prop('checked', true );
								}
								
								if (!indicator1['1']){
									$("#viewInd1CB1").prop('checked', false );	
								}else{
									$("#viewInd1CB1").prop('checked', true );
								}
								
								if (!indicator1['2']){
									$("#viewInd1CB2").prop('checked', false );	
								}else{
									$("#viewInd1CB2").prop('checked', true );
								}
								
								if (!indicator1['3']){
									$("#viewInd1CB3").prop('checked', false );	
								}else{
									$("#viewInd1CB3").prop('checked', true );
								}
								
								if (!indicator1['4']){
									$("#viewInd1CB4").prop('checked', false );	
								}else{
									$("#viewInd1CB4").prop('checked', true );
								}
								
								if (!indicator1['5']){
									$("#viewInd1CB5").prop('checked', false );	
								}else{
									$("#viewInd1CB5").prop('checked', true );
								}
								
								if (!indicator1['6']){
									$("#viewInd1CB6").prop('checked', false );	
								}else{
									$("#viewInd1CB6").prop('checked', true );
								}
								
								if (!indicator1['7']){
									$("#viewInd1CB7").prop('checked', false );	
								}else{
									$("#viewInd1CB7").prop('checked', true );
								}
								
								if (!indicator1['8']){
									$("#viewInd1CB8").prop('checked', false );	
								}else{
									$("#viewInd1CB8").prop('checked', true );
								}
								
								if (!indicator1['9']){
									$("#viewInd1CB9").prop('checked', false );	
								}else{
									$("#viewInd1CB9").prop('checked', true );
								}
								
								if (!indicator2['#']){
									$("#viewInd2CBUndefine").prop('checked', false );	
								}else{
									$("#viewInd2CBUndefine").prop('checked', true );
								}
								
								if (!indicator2['0']){
									$("#viewInd2CB0").prop('checked', false );	
								}else{
									$("#viewInd2CB0").prop('checked', true );
								}
								
								if (!indicator2['1']){
									$("#viewInd2CB1").prop('checked', false );	
								}else{
									$("#viewInd2CB1").prop('checked', true );
								}
								
								if (!indicator2['2']){
									$("#viewInd2CB2").prop('checked', false );	
								}else{
									$("#viewInd2CB2").prop('checked', true );
								}
								
								if (!indicator2['3']){
									$("#viewInd2CB3").prop('checked', false );	
								}else{
									$("#viewInd2CB3").prop('checked', true );
								}
								
								if (!indicator2['4']){
									$("#viewInd2CB4").prop('checked', false );	
								}else{
									$("#viewInd2CB4").prop('checked', true );
								}
								
								if (!indicator2['5']){
									$("#viewInd2CB5").prop('checked', false );	
								}else{
									$("#viewInd2CB5").prop('checked', true );
								}
								
								if (!indicator2['6']){
									$("#viewInd2CB6").prop('checked', false );	
								}else{
									$("#viewInd2CB6").prop('checked', true );
								}
								
								if (!indicator2['7']){
									$("#viewInd2CB7").prop('checked', false );	
								}else{
									$("#viewInd2CB7").prop('checked', true );
								}
								
								if (!indicator2['8']){
									$("#viewInd2CB8").prop('checked', false );	
								}else{
									$("#viewInd2CB8").prop('checked', true );
								}
								
								if (!indicator2['9']){
									$("#viewInd2CB9").prop('checked', false );	
								}else{
									$("#viewInd2CB9").prop('checked', true );
								}
								
								
																
								if (!subfields.a){
									$("#viewSFCBa").prop('checked', false );	
								}else{
									$("#viewSFCBa").prop('checked', true );
								}
								
								if(!subfields.b){
									$("#viewSFCBb").prop('checked', false );									
								}else {
									$("#viewSFCBb").prop('checked', true );
								}
								
								if(!subfields.c){
									$("#viewSFCBc").prop('checked', false );									
								}else {
									$("#viewSFCBc").prop('checked', true );
								}
								
								if(!subfields.d){
									$("#viewSFCBd").prop('checked', false );									
								}else {
									$("#viewSFCBd").prop('checked', true );
								}
								
								if(!subfields.e){
									$("#viewSFCBe").prop('checked', false );									
								}else {
									$("#viewSFCBe").prop('checked', true );
								}
								
								if(!subfields.f){
									$("#viewSFCBf").prop('checked', false );									
								}else {
									$("#viewSFCBf").prop('checked', true );
								}

								if(!subfields.g){
									$("#viewSFCBg").prop('checked', false );									
								}else {
									$("#viewSFCBg").prop('checked', true );
								}
								
								if(!subfields.h){
									$("#viewSFCBh").prop('checked', false );									
								}else {
									$("#viewSFCBh").prop('checked', true );
								}
								
								if(!subfields.i){
									$("#viewSFCBi").prop('checked', false );									
								}else {
									$("#viewSFCBi").prop('checked', true );
								}
								
								if(!subfields.j){
									$("#viewSFCBj").prop('checked', false );									
								}else {
									$("#viewSFCBj").prop('checked', true );
								}
								
								if(!subfields.k){
									$("#viewSFCBk").prop('checked', false );									
								}else {
									$("#viewSFCBk").prop('checked', true );
								}
								
								if(!subfields.l){
									$("#viewSFCBl").prop('checked', false );									
								}else {
									$("#viewSFCBl").prop('checked', true );
								}
								
								if(!subfields.m){
									$("#viewSFCBm").prop('checked', false );									
								}else {
									$("#viewSFCBm").prop('checked', true );
								}
								
								if(!subfields.n){
									$("#viewSFCBn").prop('checked', false );									
								}else {
									$("#viewSFCBn").prop('checked', true );
								}
								
								if(!subfields.o){
									$("#viewSFCBo").prop('checked', false );									
								}else {
									$("#viewSFCBo").prop('checked', true );
								}
								
								if(!subfields.p){
									$("#viewSFCBp").prop('checked', false );									
								}else {
									$("#viewSFCBp").prop('checked', true );
								}
								
								if(!subfields.q){
									$("#viewSFCBq").prop('checked', false );									
								}else {
									$("#viewSFCBq").prop('checked', true );
								}
								
								if(!subfields.r){
									$("#viewSFCBr").prop('checked', false );									
								}else {
									$("#viewSFCBr").prop('checked', true );
								}
								
								if(!subfields.s){
									$("#viewSFCBs").prop('checked', false );									
								}else {
									$("#viewSFCBs").prop('checked', true );
								}
								
								if(!subfields.t){
									$("#viewSFCBt").prop('checked', false );									
								}else {
									$("#viewSFCBt").prop('checked', true );
								}
								
								if(!subfields.u){
									$("#viewSFCBu").prop('checked', false );									
								}else {
									$("#viewSFCBu").prop('checked', true );
								}
								
								if(!subfields.v){
									$("#viewSFCBv").prop('checked', false );									
								}else {
									$("#viewSFCBv").prop('checked', true );
								}
								
								if(!subfields.w){
									$("#viewSFCBw").prop('checked', false );									
								}else {
									$("#viewSFCBw").prop('checked', true );
								}
								
								if(!subfields.x){
									$("#viewSFCBx").prop('checked', false );									
								}else {
									$("#viewSFCBx").prop('checked', true );
								}
								
								if(!subfields.y){
									$("#viewSFCBy").prop('checked', false );									
								}else {
									$("#viewSFCBy").prop('checked', true );
								}
								
								if(!subfields.z){
									$("#viewSFCBz").prop('checked', false );									
								}else {
									$("#viewSFCBz").prop('checked', true );
								}
								
								if(!subfields['0']){
									$("#viewSFCB0").prop('checked', false );									
								}else {
									$("#viewSFCB0").prop('checked', true );
								}
								
								if(!subfields['1']){
									$("#viewSFCB1").prop('checked', false );									
								}else {
									$("#viewSFCB1").prop('checked', true );
								}
								
								if(!subfields['2']){
									$("#viewSFCB2").prop('checked', false );									
								}else {
									$("#viewSFCB2").prop('checked', true );
								}
								
								if(!subfields['3']){
									$("#viewSFCB3").prop('checked', false );									
								}else {
									$("#viewSFCB3").prop('checked', true );
								}
								
								if(!subfields['4']){
									$("#viewSFCB4").prop('checked', false );									
								}else {
									$("#viewSFCB4").prop('checked', true );
								}
								
								if(!subfields['5']){
									$("#viewSFCB5").prop('checked', false );									
								}else {
									$("#viewSFCB5").prop('checked', true );
								}
								
								if(!subfields['6']){
									$("#viewSFCB6").prop('checked', false );									
								}else {
									$("#viewSFCB6").prop('checked', true );
								}
								
								if(!subfields['7']){
									$("#viewSFCB7").prop('checked', false );									
								}else {
									$("#viewSFCB7").prop('checked', true );
								}
								
								if(!subfields['8']){
									$("#viewSFCB8").prop('checked', false );									
								}else {
									$("#viewSFCB8").prop('checked', true );
								}
								
								if(!subfields['9']){
									$("#viewSFCB9").prop('checked', false );									
								}else {
									$("#viewSFCB9").prop('checked', true );
								}		
								
								$("#viewInd1TFUndefine").val(indicator1['#']);
								$("#viewInd1TF0").val(indicator1['0']);
								$("#viewInd1TF1").val(indicator1['1']);
								$("#viewInd1TF2").val(indicator1['2']);
								$("#viewInd1TF3").val(indicator1['3']);
								$("#viewInd1TF4").val(indicator1['4']);
								$("#viewInd1TF5").val(indicator1['5']);
								$("#viewInd1TF6").val(indicator1['6']);
								$("#viewInd1TF7").val(indicator1['7']);
								$("#viewInd1TF8").val(indicator1['8']);
								$("#viewInd1TF9").val(indicator1['9']);
								
								$("#viewInd2TFUndefine").val(indicator2['#']);
								$("#viewInd2TF0").val(indicator2['0']);
								$("#viewInd2TF1").val(indicator2['1']);
								$("#viewInd2TF2").val(indicator2['2']);
								$("#viewInd2TF3").val(indicator2['3']);
								$("#viewInd2TF4").val(indicator2['4']);
								$("#viewInd2TF5").val(indicator2['5']);
								$("#viewInd2TF6").val(indicator2['6']);
								$("#viewInd2TF7").val(indicator2['7']);
								$("#viewInd2TF8").val(indicator2['8']);
								$("#viewInd2TF9").val(indicator2['9']);
								
								$("#viewSFTFa").val(subfields.a);
								$("#viewSFTFb").val(subfields.b);
								$("#viewSFTFc").val(subfields.c);
								$("#viewSFTFd").val(subfields.d);
								$("#viewSFTFe").val(subfields.e);
								$("#viewSFTFf").val(subfields.f);
								$("#viewSFTFg").val(subfields.g);
								$("#viewSFTFh").val(subfields.h);
								$("#viewSFTFi").val(subfields.i);
								$("#viewSFTFj").val(subfields.j);
								$("#viewSFTFk").val(subfields.k);
								$("#viewSFTFl").val(subfields.l);
								$("#viewSFTFm").val(subfields.m);
								$("#viewSFTFn").val(subfields.n);
								$("#viewSFTFo").val(subfields.o);
								$("#viewSFTFp").val(subfields.p);
								$("#viewSFTFq").val(subfields.q);
								$("#viewSFTFr").val(subfields.r);
								$("#viewSFTFs").val(subfields.s);
								$("#viewSFTFt").val(subfields.t);
								$("#viewSFTFu").val(subfields.u);
								$("#viewSFTFv").val(subfields.v);
								$("#viewSFTFw").val(subfields.w);
								$("#viewSFTFx").val(subfields.x);
								$("#viewSFTFy").val(subfields.y);
								$("#viewSFTFz").val(subfields.z);
								
								$("#viewSFTF0").val(subfields['0']);
								$("#viewSFTF1").val(subfields['1']);
								$("#viewSFTF2").val(subfields['2']);
								$("#viewSFTF3").val(subfields['3']);
								$("#viewSFTF4").val(subfields['4']);
								$("#viewSFTF5").val(subfields['5']);
								$("#viewSFTF6").val(subfields['6']);
								$("#viewSFTF7").val(subfields['7']);
								$("#viewSFTF8").val(subfields['8']);
								$("#viewSFTF9").val(subfields['9']);

							}
					 	}
					 );
				break;
			}
			
		});
	  
		$('#tagParameter_table').on('click', '#Delete', function () {
			var deleteSelectedTag = $(this).attr('data-id');

			var index = $('#tagParameter_table').DataTable().rows({ search: 'applied'})
			.nodes().to$().index($(this).closest('tr'));
			
			swal({
				text: "Are you sure want to delete?",
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : 'Yes',
				cancelButtonText : 'No',
				confirmButtonClass : 'confirm-class',
				cancelButtonClass : 'cancel-class',
				closeOnConfirm : false,
				closeOnCancel : false
			}).then(
				function() {
					$.ajax({
						url : "Handler_DeleteTag", 
						data: { TagNo: deleteSelectedTag},
							success : function(result) {
								var status = result.replace(/\s+/g, '');
								if (status == "ok") {
									messageBox("005","Deleted","@action");
									$('.swal2-confirm').click(
										function() {
											$('#tagParameter_table').DataTable().row(index).remove().draw();
									});
								}else{
		     						swal("Unsuccessfully");
		     					}
							}
						});
				}, function(dismiss) {
					if (dismiss == 'cancel') {
						swal('', 'Cancelled');
					}
			})
		});
	});
