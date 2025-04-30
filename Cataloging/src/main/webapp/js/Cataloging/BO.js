/**
 * 
 */
/*$(document).on('focusout',".data", function(event) {
	alert("sdsd");
	var autflag = $(this).data("autflag");
	if(autflag=="Y"){
		var rawData = $(this).val();
		var tag = $("#tagValue").text().toUpperCase();
		
		 $.get("Handler_AutFlag",{rawData:rawData, tag:tag, table: "CTTABLE"},function(authlink){
			 $("#authlink").modal();
			 $("#modalauthlink").html(authlink);
		 });
		 }
    event.preventDefault(); 
  });*/
 

var FORM_HAS_CHANGED = false;

/*$('#mybutton').click(function() {
    if (FORM_HAS_CHANGED) {
        // The form has changed
    }
});
*/
$("[title]").tooltip();
$("input, select").change(function(){
    FORM_HAS_CHANGED = true;
});

$('.data, .concatData').blur(function(){

	var autflag = $(this).data("autflag");
	var action = "frontBO";
	if(autflag=="Y"){
		var rawData = $(this).val();
		 var $row = $(this).closest('tr');
	     var tag = $row.find('.tag').text();
	     var tagid = "";
	   
	     if(tag==""){
	    	 tag =  $("#tagValue").text();
	    	 action = "EditTerm";
	    }else{
	        tagid = $row.closest('tr').attr('class');
	    }
		 $.get("Handler_AutFlag",{rawData:rawData, tag:tag, table: "CTTABLE", action: action, tagid:tagid},function(authlink){
			 $("#authlink").modal();
			 $("#modalauthlink").html(authlink);
		 });
		 }
});


$('#searchAccNo').click(function(){
	var matno = $('.Cmatno').text();

	var url = "template?MODULE=Cataloging/03_Accession_Maintenance&ACTION=MainPage.jsp&matno=" + matno;
	 var win = window.open(encodeURI(url), '_blank');

		 $.get('Table_CTResultMaster',{controlNoInput:matno},function(data){
				$(".accession_list").html(data);
		});
	 	/*$.get('Table_SearchByAccNo',{txtMatNo:matno},function(data){
			 win.document.write(data)
			//$(".accession_list").html(data);
		});*/
	  win.focus();
})

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
						  $(this).find(".indiValue2").val(stopword_length.trim());
				
					  }
				  })
			}
		}
	}
})

$(".data").focusout(function(event){
	var data = $(this).val();
	var $row = $(this).closest('tr');
    var tag = $row.find('.tag').text();
	var subfArray = data.split("|");
	var module = $("#module").val();

		if(subfArray[1].substring(0,1)=="a"){
			data = subfArray[1].substring(1).split(" ");
			$.get("Handler_NonFilling",{tag:tag, data:data[0], module:module},function(stopword_length){

					  if(stopword_length.trim()!=''){
						  $row.find('.indi2').val(stopword_length);
						/*  $('input[name^="indi2_"]').each(function (event) {

								  var no = this.name.replace("indi2_", "");
							
								  var selector = "indi2_" + no;
								  document.getElementsByName(selector)[0].value = stopword_length.trim();
								  //$("[name="+selector+"]").value(stopword_length.trim());
								  //$( "input[name='"+selector+"']" ).value(stopword_length.trim());
		

							});
						  $(this).find(".indiValue2").val(stopword_length.trim());*/
				
					  }
				  })
		}
		event.preventdefault();
})
/*$(".concatData").focusout(function(){
	
	var data = $(this).val();
	var tag = $("#tagValue").text();
	var stringArray = data.split(" ");
	var row = $(this).closest('tr');
	alert(row);
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
      
  });*/


//In use
function getMedia(){
	
	 $('.slct').each(function(){  
		  	var input = $(this).val();
			var id = $(this).attr('id');
			
			 if(input.length>1){
				 for (i = 0; i < input.length; i++) { 
					 document.getElementById(id).innerHTML = input[i];
					 if(document.getElementById(id).innerHTML !=""){
						 id = parseInt(id)+1;
					 } 
				}
	         }
	});
	 
	  $('#showList tr').each(function(){
          //var cell = 'table tr:nth-child(0) td:nth-child(1)';
           var cells = $(this).find("td:nth-child(2)");
        
           if(cells.text().trim()=="Date entered on file"){
        	   var input = $(this).find(".ctrlInput");
        	   var id =  $(this).find(".ctrlInput").attr("id");
        	   $(input).attr('maxlength','6');
        	   var today = new Date();
        	   var dd = today.getDate();
        	   var mm = today.getMonth()+1; //January is 0!
        	   var yyyy = today.getFullYear().toString().substr(2,2);

        	   if(dd<10) {
        	       dd = '0'+dd
        	   } 

        	   if(mm<10) {
        	       mm = '0'+mm
        	   } 

        	   today = yyyy + mm + dd;
        	   $(input).val(today);
        	    
    			 if(today.length>1){
    				 for (i = 0; i < today.length; i++) { 
    					 document.getElementById(id).innerHTML = today[i];
    					 if(document.getElementById(id).innerHTML !=""){
    						 id = parseInt(id)+1;
    					 } 
    				}
    	         }
           }
           
           if(cells.text().trim()=="Date 1"){
        	   var input = $(this).find(".ctrlInput");
        	   $(input).attr('maxlength','4');
           }
           
           if(cells.text().trim()=="Date 2"){
        	   var input = $(this).find(".ctrlInput");
        	   $(input).attr('maxlength','4');
           }
      });
}

