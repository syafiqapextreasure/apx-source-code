<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/Maintenance/AATM0250/AATM0250.js"></script>
 <div class="modal-content">
  <div class="modal-header">
     <button type="button" class="close closePatrModal" aria-label="Close">
   	 <span aria-hidden="true">&times;</span></button>
           <h4 class="modal-titles">Search Criteria</h4>
  </div>
<div class="modal-body" >
<div class="panel-group" id="form_parent">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#form_parent" href="#search_title" id="search-title">
								Search
								<i class="indicator glyphicon glyphicon-chevron-down  pull-right"></i>
							</a>
						</h4>
					</div>
					<div  class="panel-collapse collapse in" id="search_title">
						<div class="panel-body">
							<div class="form-group">
									<div class="col-sm-3 col-md-3">
										<label>Search For</label>
									</div>
									<div class="col-sm-6 col-md-6">
										<input type="text" class="form-control criteria" name="inputCriteria" id="inputCriteria" style="height: 136px;">
									</div>
								</div>
						<div class="form-group">
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="control">Title
							 
								</label><br/>
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="instructor">Patron </label><br/>
							<label><input type="radio" name="retrieveRadios"
								id="optradio" value="control">Control No </label>
						</div>
					</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="findBtn">
						<span class="glyphicon glyphicon-search"></span>  Search
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close
					</button>
				</div>
			</div>
			</div>
	</div>
</div>
</div>
</div>
	
