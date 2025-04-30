<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.sql.*"%>

<input id="action" type="hidden" value="edit" />

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title">Edit Reserve</h4>
</div>

<div class="modal-body">
    <div class="padding-15" id="patronSearchPanel123">
        <div class="row pwidth-100">
            <div class="col-xs-12">
                <div class="margin-btm-5"></div>

                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="courseCodeCB" id="courseCodeCB" disabled/> Course Code :</div>
                            <div class="col-md-3" style="margin-right: -40;">
                                <div class="input-group select">
                                    <select
                                        class="labelBlack form-control courseCodeId"
                                        id="courseCodeId1"
                                        data-id="1"
                                        style="
                                            border-style: solid;
                                            border-width: thin;
                                            padding-top: 2px;
                                            padding-bottom: 0px;
                                            top: 2px;
                                            bottom: 2px;
                                            height: 30px;
                                            left: 0px;
                                            width: 86px;
                                            border-bottom-width: 0px;
                                            border-top-width: 0px;
                                            padding-left: -;
                                            margin-left: -;
                                        "
                                   disabled ></select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group select">
                                    <select
                                        class="labelBlack form-control courseCodeDesc"
                                        id="courseCodeDesc1"
                                        data-id="1"
                                        style="
                                            border-style: solid;
                                            border-width: thin;
                                            padding-top: 2px;
                                            padding-bottom: -;
                                            top: 2px;
                                            bottom: 2px;
                                            height: 30px;
                                            left: -;
                                            width: 250px;
                                            padding-left: -;
                                            border-left-width: -;
                                            border-bottom-width: -;
                                            border-top-width: -;
                                            border-bottom-width: -;
                                            padding-bottom: -;
                                            padding-bottom: -;
                                            padding-bottom: 0px;
                                            padding-left: 0px;
                                            border-left-width: 0px;
                                        "
                                    disabled></select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                                <input type="checkbox" class="form-check-input" name="semesterMasterCB" id="semesterMasterCB" disabled/>
                                Semester Master :
                            </div>
                            <div class="col-md-3" style="margin-right: -40;">
                                <div class="input-group select">
                                    <select
                                        class="labelBlack form-control semMasterId"
                                        id="semMasterId1"
                                        data-id="1"
                                        style="
                                            border-style: solid;
                                            border-width: thin;
                                            padding-top: 2px;
                                            padding-bottom: 0px;
                                            top: 2px;
                                            bottom: 2px;
                                            height: 30px;
                                            left: 0px;
                                            width: 86px;
                                            border-bottom-width: 0px;
                                            border-top-width: 0px;
                                            padding-left: -;
                                            margin-left: -;
                                        "
                                    disabled></select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group select">
                                    <select
                                        class="labelBlack form-control semMasterDesc"
                                        id="semMasterDesc1"
                                        data-id="1"
                                        style="
                                            border-style: solid;
                                            border-width: thin;
                                            padding-top: 2px;
                                            padding-bottom: -;
                                            top: 2px;
                                            bottom: 2px;
                                            height: 30px;
                                            left: -;
                                            width: 250px;
                                            padding-left: -;
                                            border-left-width: -;
                                            border-bottom-width: -;
                                            border-top-width: -;
                                            border-bottom-width: -;
                                            padding-bottom: -;
                                            padding-bottom: -;
                                            padding-bottom: 0px;
                                            padding-left: 0px;
                                            border-left-width: 0px;
                                        "
                                    disabled></select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 10px;">
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="subjectCB" id="subjectCB" disabled/> Subject* :</div>
                            <div class="col-md-3" style="margin-right: -40;">
                                <div class="input-group select">
                                    <select
                                        class="labelBlack form-control subjectId"
                                        id="subjectId1"
                                        data-id="1"
                                        style="
                                            border-style: solid;
                                            border-width: thin;
                                            padding-top: 2px;
                                            padding-bottom: 0px;
                                            top: 2px;
                                            bottom: 2px;
                                            height: 30px;
                                            left: 0px;
                                            width: 86px;
                                            border-bottom-width: 0px;
                                            border-top-width: 0px;
                                            padding-left: -;
                                            margin-left: -;
                                        "
                                    disabled></select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group select">
                                    <select
                                        class="labelBlack form-control subjectDesc"
                                        id="subjectDesc1"
                                        data-id="1"
                                        style="
                                            border-style: solid;
                                            border-width: thin;
                                            padding-top: 2px;
                                            padding-bottom: -;
                                            top: 2px;
                                            bottom: 2px;
                                            height: 30px;
                                            left: -;
                                            width: 250px;
                                            padding-left: -;
                                            border-left-width: -;
                                            border-bottom-width: -;
                                            border-top-width: -;
                                            border-bottom-width: -;
                                            padding-bottom: -;
                                            padding-bottom: -;
                                            padding-bottom: 0px;
                                            padding-left: 0px;
                                            border-left-width: 0px;
                                        "
                                    disabled></select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="instructorCB" id="instructorCB" disabled/> Instructor :</div>
                            <div class="col-md-4" style="margin-right: -50;">
                                <input type="text" id="instructorTF" name="instructorTF" option="Id" old-value="Id==" style="width: 180px;" disabled/>
                            </div>
                            <div class="col-md-2" style="width: 60px;">
                                <a href="Modal_PatrSearch" id="searchPatronID" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#searchPatronModal" > <span class="glyphicon glyphicon-search"></span></a>
                            </div>
                            <div class="col-md-5" style="margin-right: -50;">
                                <span id="lblInstructor" style="margin-left: 103px;"></span>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="ControlNoCB" id="controlNoCB" /> Control No :</div>
                            <div class="col-md-4" style="margin-right: -50;">
                                <!-- <input type="text" class="form-control CT03MATNO" id="CT03MATNO" value="" maxlength="10" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)" required /> -->
                            	<div class="input-group">
									<input type="text" class="form-control ctrlno" id="controlNo" name="controlNo" maxlength="10" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)" required disabled/>
										<a href="RetrieveTitleModal?action=20" id="searchISSN" class="input-group-addon btn btn-primary searchpatr" data-toggle="modal" data-target="#titleSearch" >
										<span class="glyphicon glyphicon-option-horizontal"></span></a>
								</div>
                            </div>
