$(document).ready(function(){
	
	var today = new Date(); 
	
	function messageBox(langcode, code, criteria, label){
			$.get("Global?type=Handler&name=Error_Page",{
				GL79LANGCODE : langcode,
				GL79ERRCODE : code,
				criteria : criteria,
			 	label : label},function(result){
			 		//alert(result);
					//swal('',result, 'info' );
					swal(result);
			});
			
			
	} 
	
	
	setup();
	
	
	function setup(){
		$("#branch").prop("selectedIndex",-1);
		$("#tarikh").val(moment(today).format("DD/MM/YYYY"));
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	///////////////////////////// SET MAXLENGTH /////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	$('#noPerolehan').attr('maxlength', 10);
	$('#noahli').attr('maxlength', 12);
	
	
	/////////////////////////////////////////////////////////////////////////
	///////////////////////////// ON BLUR ///////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	////no perolehan
	$("#noPerolehan").blur(function(e){
		
		 e.preventDefault();
		 e.stopImmediatePropagation(); 
	
		var noPerolehan = $("#noPerolehan").val();
		
		
		$.get("Global?type=Table&name=Table_BibSearch",{criteria:noPerolehan,actiontype:"keyupAccNo"},function(title){

			if(title.trim()=="Error"){

				// alert("No Perolehan tidak wujud");
				 messageBox("002","006","","");
				 $("#noPerolehan").val("");
				 $("#save").prop('disabled', true);
				
			}else{
				$("#title").val(title);
				
				$.get('GetAccDetail', {
		        	noPerolehan : noPerolehan
			 	}, function(responseJson) {
				if(responseJson != null){
					
					/*if(responseJson==''){
					}*/
					
					$.each(responseJson, function(key,value) {
						
						if(value['AccStat'] == "C"){
							
							$("#noahli").val(value['AccPatr']);				
							$("#branch").val(value['AccLoca']);
							$("#namapeminjam").val(value['PatrName']);
							$("#alamat").val(value['PatrAdd']);				
							$("#alamat2").val(value['PatrAdd2']);
							$("#alamat3").val(value['PatrAdd3']);
							$("#poskod").val(value['PatrPostcode']);
							$("#negeri").val(value['PatrTown']);
							$("#phone").val(value['Htel']);
							
						
							
						}else{
							//alert("Bahan bukan untuk dalam pinjaman");
							 messageBox("002","008","","");
							$('#formdataitemdamage').trigger("reset");
							$('#formdataitemdamage').data('bootstrapValidator').resetForm();
							setup();
						}
						
						
						
						
						
							
					});
				}
				});
				
				
				 
				 $("#save").prop('disabled', false);
			}
	   });
	});
	
	
	////detail user
	$("#noahli").blur(function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation(); 
		
		
		var noahli = $("#noahli").val();
		
		
		$.get('GetpatrNameAddHphone', {
        	userID : noahli
	 	}, function(responseJson) {
		if(responseJson != null){
			
			/*if(responseJson==''){
			}*/
			
			$.each(responseJson, function(key,value) {
				$("#namapeminjam").val(value['Name']);
				$("#alamat").val(value['Add1']);				
				$("#alamat2").val(value['Add2']);
				$("#alamat3").val(value['Add3']);
				$("#poskod").val(value['Poscode']);
				$("#negeri").val(value['State']);
				$("#phone").val(value['Htel']);
					
			});
		}
		});
	});
	
	
	
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
	$('#formdataitemdamage').bootstrapValidator({
		framework: 'bootstrap',
		 excluded: [':disabled'],
		 icon: {
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        //feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
			noPerolehan: {
                validators: {
                    notEmpty: {
                        message: 'No Perolehan diperlukan'
                    }
                }
            },
			noahli: {
                validators: {
                    notEmpty: {
                        message: 'No Ahli diperlukan'
                    }
                }
            },		 
        }
    })
     .on('success.form.bv', function (e) {
    	 
    	// Prevent form submission
        e.preventDefault();

         // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');

		 var noPerolehan = $('input[name="noPerolehan"]').val();
	     var noahli = $('input[name="noahli"]').val();
		 var branch =  $("#branch").val();
	
	     var namapeminjam = $('input[name="namapeminjam"]').val();
		 var title = $('input[name="title"]').val();
	     var tarikh = $('input[name="tarikh"]').val();
		 var phone = $('input[name="phone"]').val();




		$.post("modifyItemStatus", {
				patrid : noahli,
				accno: noPerolehan,
				newStat : "L",
				id : $("#liferayLogin").val()
			},function(data){
				//alert(data.trim()+"data");
				//var accno = noPerolehan;
				
				
				
				if(data.trim() == "Success"){
					messageBox("002","005","","@action");
					$('#formdataitemdamage').trigger("reset");
					$('#formdataitemdamage').data('bootstrapValidator').resetForm();
					setup();
					
					$.post("SetMailOutput",{idsmstempt : "M001", namapeminjam : namapeminjam,
							noahli : noahli,  title : title, tarikh : tarikh, noPerolehan: noPerolehan,
							phone : phone},
						function(data){
							
							if(data.trim() == "Success"){
								//alert(data+"notification sudah di hantar");
								messageBox("002","007","","");
							}
							
			
						}
						).fail(function(data){
							swal(data+"error");
							
						}).success(function(data){   
							swal(data+"success");
					 });
					
					
				}else{
					alert("fail");
				}
				
				
			}
			).fail(function(data){
				swal("error");
			}).success(function(data){   
			
		});



	});
	
	
	
	
	
	
	
});