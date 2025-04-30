	<%@ page import="com.ilmu.global.connection.*, com.ilmu.serial.serial_master.*"%>

	<%
	try {
		String controlNo = request.getParameter("controlNo");
		boolean delete = Serial_Master.SE01_deletePeriodicalsMaster(controlNo);
		
		if(delete)
		{
			out.println("ok");
		}else{
			out.println("error");
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>
