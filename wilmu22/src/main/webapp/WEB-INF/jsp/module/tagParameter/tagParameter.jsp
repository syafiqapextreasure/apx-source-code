<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%--     <%@ include file="/WEB-INF/jsp/module/tagParameter/Handler_DeleteTag.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/base/js/global.js"></script>	

<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/sweetalert2/js/sweetalert2.min.js "></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/moment.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tagParameter/js/validateTagParameter.js"></script>

<!-- <script type="text/javascript" src="base64.js"></script>  -->

<script>
$( document ).ready(function() {
	 
	var username = window.frameElement.getAttribute("name");

	var programID = $("#programID").val();
	 $("#liferayLogin").val(loggedIn);
	 $.get("SignedInUser",{username:username,programID:programID}, function(data){
			 if(data.trim()=="true"){
				 $(".box").show();
			 }else{
				 swal({
					  title: 'Access is denied',
					  text: 'You do not have permission to view this resource using the credentials that you supplied.',
					  showCancelButton: false,
					  showConfirmButton: false,
					  allowOutsideClick: false
					}).then(
					  function () {},
					  function (dismiss) {
					    if (dismiss === 'timer') {
					    }
					  }
					)
			 }
		});	
});

 </script>
 
<style>
    textarea {
        resize: none;
    }
