
<%@page import="java.util.List, com.ilmu.eRegistration.eRegistration"%>
<%@ page import="java.util.*, com.ilmu.utilities.query.*, com.ilmu.global.DateTime" %>

<%
	try {
		
		String namaAhli = request.getParameter("namaAhli");
		System.out.println("namaAhli >  "+namaAhli);
		
		String gelaran = request.getParameter("gelaran");
		System.out.println("gelaran >  "+gelaran);
		
		String newICval = request.getParameter("newICval");
		System.out.println("newICval >  "+newICval);
		
		String inputDOB = request.getParameter("inputDOB");
		System.out.println("inputDOB >  "+inputDOB);
		
		String alamat = request.getParameter("alamat");
		System.out.println("alamat >  "+alamat);
		
		String alamat2 = request.getParameter("alamat2");
		System.out.println("alama2t >  "+alamat2);
		
		String alamat3 = request.getParameter("alamat3");
		System.out.println("alamat3 >  "+alamat3);
		
		String negeri = request.getParameter("negeri");
		System.out.println("negeri >  "+negeri);
		
		String poskod = request.getParameter("poskod");
		System.out.println("poskod >  "+poskod);
		
		String alamatSurat = request.getParameter("alamatSurat");
		System.out.println("alamatSurat >  "+alamatSurat);
		
		String alamatSurat2 = request.getParameter("alamatSurat2");
		System.out.println("alamatSurat2 >  "+alamatSurat2);
		
		String alamatSurat3 = request.getParameter("alamatSurat3");
		System.out.println("alamatSurat3 >  "+alamatSurat3);

		String negeriSurat = request.getParameter("negeriSurat");
		System.out.println("negeriSurat >  "+negeriSurat);
		
		String poskodSurat = request.getParameter("poskodSurat");
		System.out.println("poskodSurat >  "+poskodSurat);
		
		String warganegara = request.getParameter("warganegara");
		System.out.println("warganegara >  "+warganegara);

		String phone = request.getParameter("phone");
		System.out.println("phone >  "+phone);
		
		String houseno = request.getParameter("houseno");
		System.out.println("houseno >  "+houseno);
	
		String kaum = request.getParameter("kaum");
		System.out.println("kaum >  "+kaum);
		
		String kad = request.getParameter("kad");
		System.out.println("kad >  "+kad);
		
		String emailAhli = request.getParameter("emailAhli");
		System.out.println("emailAhli >  "+emailAhli);	
		
		String sex = request.getParameter("sex");
		System.out.println("sex >  "+sex);	
		
		boolean bSuccessful = false;
		
		bSuccessful = eRegistration.CreateFundAccountChart(namaAhli, gelaran, newICval, inputDOB, alamat, 
				alamat2, alamat3, negeri, poskod, alamatSurat, alamatSurat2, 
				alamatSurat3, negeriSurat, poskodSurat, warganegara, phone, houseno, kaum, kad, emailAhli,
				sex);
		
		if(bSuccessful){
			out.println("ok");
		}else{
			out.println("fail");
		}
	} catch (Exception e) {
		out.println("error");
	}
	
%>