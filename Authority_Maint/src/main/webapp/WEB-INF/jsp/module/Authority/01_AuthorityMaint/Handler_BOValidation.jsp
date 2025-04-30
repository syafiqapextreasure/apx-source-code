<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<%

String validationStatus = request.getParameter("validation");
String CT04MATNO = request.getParameter("CT04MATNO");
/* 
List<BO_Validation> BO_Validations = null;
BO_Validations = BO_Validation.mandatoryTag();
for(BO_Validation i: BO_Validations)
{
out.println(i.getGL17TAG());
} */
System.out.println(validationStatus);
String status = "";
if(validationStatus.equals("ExistMatno")){
	//Handler_CTWORK.DeleteInsertCTWORK(CT04MATNO);
 	boolean CT04MATNOExist = Handler_BO.CT04MATNOExist(CT04MATNO);
 	System.out.println(CT04MATNO);
	if(CT04MATNOExist)
	{
		System.out.println("Update");
		out.println(CT04MATNO);
		Handler_BO.Delete_CTWORK(CT04MATNO);
		status = "deleted";
	} else{
		status = "no_matno";
	}
	
	System.out.println(CT04MATNO);
	out.println(CT04MATNO); 
}else if(validationStatus.equals("GetTplCounter")){
	Glnumb CT04COUNTER = Glnumb.getGL98VALUE("CTTPLSEQNO");
	int counter = CT04COUNTER.getGL98VALUE();
	 out.println(counter);
}
else if(validationStatus.equals("GetCounter")){
	 Glnumb CT04COUNTER = Glnumb.getGL98VALUE("BUFPOINT");
	  int counter = CT04COUNTER.getGL98VALUE();
	  out.println(counter);
}else if(validationStatus.equals("MandaTag")){
	List<BO_Validation> BO_Validations = null;
	BO_Validations = BO_Validation.mandatoryTag();
	for(BO_Validation i: BO_Validations)
	{
	out.println(i.getGL17TAG());
	}
}else if(validationStatus.equals("BufferNo")){
	 Glnumb BUFFERNO = Glnumb.getGL98VALUE("CATBUFFERNO");
	  String bufferno = Handler.concatMatno(String.valueOf(BUFFERNO.getGL98VALUE()));
	  out.println(bufferno);
}
else if(validationStatus.equals("CtrlNo")){
	 Glnumb CTRLNO = Glnumb.getGL98VALUE("CATMATERIALNO");
	  String ctrlno = Handler.concatMatno(String.valueOf(CTRLNO.getGL98VALUE()+1));
	  out.println(ctrlno);
}else if(validationStatus.equals("SubfieldValidation")){
	String GL23TAG = request.getParameter("GL23TAG");
	List<BO_Validation> BO_Validations = null;
	BO_Validations = BO_Validation.validSubfields(GL23TAG);
	for(BO_Validation i: BO_Validations)
	{
	out.println(i.getGL23SUBF());
	}
}else if(validationStatus.equals("repeatTags")){
	List<BO_Validation> BO_Validations = null;
	BO_Validations = BO_Validation.repeatTags();
	for(BO_Validation i: BO_Validations)
	{
	out.println(i.getGL17TAG());
	}
}else if(validationStatus.equals("subfvalidation")){
	String tag = request.getParameter("GL23TAG");
	String subfield = request.getParameter("subfield");
	boolean exist = GlobalValidation.checkSubfield(subfield, tag);
	out.println(exist);
}

%>