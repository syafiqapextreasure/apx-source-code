<%@page import="java.util.List, com.ilmu.ill.IncomingRequest.Patron"%>
<%@page import="com.ilmu.ill.IncomingRequest.PatValidate, com.ilmu.ill.IncomingRequest.DocValidate"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String id = request.getParameter("code");
		String accno = request.getParameter("accno");
		String ctrlno = request.getParameter("ctrlno");
		
		boolean bValidPatronID = false;
		boolean bValidateDoc = false;
		
		
		String sPatronCat = null;
		
		
		Patron ptr = new Patron();         
		ptr.CheckPatronExist(id);  
		String output=ptr.getErrMessage();           
		                                             
		System.out.println("dfsfsfsdfsdfds" +output);
		
		if(!output.equals("Valid")){
			out.println(output);
		}else{
			System.out.println("bbbbbbbbbb");
			PatValidate patrvalid = new PatValidate();
			bValidPatronID = patrvalid.PatValidateMain(id);
			String output2=patrvalid.getErrMessage();
			//output = patrvalid.getErrMessage();
			System.out.println("dfsfsfsdfsdfds22233" +output2 +"");
			
			if(!bValidPatronID){
				out.println(output2);
				System.out.println("dfsfsfsdfsdfds222" +output2);
				
				/* if(output2.equals("")){
					out.println("Valid");
				} */
			}else{
				/* if(output2.equals("")){
					out.println("Valid");
				}else{ */
				
				
				 if (!ctrlno.equals(""))
					{
						DocValidate doc = new DocValidate();
						doc.DocValidateMain(accno, id, ctrlno);
						String output3=doc.getErrMessage();
						
						System.out.println("dfsfsfsdfsdfds3333" +output3);
						out.println(output3);
						
						if(output3.equals("")){
							out.println("Valid");
						}
					}
				//}
			}
			/* if(!output.equals("")){
				out.println(output2);
				System.out.println("dfsfsfsdfsdfds222" +output2);
				//out.println("Valid");
			}else{
				out.println("Valid");
			} *//* else{
				out.println(output2);
				System.out.println("dfsfsfsdfsdfds222" +output2);
			} */
				/* if (!ctrlno.equals(""))
				{
					DocValidate doc = new DocValidate();
					doc.DocValidateMain(accno, id, ctrlno);
					String output3=doc.getErrMessage();
					
					System.out.println("dfsfsfsdfsdfds3333" +output3);
					out.println(output3);
				} */

		}			


	}catch (Exception e) {
		out.println("error");
	}

%>