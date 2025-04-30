<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.*, 
				com.ilmu.cataloging.Bibliography.*, 
				com.ilmu.cataloging.Template_Maint.*,
				java.util.List, com.ilmu.global.ErrorMessage_Handler, com.ilmu.global.Handler"%>
				
 <link rel="stylesheet" type="text/css" href="http://davidstutz.github.io/bootstrap-multiselect/dist/css/bootstrap-multiselect.css">
 <script type="text/javascript" src="http://davidstutz.github.io/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>	
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>											
 								
<script>
//Plugin for multiselect
$('.multiselect').multiselect({
	 includeSelectAllOption: true,
	 maxHeight: 200
});

$("#closeSubf").click(function(){
	$("#addSubfield").modal("hide");
});
	
</script>
<%
String tag = request.getParameter("tag");
String indi = request.getParameter("indi");
String indilvl = request.getParameter("indilvl");
String GL23TAG = request.getParameter("GL23TAG");
String splitSubfs = request.getParameter("subfields");
String createKey = request.getParameter("createKey");
String module = request.getParameter("module");
System.out.println("S" + splitSubfs);

if(GL23TAG!=null){
	List<Subfield_Handler> subfList = null;
	

	subfList = Subfield_Handler.getListOfSubf(GL23TAG, Handler.marcType(module));
	String datatype = BO_Validation.dataType(GL23TAG, Handler.marcType(module));
	out.println("<div class='modal-header'>");
	out.println("<h4 class='modal-title' id='myModalLabel'>Search</h4>");
	out.println("</div>");
	out.println("<div class='modal-body'>");
	out.println("<select class='multiselect form-control' id='Testingsubfields' name='email' multiple='multiple'>");
	for(Subfield_Handler i: subfList)
	{
		out.println("<option ");
		
		String[] splitSubf = splitSubfs.split(",");
		StringBuilder result = new StringBuilder(); 
		int count = 0;
		if(!splitSubfs.isEmpty()){
			count = 0;
		}else{
			count = 1;
		}
		if ((i.getGL23REPEAT()).equals("N")){

			for(int j = count; j < splitSubf.length; j++)
			{

				String splitData = splitSubf[j].substring(0,1);
				
				if((i.getGL23SUBF()).equals(splitData)){
					out.println("disabled=disabled ");
				}
			} 
		}
		out.println("value='"+i.getGL23SUBF()+"'data-id='"+i.getGL23REPEAT()+"'>" + i.getGL23SUBF() + "-" + i.getGL23DESC()+"</option>");
		
	}
	out.println("</select>");
	out.println("</div>");
%>
<div class='modal-footer'>
	<a class='btn btn-info tester' onclick="addRow('subfTable','<%=createKey%>', '<%=datatype%>')">
			Add Subfield
		</a>
	<button class='btn btn-info' id="closeSubf">Cancel</button>
</div>
<%
}

%>