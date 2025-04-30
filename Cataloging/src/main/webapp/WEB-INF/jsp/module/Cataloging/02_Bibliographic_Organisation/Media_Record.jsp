<%@page import="java.util.List"%>
<%@ page import="java.util.*, com.ilmu.cataloging.Bibliography.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Media Record</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>			
<script>
$(document).ready(function() {
	var retrievedData = localStorage.getItem("quentinTarantino");
	var movies2 = JSON.parse(retrievedData);
	
	if(movies2!=null){
		 alert( movies2.length);
	for (i = 0; i < movies2.length; i++) {
		$("#" + i).val( movies2[i]);
		 alert("a" + i +"e"+ movies2[i]);
		}
	}

  });
</script>
</head>

<body onload="loadWindow()">
 <%
 	String ctrlTagCodes = request.getParameter("ctrlTagCodes");
 System.out.println("Control" + ctrlTagCodes);
	List<Media_Record> getDistinct_MediNew = null;
	getDistinct_MediNew = Media_Record.getDistinct_MediNew(ctrlTagCodes);
	
	List<Media_Record> getMediNew = null;
	getMediNew = Media_Record.getMediNew(ctrlTagCodes);
	
	List<Media_Record> getInfoNew = null;
	getInfoNew = Media_Record.getInfoNew(ctrlTagCodes);
	
	List<Media_Record> getmedi = null;
	getmedi = Media_Record.getmedi(ctrlTagCodes);
	
	List<Media_Record> getMedi = null;
	getMedi = Media_Record.getMedi(ctrlTagCodes);
	
	/* int getMaxLength = 0;
	getMaxLength = Media_Record.getMaxLength(ctrlTagCodes); */
 %>
<form name="ctrl_pop" id="ctrl_pop" action="">
 <input type="hidden" id="ctrltag" value="<%=ctrlTagCodes%>">
<table border="1" style="text-align:center" id="mytable">
 <tbody><tr id="tr_result">
 <%

 		for(Media_Record i: getMedi){
 			for(int j=Integer.parseInt(i.getGL25START());j<=Integer.parseInt(i.getGL26DESC());j++){
 				
 				if(i.getGL26STYLE().equals("3")){

 %>		
  						<td width="15px" style="padding:2px;" title="Pos 4. Value: &quot;<%=j %>&quot;" style="background-color: white;" class="position" id="<%=j %>"></td> 
 <%				
 				}else{
 					if(j==Integer.parseInt(i.getGL25START())){
	 					for(Media_Record k:getmedi){
	 						System.out.println("Start" + k.getGL26MSTRCODE()+i.getGL25START());
	 						if(k.getGL26MSTRCODE().equals(i.getGL25START())){
	 							System.out.println(k.getGL26MSTRCODE() + "e" + i.getGL25START());
%>
								<td width="15px" style="padding:2px;" title="Pos 4. Value: &quot;<%=j %>&quot;" style="background-color: white;" class="position" id="<%=j %>">
								<%
									if(k.getGL26STYLE().equals("auto")){
										out.println("");
									}else{
										out.println(k.getGL26STYLE());
									}
								%>			
								</td> 			
 <%
 								}
 							}
 						}else{
 %>
 								<td width="15px" style="padding:2px;" title="Pos 4. Value: &quot;<%=j %>&quot;" style="background-color: white;" class="position" id="<%=j %>"></td> 
 <%
 						}
 					}
 				}
 			}

 %> 

 
</tbody></table>
<input type="hidden" id="allValue" value="
 <%
 	for(Media_Record i: getInfoNew){
 	if (i.getGL26CODE().equals("#")){
 %>
  &nbsp;
  <%}else{ %>
 <%=i.getGL26CODE() %>
  <%
   }}
 %>">



<br/>
<table class="table table-bordered table-condensed" style="border-collapse:collapse;" id="showList">
 <%
 	for(Media_Record i: getDistinct_MediNew){
 %>
		 <tr>
			  <%
				  String start = (i.getGL25START()).toString();
				  if(start.length()>1){
			  %>
			  <td>
			  	<%=i.getGL25START() %> 
			  </td>
			   <%
			  	}else{
			   %>
			   <td>
			   	0<%=i.getGL25START() %>
			   </td>
			   <%
			  }
			   %>
		 	<td>
		 		<%=i.getGL26DESC() %>
		 	</td>
		 	<td>
		 		<%if((i.getGL26STYLE()).equals("1")){
			   		for(Media_Record j: getMediNew){
			   			System.out.println("Print" + j.getGL26START()+i.getGL25START());
			   	    	if ((i.getGL25START()).equals(j.getGL26START())){ %>
			   	  		  <%=j.getGL26CODE() %>-<%=j.getGL26DESC() %><input type="hidden" class="ctrl" id="<%=i.getGL25START()%>" value="<%=j.getGL26CODE() %>">
			   	    <%
				   	    }
				   	    	}
				   	   	}else if ((i.getGL26STYLE()).equals("2")){
		 			%>
		 			<select class="ctrl slct form-control" id="<%=i.getGL25START()%>" onchange="getValue(this.id, this.value)">
				   	 <%
				   		for(Media_Record j: getMediNew){
					   		if  ((i.getGL25START()).equals(j.getGL26START())){
				 	 %>
				   		<option value="<%=j.getGL26CODE() %>"><%=j.getGL26CODE() %>-<%=j.getGL26DESC() %></option>
				   	 <%
				 		 }}
					%>
					   	</select>
				   	<%
				   	   }else{
				   	%>
				   	<input id="<%=i.getGL25START()%>" type="text" name="text" onkeyup="getCtrlInput(this.id, this.value)" onkeydown="getCtrlInputDown(this.id, this.value)"  class="ctrl ctrlInput form-control <%=i.getGL25START()%>">
				   	<%
				   	   }
				   	%>
		 	</td>
		 </tr>
<%
 	}
%>


 </table>

<!--  <input type="button" value="OK" onclick="report()"> -->
 </form>
</body>
</html>
