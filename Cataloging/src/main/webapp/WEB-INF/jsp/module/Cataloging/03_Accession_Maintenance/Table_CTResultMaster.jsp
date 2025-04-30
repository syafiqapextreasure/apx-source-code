	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.ilmu.cataloging.Acc_Maint.*, java.util.List"%>
	
			<% 
			String controlNoInput = request.getParameter("controlNoInput");
			System.out.println("ssd" + controlNoInput);
			
			List<CTRetrieve> searchResult = null;
			List<CTRetrieve> searchResult1 = null;
			
			searchResult = CTRetrieve.CT_RetrieveDetails(controlNoInput);
			System.out.println("ssd1"+ controlNoInput);
			searchResult1 = CTRetrieve.CT_SearchByAccno(controlNoInput);
			System.out.println("ssd2"+ controlNoInput);
			int id= 0;
			for(CTRetrieve i: searchResult)
			{
				
					id++;
					

				String deleteFunc = "deleteRecord(&quot;" +controlNoInput + "&quot;)";
				out.println("<tr id='"+i.getCT03DOCNO()+"'>");
				//out.println("<td>" + id + "</td>");
				out.println("<td>" + i.getCT03DOCNO() + "</td>");
				for(CTRetrieve j: searchResult1)
				{
					if (!searchResult1.isEmpty()) {
							out.println("<td>" + j.getCT05SRAW() + "</td>");
					}else{
					        out.println("<td></td>");
					}
				}
				out.println("<td>" + i.getGL10DESC() + "</td>");
				out.println("<td>" + i.getGL47DESC() + "</td>");
				out.println("<td>" + i.getGL71DESC() + "</td>");
				out.println("<td>" + i.getGL05DESC() + "</td>");
				out.println("<td>" + i.getCT03VOLUME() + "</td>");
			
				if(i.getCT03STAT().trim()==null || i.getCT03STAT().trim()==" " || i.getCT03STAT().trim().equals("null")){
				out.println("<td>" + " " +"</td>");
				}
				else{
					out.println("<td>"+i.getCT03STAT()+" </td>");
				}
				out.println("<td>" + i.getCT03COPYNO() + "</td>");
				out.println("<td><a type='button' title='View' class='btn btn-primary' data-toggle='modal' data-target='#viewAccMaintenance' href='Modal_ViewAccMaint?accNo="+i.getCT03DOCNO()+"&controlNo="+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-eye-open'></span></a>");
				if((i.getCT03VOLUME().equals("DISCARDED"))){
					out.println("<a type='button' title='Update' class='btn btn-info' data-toggle='modal' data-target='#editAccMaintenance' href='Modal_EditAccMaint?accNo="+i.getCT03DOCNO()+"&controlNo="+i.getCT03MATNO()+"' disabled><span class='glyphicon glyphicon-pencil'></span></a>");
				}else{
					out.println("<a type='button' title='Update' class='btn btn-info update' data-toggle='modal' data-target='#editAccMaintenance' href='Modal_EditAccMaint?accNo="+i.getCT03DOCNO()+"&controlNo="+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-pencil'></span></a>");
				}
				out.println("<a type='button' title='Delete' class='btn btn-dull' onclick='deleteMATNO(this)' id='"+i.getCT03DOCNO()+"' data-id='"+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-trash'></span></a>"); 
			
				}
			%>
			
			

	
	
	