//Select media record in the dropdown list -- In use
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
                        getMedia();
                        

                   }
               }); 
           
       });
	 
	 
	 
	//Change status accordingly 
	if($(".Cstatus").text()=="New" || $(".Cstatus").text()=="New Record"){
		$("li").removeClass("saveIndexBO");
		$("li").removeClass("saveBO");
		$("li").removeClass("addTagBO");
		$("#duplicateCopy").addClass("isDisabled");
		$("#searchAccNo").addClass("isDisabled");
		$("#delete").addClass("isDisabled");
		$("#modify").addClass("isDisabled");
		$("#security").addClass("isDisabled");
	}else if($(".Cstatus").text()=="Indexed" ||$(".Cstatus").text()=="Record Indexed"){
		 $("#addTag").attr("disabled","disabled");
		$("li").removeClass("unindexBO");
		//$("li").removeClass("addTagBO");
		$("li").removeClass("duplicate");
		$("#security").removeClass("isDisabled");
		 $('#BO_Record').find('textarea, select, input, .editdata, #retrieveLinks, .delete, .linkage,.duplicateTag').attr('disabled','disabled');
		//$('#BibRcrd').find('input, textarea, button, select, a').attr('disabled','disabled');
	}else if($(".Cstatus").text()=="Buffer Record"){
		$("li").removeClass("modifyBO");
		$("li").removeClass("deleteBO");
		$("li").removeClass("duplicate");
		 $('#BO_Record').find('textarea, select, input, .editdata, #retrieveLinks, .delete, .linkage,.duplicateTag').attr('disabled','disabled');

		//$('#BibRcrd').find('input, textarea, button, select, a').attr('disabled','disabled');
	}else if ($(".Cstatus").text()=="Record Unindexed"){
		//alert("dsss");
		 $('#BO_Record').find('textarea, button, select, a, input.rawtag').removeAttr("disabled");
		// $('#BO_Record').find('.ctrltag').attr('disabled','disabled');
		  
	}else if ($(".Cstatus").text()=="Record Saved and Indexed"){
		$("li").removeClass("unindexBO");
	}
	
	var ctrlTagTpl = $('#ctrlTagsForm').on('hidden.bs.modal', function () {
		 // $(this).removeData('bs.modal');
		});
	
	function retainMedia(template,ctrlID){
	
		/*document.getElementById("ctrlTagSlct").value = template;
		alert("q");*/
         var data = 'ctrlTagCodes='+ template;
              $.ajax({
                 type: "GET",
                 url: "Media_Record",
                 data: data,
                 success: function(msg){
               	
               	  //$("#ctrlTagCode").show();
                      $("#ctrlTagCode").html(msg);
                  	var data = $('#BO_Record tbody tr.' + ctrlID).find('.data').val();
                  	var number = 0;
                  	//$('#mytable > tbody  > tr> td').each(function(index, tr) { 
                  		for (var i = 0; i < data.length; i++) {
                  			$('#mytable > tbody  > tr> td').eq(i).text(data.charAt(i));
                 		}
                  	//});
                  	$('#showList > tbody  > tr').each(function(index, tr) { 
                  		var start = $(this).find('.ctrl').attr('id');
                  		var row = $(this).closest('tr');
                  		var stop =	 row.next().find(".ctrl").attr('id')-start;
                  		var array="";
                  		var number = 0;

                  		if(isNaN(stop)){
                  			array = data;
                  		}else{
                  			for (var i = 0; i < stop && data.length; i++) {
                  				array+=data.charAt(i);
                  				number++;
                  			}
                  		}

                  		data = data.substring(number, data.length);
                  		$(this).find('.ctrl').val(array.trim());
                  	//	alert("Stop" + stop);
                  	});
                  	
                      

                 }
             }); 
              
          	$('#ctrlTagSlct>option[value="' + template + '"]').prop('selected', true);
	}
	
	//Navigate control tag modal --In use
	function ctrlTags(ctrltag){

			  var ctrlTag =  $(ctrltag).attr("value");
			  var ctrlID =  $(ctrltag).attr("data-tagid");
			  var template = $(ctrltag).attr("data-template");

			 // $("#ctrlTagsForm").modal("show");
			  if(ctrlTag!="000"){
		
		      var data = 'ctrlTag='+ ctrlTag +"&ctrlID=" + ctrlID;
		           $.ajax({
		              type: "GET",
		              url: "Control_Fields",
		              data: data,
		              success: function(msg){

		            	   $("#ctrlTagDisplay").html(msg);

                           if(template.trim()!=""){
                        	   retainMedia(template,ctrlID);
                           }
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
		            	   if(template.trim()!=""){
		            		   retainMedia(template,ctrlID);
		            	   }else{
		            	   var data = 'ctrlTagCodes='+ ctrlTag;
		                   $.ajax({
		                      type: "GET",
		                      url: "Media_Record",
		                      data: data,
		                      success: function(msg){
		                           $("#ctrlTagCode").html(msg);
		                      }
		                  }); 
		            	  }
		              }
		          }); 
			  }
		  }

//Navigate to add tag modal --In use
	var id = 1;
