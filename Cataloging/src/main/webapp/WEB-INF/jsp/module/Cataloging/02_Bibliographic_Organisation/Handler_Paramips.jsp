<%-- <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection,
				 com.ilmu.cataloging.Paramips.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*, java.io.*"%>
					
					<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import = "javax.servlet.http.*" %>
<%@ page import = "org.apache.commons.fileupload.*" %>
<%@ page import = "org.apache.commons.fileupload.disk.*" %>
<%@ page import = "org.apache.commons.fileupload.servlet.*" %>
<%@ page import = "org.apache.commons.io.output.*" %>
<% 
System.out.println("rr");
	String fileName = request.getParameter("fileName");
	String scriptType = request.getParameter("script");
	String action = request.getParameter("action");
	System.out.println("rrqw");
	String SAVE_DIR="MARC";
	if(action.equals("1")){
		String home = System.getProperty("user.home");
		System.out.println(home);
		File file = new File(home+"/Downloads/" + fileName); 
		String absolute = file.toString();
		String convToMARC = ISOtoMARC.convertToMarc(absolute);
		//System.out.println(convToMARC);
		out.println("converted");
	}else if(action.equals("2")){
		List<ISOtoMARC> script = ISOtoMARC.getScript(scriptType);
		out.println("gotScript");
	Glnumb BUFFERNO = Glnumb.getGL98VALUE("CATBUFFERNO");
	String bufferno = Handler.concatMatno(String.valueOf(BUFFERNO.getGL98VALUE()));
	System.out.println("Buffer" + bufferno);
	List<Paramips> batchNo = Paramips.RetrieveMPScript(bufferno);
	System.out.println("Test" + batchNo);
	for(Paramips batchno : batchNo){
		Paramips.Delete_CTWORK(batchno.getPARA01TPLNAME());
		out.println(batchno.getPARA01TPLNAME());
	}
	}
%> --%>