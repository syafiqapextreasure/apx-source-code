<%@ page
	import="java.util.List, java.util.ArrayList, java.util.HashMap,
				com.ilmu.cataloging.PDF.*, 
				com.ilmu.cataloging.template.*,
				com.ilmu.cataloging.Release_Circulation.*"%>

<%

String CT03DOCNO = request.getParameter("CT03DOCNO");
String action = request.getParameter("action");
String CT03MATNO = request.getParameter("CT03MATNO");

if(action.equals("update")){
 /*    Library.updateCIRESV(CT03DOCNO, CT03MATNO);
    boolean update = ReleaseForCirculation.itemOnHold(CT03DOCNO); */
	boolean updateResv = ReleaseForCirculation.updateResv(CT03DOCNO, CT03MATNO);
    if(updateResv){
    	System.out.println("Successful update Resv");
    	  boolean update = ReleaseForCirculation.itemOnHold(CT03DOCNO);
    	  if(update){
    		  System.out.println("Successful item");
    		  out.println("ok");
    	  }
    }
    
}else{
	boolean ciresvExist = Library.CIRESVExist(CT03MATNO);

	if(ciresvExist){
		out.println("ok");
		boolean update = ReleaseForCirculation.itemOnHold(CT03DOCNO);
	}
}
%>
