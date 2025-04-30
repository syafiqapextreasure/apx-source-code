/**
 * 
 */

//Javascript functions in BO Organisation 

//Onchange to get template details
function getTplDetails() {
  
	var tplName = $('#tpl').val();
    $.ajax({
     type : "Get", 
     url : "Handler_BORecord?action=NewRecord", 
     data : "TPLID=" + tplName ,
     success : function(response) {
    	  $('.collapse').show();
    	 $("#tags").html(response);
     },
     error : function(e) {
      alert('Error: ' + e); 
     }
    });
   }
 
//Open View Subfields Model
function openModal(data){
	var tag = $(data).data('tag');
	var indi1 = $(data).data('indi1');
	var indi2 = $(data).data('indi2');
	var tagdesc = $(data).data('tagdesc');
	var subfields = $(data).data('subf');
	var tagid = $(data).data('id');
	var inner = "";
	$('td[id^="tag_"]').each(function (event) {
		 var no = this.id.replace("tag_", "");
		 if(no==tagid){
			 inner = this.innerHTML;
		 }
	   });
	var url = "ViewSubfields?tag=" + tag + "&indi1=" + encodeURIComponent(indi1) + "&indi2=" + encodeURIComponent(indi2) + 
		"&tagdesc=" + tagdesc + "&subfields=" + inner + "&tagid=" + tagid;
	$.get(url,function(data){
	$("#subfList").html(data);
	});	
}

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

//Auto create keys 
function CreateKey(){
    return parseInt(Math.random() * 100000);
}

//Add new subfield
function addRow(tableID) {
	  var selected = [];
	  $('#Testingsubfields option:selected').each(function() {
		  //var new_key = CreateKey();
		   var table = document.getElementById(tableID);
		   var name = $(".concatData").attr('name');
		    var rowCount = table.rows.length;
		    var newID = CreateKey();

		    var row = table.insertRow(rowCount);
		    row.className = 'primarySubf';
		    row.id = $(this).val() + newID;
		     var cell1 = row.insertCell(0);
		     cell1.innerHTML = "<a class='btn btn-success btn-xs sort' onClick='sortSubf("+$(this).val()+newID+")'><span class='glyphicon glyphicon-sort'></span></a>";
		     var cell2 = row.insertCell(1);
		     cell2.innerHTML = $(this).val();
		     cell2.className = 'subf_'+$(this).val();
		     
		     var cell3 = row.insertCell(2);
		     cell3.colSpan = 2;
		     cell3.innerHTML ="<input size='99' type='text' onkeyup='getDesc(this);getData(this)' name='"+name+"' data-desc='|"+$(this).text()+"' id='|"+$(this).val()+"'>";
    
		     if($(this).data("id")=="Y"){
		     var cell4 = row.insertCell(3);
		     cell4.innerHTML = "<a class='btn btn-danger btn-sm' onclick='removeSubf(this)'><span class='glyphicon glyphicon-trash'></span></a>" +
		     "<a class='btn btn-default btn-sm' onclick='CloneSubfield("+$(this).val()+newID+")'><span class='glyphicon glyphicon-duplicate'></span></a>";
		     }else{
		    	 var cell4 = row.insertCell(3);
			     cell4.innerHTML = "<a class='btn btn-danger btn-sm' onclick='removeSubf(this)'><span class='glyphicon glyphicon-trash'></span></a>";
		     }
		     
	  });
	  $('#addSubfield').modal('hide');
}

function CloneSubfield(index){
    var original = document.getElementById(index); //original <div>
    var clone = original.cloneNode(true);
    var new_key = CreateKey();
alert("dd");
    // set the attribute for the new 'div' subfields
    var inputs     = clone.getElementsByTagName('input');
    var selects    = clone.getElementsByTagName('select');
    var textareas  = clone.getElementsByTagName('textarea');
    var linkid;

    // input
    var id_input = "";
    for(var i=0,len=inputs.length; i<len ; i++ ){
        id_input = inputs[i].getAttribute('id')+new_key;
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

//Add new tag
var values = 11;
function addTag(tableID) {
	
		  //var new_key = CreateKey();
		   var table = document.getElementById(tableID);
		    var rowCount = table.rows.length;
		    var rowCount1 = table.rows.length;
		    var row = table.insertRow(rowCount);
		    var row1 = table.insertRow(rowCount1);

		     var cell1 = row1.insertCell(0);
		     cell1.innerHTML = $("#tag").val();
		     
		     var cell2 = row1.insertCell(1);
		     cell2.innerHTML ="<input size='1' type='text' class='indiValue1' data-tag='"+$("#tag").val()+"' data-indilvl='1' onkeyup='updateIndi(this)' id='indi1' value='"+$("#indi1").val()+"'>";
		     
		     var cell3 = row1.insertCell(2);
		     cell3.innerHTML ="<input size='2' type='text' class='indiValue2' data-tag='"+$("#tag").val()+"' data-indilvl='2' onkeyup='updateIndi(this)' id='indi2' value='"+$("#indi2").val()+"'>";
		    
		     var cell4 = row1.insertCell(3);
		     $('#tag option:selected').each(function() {
		     cell4.innerHTML  = $(this).data('desc');
		     });
		     
		     var cell5 = row1.insertCell(4);
		     cell5.innerHTML =" <a class='btn btn-danger btn-sm delete'><span class='glyphicon glyphicon-trash'></span></a>";

		     var newValues = values++;
		     var cell6 = row.insertCell(0);
		     var cell7 = row.insertCell(1);
		     var cell8 = row.insertCell(2);
		     cell6.innerHTML = "";
		     cell7.colSpan = 3;
		   
		 $('#subfields option:selected').each(function() {
			
		     cell7.innerHTML  += $(this).val();
		     $('#tag option:selected').each(function() {
		    	 cell7.id='tag_' +$("#tag").val()+newValues;
		    	 cell8.innerHTML  = "<a data-toggle='modal' data-id='"+$("#tag").val()+newValues+"' data-target='#viewSubfields' data-indi1='"+$("#indi1").val()+"'"+
		     					"data-indi2='"+$("#indi2").val()+"' data-tagdesc='"+$(this).data('desc')+"' data-tag='"+$("#tag").val()+"' data-subf='"+ cell7.innerHTML+"' onclick='openModal(this)'" + 
		     					"class='btn btn-success btn-sm subfButton'><span class='glyphicon glyphicon-eye-open' title='View Subfields'></span></a>";
		     });
		     });
		     
		     
		  

	  $('#addNewRecord').modal('hide');
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
    				messageBox("Reset", "success", "004",null, null);				
						},function(dismiss) {
							  if (dismiss === 'cancel') {
								  messageBox("Cancelled", "error", "003",null, null);
							  }
							})
		   
		});

	//Duplicate subfield
		$("a.duplicateSubf").click(function() {
		    var $tr    = $(this).closest('.primarySubf');
		    var $clone = $tr.clone();
		    $clone.find(':text').val('');
		    $tr.after($clone);
		});

		//Save subfield data
		$("a#saveSubfields").click(function() {
			$('input[name^="tag_"]').each(function (event) {
				 var allvals = $('input[name^="tag_').map(function() { return  $(this).attr("id")+this.value; }).get().join('');
				  var no = this.name.replace("tag_", "");
				  var selector = "#tag_" + no;
				  //var newValue = subfield[0]+allvals;
				  //$(selector).val(allvals);
				  $(selector).html(allvals);
			   });

			 $("#viewSubfields").modal("hide");
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
	
	$('#accModal').click(function(){
		//replaceLoader("#display_title");
		alert("sss");
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
	
		