function linkage(link){
	/*  var row = $(this).closest('tr').html();
	  $('#BO_Record tbody').append('<tr>'+row+'</tr>');*/
	var tags = $(link).closest("tr").find(".tagValue").text();
	var indi1 = $(link).closest("tr").find('.indi1').val();
	var indi2 = $(link).closest("tr").find('.indi2').val();
	
	var data = $(link).closest("tr").find('.data').val();
	localStorage.setItem("data", data);

	localStorage.setItem("counter", id);
	var counter = localStorage.getItem("counter");
	
	if(counter.length<2){
		counter = "0" + counter;
	}
	
	data =  "|6"+tags.trim()+"-" + counter + data;
	
	var tagdesc = $("#linktagdesc").val();
	var button = "<a data-toggle='modal' data-autflag='N' onclick='openModal(this)' data-target='#viewSubfields' class='btn btn-info btn-sm openModal editdata' data-tagid='' data-tagdesc='"+tagdesc+"' data-template=''><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a>&nbsp;"
	
	 $(link).attr("disabled", true);
	var html = "<tr>" + 
		"<td class='tagValue'><span class='tag'>880</span>&nbsp;" + 
		"<span class='glyphicon glyphicon-question-sign desc' title='"+tagdesc+"'></span></td>" + 
		"<td><input type='text' size='1' maxlength='1' class='form-control indi1' value='"+indi1+"'></td>"+ 
		"<td><input type='text' size='1' maxlength='1' class='form-control indi2' value='"+indi2+"'></td>"+ 
		"<td><input type='text' value='"+data+"' class='form-control data' size='100'></td>" + 
		"<td>"+
		button + 
/*		"<a id='retrieveLink' class='btn  btn-primary btn-sm' data-toggle='modal' data-target='#termSearch' href=''>"+
		"<span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span>" + 
		"</a>" + */
		"<a id='retrieveLinks' class='btn  btn-primary btn-sm' data-toggle='modal' data-target='#termSearch' href='Term_Search?tag=880&action=direct_termsearch&tagid='>"+
		"<span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span>" + 
		"</a>&nbsp;" + 
		"<a class='btn btn-dull btn-sm delete' title='Delete Field'><span class='glyphicon glyphicon-trash'></span></a>" + 
		"</td>" + 
		"</tr>";
	
	var rows = $('#BO_Record tbody').find('tr').length;
	for (var i=0;i<=rows;i++){
		var tag = $('#BO_Record tbody tr').eq(i).find('.tagValue').text();
		var tag1 = $('#BO_Record tbody tr').eq(i+1).find('.tagValue').text();

		if(Number(tag)>Number("880")){
			$('#BO_Record > tbody > tr').eq(i).before(html);

			break;
		}else{
		var lasttag = $('#BO_Record tr:last').find('.tagValue').text();

		if(Number(lasttag)<Number("880")){
			 $('#BO_Record > tbody > tr:last').after(html);
			 break;
		}else{
		 if (Number(tag) <Number("880")&&Number(tag1) >=Number("880")) {
			 $('#BO_Record > tbody > tr').eq(i+1).after(html);
			 break;
		 }
		}
		}
	}
	
	var newdata = "|6880-" + counter + localStorage.getItem("data");
	$(link).closest("tr").find('.data').val(newdata);
	//localStorage.setItem("counter", Number(counter) + 1);
	localStorage.clear();
	localStorage.setItem("counter", Number(id++));

	for (var j=0;j<=rows;j++){
		/* $('#BO_Record tbody tr').eq(i).find('a')[0].attr('data-id', i);
		 $('#BO_Record tbody tr').eq(i).find('a')[1].attr('href', 'Term_Search?tag="+tags+"&action=direct_termsearch&tagid=' + i);
		 
*/		$('#BO_Record tbody tr').eq(j).find('.editdata').attr('data-tagid', j);
		var currenthref =  $('#BO_Record tbody tr').eq(j).find('#retrieveLinks').attr('href');
		var newcurrenthref = currenthref.split("&");
		var res = currenthref.replace(newcurrenthref[2], "tagid="+j+"");	
		$('#BO_Record tbody tr').eq(j).find('#retrieveLinks').attr('href', res);
	}

}

//In use
$("#viewisbd").click(function(){
	var tag = [];
	var data = [];
	
	$('#BO_Record tbody tr').each(function(){
	   tag.push($(this).find("td:eq(0)").text().trim());
	   data.push($(this).find(".data").val());
	})
	//$("#isbdview").modal("show");
	  $.get("ISBDView",{template:"ILMUISBD", tag:tag, data:data, total:tag.length},function(data){
		  $("#isbdcontent").html(data);
	  });
});

//In use
$("#viewmarc").click(function(){
	var tag = [];
	var indi1 = [];
	var indi2 = [];
	var data = [];
	
	$('#BO_Record tbody tr').each(function(){
	   tag.push($(this).find("td:eq(0)").text());
	   indi1.push($(this).find(".indi1").val());
	   indi2.push($(this).find(".indi2").val());
	   data.push($(this).find(".data").val());
	})
	
	  $.get("MarcView",{tag:tag, indi1:indi1, indi2:indi2, data:data, total:tag.length},function(data){
		  $("#marccontent").html(data);
	  });
});

//Navigate to add tag modal --In use
$("a#addTag").click(function() {
		var tags = [];
		var module = $("#module").val();
		
	  $('#BO_Record').find('tbody tr').each(function(index){
		
		  var CT04TAG = $(this).find("td.tagValue").text();
		  tags.push(CT04TAG.trim());
	  });
	  $.get("AddNewTplInfo",{action:"templateTag",GL17TAG:tags.toString(), module:module},function(GL17TAG){
		  $("#modalTag").html(GL17TAG);
	  });
});

//Save subfields -- In use
$("a#saveSubfields").click(function() {
	var exist = true;
	var tagid = $(this).data("id");
	var indi1 = $(".indiValue1").val();
	var indi2 = $(".indiValue2").val();
	//var row = $("#rowcount").val();
	var row = $(".rowcount").val();

	//var raw = $(".concatData").val();
	var raw = [];

	 $('#subfTable tbody').find('tr.primarySubf').each(function(index){
		/*	var subfield = "|" + $(".subfields").text().trim();
			alert(subfield);*/
			var subfield = "|" + $(this).find(".subfields").text().trim();
		    var data = $(this).find(".concatData").val().trim();
			raw.push(subfield + data);	
	  });
	 
			var tag = $('#tagValue').text();
			 $.get("Duplicate_Record",{data:raw.join("").toString(), tag: tag, action:"keyInData"},function(data_title){

				 if(data_title.trim() =="true"){
					 localStorage.setItem("duplicate", "Y");
					  alertMessage("Successful", "success", "114",tag, "@tag"); 
				 }else{
					 localStorage.setItem("duplicate", "N");
				 }			 
			 });

	/*$('#BO_Record tbody tr').eq(tagid).find('.indi1').val(indi1);
	$('#BO_Record tbody tr').eq(tagid).find('.indi2').val(indi2);
	$('#BO_Record tbody tr').eq(tagid).find('.data').val(raw.join("").toString());*/
	
	$('#BO_Record tbody tr.' + tagid).find('.indi1').val(indi1);
	$('#BO_Record tbody tr.' + tagid).find('.indi2').val(indi2);		 
	$('#BO_Record tbody tr.' + tagid).find('.data').val(raw.join("").toString());
	
});


