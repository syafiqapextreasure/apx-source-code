<%@ page import="com.ilmu.global.serial.*, java.util.List"%>
	
	<script>
	// Update placeholder for vendor after changes in select
    function updatePlaceholder() {
    	$("#criteria").attr("placeholder", $("#search-type option:selected").text());
    	$("#criteria").val("");
    }
	
	// Send vendor info
	function send_vendor_info() {	
		// Hide the search form
		$('#search_vendor').collapse("hide");
		// Show the result form
		$('#result_vendor').collapse("show").height("auto");
		return false;
	}
	
	/* FOR ISSUE PATTERN*/
    function patternSelect(patternID, pattern)
    {
    	// Hide the modal
		//$('#modal_pattern').modal('hide');
    	$('#modal_pattern').modal('toggle');
    	
		// Replace value
		///$("#issuePattern").val("Pattern " + patternID + " ("+pattern+")");
		$("#issuePattern").val(pattern);
		$(".xxx").text("Pattern " + patternID + " ("+pattern+")");
		$("#hidden-patternID").val(patternID);
		$("#hidden-firstPattern").val(pattern);
		
		updateCardex();
    }
	
    function updateCardex()
    {
    	//Clear all
    	$("table-cardex").html("");
    	var data;
    	
    	var firstVolume = $("#firstVolume").val();
    	var quantity = $("#quantity").val();
    	var noOfIssues = $("#numberOfIssues").val();
    	var startDate = $("#startDate").val();
    	var pattern = $("#hidden-patternID").val();
    	//var freq = $('#frequency:selected').val(); 
    	var freq = $('.frequency :selected').val();
    
    	//Cardex logic
    	if(startDate != ""){
    		calStopDate(startDate, freq, noOfIssues);
    	}
    	
    	generateCardex();
    	generateTableCardex();
    }
    
    
    function calStopDate(startDate, pattern, noOfIssues)
    {
    	var url = "ComputeStopDate?startDate=" +startDate+"&freq=" +pattern+ "&noOfIssues="+noOfIssues;
    	var stopDate = null;
    	$.ajax({
			url: url,
			success: function(result){
				alert(result);
				$("#stopDate-disabled").val(result);
			}
		});
    }
    
    function generateCardex()
    {
    	var firstVolume = $("#firstVolume").val();
    	var pattern = $("#hidden-patternID").val();
    	var quantity = $("#quantity").val();
    	var noOfIssues = $("#numberOfIssues").val();
    	
    }
    

	// Search vendor result from SearchVendor.jsp
	$(document).ready(function(){
$("#btn-geneCardex").click(function(){
			
			var firstVolume = $("#firstVolume").val();
	    	var quantity = $("#quantity").val();
	    	var noOfIssues = $("#numberOfIssues").val();
	    	var startDate = $("#startDate").val();
	    	var pattern = $("#hidden-patternID").val();
	    	var firstPattern = $("#hidden-firstPattern").val();
	    	var freq = $("#frequency").val();
	    	
	    	if(firstVolume == "" || quantity == "" || noOfIssues == "" || startDate== ""|| pattern=="" || firstPattern == "")
	    		alert("Please fill in necessary details.");
	    	else
	    	{
	    		var url = "../component/Table_GenerateIssuesList.jsp?patternID=" +pattern+"&firstPattern=" +firstPattern+"&startDate="+startDate;
				url += "&quantity="+quantity+"&noOfIssues=" + noOfIssues+"&firstVolume="+firstVolume+"&freq="+freq;
		   		// Execute AJAX Update
		   		//replaceLoader("#tableResultDiv");
		   		showPopupMsg("Generating cardex","warning");
		   		$.ajax({
		   			// Title
		   			url: url,
		   			success: function(result) {
		   				$("#table-cardex").html(result);
		   				showPopupMsg("Cardex generated successfully.","success");
		   			}
		   		});
	    	}
			
		});
	});
	</script>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<h4 class="modal-title" id="myModalLabel">Issue Pattern</h4>
	</div>
	
	<div class="modal-body">
		<div class="panel panel-default" id="form_parent">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#form_parent">Pattern List</a>
					</h4>
				</div>
				<div class="panel-collapse collapse in">
					<div class="panel-body">
					<table class="table table-condensed" style="border-collapse:collapse;font-size:10pt">
					<col width="10%">
					<col width="40%">
					    <tbody>
					    <%
					    List<IssuesPattern> list = IssuesPattern.getAllPattern();
					    //http://www.bootply.com/fdTMNTiLis
					    int counter=1;
					    for(IssuesPattern i: list)
					    {
					    	
					    	//Issue name
					    	out.println("<tr data-toggle='collapse' data-target='#demo"+counter+"' class='accordion-toggle'>");
					    	out.println("<td><button class='btn btn-default btn-xs'><span class='glyphicon glyphicon-eye-open'></span></button></td>");
					    	out.println("<td>Pattern "+i.getPatternID()+" ("+i.getList().get(0)+" )</td></tr>");
					    	
					    	//Expandable row item
					    	out.println("<tr><td colspan='12' class='hiddenRow'>");
					    	out.println("<div class='accordian-body collapse' id='demo"+counter+"'>");
					    	
					    	int patternCounter = 1;
					   		out.println("<table class='table' style='font-size:10pt'><col width='10%'><col width='40%'>");
					    	for(String s: i.getList())
					    	{
					    		out.println("<tr><td width='50px'></td><td><button class='btn btn-default btn-xs'><span class='glyphicon glyphicon-ok'");
					    		out.println("onclick=\"patternSelect("+i.getPatternID()+", '"+s+"')\" >");
					    		out.println("</span></button></td>");
					    		out.println("<td>"+s+"</td></tr>");
					    	}
					    	out.println("</table>");
					    	
					    	out.println("</div></td></tr>");
					    	counter++;
					    }
			    
					    %>     
					    </tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal-footer"></div>