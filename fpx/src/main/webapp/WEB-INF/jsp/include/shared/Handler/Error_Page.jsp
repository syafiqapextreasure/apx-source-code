<%@page import="com.wilmu.utilities.*,
				java.util.*"%>
<% 	
	String GL79ERRCODE = request.getParameter("GL79ERRCODE");
	//String GL79LANGCODE = "002";
String GL79LANGCODE = request.getParameter("GL79LANGCODE");
	String tag = request.getParameter("tag");
	String indi = request.getParameter("indi");
	String indilvl = request.getParameter("indilvl");
	String subf = request.getParameter("subfChk");
	String mandaTag = request.getParameter("mandaTag");
	String docNo = request.getParameter("docNo");
	String criteria = request.getParameter("criteria");
	String label = request.getParameter("label");
	
	List<ErrorMessage2> message = null;
	message = ErrorMessage2.getErrorMsg(GL79ERRCODE,GL79LANGCODE);
%>
			<%
			System.out.println("Values" + indilvl + indi);
				for(ErrorMessage2 i: message){
					String variable = i.getGL79ERRMSG();
					if (tag!=null){
				    	variable=variable.replace("@tag", tag);
				    	}
				    /* 	if(indiLvl!=null){
				    	variable=variable.replace("@indiLvl", indiLvl);
				    	} */
				    	if(subf!=null){
				    	variable= variable.replace("@subf", subf);
				    	}
				    	if(mandaTag!=null){
				        	variable= variable.replace("@mandaTag", "000 and 245");
				        }
				    	if(docNo!=null){
				        	variable= variable.replace("@docNo", docNo);
				        }
				    	if(docNo!=null){
				        	variable= variable.replace("@docNo", docNo);
				        }
				    	if(criteria!=null){
				    		variable= variable.replace(label, criteria);
				    	}else if(indilvl!=null && indi!=null){
				    		System.out.println("Indicator");
				 /*    		boolean isExist = BO_Record.getIndiCheck(tag, indilvl, indi);
				    		if (!isExist){
				    		variable= variable.replace("@indi", indi);
				    		variable= variable.replace("@level", indilvl);
				    		variable= variable.replace("@tag", tag);
				    		}else{
				    			variable= variable.replace("@indi", "none");
					    		variable= variable.replace("@level", indilvl);
					    		variable= variable.replace("@tag", tag);
				    		} */
				    	}
	
				out.println(variable);
		}
			%>
 
