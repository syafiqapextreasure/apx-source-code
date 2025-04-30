<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.global.Glnumb,
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%
	String action = request.getParameter("action");
	
	if(action.equals("keyInData")){
		String tag = request.getParameter("tag");
		String data = request.getParameter("data");
		//Check if duplicate record is yes or no 
	 	boolean duplicateRcrd = Bibliography.chkDuplicateRcrd(tag);
		System.out.println("true" + duplicateRcrd);
		
		////16112021 --> WNH edit
		//data= Handler.convUpperCase(Handler.removeSubfield(data));
	 	/////////data= Handler.convUpperCase(Handler.removeNumberSubf(data));
	 	System.out.println("true11" + data);
	 	boolean duplicate = false;
	 	boolean duplicate2 = false;
	 	
	 	//If duplicate yes, check if record exist
	 	if(duplicateRcrd){
	 		
	 		
	 		
	 		duplicate2 = Indexing.CT05SCONV_Exist(data, tag);
	 		
	 		//System.out.println("duplicate123" + duplicate2);
	 		
	 		if(duplicate2 == true){
	 			duplicate2 = Indexing.CT05SCONV_Exist_v1(data, tag);
	 			//System.out.println("duplicat098" + duplicate2);
	 		}else{
	 			duplicate2 = false;
	 		}
	 		
	 	}
	 	//System.out.println("duplicatfinal" + duplicate);
	 	out.println(duplicate2);
	}else{
		String[] tag = request.getParameterValues("tag[]");
		String[] raw = request.getParameterValues("data[]");
		int total = Integer.parseInt(request.getParameter("total"));
		
		for (int i = 0; i < total; i++) {
			boolean duplicateRcrd = Bibliography.chkDuplicateRcrd(tag[i]);
			System.out.println(duplicateRcrd);
		 	String data= Handler.removeSubfield(Handler.convUpperCase(raw[i]));
		 	boolean duplicate = false;
		 	
		 	//If duplicate yes, check if record exist
		 	if(duplicateRcrd){
		 		duplicate = Indexing.CT05SCONV_Exist(data, tag[i]);
		 	}
		 	
		 	if(duplicate){
		 		out.println(duplicate);
		 	}
		 
		}
	}
%>