$("a#saveCtrlTag").click(function() {
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
							
				 				return this.value;
				 				

				 			}).get().join('');

			  var no = this.name.replace("tag_", "");
			  var selector = "#tag_" + no;
			  $(selector).html(allvals);
		   });
		
		 $("#viewSubfields").modal("hide");
		   e.stopPropagation();
		});
		

//Add new subfields
$("a#addNewSubf").click(function() {
	var arraySubf = [];
	var module = $("#module").val();

	$("#subfTable tr.primarySubf").each(function(){
		 var value = $(this).find("td.subfields").text();
		 arraySubf.push(value.trim());
	});
		var GL23TAG = $("#tagValue").text();
		var createKey = $(this).data("tagid");
		$.get("SubfieldList",{subfields:arraySubf.toString(), GL23TAG:GL23TAG, createKey:createKey, module:module},function(data_title){
		$("#modalSubf").html(data_title);
		   });	
		
	
});

/*$(".data").blur(function(event) {
	var arraySubf = [];
	var value = $(this).val();
	var str_array = value.split(',');

	for(var i = 0; i < str_array.length; i++) {
		arraySubf.push(value.trim().substring( 0, 1 ));
	}
	var GL23TAG = $(this).find(".tagValue").text();
alert(GL23TAG);
	$.get("Handler_BOValidation",{subfields:arraySubf.toString(), GL23TAG:GL23TAG,validation:"subfvalidation"},function(data_title){
		alert(data_title);
	});	
	event.preventdefault();
	
});*/

$("#submits").click(function() {
	
$get("Uploads", function(){

})
});

var maxWidth = 878;
function Horloge(maxWidth) {
	
    var w = $('#myBar1').width();
    var percent = parseInt((w * 100) / maxWidth);
    $('#myBar1').html(percent + ' %');

}

