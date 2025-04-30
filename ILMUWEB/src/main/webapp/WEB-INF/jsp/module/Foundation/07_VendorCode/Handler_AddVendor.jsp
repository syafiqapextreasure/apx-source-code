	<%@ page import="com.ilmu.foundation.VendorCode.*, com.ilmu.foundation.*, java.util.List" %>
	
	<%
	
	
	System.out.println("Masuk Handler Add vendor");
	
	 
	String GL39CODE = request.getParameter("GL39CODE");
	String GL39NAME = request.getParameter("GL39NAME");
	String GL39ADD1 = request.getParameter("GL39ADD1");
	String GL39ADD2 = request.getParameter("GL39ADD2");
	String GL39ADD3 = request.getParameter("GL39ADD3");
	String GL39TELNO = request.getParameter("GL39TELNO");
	String GL39FAX = request.getParameter("GL39FAX");
	String GL39PERINC = request.getParameter("GL39PERINC");
	String GL39DESIG = request.getParameter("GL39DESIG");
	String GL39CONTNO = request.getParameter("GL39CONTNO");
	String GL39CBDATE = request.getParameter("GL39CBDATE");
	String GL39CEDATE = request.getParameter("GL39CEDATE");
	String GL39REMARK = request.getParameter("GL39REMARK");
	String GL39ACCNO = request.getParameter("GL39ACCNO");
	String GL39PCODE = request.getParameter("GL39PCODE");
	String GL39IPADD = request.getParameter("GL39IPADD");
	String GL39BINDER = request.getParameter("GL39BINDER");
	String GL39SUPPLIER = request.getParameter("GL39SUPPLIER");
	String GL39PUB = request.getParameter("GL39PUB");
	String GL39INDI = request.getParameter("GL39INDI");
	String GL39USERNAME = request.getParameter("GL39USERNAME");
	String GL39PASSWORD = request.getParameter("GL39PASSWORD");
	String GL39EMAIL = request.getParameter("GL39EMAIL");
	String GL39BANK = request.getParameter("GL39BANK");
			
	SQLStatement.AddVendor( GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39TELNO, GL39FAX, GL39PERINC, GL39DESIG, GL39CONTNO, GL39CBDATE, GL39CEDATE, GL39REMARK, GL39ACCNO, GL39PCODE, GL39IPADD, GL39BINDER, GL39SUPPLIER,GL39PUB, GL39INDI, GL39USERNAME, GL39PASSWORD, GL39EMAIL, GL39BANK);
	
	%>