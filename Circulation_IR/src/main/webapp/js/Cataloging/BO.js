/**
 * 
 */

 
	   
var FORM_HAS_CHANGED = false;

/*$('#mybutton').click(function() {
    if (FORM_HAS_CHANGED) {
        // The form has changed
    }
});
*/

$("input, select").change(function(){
    FORM_HAS_CHANGED = true;
});


//Select media record in the dropdown list
$(".replaceIndi").click(function(){
	var indi1 = $( ".indi1Help option:selected" ).val();
	var indi2 = $( ".indi2Help option:selected" ).val();
	$('.indiValues1').val(indi1);
	$('.indiValues2').val(indi2);
  });

//Select media record in the dropdown list
$(".concatData").focusout(function(){
	
	var data = $(this).val();
	var tag = $("#tagValue").text();
	var stringArray = data.split(" ");
	var subfield = $(".subfields").text().trim();
	var subfArray = subfield.split("\n");
	for(var j = 0; j < subfArray.length; j++)
	{
		if(subfArray[j]=="a"){
			for(var i = 0; i < stringArray.length; i++)
			{
				  $.get("Handler_NonFilling",{tag:tag, data:stringArray[0]},function(stopword_length){
					  if(stopword_length.trim()!=''){
						  $('input[name^="indi2_"]').each(function (event) {

								  var no = this.name.replace("indi2_", "");
							
								  var selector = "indi2_" + no;
								  document.getElementsByName(selector)[0].value = stopword_length.trim();
								  //$("[name="+selector+"]").value(stopword_length.trim());
								  //$( "input[name='"+selector+"']" ).value(stopword_length.trim());
		

							}); 
				
					  }
				  })
			}
		}
	}
	
	$('#BOIndi').modal('hide');
      
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
	 
	 
	 
	//Change status accordingly 
	if($(".Cstatus").text()=="New" || $(".Cstatus").text()=="New Record"){
		$("li").removeClass("saveIndexBO");
		$("li").removeClass("saveBO");
		$("li").removeClass("addTagBO");
	}else if($(".Cstatus").text()=="Indexed" ||$(".Cstatus").text()=="Record Indexed"){
		 $("#addTag").attr("disabled","disabled");
		$("li").removeClass("unindexBO");
		//$("li").removeClass("addTagBO");
		$("li").removeClass("duplicate");
		$('#BibRcrd').find('textarea, button, select, a').attr('disabled','disabled');
		//$('#BibRcrd').find('input, textarea, button, select, a').attr('disabled','disabled');
	}else if($(".Cstatus").text()=="Buffer Record"){
		$("li").removeClass("modifyBO");
		$("li").removeClass("deleteBO");
		$("li").removeClass("duplicate");
		$('#BibRcrd').find('textarea, button, select, a').attr('disabled','disabled');
		//$('#BibRcrd').find('input, textarea, button, select, a').attr('disabled','disabled');
	}else if ($(".Cstatus").text()=="Record Unindexed"){
		//alert("dsss");
		 $('#BibRcrd').find('textarea, button, select, a').removeAttr("disabled");
		  
	}else if ($(".Cstatus").text()=="Record Saved and Indexed"){
		$("li").removeClass("unindexBO");
	}
	
	$('#ctrlTagsForm').on('hidden.bs.modal', function () {
		  $(this).removeData('bs.modal');
		});
	
	//Navigate control tag modal 
	function ctrlTags(ctrltag){
			  var ctrlTag =  $(ctrltag).attr("value");
			  var ctrlID =  $(ctrltag).data("id");

			  if(ctrlTag!="000"){
		      var data = 'ctrlTag='+ ctrlTag +"&ctrlID=" + ctrlID;
		           $.ajax({
		              type: "GET",
		              url: "Control_Fields",
		              data: data,
		              success: function(msg){
		            	   $("#ctrlTagDisplay").html(msg);
		              }
		          }); 
			  }else{
				  var data = 'ctrlTag='+ ctrlTag +"&ctrlID=" + ctrlID;
		           $.ajax({
		              type: "GET",
		              url: "Control_Fields",
		              data: data,
		              success: function(msg){
		            	   $("#ctrlTagDisplay").html(msg);
		            	   var data = 'ctrlTagCodes='+ ctrlTag;
		                   $.ajax({
		                      type: "GET",
		                      url: "Media_Record",
		                      data: data,
		                      success: function(msg){
		                    	
		                    	  //$("#ctrlTagCode").show();
		                           $("#ctrlTagCode").html(msg);

		                      }
		                  }); 
		              }
		          }); 
			  }
		  }
	

//Navigate to add tag modal 
$("a#addTag").click(function() {

	 /*  if($("#addTag").attr("disabled")=="disabled")
       {
		   //$(this).css({'cursor' :" not-allowed"});
           e.preventDefault();    
       }    */  

		var tags = [];
	  $('#BibRcrd').find('tbody').each(function(index){
		
		  var CT04TAG = $(this).find("td.tagValue").text();
		  tags.push(CT04TAG.trim());
	  });
	
		
	  $.get("AddNewTplInfo",{action:"templateTag",GL17TAG:tags.toString()},function(GL17TAG){
		  $("#modalTag").html(GL17TAG);
	  });
});

//Save subfields
$("a#saveSubfields").click(function() {
	var exist = true;
	var tag = $(this).data("id");
	
	//Check for indicator1 and replace in main page
	$('input[name^="indi1_"]').each(function (event) {
		 var indi1 = $('input[name^="indi1_').map(function() {
			
						return this.value; 
			 			}).get().join('');
		  var no = this.name.replace("indi1_", "");
		
		  var selector = "indi1_" + no;
		  document.getElementById(selector).value = indi1;

	}); 
	
	//Check for indicator2 and replace in main page
	$('input[name^="indi2_"]').each(function (event) {
		 var indi2 = $('input[name^="indi2_').map(function() {
			
						return this.value; 
			 			}).get().join('');
		  var no = this.name.replace("indi2_", "");
		
		  var selector = "indi2_" + no;
		  document.getElementById(selector).value = indi2;

	}); 

	//Check for raw data and replace in main page
		$('input[name^="tag_"]').each(function (event) {
			var id;
			 var subfArray = [];
			 var allvals = $('input[name^="tag_').map(function() {
				 
				 			//Get value of subfields if the symbol "|"
							 if(this.value==null||this.value==""){
									id="";
								}else{
									id =$(this).attr("id");
								}

				 			//Check if the first char for each data is pipe
							 var allvals1 = (this.value).charAt(0);
							 var newValue="";
							 
							 //If it is pipe take orginal subf, if not take the data
							 if(allvals1=="|"){
								 newValue = (this.value).substring(2, (this.value).length);
								}else{
									newValue=this.value;
								}
							 
							 var raw = id+newValue;
							 
							 //Get character after the pipe
							 var pos = raw.indexOf("|");
							
							 while(pos > -1) {
								 var newRaw = raw.replace(raw.charAt(pos+1), raw.charAt(pos+1).toLowerCase());
							     subfArray.push(raw.charAt(pos+1).toLowerCase());
								 pos = raw.indexOf("|", pos+1);
							 }
							
				 				return newRaw;
				 				

				 			}).get().join('');
			 
	
			 
		/* 	 //Check for valid subfields before replacing
			  $.get("Handler_BOValidation",{validation:"SubfieldValidation", GL23TAG:tag},function(GL23SUBF){
				 
				  var newGL23SUBF = GL23SUBF.trim().split("\n");
				  $.each(newGL23SUBF, function(k){
					  alert(subfArray + newGL23SUBF[k].trim());
					  if(subfArray.indexOf(newGL23SUBF[k].trim())>=0){
						  exist = true;
					  }else{
						  exist = false;
					  }
					  return exist;
				  }); 
				  
				  if(exist=="true"){
					  alert("ss");
				  }else{
					  alert("fails");
				  }
			  }); */
			 
			 //Replace the subfield and data at the main page
			  var no = this.name.replace("tag_", "");
			  var selector = "#tag_" + no;
			  $(selector).html(allvals);
			  /*$('#BibRcrd').find('input, textarea, button, select, a').attr('disabled',"");*/
		   });
		
		 $("#viewSubfields").modal("hide");
		   e.stopPropagation();
		});
		

//Add new subfields
$("a#addNewSubf").click(function() {
	var arraySubf = [];
	$("#subfTable tr.primarySubf").each(function(){
		 var value = $(this).find("td.subfields").text();
		 arraySubf.push(value.trim());
	});
		var GL23TAG = $("#tagValue").text();
		var createKey = $(this).data("tagid");
		$.get("BO_AjaxHandler",{subfields:arraySubf.toString(), GL23TAG:GL23TAG, createKey:createKey},function(data_title){
		$("#modalSubf").html(data_title);
		   });	
		
	
});

$("#submits").click(function() {
	alert("sddds12");
$get("Uploads", function(){
	alert("sds");
})
});
$("#convert").click(function() {
	 var fileName = null;
	 if( $('#div_MARC').css('display') == "block") {
		 var name = document.getElementById('thefile');
		 fileName = name.files.item(0).name;
	 }else{
		fileName =  $("#filename2").text();
	 }
	 
	var scriptType = $("#script").val();
	
	 $.get("Handler_Paramips",{action:"1",fileName:fileName,script:scriptType},function(authlink){
		 if(authlink.trim()=="converted"){
			 var elem = document.getElementById("myBar"); 
			    var width = 0;
			    var id = setInterval(frame, 0);
			    function frame() {
			        if (width >= 66) {
			            clearInterval(id);
			            $.get("Handler_Paramips",{action:"2",fileName:fileName,script:scriptType},function(authlink){
							$.get("Handler_ParamipsTable",function(list){
								 var elem = document.getElementById("myBar"); 
								    var width = 66;
								    var id = setInterval(frame, 66);
								    function frame() {
								        if (width >= 100) {
								            clearInterval(id);
								        	$(".tableList").html(list);
								        } else {
								        	
								        	if(width!=100){
								            width++; 
								            elem.style.width = width + '%'; 
								            elem.innerHTML = width * 1 + '%';
								        	}
								        }
								    }
								
								
							});

			
			 });
			        } else {
			        	
			        	if(width!=66){
			            width++; 
			            elem.style.width = width + '%'; 
			            elem.innerHTML = width * 1 + '%';
			        	}
			        }
			    }
			}

	 });
	
	 /*  $.post("${pageContext.request.contextPath}/UploadDownloadFileServlet",
		        function(test){
		            alert(test);
		        });*/
});

$("#exampleInputFile").click(function() {
	if($('#exampleInputFile').val()!=null){
		 var elem = document.getElementById("myBar"); 
		    var width = 10;
		    var id = setInterval(frame, 10);
		    function frame() {
		        if (width >= 100) {
		            clearInterval(id);
		        } else {
		        	
		        	if(width!=33){
		            width++; 
		            elem.style.width = width + '%'; 
		            elem.innerHTML = width * 1 + '%';
		        	}
		        }
		    }
		}
});


//Reset form
$(".reset").click(function() {

	swal({   
		title: 'Are you sure want to reset?',
		text: 'The form will be reset',
		type: 'warning',   showCancelButton: true,
		confirmButtonColor: '#3085d6', 
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, reset it!',
		cancelButtonText: 'No, cancel !',
		confirmButtonClass: 'confirm-class',
		cancelButtonClass: 'cancel-class',
		closeOnConfirm: false,
		closeOnCancel: false 
		}).then(function() {
			$(':input','#viewsubfform')
			 .not(':button, :submit, :reset, :hidden')
			 .val('')
			 .removeAttr('checked')
			 .removeAttr('selected');
			deletedMessage("Reset", "success", "004",null, null);				
				},function(dismiss) {
					  if (dismiss === 'cancel') {
						  deletedMessage("Cancelled", "error", "003",null, null);
					  }
					})
 
}); 


//AuthLink modal 
$(".concatData").focusout(function() {
	var autflag = $(this).data("autflag");
	if(autflag=="Y"){
		var rawData = $(this).val();
		var tag = $("#tagValue").text().toUpperCase();
		
		 $.get("Handler_AutFlag",{rawData:rawData, tag:tag, table: "CTTABLE"},function(authlink){
			 $("#authlink").modal();
			 $("#modalauthlink").html(authlink);
		 });
		 }

});


//Javascript functions in BO Organisation 

/*$("#retrieveLink").click(function() {
loader("#titleSearch");
$('#titleSearch').on('shown.bs.modal', function (e) {
  // do something...
});
});*/



//Click modify to enable to edit form 
function modifyRcrd(){
	$('#BibRcrd').find('textarea, button, select, a').prop("disabled", false);
	 $('a').removeAttr("disabled");
	 $("li").removeClass("addTagBO");
	 $("li").removeClass("saveBO");
	 $("li").removeClass("indexBO");
	$("li").removeClass("saveIndexBO");
	$('.Cstatus').html("Modify Record");
}
//Add new tag
var values = 11;
function addTag(tableID,ctmstr) {
	
	//Create random key 
	var new_key = CreateKey();
	
	var table = document.getElementById(tableID);
	
	var tags = $(".tags").val();
	var tagdesc = $(".tags :selected").attr('data-desc');
	var indi1 = $(".tplindi1").val();
	var indi2 = $(".tplindi2").val();
	var auth = $(".tags :selected").attr('data-auth');
	
	
	var tbody = document.createElement("tbody");
		   //table.appendChild(tbody);
	var newTbody = table.appendChild(tbody);
		  // var tbodies = document.getElementsByTagName("tbody");
	var rowCount = newTbody.rows.length;
	var rowCount1 = newTbody.rows.length;
	var row = newTbody.insertRow(rowCount);
	var row1 = newTbody.insertRow(rowCount1);

	var cell1 = row1.insertCell(0);
	cell1.innerHTML = tags;
	cell1.className = 'tagValue';
		     
		    
	if(indi1=="NoIndicator" || indi2=="NoIndicator"){
		
		  var cell2 = row1.insertCell(1);
		  cell2.innerHTML ="<input size='1' maxlength='1' style='text-align:center;display:none' type='text' class='indiValue1' data-tag='"+tag+"' data-indilvl='1' onkeyup='updateIndi(this)' id='indi1_"+indi1+new_key+"' value=&#32; disabled>";
			     
		  var cell3 = row1.insertCell(2);
		   cell3.innerHTML ="<input size='1' maxlength='1' style='text-align:center;display:none' type='text' class='indiValue2' data-tag='"+tag+"' data-indilvl='2' onkeyup='updateIndi(this)' id='indi2_"+indi2+new_key+"' value=&#32; disabled>";
	  
	}else{
			var cell2 = row1.insertCell(1);
			cell2.innerHTML ="<input size='1' maxlength='1' style='text-align:center' type='text' class='indiValue1' data-tag='"+tag+"' data-indilvl='1' onkeyup='updateIndi(this)' id='indi1_"+indi1+new_key+"' value='"+indi1+"' disabled>";
			     
			var cell3 = row1.insertCell(2);
			cell3.innerHTML ="<input size='1' maxlength='1' style='text-align:center' type='text' class='indiValue2' data-tag='"+tag+"' data-indilvl='2' onkeyup='updateIndi(this)' id='indi2_"+indi2+new_key+"' value='"+indi2+"' disabled>"; 
	}
		    
			var cell4 = row1.insertCell(3);
		     cell4.innerHTML  =tagdesc;
		     cell4.className = 'tagdesc';
	
		     
		     var cell5 = row1.insertCell(4);
		     cell5.innerHTML =" <a class='btn btn-dull btn-sm delete'><span class='glyphicon glyphicon-trash'></span></a>";

		     var newValues = values++;
		     var cell6 = row.insertCell(0);
		     var cell7 = row.insertCell(1);
		     var cell8 = row.insertCell(2);
		     cell6.innerHTML = "";
		     cell7.colSpan = 3;
		     cell7.className = 'subfield';
	    	 cell7.id='tag_' +tags+new_key;
		 $('#subfields option:selected').each(function() {
			
		     cell7.innerHTML  += $(this).val();
		    	 cell8.innerHTML  = "<a data-toggle='modal' data-indiid1='"+indi1+new_key+"' data-indiid2='"+indi2+new_key+"' data-ctmstr='"+ctmstr+"' data-id='"+tags+new_key+"' data-target='#viewSubfields' data-indi1='"+indi1+"'"+
		     					"data-indi2='"+indi2+"' data-tagdesc='"+tagdesc+"' data-tag='"+tags+"' data-subf='"+ cell7.innerHTML+"' onclick='openModal(this)'" + 
		     					"class='btn btn-info btn-sm subfButton'><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a>";
		     });
		
		 if(tags=="000"||tags=="006"||tags=="007"||tags=="008"){
			 cell8.innerHTML  = "<a data-toggle='modal' href='#ctrlTagsForm' onclick='ctrlTags(this)' class='btn btn-circle btn-raised btn-primary btn-sm' title='Add Control Fields' data-auth= '"+auth+"' data-id='"+tags+new_key+"' value='"+tags+"'><span class='glyphicon glyphicon-edit' title='Control Tags'></span></a>";
		 }			
		 			var $sort = this;
				    var $table = $('#BibRcrd');
				    var $rows = $('tbody',$table);
				    $rows.sort(function(a, b){
				        var keyA = $('td.tagValue',a).text();
				        var keyB = $('td.tagValue',b).text();
				    
				        if($($sort).hasClass('asc')){
				            return (keyA > keyB) ? 1 : 0;
				        } else {
				            return (keyA > keyB) ? 1 : 0;
				        }
				    });
				    $.each($rows, function(index, row){
				      $table.append(row);
				    });

	  $('#addNewRecord').modal('hide');
}

//form changed
form_changed = false;
$("input, select").change(function() {
	form_changed = true;
});

//Open new template
function getTemplate(){
	var tplName = $('#tpl').val();
	loader("#tags");
	
    $.ajax({
     type : "Get", 
     url : "Handler_BORecord?action=NewRecord", 
     data : "TPLID=" + tplName ,
     success : function(response) {
    	 $("#loader").fadeOut("slow");
    	 $('.collapse').show();
    	 $("#tags").html(response);
     },
     error : function(e) {
      alert('Error: ' + e); 
     }
    });
}

//Onchange to get template details
function getTplDetails() {

	 if (FORM_HAS_CHANGED) {
		 swal({
			  title: 'Proceed anyways?',
			  text: "Unsaved changes",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, proceed',
			  cancelButtonText: 'No, save the changes',
			  confirmButtonClass: 'btn btn-success',
			  cancelButtonClass: 'btn btn-danger',
			  buttonsStyling: false
			}).then(function() {
				getTemplate();
			}, function(dismiss) {
			  // dismiss can be 'cancel', 'overlay',
			  // 'close', and 'timer'
			  if (dismiss === 'cancel') {
			    swal(
			      'Cancelled',
			      'Please continue editing the BO record',
			      'success'
			    );
			  }
			})
	    }else{
	    	getTemplate();
   }
}
 
//Open View Subfields Model
function openModal(data){
	var tag = $(data).data('tag');
/*	var indi1 = $(data).data('indi1');
	var indi2 = $(data).data('indi2');*/
	var indiID1 = $(data).data('indiid1');
	var indi1ID = $(data).attr("id");
	var indiID2 = $(data).data('indiid2');
	var tagdesc = $(data).data('tagdesc');
	var subfields = $(data).data('subf');
	var tagid = $(data).data('id');
	var ctmstr = $(data).data('ctmstr');
	var autflag = $(data).data('autflag');
	var indiid1 
	var indi1 = "";
	var indi2 = "";
	var inner = "";
	$('td[id^="tag_"]').each(function (event) {
		 var no = this.id.replace("tag_", "");
		 if(no==tagid){
			 inner = this.innerHTML;
			 if(inner.indexOf('©') >= 0){
				inner = encodeURIComponent(inner);
			}
		 }
	   });
	
	$('input[id^="indi1_"]').each(function (event) {
		 var no = this.id.replace("indi1_", "");
		 if(no==indiID1){
			 indi1 = encodeURIComponent(this.value);
		 }
	   });
	
	$('input[id^="indi2_"]').each(function (event) {
		 var no = this.id.replace("indi2_", "");
		 if(no==indiID2){
			 indi2 = encodeURIComponent(this.value);
		 }
	   });

	var url = "ViewSubfields?tag=" + tag + "&indi1=" + indi1 + "&indi2=" + indi2 + 
		"&tagdesc=" + tagdesc + "&subfields=" + inner + "&tagid=" + tagid + "&ctmstr=" + ctmstr +
		"&indiinc1="+encodeURIComponent(indiID1) + "&indiinc2="+encodeURIComponent(indiID2)+ 
		"&autflag="+autflag;

	$.get(url,function(data){
	$("#subfList").html(data);
	});	
}

//Update Indicators
function updateIndi(indi){
	var tag = $(indi).data('tag');
	var indilvl = $(indi).data('indilvl');
	var indi = $(indi).val();
	/*var criteria = "indi=" + indi + "indilvl=" + indilvl + "tag=" + tag;*/
		/*var message = indiMessage("010", indi, indilvl, tag);*/
	
	$.get("BO_AjaxHandler",{tag : tag, indilvl: indilvl, indi : indi, GL79ERRCODE:"010"},function(data){

		$(".errorMessage").html(data);
		if ($('div').hasClass('alert-danger')){
			$("#addBORcrd").css("cursor", "not-allowed");
			$("#index").css("cursor", "not-allowed");
			$("#saveindex").css("cursor", "not-allowed");
		}else{
			$("#addBORcrd").css("cursor", "pointer");
			$("#index").css("cursor", "pointer");
			$("#saveindex").css("cursor", "pointer");
		}
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

//Auto create keys 
function CreateKey(){
    return parseInt(Math.random() * 100000);
}

//Add new subfield
function addRow(tableID, key) {

	  var selected = [];
	  $('#Testingsubfields option:selected').each(function() {
		  //var new_key = CreateKey();
		   var table = document.getElementById(tableID);
		   var name = key;
		    var rowCount = table.rows.length;
		    var newID = CreateKey();

		    var row = table.insertRow(rowCount);
		    row.className = 'primarySubf';
		    row.id = $(this).val() + newID;
		     var cell1 = row.insertCell(0);
		     var newSubf = '"'+$(this).val()+newID+'"';
		     cell1.innerHTML = "<a class='btn btn-success btn-xs sort' onClick='sortSubf("+newSubf+")'><span class='glyphicon glyphicon-sort'></span></a>";
		     var cell2 = row.insertCell(1);
		     cell2.innerHTML = $(this).val();
		     cell2.className = 'subf_'+$(this).val() + ' subfields';
		     
		     var cell3 = row.insertCell(2);
		     cell3.colSpan = 2;
		     cell3.innerHTML ="<input size='99' type='text' onfocus='getDesc(this);getData(this)' name='"+name+"' data-desc='|"+$(this).text()+"' id='|"+$(this).val()+"'>";
    
		     if($(this).data("id")=="Y"){
		     var cell4 = row.insertCell(3);
		     cell4.innerHTML = "<a class='btn btn-dull btn-sm' onclick='removeSubf(this)'><span class='glyphicon glyphicon-trash'></span></a>" +
		     "<a class='btn btn-default btn-sm' onclick='CloneSubfield("+newSubf+")'><span class='glyphicon glyphicon-duplicate'></span></a>";
		     }else{
		    	 var cell4 = row.insertCell(3);
			     cell4.innerHTML = "<a class='btn btn-dull btn-sm' onclick='removeSubf(this)'><span class='glyphicon glyphicon-trash'></span></a>";
		     }
		     
	  });

	  $('#addSubfield').modal('hide');
}

$("#addSubfield").on("hidden.bs.modal", function(){
	  $('#Testingsubfields').multiselect('refresh');
	  $('#Testingsubfields option:selected').each(function() {
		  $(this).prop('selected', false);
	    })
});
	

//Clone for duplicate subfields
function CloneSubfield(index){
    var original = document.getElementById(index); //original <div>
    var clone = original.cloneNode(true);
    var new_key = CreateKey();

    // set the attribute for the new 'div' subfields
    var inputs     = clone.getElementsByTagName('input');
    var selects    = clone.getElementsByTagName('select');
    var textareas  = clone.getElementsByTagName('textarea');
    var linkid;

    // input
    var id_input = "";
    for(var i=0,len=inputs.length; i<len ; i++ ){
        id_input = inputs[i].getAttribute('id');
        inputs[i].setAttribute('id',id_input);
        inputs[i].setAttribute('name',inputs[i].getAttribute('name')+new_key);
        linkid = id_input;
    }
    // Plugin input
    if( $(inputs[1]).hasClass('framework_plugin') ) {
        var oldcontrol= original.getElementsByTagName('input')[1];
        AddEventHandlers( oldcontrol, inputs[1], linkid );
    }

    // select
    for(var i=0,len=selects.length; i<len ; i++ ){
        id_input = selects[i].getAttribute('id')+new_key;
        selects[i].setAttribute('id',selects[i].getAttribute('id')+new_key);
        selects[i].setAttribute('name',selects[i].getAttribute('name')+new_key);
    }

    // textarea
    for(var i=0,len=textareas.length; i<len ; i++ ){
        id_input = textareas[i].getAttribute('id')+new_key;
        textareas[i].setAttribute('id',textareas[i].getAttribute('id')+new_key);
        textareas[i].setAttribute('name',textareas[i].getAttribute('name')+new_key);
    }

    // Handle click event on buttonDot for plugin
    var links  = clone.getElementsByTagName('a');
    if( $(links[0]).hasClass('framework_plugin') ) {
        var oldcontrol= original.getElementsByTagName('a')[0];
        AddEventHandlers( oldcontrol, links[0], linkid );
    }

  
    // setting a new id for the parent div
    var new_id  = original.getAttribute('id')+new_key;
    clone.setAttribute('id',new_id);

    try {
        var buttonUp = clone.getElementsByTagName('img')[0];
        buttonUp.setAttribute('onclick',"upSubfield('" + new_id + "')");
        var anchors = clone.getElementsByTagName('a');
        if(anchors.length){
            for(var i = 0 ,lenanchors = anchors.length ; i < lenanchors ; i++){
                if(anchors[i].getAttribute('class') == 'buttonPlus'){
                    anchors[i].setAttribute('onclick',"CloneSubfield('" + new_id + "'); return false;");
                } else if (anchors[i].getAttribute('class') == 'buttonMinus') {
                    anchors[i].setAttribute('onclick',"UnCloneField('" + new_id + "'); return false;");
                }
            }
        }
    }
    catch(e){
        // do nothig if ButtonPlus & CloneButtonPlus don't exist.
    }
    // insert this line on the page
    original.parentNode.insertBefore(clone,original.nextSibling);
}

//Sort subfields
function sortSubf(index) {
	
    try{
        var line = document.getElementById(index);
    } catch(e) {
        return; 
    }
    var tag = line.parentNode; 

    var subfields = tag.getElementsByTagName('tr');
    var subfieldsLength = subfields.length;

    if(subfieldsLength<=1) return; 

    for(var i=0;i<subfieldsLength;i++){
        if(subfields[i].getAttribute('id') == index){ 
            if(i==1){
                tag.appendChild(subfields[1]);
                return;
            } else {
                var lineAbove = subfields[i-1];
                tag.insertBefore(line,lineAbove);
                return;
            }
        }
    }
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
	
	function getCtrlInput(id, values){
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
	
		   $.get("template",{MODULE:"Cataloging/02_Bibliographic_Organisation",ACTION:"listBibRecord.jsp"},function(data_title){
		   });
		   
		
			
		//Delete selected row with tag and details
/*		function removeTag(deleteTag){
			alert("sdsdsd");
			  var i=deleteTag.parentNode.parentNode.rowIndex;
			    document.getElementById('BibRcrd').removeTag(i);
		}
		*/

	//Duplicate subfield
		$("a.duplicateSubf").click(function() {
		    var $tr    = $(this).closest('.primarySubf');
		    var $clone = $tr.clone();
		    $clone.find(':text').val('');
		    $tr.after($clone);

		});

		//Save subfield data
		/*$("a#saveSubfields").click(function() {
			$('input[name^="tag_"]').each(function (event) {
				var id;
				 var allvals = $('input[name^="tag_').map(function() {
								 if(this.value==null||this.value==""){
										id="";
									}else{
										id =$(this).attr("id");
									}
					 				return  id+this.value; 
					 			}).get().join('');
				  var no = this.name.replace("tag_", "");
				  var selector = "#tag_" + no;
				  $(selector).html(allvals);
			   });

			 $("#viewSubfields").modal("hide");
			});*/
/*		
	$('#btn-submit-title').click(function(){
			
			
			var title = $("#criteria").val();
			
			var tag = $("#tag").val();
			loader("#display_title");
			$.get("Table_TermSearch",{criteria:title,tag:tag},function(data_title){
				$("#display_title").html(data_title);
			   });	
			
		});*/
	
	

   	  $("#retrieveLink").click(function(){
 
   
   		 if (FORM_HAS_CHANGED || $(".concatData").val().length > 0) {
   			 if(($('.Cstatus').text()=="New")||($('.Cstatus').text()=="Record Saved")||
   					($('.Cstatus').text()=="Record Unindexed")||($('.Cstatus').text()=="Modify Record")){
   			 swal({
   				  title: 'Proceed anyways?',
   				  text: "Unsaved changes",
   				  type: 'warning',
   				  showCancelButton: true,
   				  confirmButtonColor: '#3085d6',
   				  cancelButtonColor: '#d33',
   				  confirmButtonText: 'Yes, proceed',
   				  cancelButtonText: 'No, save the changes',
   				  confirmButtonClass: 'btn btn-success',
   				  cancelButtonClass: 'btn btn-danger',
   				  buttonsStyling: false
   				}).then(function() {
   					$('#tpl').prop('disabled', true);
   			 	
   				}, function(dismiss) {
   				  // dismiss can be 'cancel', 'overlay',
   				  // 'close', and 'timer'
   				  if (dismiss === 'cancel') {
   					$( "#titleSearch" ).modal("hide");
   				    swal(
   				      'Cancelled',
   				      'Please continue editing the BO record',
   				      'success'
   				    );
   				  }
   				})
   			 }
   		    }else{
	   		 	$('#tpl').prop('disabled', true);
   	   }
   		
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

   			        //alert(string1);
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
					$("#search_title").collapse('hide');
					$("#result_title").collapse('show');
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
				$.get(url,function(data_title){
					$('#titleSearch').modal('hide');
					$('.collapse').show();
					$("#tags").html(data_title);
					$("#actionstatus").html("Status: Record Retrieved");
				});	
			}
		});
	
	$('.cancel').click(function() {
		$('#product-options').modal('hide');
	});

	/*$('.unindex').click(function() {
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
	*/
	$("#enableSelect").click(function(){
        $('#tpl').prop('disabled', false);
     });
	
	$('#accModal').click(function(){
		//replaceLoader("#display_title");
		var title = $('#criteria').val();
		var searchType = $('#search_type').val();

			$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType},function(data_title){
				$("#display_title").html(data_title);
				$('#result_title').collapse("show").height("auto");
				
		   });			
	})


     });
	
	
	
	function getAccModal(){
	
		var title = $('.criteria').val();
		var searchType = $('#search_type').val();

			$.get("RetrieveSearchTitle",{criteria:title,search_type:searchType},function(data_title){
				$("#display_title").html(data_title);
				$('#result_title').collapse("show").height("auto");
				
		   });	
	

	}
	
	
	
	$('form').on('change keyup keydown', 'input, textarea, select', function (e) {
	    $(this).addClass('changed-input');
	});
	
	$(window).on('beforeunload', function () {
	    if ($('.changed-input').length) {
	      alert("Changes.Save?")
	    }else{
	    	alert("continue")
	    }
	});

/*	$("#titleSearch").on('hidden.bs.modal', function () {
	    $(this).data('bs.modal', null);
	});
	*/
	
	
	$("#duplicateCopy").click(function(){
		$("#rcrddetails").find("tr").remove();
		  $.get("Duplicate_Copy",{action:"Duplicate Copy"},function(data_title){
			 // $('#rcrddetails').find('tbody').append(data_title);
			  $('#BOdetails').html("");
			  $('#BOdetails').html(data_title);
			  $('#BibRcrd').find('input').prop("disabled", true);
			    $("li").removeClass("saveIndexBO");
			    $("li").removeClass("saveBO");
		  }); 
	});
	
	
	$("#BibRcrd :input").change(function() {
		   $("#BibRcrd").data("changed",true);
		});
	
	$('#BibRcrd').on('click', '.delete', function () {
	    $(this).closest('tbody').remove();
	})
	/*
	  $("#addNewRecord").on("shown.bs.modal", function(){
		  alert("dssd");
			 $(this).removeData();
		});*/
	//Add new subfields
	
$("a#showIndi").click(function() {

		var GL23TAG = $(this).data("id");

		$.get("Indicator_Help",{GL18TAG:GL23TAG},function(data_title){
			$("#BOIndi").modal("show");
			$("#indicator_help").html(data_title);
		   });	
		
	
});

