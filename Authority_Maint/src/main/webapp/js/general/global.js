/**
 * 
 */
 
function getDetails(details){
	var title = $("#criteria").val();
	var tag = $('.tags :selected').val();
	var action = $("#action").val();

	if(action.trim()=="fromTerm"){
		localStorage.setItem("fromTerm", title);
		localStorage.setItem("fromTag", tag);
	}
	
	loader("#searchcontent");
	$.get("TermRetrieval",{criteria:title,tag:tag},function(data_title){
		$("#searchcontent").html(data_title);
		$("#changercrd").val("");
	 });	
	
}

function loader(target_id) {
	$(target_id).load('Loading');
}

function getRecord(){

	var titles = [];
	var pointers = [];
	
	  var rows = $("#rglobalchgtbl").dataTable().fnGetNodes();
      for(var i=0;i<rows.length;i++)
      {
    	  
    	  if($(rows[i]).hasClass('selected')){
    		  titles.push($(rows[i]).find("td:nth-child(4)").text());
    			pointers.push($(rows[i]).data("pointer"));
    			localStorage.setItem("tags",$('.tags :selected').val());
    			localStorage.setItem("pointers", pointers.toString());
    			//localStorage.setItem("term", titles.toString());
    			localStorage.setItem("tags",$('.tags :selected').val());
    			localStorage.setItem("pointers", JSON.stringify(pointers));
    			localStorage.setItem("term", JSON.stringify(titles));
    	  }
    	/*  var order = $(rows[i]).find("tr.selected td").eq(4).text();
    	  alert(order);*/
    	
    	   //total.push("'" + order + "'");
      }

/*	$('#rglobalchgtbl > tbody  > tr.selected').each(function() {
		alert($(this).find("td:nth-child(4)").text());
		titles.push($(this).find("td:nth-child(4)").text());
		pointers.push($(this).data("pointer"));
		localStorage.setItem("tags",$('.tags :selected').val());
		localStorage.setItem("pointers", pointers.toString());
		//localStorage.setItem("term", titles.toString());
		localStorage.setItem("tags",$('.tags :selected').val());
		localStorage.setItem("pointers", JSON.stringify(pointers));
		localStorage.setItem("term", JSON.stringify(titles));
	});
	*/
	var nameArr = JSON.parse(localStorage.getItem("term"));
	var pointer = JSON.parse(localStorage.getItem("pointers"));

	var html = "";
	$.each(nameArr,function(i){
		html +=  "<tr><td>"+ nameArr[i] + "<input type='hidden' class='slctdpointer' value='"+pointer[i]+"'><input type='hidden' class='slctdtag' value='"+localStorage.getItem("tags")+"'></td><td><button type='button' class='btn btn-info btn-sm' onclick='remove()' id='remove' title='Delete'>" + 
				"<span class='glyphicon glyphicon-trash'></span>" +
			"</button></td></tr>";
	})
		 $("#fromTerm").append(html);
		$("#retrieval").modal("hide");
	  //localStorage.clear();
		window.localStorage.removeItem('term');
		window.localStorage.removeItem('pointers');
}


