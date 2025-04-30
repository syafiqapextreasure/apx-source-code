<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.sql.*"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/init.js"></script>
	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/Maintenance/AAVM0150/AAVM0150.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/Reserve_Collection/ColReorderWithResize.js"></script>
 <div class="modal-content">
<input id="action" type="hidden" value="add" />

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title"></h4>
</div>

<div class="modal-body">
  <form role="form" class="form-horizontal" id="formreservecollection" method="post">
    <div class="padding-15" id="patronSearchPanel123">
        <div class="row pwidth-100">
            <div class="col-xs-12">
                <div class="margin-btm-5"></div>
                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="course" name="checkedValue" id="courseCodeCB" /> Course Code </label>
                            	</div>
                            <div class="col-sm-4">
                                   <jsp:include page="../../../../include/shared/Selection/Select_CourseCode.jsp"></jsp:include>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                                <label for="isbn"><input type="checkbox" class="form-check-input"  value="semMasterId1" name="checkedValue" id="semesterMasterCB" />
                                Semester Master <span style="color:red">*</span>
                                </label>
                            </div>
                            <div class="col-md-4">
                                   <select
                                        class="labelBlack form-control semMasterId"
                                        id="semMasterId1" name="semMasterId">
                                        <option value="0">Please Select</option>
                                      	<jsp:include page="../../../../include/shared/Selection/Select_Fndcode.jsp">
											<jsp:param name="fcode" value="L" /> 
										</jsp:include>
                                        </select> 
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 10px;">
                            <div class="col-md-3"><label><input type="checkbox" class="form-check-input" value="subj" name="checkedValue" id="subjectCB" /> Subject<span style="color:red">*</span></label></div>
                                       <div class="col-sm-4">
			                                   <jsp:include page="../../../../include/shared/Selection/Select_AcdSubj.jsp"></jsp:include>
			                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="requestorId" name="checkedValue" id="instructorCB" /> Instructor</label>
                            	</div>
                            <div class="col-sm-4">
										<div class="input-group">
											<input type="text" class="form-control lblPatronID" id="requestorId" name="requestorId">
												<a href="Modal_PatrSearch" id="searchpatr" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#modal_patrSearchMono">
												<span class="glyphicon glyphicon-th-list"></span></a>
										</div>
									</div>
                            <div class="col-md-5">
                               <span id="div-reqname"></span>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="input-contorlNo" name="checkedValue" id="controlNoCB" /> Control No </label>
                            </div>
                            <div class="col-sm-4">
										    	<div class="input-group">
											    	<input type="text" class="form-control CT03MATNO" id="input-contorlNo" name="inputControlNo" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)">
												    <a href="Global?type=Modal&name=Modal_BibSearch&action=8&module=Cataloging" id="searchCtrlNo" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
												    <span class="glyphicon glyphicon-th-list"></span></a>
										    	</div>
											</div>
                              <div class="col-md-4">
                                <input type="checkbox" class="form-check-input" name="addMandatoryCheck" id="addMandatoryCheck" />
                                <label>Item not on catalog database</label>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="accessionNo" name="checkedValue" id="AccNoCB" /> Accession No </label>
                            </div>
                            <div class="col-md-4">
                                    <select class="form-control" id="accessionNo"><option value="0">Please Select</option></select>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="startDate" name="checkedValue" id="startDateCB" /> Start Date </label>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group date" id="dateFrom">
                                    <input type="text" class="form-control" name="startDate" id="startDate" />
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-calendar"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="endDate" name="checkedValue" id="endDateCB" /> End Date </label>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group date" id="dateTo">
                                    <input type="text" class="form-control" name="endDate" id="endDate"/>
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-calendar"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group"></div>

                    <div class="form-group"></div>
                </div>
               
            </div>
        </div>
        <div class="margin-btm-15"></div>
        <div class="row pwidth-100">
            <div
                class="col-xs-12 col-sm-12 col-md-12 col-lg-12"
                id="patronListingContainer"
                action-url="/Acquisition/acquisition/patron/search-patron/submit-ajax"
                action-search-mode="Id"
                action-search-filtered-by=""
                action-search-sorted-by="Id"
                action-search-text=""
            >
                <div class="panel panel-default">
                    <div id="statusCombineDetail" class="panel-collapse collapse in">
                        <div class="panel-body" id="statusCombine">
                            <div class="row">
                                <div class="col-md-12 col-lg-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="nav nav-tabs">
                                            <li class="active">
                                                <a href="#reserveCollection" data-toggle="tab" aria-expanded="false"><strong>Reserve Collection</strong></a>
                                            </li>
                                            <li>
                                                <a href="#viewItemDetails" data-toggle="tab"><strong>View Item Details</strong></a>
                                            </li>
                                        </ul>

                                        <!-- TAB CONTENT -->
                                        <div class="tab-content">
                                            <!-- TAB 1 -->
                                            <div class="tab-pane active" id="reserveCollection">
                                                <div class="box-body">
                                                    <div class="row" style="margin-left: 0px;">
                                                      <div class="col-md-2">
                                                    	<label for="isbn">Call Number</label>
                                                    </div>
                                                      <div class="col-md-10">
                                                    	<input type="text" class="form-control" id="callNo" name="callNo" style="margin-bottom: 5px;" disabled />
                                                    	</div>
                                                    </div>
                                                    <div class="row" style="margin-left: 0px;">
                                                    <div class="col-md-2">
                                                    	<label for="isbn">Subject</label>
                                                    </div>
                                                    <div class="col-md-10">
                                                    	<input type="text" id="subject" class="form-control" name="subject" style="margin-bottom: 5px;" disabled />
                                                   </div>
                                                    </div>
                                                    <div class="row" style="margin-left: 0px;">
                                                    <div class="col-md-2">
                                                    	<label for="isbn">Title</label>
                                                    </div>
                                                    <div class="col-md-10">
                                                    <textarea id="titles" class="form-control" name="title" style="margin-bottom: 5px;" disabled >
                                                    </textarea>
                                                    	
                                                    </div>
                                                    </div>
                                                    <div class="row" style="margin-left: 0px;">
                                                    <div class="col-md-2">
                                                    	<label for="isbn">Author </label>
                                                    </div>
                                                    <div class="col-md-10">
                                                    	<input type="text" id="author" class="form-control" name="author" style="margin-bottom: 5px;" disabled />
                                                   	</div>
                                                   </div>
                                                    <div class="row" style="margin-left: 0px;">
                                                    <div class="col-md-2">
                                                    	<label for="isbn">Publication</label>
                                                    </div>
                                                    <div class="col-md-10">
                                                    	<input type="text" id="publication" class="form-control" name="publication" style="margin-bottom: 5px;" disabled />
                                                    </div>
                                                    </div>
                                                    <div class="row remove" style="margin-left: 0px;">
                                                    <div class="col-md-2">
                                                    <label for="isbn">Location </label>
                                                    </div>
                                                    <div class="col-md-10">
                                                    <input type="text" id="location" class="form-control" name="location" style="margin-bottom: 5px;" disabled />
                                                   </div>
                                                    </div>
                                                    <div class="row remove" style="margin-left: 0px;">
                                                    <div class="col-md-2">
                                                    	<label for="isbn">Branch </label>
                                                    </div>
                                                    <div class="col-md-10">
                                                    	<input type="text" id="branch" class="form-control" name="branch" style="margin-bottom: 5px;" disabled />
                                                   </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /.End Tab item -->
                                            <!-- TAB 2 -->
                                            <div class="tab-pane" id="viewItemDetails">
                                                <div class="box-body">
                                                    <div class="row">
                                                        <table class="table table-hover table-responsive" id="viewItemTable">
                                                            <thead>
                                                                <tr>
                                                                    <th data-sortable="true">Accession No</th>
                                                                    <th data-sortable="true">Location</th>
                                                                    <th data-sortable="true">Branch</th>
                                                                    <th data-sortable="true">Item Category</th>
                                                                    <th data-sortable="true">SMD</th>
                                                                    <th data-sortable="true">Status</th>
                                                                    <th data-sortable="true">Patron ID</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody></tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- /.End Tab relatedPatron -->
                                        </div>
                                        <!-- /.END TAB CONTENT -->
                                    </div>
                                    <!-- /.END CUSTOM TAB -->
                                </div>
			                    <div class="col-md-7">
									Date Recorded<span id="dateRecorded" style="margin-left: 50px; margin-right: 130px;"></span>
								</div>
								<div class="col-md-5">
									Recorded By:<span id="recordedBy" style="margin-left: 50px;"></span>
								</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group modal-footer">
        <button type="submit" id="okBtn" class="btn btn-primary">Save</button>
 		<button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
		</div>
    </div>
    </form>
</div>
</div>		
				<!-- MODAL WHEN CLICK Search officer -->
		<div class="modal fade" id="modal_patrSearchMono" tabindex="-1" role="dialog" aria-labelledby="modal_patrSearchMono" data-keyboard="false" data-backdrop="static"> 
			<div class="modal-dialog" style="width:80%;">
				    <div class="modal-content" id="modal_patrSearchMonoContent">
					  <!-- Remote content load here  -->
				  	</div>
			</div>
		</div>
		<!-- Modal END modal search officer-->
		
				<!-- MODAL WHEN CLICK Search ACCESSION NUMBER -->
		<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog" role="document" style="width:80%;overflow:auto">
				<div class="modal-content">
				<!-- Remote content load here -->
				</div>
			</div>
		</div>
		<!-- Modal END modal search  ACCESSION number-->