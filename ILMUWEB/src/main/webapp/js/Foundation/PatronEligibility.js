$('#example1 tr td:last-child').on("click",function(){
	addRow();   
	});
var count = 2;


	function addRow(){
		
		alert($(this).parent().parent().find('.rowNo').text());
		
	    $sampleRow = $(' <tr class="rowId"><td class="rowNo">'+(count++)+'</td><td></td><td contenteditable="true"></td> <td contenteditable="true"></td><td contenteditable="true"></td></tr>');
	    $sampleRow.find("td:last").click(function(){
	    	addRow();
	    });
	   $('#example1 tbody').append($sampleRow);
	   var GL08STOP = document.getElementById('GL08STOP');
	   $('GL08STOP').val();
	   
	   
}
	

	
