<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	


<%
	String action = request.getParameter("action");
	System.out.println(action);
	int tplID = 0;
	String controlNoInput = null;
	List<BO_Record> CT02LEADER = null;
	List<BO_Record> BORecord = null;
	BO_Record CTMATM = null;
	System.out.println(action);

		controlNoInput = request.getParameter("matno");
		System.out.println(controlNoInput);
		CT02LEADER = BO_Record.getLeader(controlNoInput);
		CTMATM = BO_Record.getCTMATM(controlNoInput);

%>
<div id='BOdetails'>
<a data-toggle="modal" href="#ctrlTagsForm" class="ctrlTags btn btn-circle btn-raised btn-primary btn-sm" title="Add Control Fields" data-autflag="null" data-id="0060" value="006"><span class="glyphicon glyphicon-edit"></span></a>
<a data-toggle="modal" href="#ctrlTagsForm" class="ctrlTags btn btn-circle btn-raised btn-primary btn-sm" title="Add Control Fields1" data-autflag="null" data-id="0060" value="006"><span class="glyphicon glyphicon-edit"></span></a>

</div>


 	<div class="modal fade" style="z-index:1050" id="viewSubfields" tabindex="-1" role="dialog" aria-labelledby="viewSubfields">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div id="subfList"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="ctrlTagsForms" tabindex="-1" role="dialog" aria-labelledby="viewSubfields">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div class="ctrlTagDisplay"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="ctrlTagsForm" tabindex="-1" role="dialog" aria-labelledby="viewSubfields">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div id="ctrlTagDisplay"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="addNewRecord" tabindex="-1" role="dialog" aria-labelledby="addNewRecord">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" id='modalTag'>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch">
		<div class="modal-dialog" role="document" style="width:900px;">
			<div class="modal-content">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	