<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.wilmu.global.SearchCriteria,java.util.List" %>
    
<%


	String module = request.getParameter("auditTrailBy");
	System.out.println("modulemodule"+module);
	
		if(module.equals("Acquisition")){
			module = "AC";
		}else if(module.equals("Authority")){
			module = "AU";
		}else if(module.equals("Binding")){
			module = "BI";
		}else if(module.equals("Booking")){
			module = "BK";
		}else if(module.equals("Circulation")){
			module = "CI";
		}else if(module.equals("Cataloguing")){
			module = "CT";
		}else if(module.equals("Fund Accounting")){
			module = "FA";
		}else if(module.equals("Foundation")){
			module = "GL";
		}else if(module.equals("IRS")){
			module = "IR";
		}else if(module.equals("Label Config")){
			module = "LB";
		}else if(module.equals("Reserve collection")){
			module = "RC";
		}else if(module.equals("Receipting")){
			module = "RA";
		}else if(module.equals("Serial")){
			module = "SE";
		}else if(module.equals("Weeding")){
			module = "WE";
		}

    List<SearchCriteria> selectbrnc = SearchCriteria.selectAuditTrail(module);
        	
        	String selected = "-";
        	String output = "";

        	for(SearchCriteria i: selectbrnc)
        	{
        		output += "<option value='" + i.getCode() + "' ";

        		
        		output += ">" + i.getCode() + " - " + i.getDesc() + "</option>";
        	}
        	
        	out.println(output);
    %>