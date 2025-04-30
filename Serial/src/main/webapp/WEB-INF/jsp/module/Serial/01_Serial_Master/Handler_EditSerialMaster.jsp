	<%@ page import="com.ilmu.global.connection.*, com.ilmu.serial.serial_master.*"%>
	
	<%
	try {
		String controlNo = request.getParameter("controlNo");
		String smd = request.getParameter("smd");
		System.out.println("SMD" + smd);
		String language = request.getParameter("language");
		String department = request.getParameter("department");
		String frequency = request.getParameter("frequency");
		String vendor = request.getParameter("vendor");
		String binder = request.getParameter("binder");
		String publisher = request.getParameter("publisher");
		String bibliographicSource = request.getParameter("bibliographicSource");
		String renewalAlert = request.getParameter("renewalAlert");
		String serialMode = request.getParameter("serialMode");
		String requestor = request.getParameter("requestor");
		String currency = request.getParameter("currency");
		String pubRate = request.getParameter("pubRate");
		String fPrice = request.getParameter("fPrice");
		String lPrice = request.getParameter("lPrice");
		String cIndex = request.getParameter("cumIndex");
		String cPage = request.getParameter("contPage");
		String sOrder = request.getParameter("stndOrder");
		String bTreatment = request.getParameter("bindTreatment");
		String sHeading = request.getParameter("subjHeading");
		String iIndexing = request.getParameter("irsIndexing");
		String tPage = request.getParameter("ttlPage");
		String r = request.getParameter("routing");
		
		boolean update = Serial_Master.SE01_editPeriodicalsMaster(controlNo, smd, language, department, frequency, vendor, binder, 
				publisher, bibliographicSource, renewalAlert, serialMode, requestor, currency, pubRate, fPrice, lPrice, 
				cIndex, cPage, sOrder, bTreatment, sHeading, iIndexing, tPage, r);
		
		if(update)
		{
			out.println("ok");
		}else{
			out.println("error");
		}
	} catch (Exception e) {
		out.println("error");
	}
	%>