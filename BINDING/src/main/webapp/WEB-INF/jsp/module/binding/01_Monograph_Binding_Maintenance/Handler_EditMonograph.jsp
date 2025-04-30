<%@page import="java.util.List, com.ilmu.binding.maintenance.Monograph "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String bindingNo = request.getParameter("bindingNo");
		System.out.println("bindingNo >  "+bindingNo);
		
		String officer = request.getParameter("officer");
		System.out.println("officer >  "+officer);
		
		String binder = request.getParameter("binder");
		System.out.println("binder >  "+binder);
		
		String currency = request.getParameter("currency");
		System.out.println("currency >  "+currency);
		
		String erate = request.getParameter("erate");
		System.out.println("erate >  "+erate);
		
		String year = request.getParameter("year");
		System.out.println("year >  "+year);
		
		String fcost = request.getParameter("fcost");
		System.out.println("fcost >  "+fcost);
		
		String lcost = request.getParameter("lcost");
		System.out.println("lcost >  "+lcost);
		
		String dsent = request.getParameter("dsent");
		System.out.println("dsent >  "+dsent);
		
		String dexpect = request.getParameter("dexpect");
		System.out.println("dexpect >  "+dexpect);
		
		String remarks = request.getParameter("remarks");
		System.out.println("remarks >  "+remarks);
		
		String typeBind = request.getParameter("typeBind");
		System.out.println("typeBind >  "+typeBind);
		
		String status = request.getParameter("status");
		System.out.println("status >  "+status);
		
		String spineTitle = request.getParameter("spineTitle");
		System.out.println("spineTitle >  "+spineTitle);
		
		String cColor = request.getParameter("cColor");
		System.out.println("cColor >  "+cColor);
		
		String lColor = request.getParameter("lColor");
		System.out.println("lColor >  "+lColor);
		
		String oriCover = request.getParameter("oriCover");
		System.out.println("oriCover >  "+oriCover);
		
		String advert = request.getParameter("advert");
		System.out.println("advert >  "+advert);
		
		String titlePage = request.getParameter("titlePage");
		System.out.println("titlePage >  "+titlePage);
		
		String supp = request.getParameter("supp");
		System.out.println("supp >  "+supp);
		
		String indexInc = request.getParameter("indexInc");
		System.out.println("indexInc >  "+indexInc);
		
		String contentsPage = request.getParameter("contentsPage");
		System.out.println("contentsPage >  "+contentsPage);
		
		String liferayLogin = request.getParameter("liferayLogin");
		System.out.println("liferayLogin >  "+liferayLogin);
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);

	
		//boolean bSuccessful;
		if(action.equals("Edit")){
			Monograph.UpdateMonoGraph(bindingNo, officer, binder, currency, erate, year, fcost, lcost, 
					dsent, dexpect, remarks, typeBind, status, spineTitle, cColor, 
					lColor, oriCover, advert, titlePage, supp, indexInc, contentsPage, liferayLogin);
			out.println("ok");
		}
		
	}catch (Exception e) {
		out.println("error");
	}

%>