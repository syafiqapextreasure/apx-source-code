
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.acquisition.orderManagement.vendorFeedback"%>
<%@page import="java.util.List"%>
<%-- <%@page import="com.ilmu.foundation.global.*"%> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/vendorFeedback.js"></script>	

<%
	String orderNo = request.getParameter("orderNo");
	System.out.println("PPPPP " +orderNo);
	
	/* String removeDoubleordernumber=orderNo.replace("\"", "");
	System.out.println(removeDoubleordernumber + " removeDoubleordernumber"); */

	
	/* String removeDoubleordernumber2=orderNo2..replace(/[^\w]/gi, '-');
	System.out.println(removeDoubleordernumber2.trim() + " removeDoubleordernumber"); */
%>


			<div class="modal-header">
				<h5 class="modal-title" id="modalName" align="center">Vendor Feedback</h5>
					<button type="button" id="closeModalVF" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
			</div>
			<div class="modal-body">
						<form role="form" class="form-horizontal" id="formdataVendorFeedback" method="post">
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3">
									<label for="orderNoVF">Order Number</label>
								</div>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="orderNoVF" name="orderNoVF" value="<%=orderNo%>">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Feedback</label></div>
								<div class="col-sm-6">
									<select class="form-control" id="feedbackVF" name="feedbackVF">
										<jsp:include page="../Select_VendorFeedback.jsp"></jsp:include>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-3"><label>Feedback Date</label></div>
								 <div class='col-sm-4'>
								 	<!-- <input type="text" class="form-control" id="VFDate" name="VFDate"> -->
			                      	<div class="input-group date" id="feedbackDate">
			  						 	<input type="text" class="form-control" id="VFDate" name="VFDate">
			  						 	<span class="input-group-addon">
			  							<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
			        			</div>
							</div>
							
							
								<table id="table-VFDetail" class="table table-bordered table-striped feedback">
									<thead>
										<tr>
											<th>Order No</th>
											<th>Date</th>
											<th>Feedback</th>
											<th>Officer</th>
										</tr>
									</thead>
								</table>
						 <div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="button-fbProceed">Proceed</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
						</form>
					</div>
				
