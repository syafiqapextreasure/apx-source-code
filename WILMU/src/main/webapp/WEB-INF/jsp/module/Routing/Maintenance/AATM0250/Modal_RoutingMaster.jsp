<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.sql.*"%>
 <div class="modal-content">
<input id="action" type="hidden" value="add" />

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title"></h4>
</div>

<div class="modal-body">
  <form role="form" class="form-horizontal" id="formroutingmaster" method="post">
    <div class="padding-15" id="patronSearchPanel123">
        <div class="row pwidth-100">
            <div class="col-xs-12">
                <div class="margin-btm-5"></div>
                <div class="form-horizontal">
                    <div class="form-group">
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="control" name="checkedValue" id="controlno" />Control No <span style="color:red">*</span> </label>
                            	</div>
                            	<div class="col-sm-4">
							   	<div class="input-group">
							    	<input type="text" class="form-control CT03MATNO" id="input-contorlNo" name="inputControlNo" onkeydown="matnoKeydown(event,this)" onfocusout="matnoFocusOut(this)">
									    <a href="Global?type=Modal&name=Modal_BibSearch&action=8&module=Cataloging" id="searchCtrlNo" class="input-group-addon btn btn-primary" data-toggle="modal" data-target="#titleSearch">
									    <span class="glyphicon glyphicon-th-list"></span></a>
							    	</div>
							</div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                                <label for="isbn"><input type="checkbox" class="form-check-input"  value="title" name="checkedValue" id="title" />
                               Title
                                </label>
                            </div>
                           <div class="col-md-4">
                                 <textarea rows="4" cols="30" id="titles" disabled></textarea>
                            </div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 10px;">
                            <div class="col-md-3"><label><input type="checkbox" class="form-check-input" value="patron" name="checkedValue" id="patron" /> Patron Name <span style="color:red">*</span> </label></div>
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
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="copy" name="checkedValue" id="copy" /> Copy No. <span style="color:red">*</span> </label>
                            	</div>
                            <div class="col-sm-4">
								<select id="copySelect" name="copySelect" class="form-control">
									<option value="0">Please select</option>
								</select>
							</div>
                
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"><input type="checkbox" class="form-check-input" value="grace" name="checkedValue" id="grace" /> Grace </label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="graceperiod"><span> days</span>
							</div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label for="isbn"> Priority </label>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="priority" disabled>
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
		
				