<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*,javax.sql.*"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.GeneralUtil"%>
<%@page import="javax.swing.event.ListDataEvent"%>
<%@page import="java.util.*,javax.sql.*"%>
<%@ page import="com.kmlink.ilmu.parable.parable_beta.QueryAcession"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.object.book_spine"%>
<%@page import="com.kmlink.ilmu.parable.parable_beta.Config_Print"%>

  	<div class ="row">
		        	
			        	<div class = "spine-label">	
			        	
		        			<div class = "text-center" style="line-height: 1.0;font-style=:Arial;margin-left: 280px;margin-top: 30px;" align="center">
		        			<% 	
		        				String accession = request.getParameter("accession");
		        				QueryAcession queryAcession= new QueryAcession();
		        				List<book_spine> listData =new ArrayList<book_spine>();
		        				book_spine book_spinex = new book_spine();
		        				book_spinex = queryAcession.loadAcession(accession);
		        				listData.add(book_spinex);
	        					GeneralUtil generalUtil = new GeneralUtil();
		        				String styletype = "D";
		        				String splitStyle = (String)request.getParameter("splitStyle");
		        				List<String> call_no = new ArrayList<String>();
		        				book_spine book_spine = listData.get(0);
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
/* 		        					call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(),styletype,splitStyle); */
  		        					call_no = generalUtil.DDCSplitTest(book_spine.getSpineItemCategory(),
											book_spine.getSpineSMD(),
											book_spine.getCallNo(),book_spine.getBranch(),styletype,splitStyle);
		        				}
								for(int f=0;f<call_no.size();f++){
									System.out.println("LCCCC" + call_no.toString());
									if(call_no.get(f).isEmpty()){
										continue;
									}else{
							%>
										<%=call_no.get(f).trim()%>
																					
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