</style>
</head>
<body>

	<div class ="col-md-offset-10 col-md-2 text-right" style="margin-top: 10px;">

		<button type="button" class = "btn btn-primary submit" id ="addNewTagBtn" data-toggle="modal" data-target="#addModal" title="Add" style="background-color: #1ab394"><span class="glyphicon glyphicon-plus" style="color:white"></span></button>
		<button type="button" class = "btn btn-primary submit" id ="findRecordBtn" data-toggle="modal" data-target="#searchModal" title="Search" style="background-color: #1ab394"><span class="glyphicon glyphicon-search" style="color:white"></span></button>
	</div>
	<div class="col-md-12">
		<div class="form-group">
			<div class="list-group tagParameter"></div>
			<div class="table-responsive" style="overflow-x: auto;">
				<table id="tagParameter_table" class="table table-bordered table-striped"
					style="overflow-x: auto">
 					<thead>
						<tr>
							<th>No</th>
							<th>MARC</th>
							<th>Tag</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- Add Modal -->
	<div class="modal" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="width: 85%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<label>Add Tag Parameter</label>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>MARC:</label></div>
					</div>
					<div class="col-md-2">
						<div class="input-group select">
							<!-- <select class="labelBlack form-control MARCid" id="MARCid"></select> -->
							<select class="labelBlack form-control patronCategoryId" id="patronCategoryId1" data-id="1"
								style="border-style: solid; border-width: thin; padding-top: 2px;padding-bottom: 0px;top: 2px;bottom: 2px;height: 30px;left: 0px;width: 86px;border-bottom-width: 0px;border-top-width: 0px;padding-left: -;margin-left: -;"></select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="input-group select">		
							<select class="labelBlack form-control patronCategoryDesc" id="patronCategoryDesc1" data-id="1" 
								style="border-style: solid; border-width: thin; padding-top: 2px;padding-bottom: -;top: 2px;bottom: 2px;height: 30px;left: -;width: 250px;padding-left: -;border-left-width: -;border-bottom-width: -;border-top-width: -;border-bottom-width: -;padding-bottom: -;padding-bottom: -;padding-bottom: 0px;padding-left: 0px;border-left-width: 0px;"></select>
						</div>
					</div>
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;top: 5px;">
					<div class="col-md-2" style="top: 4px;">
						<label>Tag:</label>
					</div>
					<div class="col-md-6">
						<input type="text" id="addTag" name="addTag" style="width: 378px;">
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2" style="margin-top: -3;">
						<div id="description" style="margin-top: 10px;"><label>Description</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="addDesc" name="addDesc" style="width: 378px;">
					</div>	
				</div>		
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Abbreviated Desc</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="addAbbreDesc" name="addAbbreDesc" style="width: 378px;">
					</div>	
				</div>	

				
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12" style="top: 10px";>			
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#addParameter" data-toggle="tab" aria-expanded="false"><strong>Parameters</strong></a></li>
							<li><a href="#addIndicator1" data-toggle="tab"><strong>Indicator 1</strong></a></li>
							<li><a href="#addIndicator2" data-toggle="tab" aria-expanded="true"><strong>Indicator 2</strong></a></li>
			             	<li><a href="#addSubfieldaj" data-toggle="tab"><strong>Subfield (a-j)</strong></a></li>
			             	<li><a href="#addSubfieldkt" data-toggle="tab"><strong>Subfield (k-t)</strong></a></li>
			             	<li><a href="#addSubfielduz" data-toggle="tab"><strong>Subfield (u-z)</strong></a></li>
			             	<li><a href="#addSubfield09" data-toggle="tab"><strong>Subfield (0-9)</strong></a></li>
						</ul>
			
						<!-- TAB CONTENT -->
						<div class="tab-content">
		
						<!-- TAB 1 -->
						<div class="tab-pane active" id="addParameter">
							<div class="box-body">
								<form>
								  	<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Table:</label>
									    <div class="col-sm-4">
									      	<input type="text" id="addIndexTable" name="addIndexTable" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addRepeatableCheck" id="addRepeatableCheck">
		           							Repeatable
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addAuthorityFlagCheck" id="addAuthorityFlagCheck">
		           							Authority Flag
		           						</div>
		           					</div>
		           					<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Acronym:</label>
									    <div class="col-sm-4">
									      	<input type="text" id="addAcronym" name="addAcronym" style="width: 208px;">
									    </div>
	           						  	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addMandatoryCheck" id="addMandatoryCheck">
		           							Mandatory
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addCopyPasteCheck" id="addCopyPasteCheck">
		           							Copy and Paste
		           						</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Authority Group</label>
									    <div class="col-sm-4">
									      	<input type="text" name="addAuthorityGroup" id="addAuthorityGroup" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addIndexFlagCheck" id="addIndexFlagCheck">
		           							Index Flag
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addParamLinkCheck" id="addParamLinkCheck">
		           							PARAMIPS Link
		           						</div>
	           						</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Field Length</label>
									    <div class="col-sm-4">
									      	<input type="text" name="addFieldLength" id="addFieldLength" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addKeywordCheck" id="addKeywordCheck">
		           							Keyword
		           						</div>
									   	<div class="col-sm-3">           						 
										    <input type="checkbox" class="form-check-input" name="addMultimediaTagCheck" id="addMultimediaTagCheck">
		           							Multimedia Tag
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Language</label>
									    <div class="col-sm-4">
									      	<input type="text" name="addIndexLanguage" id="addIndexLanguage" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addDuplicateCheck" id="addDuplicateCheck">
		           							Duplicate Check
		           						</div>
		           						<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="addVisible" id="addVisible">
		           							Visible
		           						</div>
								  	</div>	  
								   	<div class="form-group row indicator">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Indicator</label>
									    <div class="col-sm-4"> Indicator 1				
										      <select id="addInd1">
										        <option value="1">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
											  Indicator 2 <select id="addInd2"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
		           						</div>
		           						<div class="col-sm-3 authorityType">Authority Type
		           						 	<select id="authorityType">
											    <option value=""></option>
											    <option value="Heading">Heading</option>
											    <option value="Unused">Unused</option>
											  </select>
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Value</label>
									    <div class="col-sm-6">
									      	<input type="text" id="addDefaultValue" name="addDefaultValue" style="width: 208px;">
									    </div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Remark</label>
									    <div class="col-sm-6">
									      	<input type="text" id="addRemark" name="addRemark" style="width: 208px;">
									    </div>
								  	</div>
								</form>
							</div>
						</div><!-- /.End Tab item -->
						<!-- TAB 2 -->	 
					   	<div class="tab-pane" id="addIndicator1">
					   		<div class="box-body">					   		
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CBUndefine"> Undefined(#)
           							<input type="text" id="addInd1TFUndefine" name="addInd1TFUndefine" style="width: 650px;margin-bottom: 5px;" disabled>
           						</div>	
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB0"> 0
           							<input type="text" id="addInd1TF0" name="addInd1TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>					   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB1"> 1
           							<input type="text" id="addInd1TF1" name="addInd1TF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>						
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB2"> 2
           							<input type="text" id="addInd1TF2" name="addInd1TF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB3"> 3
           							<input type="text" id="addInd1TF3" name="addInd1TF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB4"> 4
           							<input type="text" id="addInd1TF4" name="addInd1TF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB5"> 5
           							<input type="text" id="addInd1TF5" name="addInd1TF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB6"> 6
           							<input type="text" id="addInd1TF6" name="addInd1TF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB7"> 7
           							<input type="text" id="addInd1TF7" name="addInd1TF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB8"> 8
           							<input type="text" id="addInd1TF8" name="addInd1TF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CB9"> 9
           							<input type="text" id="addInd1TF9" name="addInd1TF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>             					        						           						          						
 							</div>
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 3 -->	 
					   	<div class="tab-pane" id="addIndicator2">
					   		<div class="box-body">
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd1CBUndefine"> Undefined(#)
           							<input type="text" id="addInd2TFUndefine" name="addInd2TFUndefine" style="width: 650px;margin-bottom: 5px;" disabled>
           						</div>	
								 <div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" id="addInd2CB0"> 0
           							<input type="text" id="addInd2TF0" name="addInd2TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB1"> 1
           							<input type="text" id="addInd2TF1" name="addInd2TF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB2"> 2
           							<input type="text" id="addInd2TF2" name="addInd2TF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB3"> 3
           							<input type="text" id="addInd2TF3" name="addInd2TF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB4"> 4
           							<input type="text" id="addInd2TF4" name="addInd2TF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB5"> 5
           							<input type="text" id="addInd2TF5" name="addInd2TF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB6"> 6
           							<input type="text" id="addInd2TF6" name="addInd2TF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB7"> 7
           							<input type="text" id="addInd2TF7" name="addInd2TF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB8"> 8
           							<input type="text" id="addInd2TF8" name="addInd2TF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addInd2CB9"> 9
           							<input type="text" id="addInd2TF9" name="addInd2TF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           											    
             				</div><!-- END box body requestStatus-->
						</div><!-- /.End Tab requestStatus -->
						
						<!-- TAB 4 -->	 
					   	<div class="tab-pane" id="addSubfieldaj">
					   		<div class="box-body">
								<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBa"> a
           							<input type="text" id="addSFTFa" name="addSFTFa" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBb"> b
           							<input type="text" id="addSFTFb" name="addSFTFb" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBc"> c
           							<input type="text" id="addSFTFc" name="addSFTFc" style="width: 723px;margin-bottom: 5px; margin-left: 1px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBd"> d
           							<input type="text" id="addSFTFd" name="addSFTFd" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBe"> e
           							<input type="text" id="addSFTFe" name="addSFTFe" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBf"> f
           							<input type="text" id="addSFTFf" name="addSFTFf" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBg"> g
           							<input type="text" id="addSFTFg" name="addSFTFg" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBh"> h
           							<input type="text" id="addSFTFh" name="addSFTFh" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBi"> i
           							<input type="text" id="addSFTFi" name="addSFTFi" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div> 
        						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBj"> j
           							<input type="text" id="addSFTFj" name="addSFTFj" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div>          						      										    
             				</div><!-- END box body personalInfo-->
						</div><!-- /.End Tab personalInfo -->
						
						<!-- TAB 5 -->	 
					   	<div class="tab-pane" id="addSubfieldkt">
					   		<div class="box-body">	
 					   			<div class="row">  
           							<input type="checkbox" class="form-check-input" id="addSFCBk"> k
           							<input type="text" id="addSFTFk" name="addSFTFk" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBl"> l
           							<input type="text" id="addSFTFl" name="addSFTFl" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBm"> m
           							<input type="text" id="addSFTFm" name="addSFTFm" style="width: 719px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBn"> n
           							<input type="text" id="addSFTFn" name="addSFTFn" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBo"> o
           							<input type="text" id="addSFTFo" name="addSFTFo" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBp"> p
           							<input type="text" id="addSFTFp" name="addSFTFp"style="width: 723px; margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBq"> q
           							<input type="text" id="addSFTFq" name="addSFTFq" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBr"> r
           							<input type="text" id="addSFTFr" name="addSFTFr" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBs"> s
           							<input type="text" id="addSFTFs" name="addSFTFs" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>      
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBt"> t
           							<input type="text" id="addSFTFt" name="addSFTFt" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>    	
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 6 -->	 
					   	<div class="tab-pane" id="addSubfielduz">
					   		<div class="box-body">	
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBu"> u
           							<input type="text" id="addSFTFu" name="addSFTFu" style="width: 723px;margin-bottom: 5px ; margin-left: 2px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBv"> v
           							<input type="text" id="addSFTFv" name="addSFTFv" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBw"> w
           							<input type="text" id="addSFTFw" name="addSFTFw" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBx"> x
           							<input type="text" id="addSFTFx" name="addSFTFx" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBy"> y
           							<input type="text" id="addSFTFy" name="addSFTFy" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCBz"> z
           							<input type="text" id="addSFTFz" name="addSFTFz" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>           				
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 7 -->	 
					   	<div class="tab-pane" id="addSubfield09">
					   		<div class="box-body">	
		 						<div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" id="addSFCB0"> 0
           							<input type="text" id="addSFTF0" name="addSFTF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB1"> 1
           							<input type="text" id="addSFTF1" name="addSFTF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB2"> 2
           							<input type="text" id="addSFTF2" name="addSFTF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB3"> 3
           							<input type="text" id="addSFTF3" name="addSFTF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB4"> 4
           							<input type="text" id="addSFTF4" name="addSFTF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB5"> 5
           							<input type="text" id="addSFTF5" name="addSFTF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB6"> 6
           							<input type="text" id="addSFTF6" name="addSFTF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB7"> 7
           							<input type="text" id="addSFTF7" name="addSFTF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB8"> 8
           							<input type="text" id="addSFTF8" name="addSFTF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="addSFCB9"> 9
           							<input type="text" id="addSFTF9" name="addSFTF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>					
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->

						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
					<div class="col-md-12">
						<div class="col-md-6">
							Date Recorded :<span id="dateRecorded" style="margin-left: 50px;"></span>
						</div>
						<div class="col-md-6">
							Recorded by: <span id="recordedBy" style="margin-left: 50px;"></span>
						</div>
					</div>	
       			</div>   		
     		</div>
      	</div>
    </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary ml-auto" id="addOkBtn" style="background-color: #1ab394">Add New Tag</button>
					<button type="button" class="btn btn-primary" id="close" style="background-color: #1ab394" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>	
	<!-- Edit Modal -->
	<div class="modal" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="width: 85%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<label>Edit Tag Parameter</label>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>MARC:</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="editMARC" name="editMARC" style="width: 378px;">
					</div>
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;top: 5px;">
					<div class="col-md-2">
						<label>Tag:</label>
					</div>
					<div class="col-md-6">
						<input type="text" id="editTag" name="editTag" style="width: 378px;">
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div id="description" style="margin-top: 10px;"><label>Description</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="editDesc" name="editDesc" style="width: 378px;">
					</div>	
				</div>		
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Abbreviated Desc</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="editAbbreDesc" name="editAbbreDesc" style="width: 378px;">
					</div>	
				</div>	

				
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">			
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#editParameter" data-toggle="tab" aria-expanded="false"><strong>Parameters</strong></a></li>
							<li><a href="#editIndicator1" data-toggle="tab"><strong>Indicator 1</strong></a></li>
							<li><a href="#editIndicator2" data-toggle="tab" aria-expanded="true"><strong>Indicator 2</strong></a></li>
			             	<li><a href="#editSubfieldaj" data-toggle="tab"><strong>Subfield (a-j)</strong></a></li>
			             	<li><a href="#editSubfieldkt" data-toggle="tab"><strong>Subfield (k-t)</strong></a></li>
			             	<li><a href="#editSubfielduz" data-toggle="tab"><strong>Subfield (u-z)</strong></a></li>
			             	<li><a href="#editSubfield09" data-toggle="tab"><strong>Subfield (0-9)</strong></a></li>
						</ul>
			
						<!-- TAB CONTENT -->
						<div class="tab-content">
		
						<!-- TAB 1 -->
						<div class="tab-pane active" id="editParameter">
							<div class="box-body">
								<form>
								  	<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Table:</label>
									    <div class="col-sm-4">
									      	<input type="text" id="editIndexTable" name="editIndexTable" style="width: 208px;" readonly>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editRepeatableCheck" id="editRepeatableCheck">
		           							Repeatable
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editAuthorityFlagCheck" id="editAuthorityFlagCheck">
		           							Authority Flag
		           						</div>
	           						</div>
								  	<div class="form-group row" id="forConly">
								    	<label for="borrowedByC" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Acronym</label>
										<div class="col-sm-4">
										    <input type="text" name="editAcronym" id="editAcronym" style="width: 208px;">
										</div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editMandatoryCheck" id="editMandatoryCheck">
		           							Mandatory
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editCopyPasteCheck" id="editCopyPasteCheck">
		           							Copy and Paste
		           						</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Authority Group</label>
									    <div class="col-sm-4">
									      	<input type="text" id="editAuthorityGroup" name="editAuthorityGroup" id="editAuthorityGroup" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editIndexFlagCheck" id="editIndexFlagCheck" >
		           							Index Flag
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editParamLinkCheck" id="editParamLinkCheck">
		           							PARAMIPS Link
		           						</div>
	           						</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Field Length</label>
									    <div class="col-sm-4">
									      	<input type="text" name="editFieldLength" id="editFieldLength" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editKeywordCheck" id="editKeywordCheck">
		           							Keyword
		           						</div>
									   	<div class="col-sm-3">           						 
										    <input type="checkbox" class="form-check-input" name="editMultimediaTagCheck" id="editMultimediaTagCheck">
		           							Multimedia Tag
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Language</label>
									    <div class="col-sm-4">
									      	<input type="text" name="editIndexLanguage" id="editIndexLanguage" style="width: 208px;">
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editDuplicateCheck" id="editDuplicateCheck">
		           							Duplicate Check
		           						</div>
		           						<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" name="editVisible" id="editVisible">
		           							Visible
		           						</div>
								  	</div>
								   	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Indicator</label>
									    <div class="col-sm-4">    	Indicator 1				
										      <select id="editInd1"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
											  Indicator 2 <select id="editInd2"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Value</label>
									    <div class="col-sm-6">
									      	<input type="text" name="editDefaultValue" id="editDefaultValue" style="width: 208px;">
									    </div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Remark</label>
									    <div class="col-sm-6">
									      	<input type="text" name="editRemark" id="editRemark" style="width: 208px;">
									    </div>
								  	</div>
								</form>
							</div>
						</div><!-- /.End Tab item -->
						<!-- TAB 2 -->	 
					   	<div class="tab-pane" id="editIndicator1">
					   		<div class="box-body">					   		
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="editInd1CBUndefine"> Undefined(#)
           							<input type="text" id="editInd1TFUndefine" name="editInd1TFUndefine" style="width: 650px;margin-bottom: 5px;" disabled>
           						</div>	
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB0" id="editInd1CB0"> 0
           							<input type="text" id="editInd1TF0" name="editInd1TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>			   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB1" id="editInd1CB1"> 1
           							<input type="text" id="editInd1TF1" name="editInd1TF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB2" id="editInd1CB2"> 2
           							<input type="text" id="editInd1TF2" name="editInd1TF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB3" id="editInd1CB3"> 3
           							<input type="text" id="editInd1TF3" name="editInd1TF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB4" id="editInd1CB4"> 4
           							<input type="text" id="editInd1TF4" name="editInd1TF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB5" id="editInd1CB5"> 5
           							<input type="text" id="editInd1TF5" name="editInd1TF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB6" id="editInd1CB6"> 6
           							<input type="text" id="editInd1TF6" name="editInd1TF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB7" id="editInd1CB7"> 7
           							<input type="text" id=editInd1TF7 name="editInd1TF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB8" id="editInd1CB8"> 8
           							<input type="text" id="editInd1TF8" name="editInd1TF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd1CB9" id="editInd1CB9"> 9
           							<input type="text" id="editInd1TF9" name="editInd1TF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>             					        						           						          						
 							</div>
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 3 -->	 
					   	<div class="tab-pane" id="editIndicator2">
					   		<div class="box-body">
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="editInd2CBUndefine"> Undefined(#)
           							<input type="text" id="editInd2TFUndefine" name="editInd2TFUndefine" style="width: 650px;margin-bottom: 5px;" disabled>
           						</div>	
								 <div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" name="editInd2CB0" id="editInd2CB0"> 0
           							<input type="text" id="editInd2TF0" name="editInd2TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB1" id="editInd2CB1"> 1
           							<input type="text" id="editInd2TF1" name="editInd2TF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB2" id="editInd2CB2"> 2
           							<input type="text" id="editInd2TF2" name="editInd2TF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB3" id="editInd2CB3"> 3
           							<input type="text" id="editInd2TF3" name="editInd2TF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB4" id="editInd2CB4"> 4
           							<input type="text" id="editInd2TF4" name="editInd2TF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB5" id="editInd2CB5"> 5
           							<input type="text" id="editInd2TF5" name="editInd2TF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB6" id="editInd2CB6"> 6
           							<input type="text" id="editInd2TF6" name="editInd2TF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB7" id="editInd2CB7"> 7
           							<input type="text" id="editInd2TF7" name="editInd2TF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB8" id="editInd2CB8"> 8
           							<input type="text" id="editInd2TF8" name="editInd2TF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editInd2CB9" id="editInd2CB9"> 9
           							<input type="text" id="editInd2TF9" name="editInd2TF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           											    
             				</div><!-- END box body requestStatus-->
						</div><!-- /.End Tab requestStatus -->
						
						<!-- TAB 4 -->	 
					   	<div class="tab-pane" id="editSubfieldaj">
					   		<div class="box-body">
								<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBa" id="editSFCBa"> a
           							<input type="text" id="editSFTFa" name="editSFTFa" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBb" id="editSFCBb"> b
           							<input type="text" id="editSFTFb" name="editSFTFb" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBc" id="editSFCBc"> c
           							<input type="text" id="editSFTFc" name="editSFTFc" style="width: 723px;margin-bottom: 5px; margin-left: 1px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBd" id="editSFCBd"> d
           							<input type="text" id="editSFTFd" name="editSFTFd" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBe" id="editSFCBe"> e
           							<input type="text" id="editSFTFe" name="editSFTFe" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBf" id="editSFCBf"> f
           							<input type="text" id="editSFTFf" name="editSFTFf" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBg" id="editSFCBg"> g
           							<input type="text" id="editSFTFg" name="editSFTFg" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBh" id="editSFCBh"> h
           							<input type="text" id="editSFTFh" name="editSFTFh" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBi" id="editSFCBi"> i
           							<input type="text" id="editSFTFi" name="editSFTFi" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div> 
        						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBj" id="editSFCBj"> j
           							<input type="text" id="editSFTFj" name="editSFTFj" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div>          						      										    
             				</div><!-- END box body personalInfo-->
						</div><!-- /.End Tab personalInfo -->
						
						<!-- TAB 5 -->	 
					   	<div class="tab-pane" id="editSubfieldkt">
					   		<div class="box-body">	
					   			<div class="row">  
           							<input type="checkbox" class="form-check-input" name="editSFCBk" id="editSFCBk"> k
           							<input type="text" id="editSFTFk" name="editSFTFk" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBl" id="editSFCBl"> l
           							<input type="text" id="editSFTFl" name="editSFTFl" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBm" id="editSFCBm"> m
           							<input type="text" id="editSFTFm" name="editSFTFm" style="width: 719px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBn" id="editSFCBn"> n
           							<input type="text" id="editSFTFn" name="editSFTFn" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBo" id="editSFCBo"> o
           							<input type="text" id="editSFTFo" name="editSFTFo" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBp" id="editSFCBp"> p
           							<input type="text" id="editSFTFp" name="editSFTFp" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBq" id="editSFCBq"> q
           							<input type="text" id="editSFTFq" name="editSFTFq" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBr" id="editSFCBr"> r
           							<input type="text" id="editSFTFr" name="editSFTFr" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBs" id="editSFCBs"> s
           							<input type="text" id="editSFTFs" name="editSFTFs" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>      
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBt" id="editSFCBt"> t
           							<input type="text" id="editSFTFt" name="editSFTFt" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>          	
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 6 -->	 
					   	<div class="tab-pane" id="editSubfielduz">
					   		<div class="box-body">	
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBu" id="editSFCBu"> u
           							<input type="text" id="editSFTFu" name="editSFTFu" style="width: 723px;margin-bottom: 5px; margin-left: 2px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBv" id="editSFCBv"> v
           							<input type="text" id="editSFTFv" name="editSFTFv" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBw" id="editSFCBw"> w
           							<input type="text" id="editSFTFw" name="editSFTFw" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBx" id="editSFCBx"> x
           							<input type="text" id="editSFTFx" name="editSFTFx" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBy" id="editSFCBy"> y
           							<input type="text" id="editSFTFy" name="editSFTFy" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCBz" id="editSFCBz"> z
           							<input type="text" id="editSFTFz" name="editSFTFz" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>           				
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 7 -->	 
					   	<div class="tab-pane" id="editSubfield09">
					   		<div class="box-body">	
		 						<div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" name="editSFCB0" id="editSFCB0"> 0
           							<input type="text" id="editSFTF0" name="editSFTF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB1" id="editSFCB1"> 1
           							<input type="text" id="editSFTF1" name="editSFTF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB2" id="editSFCB2"> 2
           							<input type="text" id="editSFTF2" name="editSFTF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB3" id="editSFCB3"> 3
           							<input type="text" id="editSFTF3" name="editSFTF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB4" id="editSFCB4"> 4
           							<input type="text" id="editSFTF4" name="editSFTF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB5" id="editSFCB5"> 5
           							<input type="text" id="editSFTF5" name="editSFTF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB6" id="editSFCB6"> 6
           							<input type="text" id="editSFTF6" name="editSFTF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB7" id="editSFCB7"> 7
           							<input type="text" id="editSFTF7" name="editSFTF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB8" id="editSFCB8"> 8
           							<input type="text" id="editSFTF8" name="editSFTF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" name="editSFCB9" id="editSFCB9"> 9
           							<input type="text" id="editSFTF9" name="editSFTF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>					
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->

						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
       			</div>   		
     		</div>
      	</div>
    </div>
				<div class="modal-footer justify-content-between">
					<button type="button" class="btn btn-primary ml-auto" id="editOkBtn" style="background-color: #1ab394">Save</button>
					<button type="button" class="btn btn-primary ml-auto" id="close" style="background-color: #1ab394" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- View Modal -->
	<div class="modal" id="viewModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="width: 85%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<label>View Tag Parameter</label>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>MARC:</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="viewMARC" name="viewMARC" style="width: 378px;" disabled >
					</div>
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;top: 5px;">
					<div class="col-md-2">
						<label>Tag:</label>
					</div>
					<div class="col-md-6">
						<input type="text" id="viewTag" name="viewTag" style="width: 378px;" disabled>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div id="description" style="margin-top: 10px;"><label>Description</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="viewDesc" name="viewDesc" style="width: 378px;" disabled>
					</div>	
				</div>		
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Abbreviated Desc</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="viewAbbreDesc" name="viewAbbreDesc" style="width: 378px;" disabled>
					</div>	
				</div>	

				
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">			
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#viewParameter" data-toggle="tab" aria-expanded="false"><strong>Parameters</strong></a></li>
							<li><a href="#viewIndicator1" data-toggle="tab"><strong>Indicator 1</strong></a></li>
							<li><a href="#viewIndicator2" data-toggle="tab" aria-expanded="true"><strong>Indicator 2</strong></a></li>
			             	<li><a href="#viewSubfieldaj" data-toggle="tab"><strong>Subfield (a-j)</strong></a></li>
			             	<li><a href="#viewSubfieldkt" data-toggle="tab"><strong>Subfield (k-t)</strong></a></li>
			             	<li><a href="#viewSubfielduz" data-toggle="tab"><strong>Subfield (u-z)</strong></a></li>
			             	<li><a href="#viewSubfield09" data-toggle="tab"><strong>Subfield (0-9)</strong></a></li>
						</ul>
			
						<!-- TAB CONTENT -->
						<div class="tab-content">
		
						<!-- TAB 1 -->
						<div class="tab-pane active" id="viewParameter">
							<div class="box-body">
								<form>
								  	<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Table:</label>
									    <div class="col-sm-4">
									      	<input type="text" id="viewIndexTable" name="viewIndexTable" style="width: 208px;" disabled >
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewRepeatableCheck" disabled>
		           							Repeatable
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewAuthorityFlagCheck" disabled>
		           							Authority Flag
		           						</div>
	           						</div>
								  	<div class="form-group row">
								    	<label for="borrowedByC" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Acronym</label>
										<div class="col-sm-4">
										    <input type="text" id="viewAcronym" name="viewAcronym" style="width: 208px;" disabled>
										</div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewMandatoryCheck" disabled>
		           							Mandatory
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewCopyPasteCheck" disabled>
		           							Copy and Paste
		           						</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Authority Group</label>
									    <div class="col-sm-4">
									      	<input type="text" id="viewAuthorityGroup" name="viewAuthorityGroup" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewIndexFlagCheck" disabled>
		           							Index Flag
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewParamLinkCheck" disabled>
		           							PARAMIPS Link
		           						</div>
	           						</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Field Length</label>
									    <div class="col-sm-4">
									      	<input type="text" id="viewFieldLength" name="viewFieldLength" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewKeywordCheck" disabled>
		           							Keyword
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewMultimediaTagCheck" disabled>
		           							Multimedia Tag
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Language</label>
									    <div class="col-sm-4">
									      	<input type="text" id="viewIndexLanguage" name="viewIndexLanguage" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="viewDuplicateCheck" disabled>
		           							Duplicate Check
		           						</div>
								  	</div>
								   	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Indicator</label>
									    <div class="col-sm-4">    	Indicator 1				
										      <select id="viewInd1"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
											  Indicator 2 <select id="viewInd2"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Value</label>
									    <div class="col-sm-6">
									      	<input type="text" id="viewDefaultValue" name="viewDefaultValue" style="width: 208px;" disabled> 
									    </div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Remark</label>
									    <div class="col-sm-6">
									      	<input type="text" id="viewRemark" name="viewRemark" style="width: 208px;" disabled>
									    </div>
								  	</div>
								</form>
							</div>
						</div><!-- /.End Tab item -->
						<!-- TAB 2 -->	 
					   	<div class="tab-pane" id="viewIndicator1">
					   		<div class="box-body">					   		
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CBUndefine" disabled> Undefined(#)
           							<input type="text" id="viewInd1TFUndefine" name="viewInd1TFUndefine" style="width: 650px;margin-bottom: 5px;"disabled>
           						</div>	
           						 <div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB0" disabled> 0
           							<input type="text" id="viewInd1TF0" name="viewInd1TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>			   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB1" disabled> 1
           							<input type="text" id="viewInd1TF1" name="viewInd1TF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB2" disabled> 2
           							<input type="text" id="viewInd1TF2" name="viewInd1TF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB3" disabled> 3
           							<input type="text" id="viewInd1TF3" name="viewInd1TF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB4" disabled> 4
           							<input type="text" id="viewInd1TF4" name="viewInd1TF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB5" disabled> 5
           							<input type="text" id="viewInd1TF5" name="viewInd1TF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB6" disabled> 6
           							<input type="text" id="viewInd1TF6" name="viewInd1TF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB7" disabled> 7
           							<input type="text" id="viewInd1TF7" name="viewInd1TF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB8" disabled> 8
           							<input type="text" id="viewInd1TF8" name="viewInd1TF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd1CB9" disabled> 9
           							<input type="text" id="viewInd1TF9" name="viewInd1TF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>             					        						           						          						
 							</div>
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 3 -->	 
					   	<div class="tab-pane" id="viewIndicator2">
					   		<div class="box-body">
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CBUndefine" disabled> Undefined(#)
           							<input type="text" id="viewInd2TFUndefine" name="viewInd2TFUndifine" style="width: 650px;margin-bottom: 5px;"disabled>
           						</div>	
								 <div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" id="viewInd2CB0" disabled> 0
           							<input type="text" id="viewInd2TF0" name="viewInd2TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB1" disabled> 1
           							<input type="text" id="viewInd2TF1" name="viewInd2TF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB2" disabled> 2
           							<input type="text" id="viewInd2TF2" name="viewInd2TF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB3" disabled> 3
           							<input type="text" id="viewInd2TF3" name="viewInd2TF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB4" disabled> 4
           							<input type="text" id="viewInd2TF4" name="viewInd2TF4" style="width: 723px;margin-bottom: 5px;" disabled >
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB5" disabled> 5
           							<input type="text" id="viewInd2TF5" name="viewInd2TF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB6" disabled> 6
           							<input type="text" id="viewInd2TF6" name="viewInd2TF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB7" disabled> 7
           							<input type="text" id="viewInd2TF7" name="viewInd2TF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB8" disabled> 8
           							<input type="text" id="viewInd2TF8" name="viewInd2TF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewInd2CB9" disabled> 9
           							<input type="text" id="viewInd2TF9" name="viewInd2TF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           											    
             				</div><!-- END box body requestStatus-->
						</div><!-- /.End Tab requestStatus -->
						
						<!-- TAB 4 -->	 
					   	<div class="tab-pane" id="viewSubfieldaj">
					   		<div class="box-body">
								<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBa" disabled> a
           							<input type="text" id="viewSFTFa" name="viewSFTFa" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBb" disabled> b
           							<input type="text" id="viewSFTFb" name="viewSFTFb" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBc" disabled> c
           							<input type="text" id="viewSFTFc" name="viewSFTFc" style="width: 723px;margin-bottom: 5px; margin-left: 1px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBd" disabled> d
           							<input type="text" id="viewSFTFd" name="viewSFTFd" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBe" disabled> e
           							<input type="text" id="viewSFTFe" name="viewSFTFe" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBf" disabled> f
           							<input type="text" id="viewSFTFf" name="viewSFTFf" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBg" disabled> g
           							<input type="text" id="viewSFTFg" name="viewSFTFg" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBh" disabled> h
           							<input type="text" id="viewSFTFh" name="viewSFTFh" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBi" disabled> i
           							<input type="text" id="viewSFTFi" name="viewSFTFi" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div> 
        						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBj" disabled> j
           							<input type="text" id="viewSFTFj" name="viewSFTFj" style="width: 723px;margin-bottom: 5px; margin-left: 4px;" disabled>
           						</div>          						      										    
             				</div><!-- END box body personalInfo-->
						</div><!-- /.End Tab personalInfo -->
						
						<!-- TAB 5 -->	 
					   	<div class="tab-pane" id="viewSubfieldkt">
					   		<div class="box-body">	
					   			<div class="row">  
           							<input type="checkbox" class="form-check-input" id="viewSFCBk" disabled> k
           							<input type="text" id="viewSFTFk" name="viewSFTFk" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBl" disabled> l
           							<input type="text" id="viewSFTFl" name="viewSFTFl" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBm" disabled> m
           							<input type="text" id=viewSFTFm name="viewSFTFm" style="width: 719px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBn" disabled> n
           							<input type="text" id="viewSFTFn" name="viewSFTFn" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBo" disabled> o
           							<input type="text" id="viewSFTFo" name="viewSFTFo" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBp" disabled> p
           							<input type="text" id="viewSFTFp" name="viewSFTFp" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBq" disabled> q
           							<input type="text" id="viewSFTFq" name="viewSFTFq" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBr" disabled> r
           							<input type="text" id="viewSFTFr" name="viewSFTFr" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBs" disabled> s
           							<input type="text" id="viewSFTFs" name="viewSFTFs" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>      
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBt" disabled> t
           							<input type="text" id="viewSFTFt" name="viewSFTFt" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>          	
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 6 -->	 
					   	<div class="tab-pane" id="viewSubfielduz">
					   		<div class="box-body">	
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBu" disabled> u
           							<input type="text" id="viewSFTFu" name="viewSFTFu" style="width: 723px;margin-bottom: 5px; margin-left: 2px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBv" disabled> v
           							<input type="text" id="viewSFTFv" name="viewSFTFv" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBw" disabled> w
           							<input type="text" id="viewSFTFw" name="viewSFTFw" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBx" disabled> x
           							<input type="text" id="viewSFTFx" name="viewSFTFx" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBy" disabled> y
           							<input type="text" id="viewSFTFy" name="viewSFTFy" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCBz" disabled> z
           							<input type="text" id="viewSFTFz" name="viewSFTFz" style="width: 723px;margin-bottom: 5px; margin-left: 3px;" disabled>
           						</div>           				
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 7 -->	 
					   	<div class="tab-pane" id="viewSubfield09">
					   		<div class="box-body">	
		 						<div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" id="viewSFCB0" disabled> 0
           							<input type="text" id="viewSFTF0" name="viewSFTF0" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB1" disabled> 1
           							<input type="text" id="viewSFTF1" name="viewSFTF1" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB2" disabled> 2
           							<input type="text" id="viewSFTF2" name="viewSFTF2" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB3" disabled> 3
           							<input type="text" id="viewSFTF3" name="viewSFTF3" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB4" disabled> 4
           							<input type="text" id="viewSFTF4" name="viewSFTF4" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB5" disabled> 5
           							<input type="text" id="viewSFTF5" name="viewSFTF5" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB6" disabled> 6
           							<input type="text" id="viewSFTF6" name="viewSFTF6" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB7" disabled> 7
           							<input type="text" id="viewSFTF7" name="viewSFTF7" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB8" disabled> 8
           							<input type="text" id="viewSFTF8" name="viewSFTF8" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="viewSFCB9" disabled> 9
           							<input type="text" id="viewSFTF9" name="viewSFTF9" style="width: 723px;margin-bottom: 5px;" disabled>
           						</div>					
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->

						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
       			</div>   		
     		</div>
      	</div>
    </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="close" style="background-color: #1ab394" data-dismiss="modal">Close</button>
					
<!-- 					<button type="button" class="btn btn-primary" id=buttonDelete>Cancel</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="getPatronIdBtn">Select</button> -->

				</div>
			</div>
		</div>
	</div>
	
	<!-- Delete Modal -->
	<div class="modal" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="width: 85%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<label>Delete Tag Parameter</label>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>MARC:</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="deleteMARC" name="deleteMARC" style="width: 378px;" disabled>
					</div>
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;top: 5px;">
					<div class="col-md-2">
						<label>Tag:</label>
					</div>
					<div class="col-md-6">
						<input type="text" id="deleteTag" name="deleteTag" style="width: 378px;" disabled>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div id="description" style="margin-top: 10px;"><label>Description</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="deleteDesc" name="deleteDesc" style="width: 378px;" disabled>
					</div>	
				</div>		
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>Abbreviated Desc</label></div>
					</div>
					<div class="col-md-6">
						<input type="text" id="deleteAbbreDesc" name="deleteAbbreDesc" style="width: 378px;" disabled>
					</div>	
				</div>	

				
    <div id="statusCombineDetail" class="panel-collapse collapse in">
      	<div class="panel-body" id="statusCombine">
      		<div class="row">
       			<div class="col-md-12 col-lg-12">			
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#deleteParameter" data-toggle="tab" aria-expanded="false"><strong>Parameters</strong></a></li>
							<li><a href="#deleteIndicator1" data-toggle="tab"><strong>Indicator 1</strong></a></li>
							<li><a href="#deleteIndicator2" data-toggle="tab" aria-expanded="true"><strong>Indicator 2</strong></a></li>
			             	<li><a href="#deleteSubfieldaj" data-toggle="tab"><strong>Subfield (a-j)</strong></a></li>
			             	<li><a href="#deleteSubfieldkt" data-toggle="tab"><strong>Subfield (k-t)</strong></a></li>
			             	<li><a href="#deleteSubfielduz" data-toggle="tab"><strong>Subfield (u-z)</strong></a></li>
			             	<li><a href="#deleteSubfield09" data-toggle="tab"><strong>Subfield (0-9)</strong></a></li>
						</ul>
			
						<!-- TAB CONTENT -->
						<div class="tab-content">
		
						<!-- TAB 1 -->
						<div class="tab-pane active" id="deleteParameter">
							<div class="box-body">
								<form>
								  	<div class="form-group row">
								    	<label for="Status" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Table:</label>
									    <div class="col-sm-4">
									      	<input type="text" id="deleteIndexTable" name="deleteIndexTable" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteRepeatableCheck" disabled>
		           							Repeatable
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteAuthorityFlagCheck" disabled>
		           							Authority Flag
		           						</div>
	           						</div>
								  	<div class="form-group row">
								    	<label for="borrowedByC" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Acronym</label>
										<div class="col-sm-4">
										    <input type="text" id="deleteAcronym" name="deleteAcronym" disabled>
										</div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteMandatoryCheck" style="width: 208px;" disabled>
		           							Mandatory
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteCopyPasteCheck" disabled>
		           							Copy and Paste
		           						</div>
								  	</div>
								  	<div class="form-group row">
								    	<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Authority Group</label>
									    <div class="col-sm-4">
									      	<input type="text" id="deleteAuthorityGroup" name="deleteAuthorityGroup" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteIndexFlagCheck" disabled>
		           							Index Flag
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteParamLinkCheck" disabled>
		           							PARAMIPS Link
		           						</div>
	           						</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Field Length</label>
									    <div class="col-sm-4">
									      	<input type="text" id="deleteFieldLength" name="deleteFieldLength" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteKeywordCheck" disabled>
		           							Keyword
		           						</div>
									   	<div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteMultimediaTagCheck" disabled>
		           							Multimedia Tag
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Index Language</label>
									    <div class="col-sm-4">
									      	<input type="text" id="deleteIndexLanguage" name="deleteIndexLanguage" style="width: 208px;" disabled>
									    </div>
									    <div class="col-sm-3">           							
										    <input type="checkbox" class="form-check-input" id="deleteDuplicateCheck" disabled>
		           							Duplicate Check
		           						</div>
								  	</div>
								   	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Indicator</label>
									    <div class="col-sm-4">    	Indicator 1				
										      <select id="deleteInd1"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
											  Indicator 2 <select id="deleteInd2"> 
										        <option value="0">0</option>
											    <option value="1">1</option>
											    <option value="2">2</option>
											    <option value="3">3</option>
											    <option value="4">4</option>
											    <option value="1">5</option>
											    <option value="2">6</option>
											    <option value="3">7</option>
											    <option value="4">8</option>
											    <option value="4">9</option>
											  </select>
		           						</div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Default Value</label>
									    <div class="col-sm-6">
									      	<input type="text" id="deleteDefaultValue" name="deleteDefaultValue" style="width: 208px;" disabled> 
									    </div>
								  	</div>
								  	<div class="form-group row">
										<label for="ItemCategory" class="col-sm-2 col-form-label" style="padding-left: 0px;padding-right: 0px;">Remark</label>
									    <div class="col-sm-6">
									      	<input type="text" id="deleteRemark" name="deleteRemark" style="width: 208px;" disabled>
									    </div>
								  	</div>
								</form>
							</div>
						</div><!-- /.End Tab item -->
						<!-- TAB 2 -->	 
					   	<div class="tab-pane" id="deleteIndicator1">
					   		<div class="box-body">					   		
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> Undefined(#)
           							<input type="text" id="tag" name="tag" style="width: 650px;margin-bottom: 5px;">
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 1
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 2
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 3
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 4
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 5
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 6
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 7
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 8
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 9
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>             					        						           						          						
 							</div>
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 3 -->	 
					   	<div class="tab-pane" id="deleteIndicator2">
					   		<div class="box-body">
								 <div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 0
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 1
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 2
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 3
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 4
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 5
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 6
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 7
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 8
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 9
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           											    
             				</div><!-- END box body requestStatus-->
						</div><!-- /.End Tab requestStatus -->
						
						<!-- TAB 4 -->	 
					   	<div class="tab-pane" id="deleteSubfieldaj">
					   		<div class="box-body">
								<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> a
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> b
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> c
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 1px;">
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> d
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> e
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> f
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 4px;">
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> g
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> h
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> i
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 4px;">
           						</div> 
        						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> j
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 4px;">
           						</div>          						      										    
             				</div><!-- END box body personalInfo-->
						</div><!-- /.End Tab personalInfo -->
						
						<!-- TAB 5 -->	 
					   	<div class="tab-pane" id="deleteSubfieldkt">
					   		<div class="box-body">	
					   			<div class="row">  
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> k
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> l
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> m
           							<input type="text" id="tag" name="tag" style="width: 933px;margin-bottom: 5px;">
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> n
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> o
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> p
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> q
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> r
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> s
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>      
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> t
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>          	
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 6 -->	 
					   	<div class="tab-pane" id="deleteSubfielduz">
					   		<div class="box-body">	
					   			<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> u
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 2px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> v
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> w
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> x
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> y
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> z
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px; margin-left: 3px;">
           						</div>           				
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->
						
						<!-- TAB 7 -->	 
					   	<div class="tab-pane" id="deleteSubfield09">
					   		<div class="box-body">	
		 						<div class="row">								 					   			
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 0
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>				   		
								 <div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 1
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 2
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 3
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div> 
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 4
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 5
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 6
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>    
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 7
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 8
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>
           						<div class="row">
           							<input type="checkbox" class="form-check-input" id="exampleCheck1"> 9
           							<input type="text" id="tag" name="tag" style="width: 723px;margin-bottom: 5px;">
           						</div>					
             				</div><!-- END box body relatedPatron-->
						</div><!-- /.End Tab relatedPatron -->

						</div><!-- /.END TAB CONTENT -->
		
					</div><!-- /.END CUSTOM TAB -->
       			</div>   		
     		</div>
      	</div>
    </div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="deleteOkBtn" style="background-color: #1ab394">Delete</button>
					<button type="button" class="btn btn-primary" id="close" style="background-color: #1ab394" data-dismiss="modal">Close</button>
					
<!-- 					<button type="button" class="btn btn-primary" id=buttonDelete>Cancel</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="getPatronIdBtn">Select</button> -->

				</div>
			</div>
		</div>
	</div>
	    
	<!-- Seach Modal -->
	<div class="modal" id="searchModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document" style="width: 85%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">
						<label>Search Tag Parameter</label>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="col-md-12">
					<div class="col-md-2">
						<div style="margin-top: 10px;"><label>MARC:</label></div>
					</div>
					<div class="col-md-2">
						<div class="input-group select1">
							<select class="labelBlack form-control marcId" id="marcId1" data-id="1"
								style="border-style: solid; border-width: thin; padding-top: 2px;padding-bottom: 0px;top: 2px;bottom: 2px;height: 30px;left: 0px;width: 86px;border-bottom-width: 0px;border-top-width: 0px;padding-left: -;margin-left: -;"></select>
						</div>
					</div>
					<div class="col-md-6">
						<div class="input-group select1">		
							<select class="labelBlack form-control marcDesc" id="marcDesc1" data-id="1" 
								style="border-style: solid; border-width: thin; padding-top: 2px;padding-bottom: -;top: 2px;bottom: 2px;height: 30px;left: -;width: 250px;padding-left: -;border-left-width: -;border-bottom-width: -;border-top-width: -;border-bottom-width: -;padding-bottom: -;padding-bottom: -;padding-bottom: 0px;padding-left: 0px;border-left-width: 0px;"></select>
						</div>
					</div>
				</div>	
				<div class="col-md-12" style="margin-bottom: 10px;top: 5px;margin-top: 10px;">
					<div class="col-md-2">
						<div class="form-group ">
							<div class="radio">
								<label><input type="radio" name="retrieveRadios"
									id="selectedRadio" value="tagId" checked="checked"><Strong>Tag</Strong></label>
							</div>
							<div class="radio">
								<label><input type="radio" name="retrieveRadios"
									id="selectedRadio" value="tagDesc"><Strong>Description</Strong></label>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<textarea id="userInput" name="userInput" rows="4" cols="50"></textarea>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary ml-auto" id="findBtn" style="background-color: #1ab394">Find</button>
					<button type="button" class="btn btn-primary" id="close" style="background-color: #1ab394" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>