/*var titles = [];
var pointers=[];*/
$('.clickable-row').click(function() {
	var action =$("#action").val();
	var data = $(this).find("td:nth-child(3)").text();
	var hit = $(this).find("td:nth-child(3)").text();
	var title = $(this).find("td:nth-child(4)").text();
	var type = $(this).find("td:nth-child(2)").text();
	var pointer = $(this).data("pointer");

	if(action =="toterm"){
		$("#toterm").text(title);
		$("#topointer").val(pointer);
		$("#totag").val($('.tags :selected').val());
		$(".retrieval").modal("hide");
	}else if(action =="fromTerm"){
		$(this).toggleClass('selected');
		if($(this).hasClass("selected")){
			titles.push(title);
			pointers.push(pointer);
			localStorage.setItem("tags",$('.tags :selected').val());
			localStorage.setItem("pointers", pointers.toString());
			//localStorage.setItem("term", titles.toString());
			localStorage.setItem("tags",$('.tags :selected').val());
			localStorage.setItem("pointers", JSON.stringify(pointers));
			localStorage.setItem("term", JSON.stringify(titles));
		}
	}else{
	if(hit==0 && type=="Unused" || hit!=0){
		//document.getElementById("delete").disabled = true;
		$('#delete').prop('disabled', true);
	}else{
		document.getElementById("delete").disabled = false;		
	}
	//document.getElementById("delete").disabled = false;		
		$("#pointer").val(pointer);
		$("#changercrd").val(title);
		$("#term").val(title);
	}
});

	
$("#viewtag, #accept").click(function(){
	var pointer = $("#pointer").val();
	var term = $("#term").val();
	$("#modaltag").modal("show");
	$.get("Modal_TagChange",{pointer:pointer, term:term},function(data){
		$("#tagcontent").html(data);
	});
});


$("#delete").click(function(){
	var pointer = $("#pointer").val();
	var term = $("#changercrd").val();
	//var oriterm = $("#term").val();
	var tag = $('.tags :selected').val()
	
	swal({   
				title: 'Are you sure want to delete?',
				text: '',
				type: 'warning',   showCancelButton: true,
				confirmButtonColor: '#3085d6', 
				cancelButtonColor: '#d33',
				confirmButtonText: 'Yes, delete it!',
				cancelButtonText: 'No, cancel !',
				confirmButtonClass: 'confirm-class',
				cancelButtonClass: 'cancel-class',
				closeOnConfirm: false,
				closeOnCancel: false 
				}).then(function() {
					$.get("Handler_DeleteRecord",{pointer:pointer, tag:tag},function(data){
	
						if(data.trim()=="fail"){
							swal("", "Fail to delete");
						}else{
							swal("", "Successfully Deleted");
							var title = $("#criteria").val();
							var tag = $('.tags :selected').val()
							loader("#loader");
							$.get("TermRetrieval",{criteria:title,tag:tag},function(data_title){
								$("#searchcontent").html(data_title);
							 });	
						}
						//$("#tagcontent").html(data);
					});
							},function(dismiss) {
								  if (dismiss === 'cancel') {
								    swal(
								      'Cancelled',
								      'Your imaginary file is safe :)',
								      'error'
								    );
								  }
								})
								
	
})

$(function(){
	
        $("select[name='tags']").change(function() { 

        	$("#criteria").val("")
        	var criteria = $("#criteria").val();
            if (this.selectedIndex == 0 || $("#criteria").val().trim()==""){
            	document.getElementById("btn-submit-retrievemodal").disabled = true;
                }else{
                	document.getElementById("btn-submit-retrievemodal").disabled = false;
                }
            
            $("#changercrd").val("");
          });
        
        
    	$('#criteria').keydown(function(){
    	
    		var tag = $("#select_tags").val();

    		document.getElementById("btn-submit-retrievemodal").disabled = true;
    	})
    	
    	
    	$('#criteria').keyup(function(){
    		var tag = $("#select_tags").val();

    		if(tag.trim()!=0 && $(this).val()!=""){
    			document.getElementById("btn-submit-retrievemodal").disabled = false;
    		}else{
    			document.getElementById("btn-submit-retrievemodal").disabled = true;
    		}
    	})
}); 


function alertMessage(message, info, code, criteria, label){
	var url = "Error_Message?GL79ERRCODE="+code+"" + 
		"&criteria=" + criteria + "&label="+label+"";
	$.ajax({
			url: url,
			success: function(result) {
				//swal(message,result, info );
				//swal(message,result, info );
				swal({
					  position: 'top',
					  title: result.trim(),
					  showConfirmButton: true,
					  timer: 2500
					})
				//swal( position: 'top',result.trim());
			}
		});
}