<!--                             <div class="col-sm-1 col-md-1">
                                <button type="button" class="btn btn-default selectPopup" data-toggle="modal" data-target="#titleSearchAccMain" href="RetrieveTitleAccMainModal?action=8&module=Cataloging">...</button>
                            </div> -->
                            <div class="col-md-4" style="margin-left: 50px;">
                                <input type="checkbox" class="form-check-input" name="addMandatoryCheck" id="addMandatoryCheck" disabled/>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="AccNoCB" id="AccNoCB" disabled/> Accession No :</div>
                            <div class="col-md-2">
                                <div class="input-group select">
                                    <select class="form-control" id="accessionNo" style="width: 250px;" disabled></select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="startDateCB" id="startDateCB" /> Start Date :</div>
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
                            <div class="col-md-3"><input type="checkbox" class="form-check-input" name="endDateCB" id="endDateCB" /> End Date :</div>
                            <div class="col-md-4">
                                <div class="input-group date" id="dateTo">
                                    <input type="text" class="form-control" name="endDate" id="endDate" />
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
                                                    <div class="row" style="margin-left: 0px;">Call Number: <input type="text" id="callNo" name="callNo" style="width: 680px; margin-bottom: 5px; margin-left: 10px;" disabled /></div>
                                                    <div class="row" style="margin-left: 0px;">Subject: <input type="text" id="subject" name="subject" style="width: 680px; margin-bottom: 5px; margin-left: 40px;" disabled /></div>
                                                    <div class="row" style="margin-left: 0px;">Title: <input type="text" id="title" name="title" style="width: 680px; margin-bottom: 5px; margin-left: 60px;" disabled /></div>
                                                    <div class="row" style="margin-left: 0px;">Author <input type="text" id="author" name="author" style="width: 680px; margin-bottom: 5px; margin-left: 50px;" disabled /></div>
                                                    <div class="row" style="margin-left: 0px;">Publication: <input type="text" id="publication" name="publication" style="width: 680px; margin-bottom: 5px; margin-left: 20px;" disabled /></div>
                                                    <div class="row" style="margin-left: 0px;">Location <input type="text" id="location" name="location" style="width: 680px; margin-bottom: 5px; margin-left: 40px;" disabled /></div>
                                                    <div class="row" style="margin-left: 0px;">Branch <input type="text" id="branch" name="branch" style="width: 680px; margin-bottom: 5px; margin-left: 48px;" disabled /></div>
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
        <div class="float-right margin-top-15" style="margin-left: 530px;">
            <button type="button" class="btn btn-default" id="okBtn">
                <span aria-hidden="true"> <i class="fa fa-check"></i> OK </span>
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
                <span aria-hidden="true"> <i class="fa fa-close"></i> Close </span>
            </button>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/reserve/reserve.js"></script>