<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.cataloging.Acc_Maint.*, com.ilmu.foundation.*, java.util.List, com.ilmu.global.*,
				java.net.URLEncoder" %>

	
			<% 
			String controlNoInput = request.getParameter("txtMatNo");
			String status = request.getParameter("status");
			List<CTRetrieve> searchResult = null;
			List<CTRetrieve> searchResult1 = null;
			
			searchResult = CTRetrieve.CT_SearchByMatno(controlNoInput);
			searchResult1 = CTRetrieve.CT_getTitle(controlNoInput);
			System.out.println(searchResult);
			System.out.println("sd" + searchResult.size());
			
			if(searchResult.size()==0){
				out.println("<tr><td colspan='10'>No record found</td></tr>");
			}else{
				int id= 0;
				for(CTRetrieve i: searchResult)
				{
						id++;

					String deleteFunc = "deleteRecord(&quot;" +i.getCT03DOCNO() + "&quot;)";
					out.println("<tr id='"+controlNoInput+"'>");
					//out.println("<td>" + id + "</td>");
					out.println("<td>" + i.getCT03DOCNO() + "</td>");
					if (!searchResult1.isEmpty()) {
						System.out.println("Raw");
						for(CTRetrieve j: searchResult1)
						{
					out.println("<td>" + j.getCT05SRAW() + "</td>");
						}
					}else{
					out.println("<td></td>");
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
					out.println("<td><a type='button' title='View' class='btn btn-primary' data-toggle='modal' data-target='#viewAccMaintenance' href='Modal_ViewAccMaint?accNo="+ URLEncoder.encode(controlNoInput.replace(" ", "+"), "UTF-8")+"&controlNo="+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-eye-open'></span></a>");
					if((i.getCT03VOLUME().equals("DISCARDED"))){
						out.println("<a type='button' title='Update' class='btn btn-info' data-toggle='modal' data-target='#editAccMaintenance' href='Modal_EditAccMaint?accNo="+ URLEncoder.encode(i.getCT03DOCNO().replace(" ", "+"), "UTF-8")+"&controlNo="+i.getCT03MATNO()+"' disabled><span class='glyphicon glyphicon-pencil'></span></a>");
					}else{
						out.println("<a title='Updates' class='btn btn-info update' data-toggle='modal' data-target='#editAccMaintenance' href='Modal_EditAccMaint?accNo="+URLEncoder.encode(i.getCT03DOCNO().replace(" ", "+"), "UTF-8")+"&controlNo="+i.getCT03MATNO()+"'><span class='glyphicon glyphicon-pencil'></span></a>");
					}
					out.println("<a type='button' title='Delete' class='btn btn-dull' onclick='deleteDOC(this.id)' id='"+i.getCT03DOCNO()+"' ><span class='glyphicon glyphicon-trash'></span></a>");
	
					}
			}
				
			%>
	
	
	