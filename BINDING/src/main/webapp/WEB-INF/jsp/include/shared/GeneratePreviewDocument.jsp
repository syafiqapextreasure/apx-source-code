<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Perform Search -->
<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.binding.pdf.*"%>
<%

	String text = "";

	String bindno = request.getParameter("bindno");
	String action = request.getParameter("action");
	String liferayLogin = request.getParameter("liferayLogin");
	
	String letterId = "";
	
	Library library = Library.getContactsOnly();
	Library loginid = Library.loginDetail(liferayLogin);
	Library vendor = Library.getVendorDetails2(bindno);
	List<Library> bindClaim = Library.getBindClaimDetail(bindno);

	//Library letterTitile = Library.lettersubject(letterId);

	
	if(action.equals("bindingClaim")){
		text = Document.BindingClaim(library, vendor, loginid, bindClaim);
	}
	
	if (text!=null) {
		int mailno = saveMail.Get98VALUE("MAILNO");
		System.out.println(mailno + "mailno"); 
			
		int newmailno = new Integer(mailno + 1);
		saveMail.updatingmailno(newmailno);
		
		saveMail.SAVEMAIL(newmailno, liferayLogin, vendor.getcode(), text.replace("'", "''"), "BI01"); 
		
		out.print(text);
	} 

%>