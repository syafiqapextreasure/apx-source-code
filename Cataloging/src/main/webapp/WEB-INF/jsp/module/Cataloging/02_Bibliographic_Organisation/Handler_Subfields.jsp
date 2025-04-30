<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.*, 
				com.ilmu.cataloging.Bibliography.*, 
				com.ilmu.cataloging.Template_Maint.*,
				java.util.List, com.ilmu.global.Handler"%>

<%
String tag =request.getParameter("tag");
String title =request.getParameter("title");
String name =request.getParameter("name");
String[] rawArray = title.split("\\|");
String html = "";
String action = request.getParameter("action");
String module = request.getParameter("module");

System.out.println("Name" + name);
if(action.equals("modal_termsearch")){
	System.out.println("Subfieldsd");
for(int i = 1; i < rawArray.length; i++)
{
	String subfield = rawArray[i].substring(0,1);
	String raw = rawArray[i].substring(1);
	System.out.println("Subfieldss" + subfield + tag);
	Subfield_Handler splitData = Subfield_Handler.getSubfDetails(subfield, tag, Handler.marcType(module));
	String datatype = BO_Validation.dataType(tag, Handler.marcType(module));
	System.out.println("Subfield" + splitData);
	if(splitData!=null){
		System.out.println("Subfieldss" + splitData);
	 html += 
		    	"<tr class='primarySubf' id='"+tag+"'>" + 
			"<td>" + 
				"<a class='btn btn-success btn-xs sort'>" + 
					"<span class='glyphicon glyphicon-sort'></span>"+
				"</a>" + 
			"</td>" + 
			"<td class='subf_"+subfield+" subfields'>" + 
				subfield + 
			"</td>" + 
			"<td colspan='2'>";
			if(datatype.equals("TS")){
	 html += 	"<input class='concatData' name='"+name+"' id='|"+subfield+"' data-desc='"+subfield+"-"+splitData.getGL23DESC()+"' value='"+raw+"' data-desc='"+splitData+"' onfocus='getDesc(this);getData(this)' type='text' size='99' style='width:100%'>";
			}else{
	 html += 	"<textarea class='concatData' id='|"+subfield+"' name='"+name+"'' data-desc='"+subfield+"-"+splitData.getGL23DESC()+"' onfocus='getDesc(this);getData(this)' style='width:100%'>" + raw + "</textarea>";
			}
	html += "</td>" +
			 "<td>"  +
				"<a class='btn btn-dull btn-sm' onclick='removeSubf(this)'>" + 
					"<span class='glyphicon glyphicon-trash'></span>" + 
				"</a>&nbsp;";
	 if(splitData.getGL23REPEAT().equals("Y")){
		html +="<a class='btn btn-default btn-sm duplicateSubf'>" + 
					"<span class='glyphicon glyphicon-duplicate'></span>"+
   				"</a>";
	} 
	 html += "</td></tr>";
}
}
}else{
	html+=title;
}
out.println(html);

	
	
%>