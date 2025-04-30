$(document).ready(function(){
	
	$(".toremove").remove();
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
	
	$("#hntrcdgn").prop("disabled",true);
	reload();
	
	
	//////table setup
	var t = $('#tblCdgnBahan').DataTable( {
		destroy: true,
		searching: false,
		bLengthChange: false,
		autoWidth: false,
		responsive: true,
		language : {
         zeroRecords: " "             
    	},
		columnDefs: [{ targets: [8,9], className: 'dt-body-right' },
                         //  { targets: [0, 1, 2,3,4,5,6,7], className: 'dt-body-left' }
                         ],
	});
	
	
	
	function reload(){
		var userID = $("#liferayLogin").val();
		$("#idcadang").val(userID);
		$("#jenisbahan, #subjek").prop("selectedIndex",-1);
		$('#inputtarikh').val(moment(today).format("DD/MM/YYYY"));
		$("#Tambah").show();
		$("#EditCdgn").hide();
		
		////get user datil by id
	$.get('GetUserDetailByID', {
        	userID : userID
	 	}, function(responseJson) {
		if(responseJson != null){
			
			/*if(responseJson==''){
			}*/
			
			$.each(responseJson, function(key,value) {
				
				$("#namacadang").val(value['Name']);
				$("#phone").val(value['Htel']);
				$("#email").val(value['Email']);
				
			});
		}
		});
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////
	///////////////////////////// SET MAXLENGTH /////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	$('#judul, #pengarang').attr('maxlength', 255);
	$('#isbn, #edisi').attr('maxlength', 50);
	$('#penerbit').attr('maxlength', 100);
	$('#noLC').attr('maxlength', 20);
	
	
	/////////////////////////////////////////////////////////////////////////
	//////////////////////// key in number only /////////////////////////////
	/////////////////////////////////////////////////////////////////////////

	$("#set, #copy").on("keypress keyup blur",function (event) {    
        $(this).val($(this).val().replace(/[^\d].+/, ""));
         if ((event.which < 48 || event.which > 57)) {
             event.preventDefault();
         }
     });
     
     
     //////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	  var counter = 1;
 
   /* $('#Tambah').on('click', function () {
	
	
		var judul = $('input[name="judul"]').val();
		var subjek =  $("#subjek").val();
		var isbn = $('input[name="isbn"]').val();
		var jenisbahan = $("#jenisbahan").val();
		var pengarang = $('input[name="pengarang"]').val();
		var penerbit = $('input[name="penerbit"]').val();
		var edisi = $('input[name="edisi"]').val();
		var bilsalin = $('input[name="copy"]').val();
		var bilset = $('input[name="set"]').val();
		var remarks = $('#remarks').val();
		if(remarks === undefined){
			remarks = "";
		}
	
		
       
     	
    });*/
    
    
    $('#detailcdgn').on('hidden.bs.modal', function () {
		$('#formdataborangcadangan').data('bootstrapValidator').resetForm();
    	$('#detailcdgn form')[0].reset();
    	$("#jenisbahan, #subjek").prop("selectedIndex",-1);
    	$("#Tambah").show();
		$("#EditCdgn").hide();
    });
    
     $.fn.rowCount = function() {
	    return $('tr', $(this).find('tbody')).length;
	};
    
    
     $.fn.rowVal = function() {
	    return $('tr', $(this).find('tbody')).val();
	};
    
    $('#hntrcdgn').on('click', function () {
	
		var rowCount =  $('#tblCdgnBahan >tbody >tr').length;


		if(rowCount == 1 &&  t.row( 0 ).data() == undefined){
			messageBox('002', '020', "", "");
			//messageBox(langcode, code, criteria, label)
		}else{
		
			//for table
			var list = [];
			$('#tblCdgnBahan > tbody  > tr').each(function(i, el) {
			var $tds = $(this).find('td');
			  var obj = {
			    //no: $tds.eq(0).text(),
			    judul: $tds.eq(1).text(),
			    subjek: $tds.eq(2).text(),
			    isbn: $tds.eq(3).text(),
			    jenis_bahan: $tds.eq(4).text(),
			    pengarang: $tds.eq(5).text(),
			    penerbit: $tds.eq(6).text(),
			    edisi: $tds.eq(7).text(),
			    bilsalinan: $tds.eq(8).text(),
			    bilset: $tds.eq(9).text(),
			    catatan: $tds.eq(10).text()
			  }
			list.push(obj);
			});
			
			
			///maklumat pencadang
			var idcadang = $('input[name="idcadang"]').val();
		 	var inputtarikh = moment($("#inputtarikh").val(), 'DD/MM/YYYY').format("YYYYMMDD");
		 	var namaAhli = $('input[name="namacadang"]').val();
		 	var mail = $("#email").val();
			var phone = $("#phone").val();
			var tarikh = $("#inputtarikh").val();
			
			


			$.post("Handle_BorangCadangan", {cadangan : JSON.stringify(list),
					totalrow : list.length,
					idcadang : idcadang, inputtarikh : inputtarikh}, function(result){

				
				if(result.trim().includes('Success')){
					
					var getaccno = removeFirstWord(result);
					///alert("result"+result);
					getaccno = getaccno.replace("[", "('");
					getaccno = getaccno.replace("]", "')");
					getaccno = getaccno.replaceAll(", ", "', '");
					
					///alert("final"+getaccno);
					//messageBox("002","004","","");
					//alert("Berjaya daftar");
					messageBox("002","005","Simpan","@action");
					t.clear().draw();
					//$('#formdataborangcadangan').trigger("reset");
					//$('#formdataborangcadangan').data('bootstrapValidator').resetForm();
					
					reload();


					$.post("SetMailOutputCadangan",{idsmstempt : "M003", namaAhli : namaAhli,
							idcadang : idcadang,
							emailAhli : mail,  phone : phone, tarikh : tarikh, accno :getaccno},
						function(data){
							
							if(data.trim() == "Success"){
								///alert("x buat lagi")
							}
							
			
						}
						).fail(function(data){
							swal(data+"error");
							
						}).success(function(data){   
							swal(data+"success");
					 });
					
					
					
					
					
					
				}
  		});

			
		}
	
	});
	
	
	function removeFirstWord(str) {
  const indexOfSpace = str.indexOf(' ');

  if (indexOfSpace === -1) {
    return '';
  }

  return str.substring(indexOfSpace + 1);
}
    
    


	
	//////////////////////////////////////////////////////////////////////////
	////////////////////////btn Reterive Click ///////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	
	
	
	
	/*$('#Tambah').click(function(){
		alert("tambah");
		
		// Add product to Table
        productAddToTable();

        // Clear form fields
       // formClear();

        // Focus to product name field
        $("#judul").focus();

	});
	
	function productAddToTable() {
		$('.odd').remove();
		// First check if a <tbody> tag exists, add one if not
	    if ($("#tblCdgn tbody").length == 0) {
			
	        $("#tblCdgn").append("<tbody></tbody>");
			//$("#tblCdgn > tbody > tr:first-child").remove();
	    }
	
	    // Append product to the table
	    $("#tblCdgn tbody").append("<tr>" +
	        "<td>" + $("#judul").val() + "</td>" +
	        "<td>" + $("#subjek").val() + "</td>" +
	        "<td>" + $("#jenisbahan").val() + "</td>" +
	        "</tr>");
	}*/
	
	
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////
	
	$('#formdataborangcadangan').bootstrapValidator({
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
			judul: {
	            validators: {
	               notEmpty: {
	                   message: 'Judul diperlukan'
	               },
	           }
	        },
			jenisbahan: {
                validators: {
                    notEmpty: {
                        message: 'Sila pilih Jenis Bahan'
                    }
                }
            },
			copy : {
                validators: {
                    notEmpty: {
                        message: 'Bil Salinan diperlukan'
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

		 var judul = $('input[name="judul"]').val();
		 var subjek =  $("#subjek").val();
		 var isbn = $('input[name="isbn"]').val();
		 var jenisbahan = $("#jenisbahan").val();
		 var pengarang = $('input[name="pengarang"]').val();
		 var penerbit = $('input[name="penerbit"]').val();
		 var edisi = $('input[name="edisi"]').val();
	     var noLC = $('input[name="noLC"]').val();
		 var bilsalin = $('input[name="copy"]').val();
		 var bilset = $('input[name="set"]').val();
		 var remarks = $('#remarks').val();
		 if(remarks === undefined){
			 remarks = "";
		 }
	
	     /*var idcadang = $('input[name="idcadang"]').val();
	 	 var inputtarikh = moment($("#inputtarikh").val(), 'DD/MM/YYYY').format("YYYYMMDD");
	 	 var namaAhli = $('input[name="namacadang"]').val();*/



		//alert("dfdggfd");
		
		t.row.add([counter, judul, subjek, isbn, jenisbahan, 
       	pengarang, penerbit, edisi, bilsalin, bilset, remarks,
      	'<button id="Edit" class="btn btn-info btn-xs"  title="Edit Cadangan Bahan" data-rowid="'+counter+'"><span class="glyphicon glyphicon-pencil"></span></button> ' +
        '<button id="Delete" class="btn btn-dull btn-xs DeleteReq" data-bil="'+counter+'"><span class="glyphicon glyphicon-trash" title="Delete Oder" ></span></button>']).draw(false);
 
        counter++;
        
        $('#formdataborangcadangan').data('bootstrapValidator').resetForm();
     	$('#formdataborangcadangan').trigger('reset');
     	$("#jenisbahan, #subjek").prop("selectedIndex",-1);
     	$("#Tambah").show();
		$("#EditCdgn").hide();
			
		var totalrow = $('#tblCdgnBahan >tbody >tr').length;
		
		if(totalrow > 0 ){
			$("#hntrcdgn").prop("disabled",false);
		}else{
			$("#hntrcdgn").prop("disabled",true);
		}


	});
	
	
	///////edit
	/*$('#detailcdgn').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var modal = $(this);
		var recipient = button.data('whatever'); // Extract info from data-* attributes
		var rowid = button.data('rowid');
		
		alert(recipient);

		modal.find('.modal-title').text(recipient);
	});*/

	
	$('#tblCdgnBahan tbody').on( 'click', '#Edit', function () {
		
		$("#Tambah").hide();
		$("#EditCdgn").show();
		
		var index = $('#tblCdgnBahan').DataTable()
	       .rows({ search: 'applied'})
	       .nodes()
	       .to$()
	       .index($(this).closest('tr'));
	       

	    var data = t.row( $(this).parents('tr') ).data();	 
	    
	    $("#rownos").val(index);
	    $('#detailcdgn').modal('show');
	    $('input[name="judul"]').val(data[1]);
	    $("#subjek").val(data[2]);
	    $('input[name="isbn"]').val(data[3]);
	    
	    $('#jenisbahan').val(data[4]);
	    
	    
	    $('input[name="pengarang"]').val(data[5]);
	    $('input[name="penerbit"]').val(data[6]);
	    $('input[name="edisi"]').val(data[7]);
	    $('input[name="copy"]').val(data[8]);
	    $('input[name="set"]').val(data[9]);
	    $('#remarks').val(data[10]);
	    
	    

	    
	    
	   // t.row.add( $(myTrString)[0] ).draw();

	    
	   
	    
		
	} );
	
	
	$("#EditCdgn").click(function(){
		

		var judul = $('input[name="judul"]').val();
		 var subjek =  $("#subjek").val();
		 var isbn = $('input[name="isbn"]').val();
		 var jenisbahan = $("#jenisbahan").val();
		 var pengarang = $('input[name="pengarang"]').val();
		 var penerbit = $('input[name="penerbit"]').val();
		 var edisi = $('input[name="edisi"]').val();
		 var bilsalin = $('input[name="copy"]').val();
		 var bilset = $('input[name="set"]').val();
		 var remarks = $('#remarks').val();
		 
		var rowno = $("#rownos").val();
		var irowno = parseInt(rowno) + 1;
		///alert("row"+irowno);
		var rows = t.row(rowno);
		
		

	    
		//rows.data([irowno,judul,subjek]).draw();
	    rows.data([irowno, judul, subjek, isbn, jenisbahan, 
       	pengarang, penerbit, edisi, bilsalin, bilset, remarks,
      	'<button id="Edit" class="btn btn-info btn-xs"  title="Edit Cadangan Bahan" data-rowid="'+irowno+'"><span class="glyphicon glyphicon-pencil"></span></button> ' +
        '<button id="Delete" class="btn btn-dull btn-xs DeleteReq" data-bil="'+irowno+'"><span class="glyphicon glyphicon-trash" title="Delete Oder" ></span></button>']).draw();
						

		
		$("#closeModalAdd").click();

		
	});
		
	
	/////DELETE FUNCTION
	$('#tblCdgnBahan tbody').on( 'click', '#Delete', function () {
		
		//alert("sdfsfs")
		
		//var deletereq = $(this).attr('data-bil');
		//alert(deletereq);
		var index = $('#tblCdgnBahan').DataTable()
	       .rows({ search: 'applied'})
	       .nodes()
	       .to$()
	       .index($(this).closest('tr'));//$(this).closest('tr').index();
		//alert(index);
		
		$('#tblCdgnBahan').DataTable().row(index).remove().draw();

		  /*setTimeout(function() { 
         alert($('#tblCdgnBahan >tbody >tr').length);
		
		var totalrow = $('#tblCdgnBahan >tbody >tr').length;
		
		if(totalrow > 0 ){
			$("#hntrcdgn").prop("disabled",false);
		}else{
			$("#hntrcdgn").prop("disabled",true);
			}
	    }, 1000);*/
 
	    
	    
		
		t.on('order.dt search.dt', function () {
			 t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
				cell.innerHTML = i+1;
				//t.cell(cell).invalidate('dom');
			 });
	   }).draw();
	   
	   
	   
	 
	   
	   
	  

	});
	
	
	////click cancel
	/*$("#detailcdgn #cancel").click(function(){
		alert("fdgdgd");
		$('#formdataborangcadangan').trigger("reset");
		$('#formdataborangcadangan').data('bootstrapValidator').resetForm();
					
		reload();
	});*/
	
	
});