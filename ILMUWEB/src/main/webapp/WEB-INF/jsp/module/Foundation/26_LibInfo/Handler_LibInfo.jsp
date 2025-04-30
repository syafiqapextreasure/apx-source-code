<%@page import="java.util.List, com.ilmu.foundation.LibInfo.LibInfo"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String libName = request.getParameter("libName");
		System.out.println("libName >  "+libName);
		
		String orgName = request.getParameter("orgName");
		System.out.println("orgName >  "+orgName);

		String libSymbol = request.getParameter("libSymbol");
		System.out.println("libSymbol >  "+libSymbol);
		
		String brncCode = request.getParameter("brncCode");
		System.out.println("brncCode >  "+brncCode);
		
		String add1 = request.getParameter("add1");
		System.out.println("add1 >  "+add1);
		
		String add2 = request.getParameter("add2");
		System.out.println("add2 >  "+add2);
		
		String add3 = request.getParameter("add3");
		System.out.println("add3 >  "+add3);
	
		String postcode = request.getParameter("postcode");
		System.out.println("postcode >  "+postcode);
	
		String town = request.getParameter("town");
		System.out.println("town >  "+town);
	
		String tel = request.getParameter("tel");
		System.out.println("tel >  "+tel);
		
		String fax = request.getParameter("fax");
		System.out.println("fax >  "+fax);
		
		String headLib = request.getParameter("headLib");
		System.out.println("headLib >  "+headLib);
		
		String headLibExt = request.getParameter("headLibExt");
		System.out.println("headLibExt >  "+headLibExt);
		
		String profficer = request.getParameter("profficer");
		System.out.println("profficer >  "+profficer);

		String profficerExt = request.getParameter("profficerExt");
		System.out.println("profficerExt >  "+profficerExt);
			
		String acqhead = request.getParameter("acqhead");
		System.out.println("acqhead >  "+acqhead);
			
		String acqext = request.getParameter("acqext");
		System.out.println("acqext >  "+acqext);
			 
		String gp1 = request.getParameter("gp1");
		System.out.println("gp1 >  "+gp1);
		
		String gp2 = request.getParameter("gp2");
		System.out.println("gp2 >  "+gp2);
		
		String gp3 = request.getParameter("gp3");
		System.out.println("gp3 >  "+gp3);
		
		String orderspan = request.getParameter("orderspan");
		System.out.println("orderspan >  "+orderspan);
		
		String acqprint = request.getParameter("acqprint");
		System.out.println("acqprint >  "+acqprint);
		
		String cathead = request.getParameter("cathead");
		System.out.println("cathead >  "+cathead);
		
		String catext = request.getParameter("catext");
		System.out.println("catext >  "+catext);
		
		String marctype = request.getParameter("marctype");
		System.out.println("marctype >  "+marctype);
		
		String itt = request.getParameter("itt");
		System.out.println("itt >  "+itt);
		
		String cirhead = request.getParameter("cirhead");
		System.out.println("cirhead >  "+cirhead);
		
		String cirext = request.getParameter("cirext");
		System.out.println("cirext >  "+cirext);
			 
		String libweekend = request.getParameter("libweekend");
		System.out.println("libweekend >  "+libweekend);
		
		String resvtime = request.getParameter("resvtime");
		System.out.println("resvtime >  "+resvtime);
		
		String rcost = request.getParameter("rcost");
		System.out.println("rcost >  "+rcost);
		
		String timecost = request.getParameter("timecost");
		System.out.println("timecost >  "+timecost);
		
		String circmode = request.getParameter("circmode");
		//System.out.println("circmode >  "+circmode);
		if(circmode == null){
			circmode = "";
		}
		System.out.println("circmode >  "+circmode);
		
		String opaclimit = request.getParameter("opaclimit");
		System.out.println("opaclimit >  "+opaclimit);
		
		String loadAll = request.getParameter("loadAll");
		if(loadAll == null){
			loadAll = "";
		}
		System.out.println("loadAll >  "+loadAll);
		
		String newEnable = request.getParameter("newEnable");
		System.out.println("newEnable >  "+newEnable);
		
		String newInc = request.getParameter("newInc");
		System.out.println("newInc >  "+newInc);
		
		String opacmsgtime = request.getParameter("opacmsgtime");
		System.out.println("opacmsgtime >  "+opacmsgtime);
		
		String sercHistory = request.getParameter("sercHistory");
		System.out.println("sercHistory >  "+sercHistory);
		
		String serHead = request.getParameter("serHead");
		System.out.println("serHead >  "+serHead);
		
		String serext = request.getParameter("serext");
		System.out.println("serext >  "+serext);
		
		String serbprint = request.getParameter("serbprint");
		System.out.println("serbprint >  "+serbprint);
			
		String irsHead = request.getParameter("irsHead");
		System.out.println("irsHead >  "+irsHead);
		
		String irsext = request.getParameter("irsext");
		System.out.println("irsext >  "+irsext);
		
		String irstype = request.getParameter("irstype");
		System.out.println("irstype >  "+irstype);
		
		String irsflag = request.getParameter("irsflag");
		if(irsflag == null){
			irsflag = "";
		}
		System.out.println("irsflag >  "+irsflag);
		
		String finHead = request.getParameter("finHead");
		System.out.println("finHead >  "+finHead);
		
		String finext = request.getParameter("finext");
		System.out.println("finext >  "+finext);
		
		String fund = request.getParameter("fund");
		System.out.println("fund >  "+fund);
			
		String action = request.getParameter("action");
		System.out.println("action >  "+action);
		
		String DateConfiguration = request.getParameter("DateConfiguration");
		System.out.println("DateConfiguration >  "+DateConfiguration);
		
		String Locationlist = request.getParameter("Locationlist");
		System.out.println("Locationlist >  "+Locationlist);
		
		String ItemCategorylist = request.getParameter("ItemCategorylist");
		System.out.println("ItemCategorylist >  "+ItemCategorylist);
		
		String Conditionlist = request.getParameter("Conditionlist");
		System.out.println("Conditionlist >  "+Conditionlist);
		
		String SMDlist = request.getParameter("SMDlist");
		System.out.println("SMDlist >  "+SMDlist);
		
		String Statuslist = request.getParameter("Statuslist");
		System.out.println("Statuslist >  "+Statuslist);
		
		String filePart = request.getParameter("logo");
	    System.out.println("filePart = " + filePart);
		
		/* Part filePart = request.getPart("logoLib");
	    System.out.println("filePart = " + filePart); */
	
		//boolean bSuccessful;
		if(action.equals("add")){

			LibInfo.addLibInfo(libName, orgName, libSymbol, brncCode, add1, add2, add3, postcode, town, 
					tel, fax, headLib, headLibExt, profficer, profficerExt, acqhead, acqext, gp1, gp2, 
					gp3, orderspan, acqprint, cathead, catext, marctype, itt, cirhead, cirext, 
					libweekend, resvtime, rcost, timecost, circmode, opaclimit, loadAll, newEnable, 
					newInc, opacmsgtime, sercHistory, serHead, serext, serbprint, irsHead, irsext, 
					irstype, irsflag, finHead, finext, fund, DateConfiguration, Locationlist, ItemCategorylist, 
					Conditionlist, SMDlist, Statuslist, filePart);
			out.println("ok");
			//out.println(bindnoConvert);
		}else if(action.equals("edit")){
			LibInfo.editLibInfo(libName, orgName, libSymbol, brncCode, add1, add2, add3, postcode, town, tel, fax, 
					headLib, headLibExt, profficer, profficerExt, acqhead, acqext, gp1, gp2, gp3, orderspan, acqprint, 
					cathead, catext, marctype, itt, cirhead, cirext, libweekend, resvtime, rcost, timecost, circmode, 
					opaclimit, loadAll, newEnable, newInc, opacmsgtime, sercHistory, serHead, serext, serbprint, irsHead, 
					irsext, irstype, irsflag, finHead, finext, fund, DateConfiguration, Locationlist, ItemCategorylist, 
					Conditionlist, SMDlist, Statuslist, filePart);
			out.println("ok"); 
		}

	}catch (Exception e) {
		out.println("error");
	}

%>