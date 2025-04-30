<%@page import="java.util.List, com.ilmu.binding.maintenance.Monograph "%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime, com.ilmu.global.DateFormatter" %>

<%
	try {
		
		String accno = request.getParameter("accno");
		System.out.println("accno >  "+accno);
		
		String ctrlno = request.getParameter("ctrlno");
		System.out.println("ctrlno >  "+ctrlno);
		
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

		String isbn = request.getParameter("isbn");
		System.out.println("isbn >  "+isbn);

		String typeBind = request.getParameter("typeBind");
		System.out.println("typeBind >  "+typeBind);
	
		String status = request.getParameter("status");
		System.out.println("status >  "+status);
		
		String spineTitle = request.getParameter("spineTitle");
		System.out.println("spineTitle >  "+spineTitle);
		
		String callno = request.getParameter("callno");
		System.out.println("callno >  "+callno);
		
		String cColor = request.getParameter("cColor");
		System.out.println("cColor >  "+cColor);

		String lColor = request.getParameter("lColor");
		System.out.println("lColor >  "+lColor);
		
		String daterec = request.getParameter("daterec");
		System.out.println("daterec >  "+daterec);

		String recby = request.getParameter("recby");
		System.out.println("recby >  "+recby);
		
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
		
		String action = request.getParameter("action");
		System.out.println("action >  "+action);

	
		//boolean bSuccessful;
		if(action.equals("ADD")){
			//////BINDER NO//////////////////
			int bidno = Monograph.Get98VALUE("BINDNO");
			System.out.println(bidno + "bidno"); 
				
			int newbidno= new Integer(bidno + 1);
			Monograph.updatingbidno(newbidno);
			
			String bindnoConvert = String.format("%010d", newbidno);
			System.out.println("bindnoConvert" + bindnoConvert);
			
			//////BINDER REFERENCE//////////////////
			int bindRef = Monograph.Get98VALUE("BIREFNO");
			System.out.println(bindRef + "bindRef"); 
							
			int newbindRef= new Integer(bindRef + 1);
			Monograph.updatingbindRef(newbindRef);
			
			String bindRefConvert = String.format("%010d", newbindRef);
			System.out.println("bindRefConvert" + bindRefConvert);
			
			Monograph.CreateMonograph(accno, ctrlno, officer, binder, currency, erate, 
					year, fcost, lcost, dsent, dexpect, remarks, isbn, typeBind, status, spineTitle, 
					callno, cColor, lColor, daterec, recby, oriCover, advert, titlePage, supp, 
					indexInc, contentsPage, bindnoConvert, bindRefConvert);
			out.println(bindnoConvert); 
		}
		
	}catch (Exception e) {
		out.println("error");
	}

%>