// In use
$("#convert").click(function(event) {

	 //stop submit the form, we will post it manually.
   event.preventDefault();
  var type = $("input[name='modal_MARC']:checked").val();

 if(($('#thefile').val()==''&&type=="MARC") || $('#script option:selected').val()==0){
	 swal("", "Please select value" , "");
 }else{

	   // Get form
	   var form = $('.current_form')[0];
	
		// Create an FormData object
	   var data = new FormData(form);
	   var $bar = $('#myBar1');
	   Horloge(maxWidth);
	   timer = setInterval('Horloge('+maxWidth+')', 100);	
	
	$.ajax({
	   type: "POST",
	   enctype: 'multipart/form-data',
	   url: "upload",
	   data: data,
	   processData: false,
	   contentType: false,
	   cache: false,
	   success: function (data) {
		
	   	 if(data.trim()=="converted"){
	   		 $('#myBar1').animate({
	     	       width: "50%"
	     	   }, 9000);
	   		var $bar = $('#myBar1');
	        $bar.stop();
	   		 clearInterval(timer);
	   		 var $bar = $('#myBar1');
		   	   Horloge(maxWidth);
		   	   timer = setInterval('Horloge('+maxWidth+')', 1);
	   		 $('#myBar1').animate({
	          width: "50%"
	      }, 2000, function() {
	          clearInterval(timer);
	      });
	
	   	   var $bar = $('#myBar1');
	   	   Horloge(maxWidth);
	   	   timer = setInterval('Horloge('+maxWidth+')', 100);
	
	   	   var script =  $('#script option:selected').val();
	   	   $('#myBar1').animate({
	   	       width: "80%"
	   	   }, 20000); 
					            $.ajax({
					                type: "POST",
					                enctype: 'multipart/form-data',
					                url: "upload?action=2&script=" + script,
					                data: data,
					                processData: false,
					                contentType: false,
					                cache: false,
					                success: function (data) {
					                	var value = data.split("&");
	
					                	if(value[1].trim()=="done"){
					                	
						                	$.get("Handler_ParamipsTable",{bufferno:value[0].toString(), total:value[2]},function(list){
						                		//alert("sd3");
						                					var $bar = $('#myBar1');
												            $bar.stop();
												       		clearInterval(timer);
												       		var $bar = $('#myBar1');
												    	   	Horloge(maxWidth);
												    	   	timer = setInterval('Horloge('+maxWidth+')', 1);
												       		$('#myBar1').animate({
												            width: "100%"
												          }, 2000, function() {
												              clearInterval(timer);
												          });
												        $(".tableList").html(list);
	
											});
					                	}	
					                },
					                error: function (e) {
		
					                    $("#result").text(e.responseText);
					                    console.log("ERROR : ", e);
					                    $("#btnSubmit").prop("disabled", false);
		
					                }
					            });
				        } else if(data.trim()=="invalid_length"){
				        	swal("", "Invalid record length" , "");
				        } else{
				        	swal("", data , "");
				        }
	       $("#result").text(data);
	       console.log("SUCCESS : ", data);
	       $("#btnSubmit").prop("disabled", false);
	
	   },
	   error: function (e) {
	
	       $("#result").text(e.responseText);
	       console.log("ERROR : ", e);
	       $("#btnSubmit").prop("disabled", false);
	
	   }
	   
	});
 }
	/*	   }
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


//Reset form --In use
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


//Click modify to enable to edit form 
function modifyRcrd(){
	
	 $('#BO_Record').find('textarea, button, select, a, .rawtag, .indi1, .indi2').removeAttr("disabled");
	 //$('#BO_Record').find('textarea, button, select, a, input').prop("disabled", false);
	 
	 $("#duplicateCopy").addClass("isDisabled");
	$("#addTag").removeClass("isDisabled");
	$("#searchAccNo").addClass("isDisabled");
	$("#addBORcrd").removeClass("isDisabled");
	$("#indexBO").removeClass("isDisabled");
	$("#saveindex").removeClass("isDisabled");
	$("#unindex").addClass("isDisabled");
	$("#delete").removeClass("isDisabled");
	$("#modify").addClass("isDisabled");

	$('.Cstatus').html("Modify Record");
}


function sortTable() {
	  var table, rows, switching, i, x, y, shouldSwitch;
	  table = document.getElementById("BibRcrd");
	  switching = true;
	  /*Make a loop that will continue until
	  no switching has been done:*/
	  while (switching) {
	    //start by saying: no switching is done:
	    switching = false;
	    rows = table.getElementsByTagName("tbody");
	    /*Loop through all table rows (except the
	    first, which contains table headers):*/
	    for (i = 1; i < (rows.length - 1); i++) {
	      //start by saying there should be no switching:
	      shouldSwitch = false;
	      /*Get the two elements you want to compare,
	      one from current row and one from the next:*/
	      x = rows[i].getElementsByTagName("TD")[0];
	      y = rows[i + 1].getElementsByTagName("TD")[0];
	      //check if the two rows should switch place:
	      
	      if (Number(x.innerHTML) > Number(y.innerHTML)) {
	        //if so, mark as a switch and break the loop:
	        shouldSwitch = true;
	        break;
	      }
	    }
	    if (shouldSwitch) {
	      /*If a switch has been marked, make the switch
	      and mark that a switch has been done:*/
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	    }
	  }
	}
//Add new tag


var values = 11;
function addTag(tableID,ctmstr) {

	var tags = $(".tags").val();
	var tagdesc = $(".tags :selected").attr('data-desc');
	var auth = $(".tags :selected").attr('data-auth');
	var repeat = $(".tags :selected").attr('data-repeat');
	var indi1 = $(".tplindi1").val();
	var indi2 = $(".tplindi2").val();
	var button = "";
	var authbutton = "";
	var repeatbutton = "";
	if(indi1=="undefined"){
		indi1 = "#";
	}
	if(indi2=="undefined"){
		indi2 = "#";
	}
	var data = "";
	
	var datadefault =$(".tags :selected").attr('data-default');
	
	if(datadefault.trim().length==0 || datadefault==null){
		 $('#subfields option:selected').each(function() {
			 data+=$(this).val();
		 });
	}else{
		data = datadefault.trim();
	}
	 
	 var disabled = "";
	 var className = "";
	 if(tags=="000"||tags=="006"||tags=="007"||tags=="008"){
		 button = "<a data-toggle='modal' onclick='ctrlTags(this)' data-template='' data-target='#ctrlTagsForm' data-tagid='' class='btn btn-info btn-sm editdata' title='Add Control Fields' value='"+tags+"'>" + 
		 		"<span class='glyphicon glyphicon-edit' title='Control Tag'></span></a>&nbsp;";
		 disabled = "disabled";
		 className = "controltag";
	 }else{
		 button = "<a data-toggle='modal' data-autflag='"+auth+"' data-template='' onclick='openModal(this)' data-target='#viewSubfields' class='btn btn-info btn-sm openModal editdata' data-tagid='' data-tagdesc='"+tagdesc+"'><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a>&nbsp;"
		 className = "rawtag";
	 }

	 if(auth=="Y"){
		 authbutton = "	<a class='btn btn-sm linkage' style='background-color:666699;color:white' title='Linkage' onclick='linkage(this)'><span class='glyphicon glyphicon-link'></span></a>";
	 }
	 
	 if(repeat=="Y"){
		 repeatbutton = "<a class='btn btn-dull btn-sm duplicateTag' title='Duplicate Tag' style='background-color:#00CED1;color:white' onclick='duplicateTag(this)'><span class='glyphicon glyphicon-duplicate'></span></a>";
	 }
	 
	var html = "<tr>" + 
				"<td class='tagValue'><span class='tag'>"+tags+"</span>&nbsp;" + 
				"<span class='glyphicon glyphicon-question-sign desc' title='"+tagdesc+"'></span></td>" + 
				"<td><input type='text' size='1' maxlength='1' class='form-control indi1' value='"+indi1+"'></td>"+ 
				"<td><input type='text' size='1' maxlength='1' class='form-control indi2' value='"+indi2+"'></td>"+ 
				"<td><input type='text' value='"+data+"' class='form-control data "+className+" ' size='100' "+disabled+"></td>" + 
				"<td>"+
				button +
				"<a id='retrieveLinks' class='btn  btn-primary btn-sm' data-toggle='modal' data-target='#termSearch' href='Term_Search?tag="+tags+"&action=direct_termsearch&tagid='>"+
				"<span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span>" + 
				"</a>&nbsp;" + 
				"<i class='fa fa-search-plus' aria-hidden='true'></i>" + 
				"</a>" + 
				"<a class='btn btn-dull btn-sm delete' title='Delete Field'><span class='glyphicon glyphicon-trash'></span></a>&nbsp;" + 
				authbutton + 
				repeatbutton + 
				"</td>" + 
				"</tr>";


	
	var rows = $('#BO_Record tbody').find('tr').length;
	for (var i=0;i<=rows;i++){
		var tag = $('#BO_Record tbody tr').eq(i).find('.tagValue').text();
		var tag1 = $('#BO_Record tbody tr').eq(i+1).find('.tagValue').text();

		if(Number(tag)==Number(tags)){
			if(Number(tag1)==Number(tags)){
				continue;
			}else{
			 $('#BO_Record > tbody > tr').eq(i).after(html);
			 break;
			}
		}else if(Number(tag)>Number(tags)){
			$('#BO_Record > tbody > tr').eq(i).before(html);
			break;
		}else{
		var lasttag = $('#BO_Record tr:last').find('.tagValue').text();

		if(Number(lasttag)<Number(tags)){
			 $('#BO_Record > tbody > tr:last').after(html);
			 break;
		}else{

		 if (Number(tag) <Number(tags)&&Number(tag1) >Number(tags)) {
			 $('#BO_Record > tbody > tr').eq(i).after(html);
			 break;
		 }
		}
		}
		
	}
	//$("#addNewRecord").modal("hide");

	for (var j=0;j<=rows;j++){

		$('#BO_Record tbody tr').eq(j).find('.editdata').attr('data-tagid', j);
		$('#BO_Record tbody tr').eq(j).attr('class', j);
		var currenthref =  $('#BO_Record tbody tr').eq(j).find('#retrieveLinks').attr('href');
		var newcurrenthref = currenthref.split("&");
		var res = currenthref.replace(newcurrenthref[2], "tagid="+j+"");	
		$('#BO_Record tbody tr').eq(j).find('#retrieveLinks').attr('href', res);
	
	}
	$("#addNewRecord").modal("hide");
}



//form changed
form_changed = false;
$("input, select").change(function() {
	form_changed = true;
});

//Open new template
function getTemplate(){
	var tplName = $('#tpl').val();
	var e = document.getElementById("tpl");
    var option= e.options[e.selectedIndex];
    var type = option.getAttribute("data-type");
    var module = $("#module").val();
    var officeId = $("#officer").text();
   // $(".Cuser").text("s");
	loader("#tags");
	
    $.ajax({
     type : "Get", 
     url : "Handler_BORecord?action=NewRecord&type="+ type+"&module="+ module, 
     data : "TPLID=" + tplName ,
     success : function(response) {
    	 $("#loader").fadeOut("slow");
    	 $('.collapse').show();
    	 //$("#Ctype").html("sss");
    	 $("#tags").html(response);
    	 if($(".Cuser").text().trim() ==""){
    			$(".Cuser").text(officeId);
    		}
    		if($(".Muser").text().trim() ==""){
    			$(".Muser").text(officeId);
    		}
    	$("#newUser").val(officeId);
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
 
//Open View Subfields Model -- In use

function openModal(viewSubfield){
	var module = $("#module").val();
	
	var tag =$(viewSubfield).closest("tr").find(".tag").text();
	var indi1 =$(viewSubfield).closest("tr").find(".indi1").val();
	var indi2 =$(viewSubfield).closest("tr").find(".indi2").val();
	var data =$(viewSubfield).closest("tr").find(".data").val(); 
	var tagid = $(viewSubfield).closest("tr").find(".openModal").attr('data-tagid');

	var tagdesc = $(viewSubfield).data("tagdesc");
	var autflag = $(viewSubfield).data("autflag");

	var url = "ViewSubfields?tag=" + tag + "&indi1=" + encodeURIComponent(indi1) + "&indi2=" + encodeURIComponent(indi2) + 
			"&subfields=" + encodeURIComponent(data)+"&tagdesc=" + tagdesc + "&tagid=" + tagid + "&autflag=" + autflag+"&module=" + module;

	$.get(url,function(data){
		$("#subfList").html(data);
	});	
}
/*$(".openModal").click(function(){
	var tag =$(this).closest("tr").find(".tag").text();
	alert(tag);
	var indi1 =$(this).closest("tr").find(".indi1").val();
	var indi2 =$(this).closest("tr").find(".indi2").val();
	var data =$(this).closest("tr").find(".data").val();
	var tagid = $(this).data("tagid");
	var tagdesc = $(this).data("tagdesc");
	alert(tagid);
	var url = "ViewSubfields?tag=" + tag + "&indi1=" + encodeURIComponent(indi1) + "&indi2=" + encodeURIComponent(indi2) + 
			"&subfields=" + data+"&tagdesc=" + tagdesc + "&tagid=" + tagid;
alert(url);
	$.get(url,function(data){
		$("#subfList").html(data);
	});	

})
*/
/*function openCtrlField(data){
	var tag = $(data).data('tag');
	var indi1 = $(data).data('indi1');
	var indi2 = $(data).data('indi2');
	var indiID1 = $(data).data('indiid1');
	var indi1ID = $(data).attr("id");
	var indiID2 = $(data).data('indiid2');
	var tagdesc = $(data).data('tagdesc');
	var tagid = $(data).data('id');
	var ctmstr = $(data).data('ctmstr');
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


	var url = "Modal_CtrlTag?tag=" + tag + "&indi1=" + indi1 + "&indi2=" + indi2 + 
		"&tagdesc=" + tagdesc + "&tagid=" + tagid + "&ctmstr=" + ctmstr + "&data=" + inner + 
		"&indiinc1="+encodeURIComponent(indiID1) + "&indiinc2="+encodeURIComponent(indiID2);
	

	$.get(url,function(data){
	$("#subfList").html(data);
	});	
}*/

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

//Add new subfield --In use
function addRow(tableID, key, type) {

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
		     if(type=="TS"){
		    	 cell3.innerHTML ="<input size='99' class='concatData'type='text' onfocus='getDesc(this);getData(this)' name='"+name+"' data-desc='|"+$(this).text()+"' id='|"+$(this).val()+"' style='width:100%'>";
		     }else{
		    	 cell3.innerHTML ="<textarea class='concatData' onfocus='getDesc(this);getData(this)' name='"+name+"' data-desc='|"+$(this).text()+"' id='|"+$(this).val()+"'  style='width:100%'></textarea>"; 
		     }
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

//Add new subfield --In use
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

$('#ctrlTagsForm').on('shown.bs.modal', function (e) {
	var ctrlTagValue = localStorage.getItem("ctrlTagValue");
	
	})
	


	//To get Control Tag values --In use
	function report() {
		
		var ctrlTag = [];
		
		 $("#mytable tr td").each(function () {
			 if($(this).text().trim().length>0){
				 ctrlTag.push($(this).text().trim());
			 }else{
				 ctrlTag.push("&nbsp;");
			 }
		 });
		 var template = $("#ctrlTagSlct option:selected").val();

		/* $('#myselect option:selected').each(function(){
		        alert(this.value);
		        alert($(this).attr('id'));
		        alert($(this).text());
		   });*/
		var ctrlTagValue = document.getElementById("ctrlTagValue");
		var listValue = document.getElementById("allValue").value;
		localStorage.setItem("ctrlTagValue", ctrlTagValue.value);
		var selector = "#tag_" + ctrlTagValue.value;

		listValue = ctrlTag.toString();
		listValue = listValue.replace(/\,/g,"");
		listValue = listValue.replace(/&nbsp;/g, ' ');
		//$(selector).html(listValue);
		//$('#BO_Record tbody tr').eq(ctrlTagValue.value).find('.data').val(listValue);

		$('#BO_Record tbody tr.' + ctrlTagValue.value).find('.data').val(listValue);
		$(".controltag").attr("disabled","disabled");
		var tags = $(".tags").val();

		if(tags=="000"){
			$('#BO_Record tbody tr.' + ctrlTagValue.value).find('.editdata').attr("data-template","000");
		}else{

			$('#BO_Record tbody tr.' + ctrlTagValue.value).find('.editdata').attr("data-template",template);
		}

     }
	
	function getCtrlInput(id, values){

		 var attr1 = $(".position").attr("id");
		 var key = event.keyCode || event.charCode;
		    if( key == 8 ){
		    	id = parseInt(id)+values.length;
		    	document.getElementById(id).innerHTML ="";
		    	//backspace pressed
		    }else{
				 for (i = 0; i < values.length; i++) { 
					 document.getElementById(id).innerHTML = values[i];
					 if(document.getElementById(id).innerHTML !=""){
						 id = parseInt(id)+1;
					 } 
				}
		    }
	}
	
	/*$('.ctrlInput').keydown(function(e) {
		// trap the return key being pressed
		if (e.keyCode === 13 || e.keyCode === 8)
		{
		    return false;
		}
		});*/
	
	/*function getCtrlInputDown(id, values){

		 var attr1 = $(".position").attr("id");
		 alert(attr1 + id + values);
		 for (i = 0; i < values.length; i++) { 
			 document.getElementById(id).innerHTML = values[i];
			 if(document.getElementById(id).innerHTML !=""){
				 id = parseInt(id)-1;
			 } 
		}

	}*/
	function getCtrlInput(id, values){

		 var attr1 = $(".position").attr("id");
		 var key = event.keyCode || event.charCode;
		    if( key == 8 ){

		    	id = parseInt(id)+values.length;
		    	document.getElementById(id).innerHTML ="";
		    	//backspace pressed
		    }else{
				 for (i = 0; i < values.length; i++) { 
					 document.getElementById(id).innerHTML = values[i];
					 if(document.getElementById(id).innerHTML !=""){
						 id = parseInt(id)+1;
					 } 
				}
		    }
	}
	
	function getValue(id, values){
		
		var ctrltag = $("#ctrltag").val();
		if(id=="6" && ctrltag.substr(0,3)=="008"){
				if(values=="s"){
					$(".11").attr("disabled", "disabled"); 
					var ctrl = 	$(".11").val(); 
					ctrl = ctrl.length+11;
					$(".11").val(""); 
					for(i=11;i<ctrl;i++){
						 document.getElementById(i).innerHTML = "";
					}
				}else{
					$(".11").removeAttr("disabled");
				}
		}
		var attr1 = $(".position").attr("id");

		 for (i = 0; i < values.length; i++) { 
			 document.getElementById(id).innerHTML = values[i];
			 if(document.getElementById(id).innerHTML !=""){
				 id = parseInt(id)+1;
			 } 
		}
	}
	//Get control tag and media record values onchange
	function getValues(id, values){
	
		var attr1 = $(".position").attr("id");
		
		 for (i = 0; i < values.length; i++) { 
			 document.getElementById(id).innerHTML = values[i];
			 if(document.getElementById(id).innerHTML !=""){
				 id = parseInt(id)+1;
			 } 
		}
		

	}
	

	
	function savePrivate(){
		var staffView = document.getElementById("staff");
		var matno = $(".Cmatno").text();
		var value = "";
		 if (staffView.checked == true){
			 value = "Y";
		 }else{
			 value = "N";
		 }

		 $.get("Handler_UpdateSecurity",{ctrlno:matno, status: value},function(data){
			 if(data.trim()=="successful"){
				 swal("", "Successfully updated", "");
			 }else{
				 swal("", "Fail to save", "");
			 }
		 });
	}
	

//Ajax/Jquery functions in BO Organisation
	$(document).ready(function() {
	
		   $.get("template",{MODULE:"Cataloging/02_Bibliographic_Organisation",ACTION:"listBibRecord.jsp"},function(data_title){
		   });
		   
		   $("#paraZ39").on('shown.bs.modal', function () {
				$("input[type=radio][value=MARC]").prop("checked",true);
				 $('a[id="z3950"]').attr('disabled','disabled');
				 $('input[type="file"]').removeAttr('disabled');
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
   				 //$("input, .editdata, #retrieveLink, .delete ").attr("disabled","disabled");
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
    
/*   	$('#retrieveBO input').on('change', function() {
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
		});*/
	
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
	
/*	$("a#indexBO").click(function() {
		$('span.Cstatus').html("");

		var matno ="";
		var createby = "";
		var createdate = "";
		var creatime = "";
		var CT04MATNO = "";
		var indi1 = [];
		var indi2 = [];
		var values = [];
		var raw = [];
		var manda = [];
		
			
			$('#rcrddetails tbody').each(function(index){
				createby = $(this).find("span.Cuser").text().trim();
				createdate = $(this).find("span.Cdate").text().trim();
				creatime = $(this).find("span.Ctime").text().trim();
				modiby = $(this).find("#newUser").val().trim();
				modidate = $(this).find("span.Mdate").text().trim();
				moditime = $(this).find("span.Mtime").text().trim();
				matno = $(this).find("span.Cmatno").text().trim();
			});
		$('#BO_Record tbody').each(function(index){  
			  var tag = $(this).find("td.tagValue").text().trim();
			  var indis1 = $(this).find("input.indiValue1").val().trim();
			  var indis2 = $(this).find("input.indiValue2").val().trim();
			  var rawdata;
		
			  if(tag=="000"){
				  rawdata= $(this).find("td.subfield").text();
			  }else{
				  rawdata=$(this).find("td.subfield").text().trim();
			  }
			  
			 indi1.push(indis1);
			 indi2.push(indis2);
			 values.push(tag.trim());
			 raw.push(rawdata);

			 if($(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)=="|" || 
					 $(this).find("td.subfield").data("manda")=="Y" && ($(this).find("td.subfield").text()).substring(2,3)==""){

				 manda.push($(this).find("td.tagValue").text());
			 }
			
	});

		if(manda.length<=0){
					 
			 indexing(matno,values,indi1,indi2,raw,raw.length,createby,creatime,
						createdate, modiby,modidate, moditime,"DuplicateChk");
		
		}else{
			 alertMessage("", "info", "115", null, null);
		 }
		
		    $('#tpl option[value="0"]').attr("selected",true);
		
	});*/
   	  
/*	$("#enableSelect").click(function(){
		alert("sdsds");
        $('#tpl').prop('disabled', false);
     });*/
	
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
	
	    }else{
	    
	    }
	});

/*	$("#titleSearch").on('hidden.bs.modal', function () {
	    $(this).data('bs.modal', null);
	});
	*/
	
/*	$('.concatData').focusout(function(){
		var tag = $('#tagValue').text();
		 $.get("Duplicate_Record",{data:$(this).val(), tag: tag, action:"keyInData"},function(data_title){
			 if(data_title.trim() =="true"){
				 localStorage.setItem("duplicate", "Y");
				  alertMessage("Successful", "success", "114",tag, "@tag"); 
			 }else{
				 localStorage.setItem("duplicate", "N");
			 }			 
		 });
	});*/
	

	$('.data').blur(function(){
		 var $tr = $(this).parents("tr");
		 var tag = $tr.find("td").eq(0).text() ;

		 $.get("Duplicate_Record",{data:$(this).val(), tag: tag.trim(), action:"keyInData"},function(data_title){

			 if(data_title.trim() =="true"){
				 localStorage.setItem("duplicate", "Y");
				  alertMessage("Successful", "success", "114",tag, "@tag"); 
			 }else{
				 localStorage.setItem("duplicate", "N");
			 }			 
		 });
	});
	
	
	
	$("#BibRcrd :input").change(function() {
		   $("#BibRcrd").data("changed",true);
		});
	
	//Delete tag -- In use
	$('#BO_Record').on('click', '.delete', function () {
		 $(this).closest('tr').remove();
	     return false;
	})
	
$("a#showIndi").click(function() {

		var GL23TAG = $(this).data("id");
		var module = $("#module").val();
		
		$.get("Indicator_Help",{GL18TAG:GL23TAG, module:module},function(data_title){
			$("#BOIndi").modal("show");
			$("#indicator_help").html(data_title);
		   });	
		
	
});
	

	
	
	
	
	
	function disableAllAction(){
		$("#duplicateCopy").addClass("isDisabled");
		$("#addTag").addClass("isDisabled");
		$("#searchAccNo").addClass("isDisabled");
		$("#addBORcrd").addClass("isDisabled");
		$("#indexBO").addClass("isDisabled");
		$("#saveindex").addClass("isDisabled");
		$("#unindex").addClass("isDisabled");
		$("#delete").addClass("isDisabled");
		$("#modify").addClass("isDisabled");
	}
	

	
	$("a#delete").click(function() {

		var CT02MATNO = "";
		var status = $(".Cstatus").text();
		$('#rcrddetails tbody').each(function(index){
			CT02MATNO = $(this).find("span.Cmatno").text();
		});	 

		$.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"checkRecord",status:status},
				function(record_exist){
		
		if(record_exist.trim()=="approve"){
			swal({
				  title: 'Are you sure '+CT02MATNO+'?',
				  text: "You won't be able to revert this!",
				  type: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then(function() {
					$.get("Handler_DeleteBO",{CT02MATNO:CT02MATNO,action:"confirmDelete"},
							function(confirmation){
								
								
						if(confirmation.trim()=="ok"){
							  swal(
									    'Deleted!',
									    'Your file has been deleted.',
									    'success'
									  );
							  $(".Cstatus").html('Deleted');
							  location.reload();
						}
					});
				})
		}else if(record_exist.trim()=="declineACORDD"){
			 alertMessage("", "info", "175", CT02MATNO, '@matno');
		}else{
			 alertMessage("", "info", "015", CT02MATNO, '@matno');
		}
	 });
	});
	
	$('#termSearch').on('hidden.bs.modal', function(e)
	{ 
	    $(this).removeData();
	}) ;
	
	$("#cancelDupCpy").click(function(){
		$(".cancelcpy").css("display", "");
		$(".duplicatecpy").css("display", "none");
		 /*$.get("Duplicate_Copy",{action:"Cancel Copy"},function(data_title){
			 $(".predetails").css("display", "block");
			 alert(data_title);
					  $('#rcrddetails').html("");
					  $('#rcrddetails').html(data_title);
				  });*/
	})
	$('.marctable').DataTable({
	    responsive: true
	});
	
	function enableSelection(){
		 $('#tpl').prop('disabled', false);
	}
	

