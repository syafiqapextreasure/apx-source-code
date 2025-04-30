<%@page import="com.ilmu.cataloging.Template_Maint.Template_Maintenance,
				com.ilmu.cataloging.Template_Maint.Tag_Handler"%>
<%@page import="com.ilmu.cataloging.Template_Maint.Cataloging, com.ilmu.global.Glnumb, com.ilmu.utilities.query.*"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.*" %>
<%
	//String CT15SEQNO = request.getParameter("CT15SEQNO");
	//String[] counter = request.getParameterValues("counter[]");	
	//String CT15TNAME = request.getParameter("CT15TNAME");
	//String CT15STAT = request.getParameter("CT15STAT");
	//String CTTPLNAME = request.getParameter("CTTPLNAME");
	String id = request.getParameter("id");
	String[] tag = request.getParameterValues("tag[]");
	//String frequency = request.getParameter("frequency");
	String[] subf = request.getParameterValues("subf[]");
	String[] indi1 = request.getParameterValues("indi1[]");
	String[] indi2 = request.getParameterValues("indi2[]");
	int row = Integer.parseInt(request.getParameter("row"));
	List<String> queryList = new ArrayList<String>();
	
	String action = request.getParameter("action");
	
	for (int i = 0; i < row; i++) {
		Glnumb counter = Glnumb.getGL98VALUE("CTTPLSEQNO");
		System.out.println("SSS" + tag[i] + indi1[i] +  subf[i]+ id);
		boolean tagExist = Tag_Handler.tagExists(tag[i], id);
		boolean tplIDexist = Template_Maintenance.tplIDExists(id);
		
		System.out.println(action);
		if(action.equals("appendTag")){
			if(tplIDexist){
				queryList.add(Template_Maintenance.CT_deleteTplInfo(id));
				//out.println("ok");
			}
		}else if(action.equals("appendExistTag")){
			//Add new info after delete
			queryList.add(Template_Maintenance.CT_TplInfo(counter.getGL98VALUE(), tag[i], indi1[i], indi2[i], subf[i],id));
			//out.println("ok");
		}else{
			if (!tagExist){
				queryList.add(Template_Maintenance.CT_TplInfo(counter.getGL98VALUE(), tag[i], indi1[i], indi2[i], subf[i],id));
				//out.println("ok");
			}else{
				System.out.println("error");
			}
		}
	}

		
		boolean isSuccess = DBQuery.runBatchQuery(queryList);
		
		if(isSuccess){
		 out.println("Successful");
		}else{
			out.println("Fail");
		}
	
	
	
%>