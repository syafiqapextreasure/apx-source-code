<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*,javax.sql.*"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.GeneralUtil"%>
<%@page import="javax.swing.event.ListDataEvent"%>
<%@page import="java.util.*,javax.sql.*"%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print"%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%> 
<%@page import="java.util.Locale"%> 
<%@page import="com.kmlink.ilmu.circulation.Global.DateFormatter"%> 



<style>
p {
    margin: 0;
    line-height: normal;
}
</style>

  	<div class ="row">
		        	
			        	<div class = "spine-label">	
			        	
		        			<div class = "text-center" style="line-height: 1.5;font-family :Times New Roman;margin-left: 7.1cm;margin-top: 1.0cm;
		        			font-size: 8pt" align="center">
		        			<!-- <div class="text-center" style="line-height: 1.5;font-family :Times New Roman;margin-left: 6.5cm;margin-top: 1.7cm;
		        			font-size: 14pt;font-weight: bold;" align="center"> -->
		        			<% 	
		        				String accession = request.getParameter("accession");
		        				QueryAcession queryAcession= new QueryAcession();
		        				List<book_spine> listData =new ArrayList<book_spine>();
		        				book_spine book_spinex = new book_spine();
		        				book_spinex = queryAcession.loadAcession(accession);
		        				listData.add(book_spinex);
	        					GeneralUtil generalUtil = new GeneralUtil();
		        				String styletype = "D";
		        				///String styletype = (String)request.getParameter("styletype");;
		        				String splitStyle = (String)request.getParameter("splitStyle");
		        				List<String> call_no = new ArrayList<String>();
		        				book_spine book_spine = listData.get(0);
		        				
		        				System.out.println(book_spine+"book_spine.getSpineSMD()"+styletype);
		        				
		        				if(styletype.equalsIgnoreCase("L")){
		        					System.out.println("LCCC");
		        					call_no = generalUtil.LLCSplit(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(), 
											book_spine.getVolume(),
											book_spine.getCopyNo(), 
											book_spine.getPartNo());
								
		        				}
		        				else{
		        					System.out.println("VVVVVVVV");
/* 		        					call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(),styletype,splitStyle); */
  		        					/*call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(),book_spine.getBranch(),styletype,splitStyle);*/
											
											/*call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(),styletype,splitStyle);*/
											
											
											call_no =  generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											///book_spine.getSpineSMD(),
											book_spine.getSMD(),
											book_spine.getCallNo(), 
											book_spine.getVolume(),
											book_spine.getBranch(),styletype, splitStyle);
											

		        				}
		        				System.out.println("LC" + call_no.toString()+"RRR");
								for(int f=0;f<call_no.size();f++){
									System.out.println(f+"LCCCC" + call_no.toString()+"pppp");
									if(call_no.get(f).isEmpty()){
										continue;
									}else{
							%>
									<%=call_no.get(f).trim()%>
										<%-- <%=call_no.get(f).trim().replace("<br>","")%> --%>
																					
							<%		}
									}
							%>
							</div>
		        		</div>

	        		</div>

		    <%
					//count = total - currentTotal;
				/* 	if(count >= 7 ){
						currentTotal+=8;
						startPoint= i;
					}else{
						currentTotal = currentTotal+count;
						startPoint=i;
					}		 */

      		%>
