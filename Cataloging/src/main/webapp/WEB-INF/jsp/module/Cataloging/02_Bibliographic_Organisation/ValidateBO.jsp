<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List,java.text.*,java.util.*,com.ilmu.global.*"%>
<%
String[] tag = request.getParameterValues("tag[]");
String[] indi1 = request.getParameterValues("indi1[]");
String[] indi2 = request.getParameterValues("indi2[]");
String[] subf = request.getParameterValues("subf[]");
int total = Integer.parseInt(request.getParameter("total")); 
String module = request.getParameter("module");
boolean next = false;

List <BO_Validation> tagList = BO_Validation.TagList();
List <BO_Validation> mandaTag = BO_Validation.mandatoryTag(Handler.marcType(module));
List <String> subfList = null;
boolean indi1Exist = false;
boolean indi2Exist = false;
List<ErrorMessage_Handler> ErrorMessage = null;
String variable = "";

//Check for mandatory tag
 for(BO_Validation i: mandaTag)
{
	if(Arrays.asList(tag).contains(i.getGL17TAG())){
		next = true;
	}else{
		out.println("No_MandatoryTag," + i.getGL17TAG());
		next = false;
		break;
	} 

} 

//Validate indicators
if(next){
	StringBuilder strError = new StringBuilder();
	ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("010");
	for (int i= 0; i<total;i++){
		if(subf[i].trim()!=""){
			
		String subfs = null;
		
		if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
		tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
		tag[i].equals("005")||tag[i].equals("009")){
			subfs = subf[i];
		}else{
			subfs = Handler.removeSubfield(subf[i]);
		}
				
		if(subfs.trim().length()!=0){
		indi1Exist = BO_Validation.getIndiCheck(tag[i], "1", indi1[i]);
		indi2Exist = BO_Validation.getIndiCheck(tag[i], "2", indi2[i]);
		
		if(indi1Exist){
			if(indi2Exist){
				next = true;
			}else{
				for(ErrorMessage_Handler j: ErrorMessage){

					 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]).replace("@indi", indi2[i]).replace("@level", "2");
				}
				
				strError.append(variable+ "<br>");
				next = false;
				out.println(strError);
			 	break;
			} 
		}else{
			for(ErrorMessage_Handler j: ErrorMessage){

				 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]).replace("@indi", indi1[i]).replace("@level", "1");
			}
			
			strError.append(variable+ "<br>");
			next = false;
			out.println(strError);
		 	break;
		} 
		
		if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
				tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
				tag[i].equals("005")||tag[i].equals("009")){
			
		}else{
			String subfexist = GlobalValidation.chkifsubfexist(tag[i],subf[i], Handler.marcType(module));

			if(subfexist!=""){
				 ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("129");
				for(ErrorMessage_Handler j: ErrorMessage){

					 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]).replace("@subfield",subfexist);
					
				}
				
					strError.append(variable+ "<br>");
					out.println(strError);
				 	break;
			}else{
				boolean exist = GlobalValidation.checkSubfield(tag[i],subf[i], Handler.marcType(module));
				if(!exist){
					 ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("130");
						for(ErrorMessage_Handler j: ErrorMessage){

							 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]);
							
						}
						
							strError.append(variable+ "<br>");
							out.println(strError);
						 	break;
				}
			}
			
		}
		}else{
			next = false;
		}
		}
		
	}
	//out.println(strError);
}

//Validate subfield

 /* if(next){
	 StringBuilder strError = new StringBuilder();

		for (int i= 0; i<total;i++){
System.out.println("SubfieldRecord" + subf[i]);
				if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
						tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
						tag[i].equals("005")||tag[i].equals("009")){
					
				}else{
					String subfexist = GlobalValidation.chkifsubfexist(tag[i],subf[i]);

					if(subfexist!=""){
						 ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("129");
						for(ErrorMessage_Handler j: ErrorMessage){

							 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]).replace("@subfield",subfexist);
							
						}
						
							strError.append(variable+ "<br>");
							out.println(strError);
						 	break;
					}else{
						boolean exist = GlobalValidation.checkSubfield(tag[i],subf[i]);
						if(!exist){
							 ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("130");
								for(ErrorMessage_Handler j: ErrorMessage){

									 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]);
									
								}
								
									strError.append(variable+ "<br>");
									out.println(strError);
								 	break;
						}
					}
					
				}
		} */
	/*  StringBuilder strError = new StringBuilder();
	 ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("129");
	for (int i= 0; i<total;i++){
		if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
				tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
				tag[i].equals("005")||tag[i].equals("009")){
			
		}else{
		subfList = BO_Validation.SubfieldList(tag[i]);
		//String[] subfield = (subf[i]).split("-");
			System.out.println("True" +Arrays.asList(subfList) + subfList.toString());
			if(subfList.contains(subf[i])){
				System.out.println("True" + tag[i] + subf[i]);
			}else{
				for(ErrorMessage_Handler j: ErrorMessage){

					 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]).replace("@subfield", subf[i]);
				}
				
				strError.append(variable+ "<br>");
			}
		}
	}
		out.println(strError); */
	//}
/*  if(next){
	 StringBuilder strError = new StringBuilder();
	 ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG("129");
	for (int i= 0; i<total;i++){
		if(tag[i].equals("000")||tag[i].equals("006")||tag[i].equals("007")||tag[i].equals("008")||
				tag[i].equals("001")||tag[i].equals("002")||tag[i].equals("003")||tag[i].equals("004")||
				tag[i].equals("005")||tag[i].equals("009")){
			
		}else{
		subfList = BO_Validation.SubfieldList(tag[i]);
		//String[] subfield = (subf[i]).split("-");
			System.out.println("True" +Arrays.asList(subfList) + subfList.toString());
			if(subfList.contains(subf[i])){
				System.out.println("True" + tag[i] + subf[i]);
			}else{
				for(ErrorMessage_Handler j: ErrorMessage){

					 variable = (j.getGL79ERRMSG()).replace("@tag", tag[i]).replace("@subfield", subf[i]);
				}
				
				strError.append(variable+ "<br>");
			}
		}
	}
		out.println(strError);
	}   */

%>