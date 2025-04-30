$(document).ready(function() {
	
	$('body').on('hidden.bs.modal', '.modal', function () {
	     $(this).removeData('bs.modal');
	});
	
	$('#GL17TAG').blur(function(event) {
		//alert("test");
		
		var marc = $('#GL17MARC').val();
		var id = $('#GL17TAG').val();
		//alert("test 2");
		$.get('CheckingTagP', {
			GL17TAG : id, GL17MARC : marc
		}, function(responseText) {
			//alert("test 3");
			$('#ajaxResponse').text(responseText);
		});
	});
});

function toUppercase(){
	
	//alert("testing");
	var x = document.getElementById("GL10ICAT");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL10DESC");
	x.value = x.value.toUpperCase();
	var x = document.getElementById("GL10DISPLAY");
	x.value = x.value.toUpperCase();
	
	
}


function indilevel1(){
	
	//alert("Indicator 1");
	
	var GL18INDILVL = "1";
	var GL18INDI = [];
	
	$('input:checkbox[name=GL18INDI_1]').map(function() {   
		
		 if($(this).prop('checked') === true){
			 GL18INDI.push($(this).attr('value'));
			 
	     }
	});

	return [GL18INDILVL, GL18INDI];

}

function indilevel2(){
	
	//alert("Indicator 1");
	
	var GL18INDILVL = "2";
	var GL18INDI = [];
	
	$('input:checkbox[name=GL18INDI_2]').map(function() {   
		
		 if($(this).prop('checked') === true){
			 GL18INDI.push($(this).attr('value'));
			 
	     }
	});

	return [GL18INDILVL, GL18INDI];
}


function addTag() {
	/*alert("Masuk Javasript");
	var GL17MARC = $('#GL17MARC').val();
	var GL17TAG = $('#GL17TAG').val();
	var GL17DESC = $('#GL17DESC').val();
	var GL17TRUC = $('#GL17TRUC').val();
	var GL17TABNAME = $('#GL17TABNAME').val();
	var GL17ACRO = $('#GL17ACRO').val();
	var GL17GRID = $('#GL17GRID').val();
	var GL17LEN = $('#GL17LEN').val();
	var GL17IDXLANG = $('#GL17IDXLANG').val();
	var GL17DEFAULT = $('#GL17DEFAULT').val();
	var GL17REMARK = $('#GL17REMARK').val();
	
	if (document.getElementById('GL17REPEAT').checked) {
		var GL17REPEAT = "Y";
	}else{
		var GL17REPEAT = "N";
	}
	
	
	if (document.getElementById('GL17AUTFLAG').checked) {
		var GL17AUTFLAG = "Y";
	}else{
		var GL17AUTFLAG = "N";
	}
	
	if (document.getElementById('GL17MANDA').checked) {
		var GL17MANDA = "Y";
	}else{
		var GL17MANDA = "N";
	}
	
	if (document.getElementById('GL17CPFLAG').checked) {
		var GL17CPFLAG = "Y";
	}else{
		GL17CPFLAG = "N";
	}
	
	if (document.getElementById('GL17IDXFLAG').checked) {
		GL17IDXFLAG = "Y";
	}else{
		var GL17IDXFLAG = "N";
	}
	
	if (document.getElementById('GL17PARAMIPS').checked) {
		var GL17PARAMIPS = "Y";
	}else{
		var GL17PARAMIPS = "N";
	}
	
	if (document.getElementById('GL17KEYWORD').checked) {
		var GL17KEYWORD = "Y";
	}else{
		var GL17KEYWORD = "N";
	}
	
	if (document.getElementById('GL17MEDIA').checked) {
		var GL17MEDIA = "Y";
	}else{
		var GL17MEDIA = "N";
	}
	
	if (document.getElementById('GL17STOP').checked) {
		var GL17STOP = "Y";
	}else{
		var GL17STOP = "N";
	}


	
	$.get('Handler_AddTagP', {GL17MARC:GL17MARC, GL17TAG:GL17TAG, GL17DESC:GL17DESC, GL17TRUC:GL17TRUC, GL17TABNAME:GL17TABNAME,
		GL17ACRO :GL17ACRO,GL17GRID : GL17GRID,GL17LEN :GL17LEN,GL17IDXLANG :GL17IDXLANG,GL17DEFAULT :GL17DEFAULT,
		GL17REMARK :GL17REMARK, GL17REPEAT:GL17REPEAT,GL17AUTFLAG:GL17AUTFLAG, GL17MANDA:GL17MANDA, GL17CPFLAG:GL17CPFLAG,
		GL17IDXFLAG:GL17IDXFLAG,GL17PARAMIPS:GL17PARAMIPS,GL17KEYWORD:GL17KEYWORD, GL17MEDIA:GL17MEDIA,GL17SPELL:GL17SPELL}, function(responseText) {
		
		, , GL18INDILVL:GL18INDILVL, GL18INDI:GL18INDI
				$('#ajaxResponse').text(responseText);
	});*/
	
	alert(indilevel1());
	alert(indilevel2());
	

		
}



function updateItemCat() {
	//alert("Update Patron Status");
	var GL10ICAT = $('#GL10ICAT').val();
	var GL10DESC = $('#GL10DESC').val();
	var GL10DISPLAY = $('#GL10DISPLAY').val();
	var GL10ELIG = $('#GL10ELIG').val();

	if (document.getElementById('day').checked) {
		GL10UNIT = "D";
	}else if (document.getElementById('hour').checked) {
		GL10UNIT = "H";
	}
	
	
	if (document.getElementById('GL10RESERVEC').checked) {
		GL10RESERVEC = "Y";
	}else{
		GL10RESERVEC = "N";
	}
	

	$.get('UpdateItemCat', {GL10ICAT:GL10ICAT, GL10DESC:GL10DESC, GL10DISPLAY:GL10DISPLAY,GL10ELIG:GL10ELIG,GL10UNIT:GL10UNIT,GL10RESERVEC:GL10RESERVEC}, function(responseText) {
		//alert("test 3");
		$('#ajaxResponse').text(responseText);
	});
		
	}
