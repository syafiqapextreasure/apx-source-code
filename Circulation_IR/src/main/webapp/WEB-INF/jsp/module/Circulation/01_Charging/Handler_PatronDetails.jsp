

<%

String action = request.getParameter("action");
if(action.equals("discharge")||action.equals("renewal")){
	String accession = request.getParameter("CT03DOCNO");
	String status = Circulation.getStatus(accession);
	String[] status1 = status.split(",");
	if((!(status1[0].trim()).equals("C")) && !(status1[0].trim()).equals("E")){
		out.println("066," + status1[1]);
	}else{
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.ilmu.circulation.Charging.*,java.util.*,com.ilmu.global.*,
				java.util.*, java.text.*"%>
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/Circulation/Circulation.js"></script>					
<%
	List<Patron> PatronDetails = null;
	PatronDetails = Patron.PatronDetails(accession);
	
	for (Patron i: PatronDetails) {
		%>
		   <div style="float:left">
		  
		  <img src="${pageContext.request.contextPath}/resources/image/user_default.png" id = "imgtest3" class="image--cover">
		  </div>
		<div class="row">
           <!--  <div class="col-md-1 col-lg-1 " align="center"> 
            
            <img   id = "imgtest3" alt="User Pic" style="border: 2px solid black;" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> 
             	
            </div> -->
       
            <div class=" col-md-7 col-lg-7 "> 
                <table class="table accession table-condensed" style='font-size:10pt'>
					<col width="50%">
						<tbody >
							<tr>
								<td colspan = "2">
									<button class="btn btn-primary" id='patrstatus' title="Circulation Details" data-toggle="modal" 
									data-target="#patrStatusModal" disabled><span class="glyphicon glyphicon glyphicon-book" style="color:white"></span></button>
									<button class="btn btn-primary" id='patrDetails' title="Patron Status Enquiry" data-toggle="modal" 
									data-target="#patrDetailsModal" disabled><span class="glyphicon glyphicon-user" style="color:white"></span></button>
									<!-- <a class="btn btn-primary" id='patrstatus' title="Patron's Status Enquiry" data-toggle="modal" 
									data-target="#patrStatusModal"><span class="glyphicon icon-user-add" style="color:white"></span></a> -->
									<button class="btn btn-primary" id='patrLateReturn' title="Late Return History" data-toggle="modal" 
									data-target="#patrLateReturnHis" disabled><span class="glyphicon glyphicon-info-sign" style="color:white"></span></button>
									<button class="btn btn-primary" id='override' title="Override Date" data-toggle="modal" 
									data-target="#overrideModal"><i class="fa fa-exchange" aria-hidden="true" style="color:white"></i></button>
									<button class="btn btn-primary" id='receipting' title="Receipting" disabled><span class="glyphicon glyphicon glyphicon-credit-card" style="color:white"></span></button>
								</td>
							</tr>
							<tr>
								<td>	
									<label>Patron ID: </label> 
									<span class="chargeaction"  style="display: none;">
										<input class="form-control patrid GL14PATR" name ="patr" id="GL14PATR"  type="text" style="display: inline;width:50%"  maxlength="12">
									</span>	
									<span class="dischargeaction" id="disGL14PATR">
									<%=i.getCI02PATR()%>
									</span>
									<input type="hidden" id="hiddenPatrId" value="<%=i.getCI02PATR()%>">

																		<button type="button" class="btn btn-primary btn-sm search" id="btn_add" data-toggle="modal" 
												data-target="#searchPatron"><span class="glyphicon glyphicon-search" style="color:white"></span></button>
								</td>
								
								<%-- <td>	
									<label>Patron ID: </label> 
									<span class="chargeaction">
										<input class="form-control GL14PATR" name ="patr" id="GL14PATR"  type="text" style="display: none;width:50%"  onchange="access(this)">
									</span>	
									<span class="dischargeaction" id="disGL14PATR">
									<%=i.getCI02PATR()%>
									</span>
									<input type="hidden" id="hiddenPatrId" value="<%=i.getCI02PATR()%>">
												
								</td> --%>
								
								
								<td>
									<label>Status: </label>
								<span class="GL14STAT" ><%=i.getGL08DESC() %></span>
								</td>
								
							 </tr>
							  <tr>
								<td>
									<label>Name:</label><span class="GL14NAME"><%=i.getGL14NAME()%></span>
								</td>
								 <td>
									<label>Membership Date:</label><span class="GL14MEMDATE"><%=GeneralUtility.StrToDate(i.getGL14MEMDATE())%></span>
								</td>
							  </tr>
							  <tr>
								<td>
									<label>Patron Category:</label><span class="GL14CATE"><%=i.getGL07DESC()%></span>
								</td>
								 <td>
									<label>Expiring Date:</label><span class="GL14EXPDATE"><%=GeneralUtility.StrToDate(i.getGL14EXPDATE())%></span>
								</td>
								
							  </tr>
							  <tr>
							   <td>
									<label>Branch:</label><span class="GL14BRANCH"><%=i.getGL71DESC()%></span>
								</td>
							  </tr>
						</tbody>
				</table>
             </div>
             <div class="col-md-3 col-lg-3 charging_info" style="border:1px solid #ddd;">
                <div class="row">
	               	<div class="form-group">
	               	    <div class="col-md-8 col-lg-8">
	               			<label>Item onloan </label>
	                	</div>		
	                	<div class="col-md-4 col-lg-4">
	                		<label>:</label>
	                		<label id="onloan"><%=i.getonloan() %></label>
	                	</div>
	                </div>
                </div>
                <div class="row">
	               	<div class="form-group">
	               	  	<div class="col-md-8 col-lg-8">
	               			<label>Item overdue </label>
	               		</div>
	              		<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemoverdue"><%=i.getoverdue() %></label>
	               		</div>
	               	</div>
                </div>
                <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Item reserved</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemreserved"><%=i.getreserve() %></label>
	               		</div>
	               	</div>
                </div>
               <%--  <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Item onhold</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="itemonhold"><%=i.getonhold() %></label>
		               	</div>
	               	</div>
                </div> --%>
                <div class="row">
	               	<div class="form-group">
		               	<div class="col-md-8 col-lg-8">
		               		<label>Outstanding Fines</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="fines"><%=i.getoutstanding() %></label>
		               	</div>
		               	<%-- 	<div class="col-md-8 col-lg-8">
		               		<label>Total Fines Charged</label>
		               	</div>
		               	<div class="col-md-4 col-lg-4">
		               		<label>:</label>
		               		<label id="totalfines"><%=i.getcharged() %></label>
		               	</div> --%>
	               	</div>
                </div>
             </div>
         </div>
		<%
	}
	}
}else{
String GL14PATR = request.getParameter("GL14PATR");
if(GL14PATR=="")
{
	out.println("033");
}
else
{


	boolean isPatronvalid =false;

	Patron patr = new Patron(GL14PATR);
	patr.checkPatr(GL14PATR);
	
	
	patr.getErrMessage();
	String errmessage=patr.getErrMessage();
	
	System.out.println(errmessage);
	System.out.println("Message" + patr.getErrMessage());

	if (errmessage!=null && !errmessage.isEmpty()) 
	{
		
		out.println(errmessage);
	}/* else if(date1.compareTo(date2)>0)	
	{
		out.println("032");
	
	} */
	else
	{
					System.out.println(patr.getMsPatronName("in2"+GL14PATR));
					if(patr.getMsPatronName(GL14PATR)!=null)
					{
						if(patr.getMsPatronStatus(GL14PATR)!=null)
						{
								
							//if (patr.getMsPatronElig().equals("Y"))
							//{
								isPatronvalid=true;
							//}
							
						}
					}
				
					if(isPatronvalid)
					{System.out.println(patr.getMsPatronName());
					String responsestring = patr.getMsPatronName()+"/n"
											+patr.getMsPatronCateDesc()+"/n"
											+patr.getMsPatronStatusDesc()+"/n"
											+patr.getMsMemDate()+"/n"
											+patr.getMsMemExpiryDate()+"/n"
											+patr.getMsNoCircByPatron()+"/n"
											+patr.getNoMsGetItemOverdue()+"/n"
											+patr.getNoMsGetItemReserved()+"/n"
											+patr.getMsGetTotalFinesOutstanding()+"/n"
											+patr.getMsGetTotalPaid() +"/n"
											+patr.getMsPatronBranchDesc();
					
											out.println(responsestring);
				
					//details=Circulation.viewtable(GL14PATR, "C");
				
					}
					
		
		}
	
}
}




%>

