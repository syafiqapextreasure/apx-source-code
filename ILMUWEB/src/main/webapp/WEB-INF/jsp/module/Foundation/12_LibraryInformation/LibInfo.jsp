<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@page import="com.ilmu.foundation.global.GlobalSQLStatement"%>
<%@page import="com.ilmu.foundation.global.Foundation"%>


<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Foundation/libInfo.js"></script>

  </head>
  <body class="hold-transition skin-blue sidebar-mini">
  <%
	
  GlobalSQLStatement eb = new GlobalSQLStatement();
	Foundation e = eb.getLibInfo();
%>

	<section class="content-header">
          <h1>
            <i class="fa fa-info"></i>  Library Information
          </h1>
          
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">
                 </h3>
                <div class="box-body">
                
                <!-- <form class="form-horizontal" action="PatDetails" method="post"> -->
                <form role="form" id="libInfoForm" class="form-horizontal" action="${pageContext.request.contextPath}/saveLibInfo"  method="post">
		
		<!-- --------------------- -->
          
          <!-- START CUSTOM TABS -->
          <div class="row">
            <div class="col-md-12">
              <!-- Custom Tabs -->
              <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#tab_1" data-toggle="tab"><strong>Library Information</strong></a></li>
                  <li><a href="#tab_2" data-toggle="tab"><strong>Acquisition</strong></a></li>
                  <li><a href="#tab_3" data-toggle="tab"><strong>Cataloging</strong></a></li>
                  <li><a href="#tab_4" data-toggle="tab"><strong>Circulation</strong></a></li>
                  <li><a href="#tab_5" data-toggle="tab"><strong>Infotrack</strong></a></li>
                  <li><a href="#tab_6" data-toggle="tab"><strong>Serial</strong></a></li>
                  <li><a href="#tab_7" data-toggle="tab"><strong>IRS</strong></a></li>
                  <li><a href="#tab_8" data-toggle="tab"><strong>Fund Accounting</strong></a></li>
                  
                  <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
                </ul>
                
                <div class="tab-content">
                  <div class="tab-pane active" id="tab_1">
                  
                  	<div class="box-body">
                  	<div class="row">
                    <div class="form-group">
                      <label class="col-sm-2">Library Name</label>
                      <div class="col-sm-3 col-md-7">
                        <input type="text" class="form-control" id="GL28NAME" name="GL28NAME"  value="<%=e.getGL28NAME()%>" readonly>
                     </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2">Organization Name</label>
                      <div class="col-sm-3 col-md-7">
                        <input type="text" class="form-control" id="GL28ORGNAME" name="GL28ORGNAME" value="<%=e.getGL28ORGNAME()%>" readonly>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2"><strong>Library Symbol</strong></label>
                      <div class="col-sm-3 col-md-2">
                        <input type="text" class="form-control" id="GL28LIBSYMBOL" name="GL28LIBSYMBOL" value="<%=e.getGL28LIBSYMBOL()%>">
                      </div>
                    </div>
             
             <!-- Registered Branch -->
                  <div class="form-group">
                  <label class="col-sm-2">Branch Code</label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="GL28BRNC" name="GL28BRNC">
                       <%	if((e.getGL28BRNC())== null){%> 
                     <option value=""> </option>
                      <%
                      	GlobalSQLStatement branch = new GlobalSQLStatement();
						List<Foundation> branchcodelist = branch.getBranch();
						for (Foundation i : branchcodelist) {
					  %>
					  <option value="<%=i.getGL71BRNC()%>"><%=i.getGL71BRNC()%></option>
					  <%
						}
					  %>
                     <%}else{%>
                     <option value="<%=e.getGL28BRNC()%>"><%=e.getGL28BRNC()%></option>
                       <%
                      		GlobalSQLStatement branch = new GlobalSQLStatement();
							List<Foundation> branchcodelist = branch.getBranchNotIn(e.getGL28BRNC());
							for (Foundation i : branchcodelist) {
					   %>
					  <option value="<%=i.getGL71BRNC()%>"><%=i.getGL71BRNC()%></option>
					  <%
						}
					  %> 
					  <%
						}
					  %>
                      <%-- <%
							SQLStatement branch = new SQLStatement();
							List<Foundation> branchcodelist = branch.getBranch();
							for (Foundation a : branchcodelist) {
					  %>
					  <option value="<%=a.getGL71BRNC()%>"><%=a.getGL71BRNC()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-5">
                  <select class="form-control">
					
					 <%
                      GlobalSQLStatement branchDesc2 = new GlobalSQLStatement();
							List<Foundation> branchlist2 = branchDesc2.getBranchVal(e.getGL14BRNC());
							for (Foundation i : branchlist2) {
					  %>
					  <option value="<%=i.getGL71DESC()%>"><%=i.getGL71DESC()%></option>
					  <%
						}
					  %> 
					  
                     <%
                      GlobalSQLStatement branchDesc = new GlobalSQLStatement();
							List<Foundation> branchlist = branchDesc.getBranchNotIn(e.getGL14BRNC());
							for (Foundation i : branchlist) {
					  %>
					  <option value="<%=i.getGL71DESC()%>"><%=i.getGL71DESC()%></option>
					  <%
						}
					  %> 
                      <%-- <%
							SQLStatement branchDesc = new SQLStatement();
							List<Foundation> branchlist = branchDesc.getBranch();
							for (Foundation a : branchlist) {
					  %>
                      <option value="<%=a.getGL71BRNC()%>"><%=a.getGL71DESC()%></option>
					  <option value="<%=a.getGL71BRNC()%>"><%=a.getGL71DESC()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div>              
             </div>
             <!-- End Register Branch -->
             
             <div class="form-group">
                 <label class="col-sm-2">Address</label>
                 <div class="col-sm-3 col-md-7">
                    <input type="text" class="form-control" id="GL28ADD1" name="GL28ADD1" value="<%=e.getGL28ADD1()%>">
             	 </div>
             </div>
             <div class="form-group">
                 <label class="col-sm-2"></label>
                 <div class="col-sm-3 col-md-7">
                    <input type="text" class="form-control" id="GL28ADD2" name="GL28ADD2" value="<%=e.getGL28ADD2()%>">
             	 </div>
             </div>
             <div class="form-group">
                 <label class="col-sm-2"></label>
                 <div class="col-sm-3 col-md-7">
                    <input type="text" class="form-control" id="GL28ADD3" name="GL28ADD3" value="<%=e.getGL28ADD3()%>">
             	 </div>
             </div>
             
             <div class="form-group">
                 <label class="col-sm-2">Postcode</label>
                 <div class="col-sm-3 col-md-2">
                    <input type="text" class="form-control" id="GL28POSCODE" name="GL28POSCODE" value="<%=e.getGL28POSCODE()%>">
             	 </div>
             
             
             <!-- State/Town -->
                  <div class="form-group">
                  <label class="col-sm-1 " id="lblPatron(39)">Town</label>
                  <div class="col-sm-1 col-md-2">
                  <select class="form-control" id="GL28TOWN" name="GL28TOWN" onchange="document.getElementById('townDesc').selectedIndex
                            						= document.getElementById('GL28TOWN').selectedIndex">
                      <%	if((e.getGL28TOWN())== null || (e.getGL28TOWN()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTown();
							for (Foundation u : codelist) {
					  %>
					  <option value="<%=u.getCODE()%>"><%=u.getCODE()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14TOWN()%>"><%=e.getGL14TOWN()%></option>
                      <%
                      GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTownNotIn(e.getGL28TOWN());
							for (Foundation u : codelist) {
					  %>
					  <option value="<%=u.getCODE()%>"><%=u.getCODE()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
                      <%-- <option value="0">Select</option>
                      <%
                      GlobalSQLStatement code = new GlobalSQLStatement();
							List<Foundation> codelist = code.getTown();
							for (Foundation a : codelist) {
					  %>
					  <option value="<%=a.getGL15TOWN()%>"><%=a.getGL15TOWN()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="townDesc" name="townDesc" onchange="document.getElementById('GL28TOWN').selectedIndex
                            						= document.getElementById('townDesc').selectedIndex">
                            						
                     <%	if((e.getGL28TOWN())== null || (e.getGL28TOWN()).isEmpty()){%>
                      <option value=""> </option>
                      <%
                      		GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTown();
							for (Foundation r : townlist) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL14TOWN()%>"><%=e.getDESCRIPTIONTOWNR1()%></option>
                      <%
                      		GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTownNotIn(e.getGL28TOWN());
							for (Foundation r : townlist) {
					  %>
					  <option value="<%=r.getCODE()%>"><%=r.getDESCRIPTION()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
                     <%--  <option value="0"></option>
                      <%
                      GlobalSQLStatement town = new GlobalSQLStatement();
							List<Foundation> townlist = town.getTown();
							for (Foundation a : townlist) {
					  %>
					  <option value="<%=a.getGL15DESC()%>"><%=a.getGL15DESC()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div> 
                     
             </div>
             <!-- End State/Town -->
             </div>
             
             <div class="form-group">
                 <label class="col-sm-2">Telephone</label>
                 <div class="col-sm-3 col-md-2">
                    <input type="text" class="form-control" id="GL28TEL" name="GL28TEL" value="<%=e.getGL28TEL()%>">
             	 </div>
             	 
             	 <label class="col-sm-1">Fax</label>
                 <div class="col-sm-3 col-md-2">
                    <input type="text" class="form-control" id="GL28FAX" name="GL28FAX" value="<%=e.getGL28FAX()%>">
             	 </div>
             </div>
             <div class="form-group">
                  <label class="col-sm-2">Head Librarian</label>
                  <div class="col-sm-4 col-md-2">
                       <input type="text" class="form-control" id="patronIdheadlib" name="GL28HEADLIB" onblur="updateVendorName()" onkeyup="updateVendorNameWithClear()" value="<%=e.getGL28LIBHEAD()%>">
                  </div>
                  <div class="col-md-1">
                  <button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=headlib">...</button>
                  </div>
                  <div class="col-md-3">
                  		 <%
                  		System.out.println(e.getGL28LIBHEAD() +" e.getGL28LIBHEAD");
          	 	   		String patronNameLIBHEAD;
          	 	 		if(e.getGL28LIBHEAD().trim().isEmpty()){
          	 	 			patronNameLIBHEAD = "";
          	 	 		}else{
	                  		GlobalSQLStatement LIBHEAD = new GlobalSQLStatement();
               				Foundation libhead = LIBHEAD.getPatron(e.getGL28LIBHEAD());
               				System.out.println(libhead.getGL14NAME() +" phead.getGL14NAME()");
               				patronNameLIBHEAD = libhead.getGL14NAME();
          	 	 		}
          	 	 		System.out.println(patronNameLIBHEAD +" patronNameLIBHEAD");
					  	 %>
                       <input type="text" class="form-control" id="patronNameheadlib" name="patronName" value="<%=patronNameLIBHEAD%>"  readonly>

                  </div>
                  <label class="col-sm-1 control-label">Tel. Ext</label>
                 <div class="col-sm-1 col-md-1">
                    <input type="text" class="form-control" id="GL28LIBHEADEXT" name="GL28LIBHEADEXT" value="<%=e.getGL28LIBHEADEXT()%>">
             	 </div>
             </div> 
            
             <div class="form-group">
                 <label class="col-sm-2">PR Officer</label>
                 <div class="col-sm-4 col-md-2">
                    <input type="text" class="form-control" id="patronIdPROHEAD" name="GL28PROHEAD" value="<%=e.getGL28PROHEAD()%>">
             	 </div>
             	 <div class="col-sm-1">
             	 <button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=PROHEAD">...</button>
             	 </div>
             	 <div class="col-md-3">
             	 	   
             	 	   <%
             	 	 		System.out.println(e.getGL28PROHEAD() +" e.getGL28PROHEAD");
             	 	   		String patronNamePROHEAD;
             	 	 		if(e.getGL28PROHEAD().trim().isEmpty()){
             	 	 			patronNamePROHEAD = "";
             	 	 		}else{
	                  		GlobalSQLStatement PROHEAD = new GlobalSQLStatement();
                  			Foundation phead = PROHEAD.getPatron(e.getGL28PROHEAD());
                  			System.out.println(phead.getGL14NAME() +" phead.getGL14NAME()");
                  			patronNamePROHEAD = phead.getGL14NAME();
             	 	 		}
             	 	 		System.out.println(patronNamePROHEAD +" patronNamePROHEAD");
					   %>
					   
                       <input type="text" class="form-control" id="patronNamePROHEAD" name="patronName" value="<%=patronNamePROHEAD%>" readonly>
                  </div>
             	 <label class="col-sm-1 control-label">Tel. Ext</label>
                 <div class="col-sm-1 col-md-1">
                    <input type="text" class="form-control" id="GL28PROEXT" name="GL28PROEXT" value="<%=e.getGL28PROEXT()%>">
             	 </div>
             	 
             </div>
             <div class="form-group">
                 <label class="col-sm-2">Library Logo</label>
                 <div class="col-sm-3 col-md-2">
                    <input type="text" class="form-control" id="GL28LOGO" name="GL28LOGO" value="<%=e.getGL28LOGO()%>">
             	 </div>
             </div>
             
              </div><!-- /.row -->
              </div><!-- /.box -->
              
         </div><!-- /.tab-pane -->
                  
         <div class="tab-pane" id="tab_2">
                  <div class="box-body">
                  
             		<div class="form-group">
             			<label class="col-sm-2">Acquisition Head</label>
		                 <div class="col-sm-4 col-md-2">
		                    <input type="text" class="form-control" id="patronIdACQHEAD" name="GL28ACQHEAD" value="<%=e.getGL28ACQHEAD()%>">
		             	 </div>
		             	 <div class="col-sm-1">
		             	 <button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=ACQHEAD">...</button>
		             	 </div>
		             	 <div class="col-md-3">
		             	 	   <%
		             	 	 	System.out.println(e.getGL28ACQHEAD() +" e.getGL28ACQHEAD");
	             	 	   		String patronNameACQHEAD;
	             	 	 		if(e.getGL28ACQHEAD().trim().isEmpty()){
	             	 	 			patronNameACQHEAD = "";
	             	 	 		}else{
		                  		GlobalSQLStatement PROHEAD = new GlobalSQLStatement();
	                  			Foundation prohead = PROHEAD.getPatron(e.getGL28ACQHEAD());
	                  			System.out.println(prohead.getGL14NAME() +" phead.getGL14NAME()");
	                  			patronNameACQHEAD = prohead.getGL14NAME();
	             	 	 		}
	             	 	 		System.out.println(patronNameACQHEAD +" patronNameACQHEAD");
							   %>
		                       <input type="text" class="form-control" id="patronNameACQHEAD" name="patronName" value="<%=patronNameACQHEAD%>" readonly>
		                  </div>
		             	 <label class="col-sm-1 control-label">Tel. Ext</label>
		                 <div class="col-sm-1 col-md-1">
		                    <input type="text" class="form-control" id="GL28ACQEXT" name="GL28ACQEXT" value="<%=e.getGL28ACQEXT()%>">
		             	 </div>
             		</div>
                    
                    <div class="form-group">
                      <label class="col-sm-2">1st Grace Period</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL28ACQCLAIMS1" name="GL28ACQCLAIMS1" value="<%=e.getGL28ACQCLAIMS1()%>">
                      </div>
                      
                      <label class="col-sm-3 control-label">2nd Grace Period</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL28ACQCLAIM2" name="GL28ACQCLAIM2" value="<%=e.getGL28ACQCLAIM2()%>">
                      </div>
                     </div> 
                     <div class="form-group">
                      <label class="col-sm-2">3rd Grace Period</label>
                      <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL28ACQCLAIM3" name="GL28ACQCLAIM3" value="<%=e.getGL28ACQCLAIM3()%>">
                      </div>
                      
                      <label class="col-sm-3 control-label">Order Span</label>
                      <div class="input-group col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL28ORDERSPAN" name="GL28ORDERSPAN" value="<%=e.getGL28ORDERSPAN()%>">
                      	<span class="input-group-addon">Day</span>
                      </div>
                      <!-- <label>day</label> -->
                      
                      <%-- <div class="col-sm-2 col-md-2 input-group">
					    
					     <input type="text" class="form-control" id="GL28ORDERSPAN" name="GL28ORDERSPAN" value="<%=e.getGL28ORDERSPAN()%>">
					    <span class="input-group-addon">Day</span>
					  </div> --%>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-2">Batch Order Printing</label>
                      <div class="col-sm-1 col-md-10">
                        <!-- <input type="checkbox" class="minimal" name="GLCHKMAIL" id="chkMailFlag(0)" value="" >GL28ACQBPRINT -->
                        <input type="checkbox" class="minimal" name="GL28ACQBPRINT" id="GL28ACQBPRINT" <%if (e.getGL28ACQBPRINT().equals("Y")){ %> value="Y" checked <%}%> >
                        <%-- <%if (e.getGL28ACQBPRINT().equals("Y")){  %>
	      						<label class="col-sm-2 ">
	      						<input type="checkbox" class="minimal" name=GL28ACQBPRINT id="GL28ACQBPRINT" value="Y" checked >
	      						</label>
	      				  <%}else{ %>
	      				  		<label class="col-sm-2 ">
	      						<input type="checkbox" class="minimal" name=GL28ACQBPRINT id="GL28ACQBPRINT">
	      						</label>
	      				  <%} %> --%>
                      </div>
                    </div>
                    
                  </div>  

       </div><!-- /.tab-pane -->
                  
                  
       <div class="tab-pane" id="tab_3">
       <div class="box-body">
       
       				<div class="form-group">
       					<label class="col-sm-2">Catalog Head</label>
		                 <div class="col-sm-4 col-md-2">
		                    <input type="text" class="form-control" id="patronIdCATHEAD" name="GL28CATHEAD" value="<%=e.getGL28CATHEAD()%>">
		             	 </div>
		             	 <div class="col-sm-1">
		             	 <button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=CATHEAD">...</button>
		             	 </div>
		             	 <div class="col-md-3">
		             	 	   <%
			             	 	 	System.out.println(e.getGL28CATHEAD() +" getGL28CATHEAD");
		             	 	   		String patronNameCATHEAD;
		             	 	 		if(e.getGL28CATHEAD().trim().isEmpty()){
		             	 	 			patronNameCATHEAD = "";
		             	 	 		}else{
				                  		GlobalSQLStatement CATHEAD = new GlobalSQLStatement();
			                  			Foundation cathead = CATHEAD.getPatron(e.getGL28CATHEAD());
			                  			System.out.println(cathead.getGL14NAME() +" phead.getGL14NAME()");
			                  			patronNameCATHEAD = cathead.getGL14NAME();
		             	 	 		}
		             	 	 		System.out.println(patronNameCATHEAD +" patronNameCATHEAD");
							   %>
		                       <input type="text" class="form-control" id="patronNameCATHEAD" name="patronName" value="<%=patronNameCATHEAD%>" readonly>
		                  </div>
		             	 <label class="col-sm-1 control-label">Tel. Ext</label>
		                 <div class="col-sm-1 col-md-1">
		                    <input type="text" class="form-control" id="GL28CATEXT" name="GL28CATEXT" value="<%=e.getGL28CATEXT()%>">
		             	 </div>
             		</div>
       				
             <!-- Base Marc Type -->
                  <div class="form-group">
                  <label class="col-sm-2">Base MARC Type</label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="GL28MARCTYPE" name="GL28MARCTYPE" onchange="document.getElementById('marc').selectedIndex
                            						= document.getElementById('GL28MARCTYPE').selectedIndex">
                      <%if((e.getGL28MARCTYPE())== null || (e.getGL28MARCTYPE()).trim().isEmpty()){%>
                      <option value=""> </option>
                      <%
                      	GlobalSQLStatement MARC = new GlobalSQLStatement();
						List<Foundation> deptcodelist = MARC.getMARC();
						for (Foundation a : deptcodelist) {
					  %>
					  <option value="<%=a.getGL16MARC()%>"><%=a.getGL16MARC()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL28MARCTYPE()%>"><%=e.getGL28MARCTYPE()%></option>
                      <%
                      	GlobalSQLStatement MARC = new GlobalSQLStatement();
						List<Foundation> deptcodelist = MARC.getMARCNOTIN(e.getGL28MARCTYPE());
						for (Foundation a : deptcodelist) {
					  %>
					  <option value="<%=a.getGL16MARC()%>"><%=a.getGL16MARC()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
                      
                      <%-- <option value="0"></option>
                      <%
                      GlobalSQLStatement MARC = new GlobalSQLStatement();
							List<Foundation> deptcodelist = MARC.getMARC();
							for (Foundation a : deptcodelist) {
					  %>
					  <option value="<%=a.getGL16MARC()%>"><%=a.getGL16MARC()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="marc" name="marc" onchange="document.getElementById('GL28MARCTYPE').selectedIndex
                            						= document.getElementById('marc').selectedIndex">
                      
                       <%
                      		/* GlobalSQLStatement marcDesc = new GlobalSQLStatement();
							List<Foundation> marclist = marcDesc.getMARCDESC(e.getGL14BRNC());
							for (Foundation i : marclist) { */
							GlobalSQLStatement marc = new GlobalSQLStatement();
	                  		Foundation marcDesc = marc.getMARCDESC(e.getGL28MARCTYPE());
	                  		System.out.println(marcDesc.getGL16DESC() +" marc.GL16DESC()");
					  %>
					  <option value="<%=marcDesc.getGL16DESC()%>"><%=marcDesc.getGL16DESC()%></option>
					 <%--  <%
						}
					  %>  --%>
					  
                     <%
                 	GlobalSQLStatement MARC = new GlobalSQLStatement();
						List<Foundation> deptcodelist = MARC.getMARCNOTIN(e.getGL28MARCTYPE());
						for (Foundation a : deptcodelist) {
					  %>
					  <option value="<%=a.getGL16DESC()%>"><%=a.getGL16DESC()%></option>
					  <%
						}
					  %> 
                      
				</select>
                </div>       
             </div>
             <!-- End Base Marc Type -->
             
           <div class="form-group">
               <label class="col-sm-2">Indexing Threshold Time</label>
               <div class="col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL28INXTRASH" name="GL28INXTRASH" value="<%=e.getGL28INXTRASH()%>">
               </div>
            </div> 
       
       </div><!-- /.tab-pane -->
       </div><!-- /.tab-content -->
       
       <div class="tab-pane" id="tab_4">
       <div class="box-body">
       
       		<div class="form-group">
                  <label class="col-sm-2 ">Circ. Head</label>
                  <div class="col-sm-5 col-md-2">
                       <input type="text" class="form-control" id="patronIdCIRHEAD" name="GL28CIRHEAD" onblur="updateVendorName()" onkeyup="updateVendorNameWithClear()" value="<%=e.getGL28CIRHEAD()%>">
                  </div>
                  <div class="col-md-1">
                  <button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=CIRHEAD">...</button>
                  </div>
                  <div class="col-md-5">
                  	
                  	  			<%
			             	 	 	System.out.println(e.getGL28CIRHEAD() +"getGL28CIRHEAD");
		             	 	   		String patronNameCIRHEAD;
		             	 	 		if(e.getGL28CIRHEAD().trim().isEmpty()){
		             	 	 			patronNameCIRHEAD = "";
		             	 	 		}else{
				                  		GlobalSQLStatement CIRHEAD = new GlobalSQLStatement();
			                  			Foundation cirhead = CIRHEAD.getPatron(e.getGL28CIRHEAD());
			                  			System.out.println(cirhead.getGL14NAME() +" cirhead.getGL14NAME()");
			                  			patronNameCIRHEAD = cirhead.getGL14NAME();
		             	 	 		}
		             	 	 		System.out.println(patronNameCIRHEAD +" patronNameCIRHEAD");
							   %>
                       <input type="text" class="form-control" id="patronNameCATHEAD" name="patronNameCATHEAD" value="<%=patronNameCIRHEAD%>" readonly>
                  </div>
             </div> 
       
       		<div class="form-group">
              	<label class="col-sm-2">Tel. Ext</label>
                <div class="col-sm-1 col-md-2">
                    <input type="text" class="form-control" id="GL28CIREXT" name="GL28CIREXT" value="<%=e.getGL28CIREXT()%>">
             	</div>
       		</div>
       				
             <!-- Library weekend -->
              <div class="form-group ">
                  <label class="col-sm-2">Library Weekends</label>
                  <div class="col-sm-2 col-md-2">
                  <select class="form-control" id="GL28LIBWEEKEND" name="GL28LIBWEEKEND" onchange="document.getElementById('week').selectedIndex
                            						= document.getElementById('GL28LIBWEEKEND').selectedIndex">
                      	<option value="" <%=(e.getGL28LIBWEEKEND())== null || (e.getGL28LIBWEEKEND()).trim().isEmpty()? "selected":""%>> </option>
                      	<option value="Sunday" <%=e.getGL28LIBWEEKEND().equals("Sunday")? "selected":""%>>Sunday</option>
  						<option value="Monday" <%=e.getGL28LIBWEEKEND().equals("Monday")? "selected":""%>>Monday</option>
  						<option value="Tuesday" <%=e.getGL28LIBWEEKEND().equals("Tuesday")? "selected":""%>>Tuesday</option>
  						<option value="Wednesday" <%=e.getGL28LIBWEEKEND().equals("Wednesday")? "selected":""%>>Wednesday</option>
  						<option value="Thursday" <%=e.getGL28LIBWEEKEND().equals("Thursday")? "selected":""%>>Thursday</option>
  						<option value="Friday" <%=e.getGL28LIBWEEKEND().equals("Friday")? "selected":""%>>Friday</option>
  						<option value="Saturday" <%=e.getGL28LIBWEEKEND().equals("Saturday")? "selected":""%>>Saturday</option>

				</select>
                </div>
                
               <label class="col-sm-4 control-label">Reservation Time Frame</label>
                      <div class="input-group col-sm-2 col-md-2">
                        <input type="text" class="form-control" id="GL28RESVTIME" name="GL28RESVTIME" value="<%=e.getGL28RESVTIME()%>">
                      	<span class="input-group-addon">Day(s)</span>
                      </div>
                    
             </div>
             <!-- End Lib Weekends -->
             
            <div class="form-group">
               <label class="col-md-2">Default Replacement Cost for Lost Book :</label>
               <div class="input-group col-md-2 col-md-2">
               			<span class="input-group-addon">RM</span>
                        <input type="text" class="form-control" id="GL28RCOST" name="GL28RCOST" value="<%=e.getGL28RCOST()%>">
               </div>

               <div class="input-group col-md-3 col-md-3">
                        <input type="text" class="form-control" id="GL28TIMESCOST" name="GL28TIMESCOST" value="<%=e.getGL28TIMESCOST()%>">
               			<span class="input-group-addon">times Cost of Lost Book (as Default)</span>
               </div>
               <!-- <label>times Cost of Lost Book (as Default)</label> -->
            </div>
            
            <div class="form-group">
                      <label class="col-sm-2 ">Due Date Calculation</label>
                      
                      <div class="radio"> 
                      	<div class="col-md-2"> 
  						<label>
    					<input type="radio" class="minimal" name="GL28CIRCMODE" id="GL28CIRCMODE" value="I" <%=e.getGL28CIRCMODE().equals("I")? "checked":""%> >
    					 Inclusive of Holidays
  						</label>
  						</div> 
  						<div class="col-md-2"> 
  						<label>
    					<input type="radio" class="minimal" name="GL28CIRCMODE" id="GL28CIRCMODE" value="E" <%=e.getGL28CIRCMODE().equals("E")? "checked":""%>>
    					 Exclusive of Holidays
  						</label>
					  </div>
					  </div>
			  	 </div>
       				
             
       </div><!-- /.tab-pane -->
       </div><!-- /.tab-content -->
       
       <div class="tab-pane" id="tab_5">
       <div class="box-body">
       		<div class="col-md-10">
       		<div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
					<h3 class="panel-title">OPAC Retrieval Limit</h3>
					</div>
					<div class="panel-body">
				
      					<div class="form-group">
                      	<label class="col-sm-2">OPAC Retrieval Limit</label>
                      	<div class="col-sm-5 col-md-2">
                        <input type="text" class="form-control" id="GL28OPACLIMIT" name="GL28OPACLIMIT" value="<%=e.getGL28OPACLIMIT()%>">
                      	</div>
                    	</div>
                    	
                    	<div class="form-group">
                    	<div class="radio"> 
                      		<!-- <label class="col-sm-2 control-label"></label> -->
                      		<div class="col-md-5"> 
  							<label>
    						<input type="radio" class="minimal" name="GL10UNIT" id="D" value="D" checked>
    					 	Allow user to load all the searched records
  							</label>
  							</div> 
  						</div>
  						<div class="radio"> 
  						
  							<div class="col-md-5"> 
  							<label>
    						<input type="radio" class="minimal" name="GL10UNIT" id="H" value="H">
    					 	Load until OPAC Retrieval Limit
  							</label>
					  		</div>
						</div>
			  	 		</div>
			  	 
    				</div>
    				</div>
			</div>
			  	 
			  	 
			<div class="panel-group">
    				<div class="panel panel-default">
      				<div class="panel-heading">
					<h3 class="panel-title">New Arrivals</h3>
					<!-- <span class="pull-right clickable"><i class="glyphicon glyphicon-chevron-up"></i></span> -->
					</div>
					<div class="panel-body">
				
                    	<div class="form-group">
                    	<div class="checkbox"> 
                      		<div class="col-md-5"> 
  							<label>
    						<input type="checkbox" class="minimal" name="GL10UNIT" id="D" value="D" checked>
    					 	Enable user defines date
  							</label>
  							</div> 
  						</div>
  						<div class="checkbox"> 
  							<div class="col-md-5"> 
  							<label>
    						<input type="checkbox" class="minimal" name="GL10UNIT" id="H" value="H">
    					 	Include only first released material
  							</label>
					  		</div>
						</div>
			  	 		</div>
			  	 
			  	 		<div class="col-md-1">
                  			<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="../../../include/shared/SearchModal.jsp">New Arrivals Policy</button>
                  		</div>
			  	 
    				</div>
    				</div>
			  </div>
			  </div>	 
       </div><!-- /.tab-pane -->
       </div><!-- /.tab-content -->
       
       <div class="tab-pane" id="tab_6">
       <div class="box-body">
       
       			<div class="form-group">
                  	<label class="col-sm-2 ">Serials. Head</label>
                  	<div class="col-sm-5 col-md-2">
                       <input type="text" class="form-control" id="patronIdSERHEAD" name="GL28SERHEAD" onblur="updateVendorName()" onkeyup="updateVendorNameWithClear()" value="<%=e.getGL28SERHEAD()%>">
                  	</div>
                  	<div class="col-md-1">
                  	<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=SERHEAD">...</button>
                  	</div>
                  	<div class="col-md-5">
                  				<%
			             	 	 	System.out.println(e.getGL28SERHEAD() +" getGL28SERHEAD");
		             	 	   		String patronNameSERHEAD;
		             	 	 		if(e.getGL28SERHEAD().trim().isEmpty()){
		             	 	 			patronNameSERHEAD = "";
		             	 	 		}else{
				                  		GlobalSQLStatement SERHEAD = new GlobalSQLStatement();
			                  			Foundation serhead = SERHEAD.getPatron(e.getGL28SERHEAD());
			                  			System.out.println(serhead.getGL14NAME() +" phead.getGL14NAME()");
			                  			patronNameSERHEAD = serhead.getGL14NAME();
		             	 	 		}
		             	 	 		System.out.println(patronNameSERHEAD +" patronNameSERHEAD");
							   %>
                       <input type="text" class="form-control" id="patronNameSERHEAD" name="patronName" value="<%=patronNameSERHEAD%>" readonly>
                  	</div>
             	</div> 
       
       			<div class="form-group">
              		<label class="col-sm-2">Tel. Ext</label>
                	<div class="col-sm-1 col-md-2">
                    	<input type="text" class="form-control" id="GL28SEREXT" name="GL28SEREXT" value="<%=e.getGL28SEREXT()%>">
             		</div>
       			</div>
       
       				<div class="form-group">
                      <label class="col-sm-2">Batch Order Printing</label>
                      <div class="col-sm-1 col-md-10">
                        <!-- <input type="checkbox" class="minimal" name="chkMailFlag(1)" id="chkMailFlag(1)" value="Y" > -->
                        <input type="checkbox" class="minimal" name="GL28SERBPRINT" id="GL28SERBPRINT" <%if (e.getGL28SERBPRINT().equals("Y")){ %> value="Y" checked <%}%> >
                       <%--  <%if (e.getGL28SERBPRINT().equals("Y")){  %>
	      						<label class="col-sm-2 ">
	      						<input type="checkbox" class="minimal" name=GL28SERBPRINT id="GL28SERBPRINT" value="Y" checked >
	      						</label>
	      				  <%}else{ %>
	      				  		<label class="col-sm-2 ">
	      						<input type="checkbox" class="minimal" name=GL28SERBPRINT id="GL28SERBPRINT">
	      						</label>
	      				  <%} %> --%>
                      </div>
                    </div>
       
       				
             
       </div><!-- /.tab-pane -->
       </div><!-- /.tab-content -->
       
       <div class="tab-pane" id="tab_7">
       <div class="box-body">
       
       			<div class="form-group">
                  	<label class="col-sm-2 ">IRS Head</label>
                  	<div class="col-sm-5 col-md-2">
                       <input type="text" class="form-control" id="patronIdIRSHEAD" name="GL28IRSHEAD" onblur="updateVendorName()" onkeyup="updateVendorNameWithClear()" value="<%=e.getGL28IRSHEAD()%>">
                  	</div>
                  	<div class="col-md-1">
                  	<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=IRSHEAD">...</button>
                  	</div>
                  	<div class="col-md-5">
                  			<%
			             	 	 	System.out.println(e.getGL28IRSHEAD() +" getGL28SERHEAD");
		             	 	   		String patronNameIRSHEAD;
		             	 	 		if(e.getGL28IRSHEAD().trim().isEmpty()){
		             	 	 			patronNameIRSHEAD = "";
		             	 	 		}else{
				                  		GlobalSQLStatement IRSHEAD = new GlobalSQLStatement();
			                  			Foundation irshead = IRSHEAD.getPatron(e.getGL28IRSHEAD());
			                  			System.out.println(irshead.getGL14NAME() +" phead.getGL14NAME()");
			                  			patronNameIRSHEAD = irshead.getGL14NAME();
		             	 	 		}
		             	 	 		System.out.println(patronNameIRSHEAD +" patronNameIRSHEAD");
							   %>
                       <input type="text" class="form-control" id="patronNameIRSHEAD" name="patronName" value="<%=patronNameIRSHEAD%>" readonly>
                  	</div>
             	</div> 
       
       			<div class="form-group">
              		<label class="col-sm-2">Tel. Ext</label>
                	<div class="col-sm-1 col-md-2">
                    	<input type="text" class="form-control" id="GL28IRSEXT" name="GL28IRSEXT" value="<%=e.getGL28IRSEXT()%>">
             		</div>
       			</div>
       			
       			<!-- Base Marc Type -->
                  <div class="form-group">
                  <label class="col-sm-2">IRS MARC Type</label>
                  <div class="col-sm-5 col-md-2">
                  <select class="form-control" id="GL28IRSMARCTYPE" name="GL28IRSMARCTYPE" onchange="document.getElementById('dept').selectedIndex
                            						= document.getElementById('GL28IRSMARCTYPE').selectedIndex">
                      
                      <%if((e.getGL28IRSMARCTYPE())== null || (e.getGL28IRSMARCTYPE()).trim().isEmpty()){%>
                      <option value=""> </option>
                      <%
                      	GlobalSQLStatement MARCType = new GlobalSQLStatement();
						List<Foundation> MARCTypelist = MARCType.getMARC();
						for (Foundation a : MARCTypelist) {
					  %>
					  <option value="<%=a.getGL16MARC()%>"><%=a.getGL16MARC()%></option>
					  <%
						}
					  %>
                      <%}else{%>
                      <option value="<%=e.getGL28MARCTYPE()%>"><%=e.getGL28MARCTYPE()%></option>
                      <%
                      	GlobalSQLStatement MARCType = new GlobalSQLStatement();
						List<Foundation> MARCTypelist = MARCType.getMARCNOTIN(e.getGL28IRSMARCTYPE());
						for (Foundation a : deptcodelist) {
					  %>
					  <option value="<%=a.getGL16MARC()%>"><%=a.getGL16MARC()%></option>
					  <%
						}
					  %>
					  <%
						}
					  %>
                      
                      <%-- <option value=""></option>
                      <%
                      GlobalSQLStatement marc2 = new GlobalSQLStatement();
							List<Foundation> marctype = marc2.getDept();
							for (Foundation a : marctype) {
					  %>
					  <option value="<%=a.getGL11DEPT()%>"><%=a.getGL11DEPT()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div>
               
                  <div class="col-sm-5 col-md-4">
                  <select class="form-control" id="MARCTYPEdesc" name="MARCTYPEdesc" onchange="document.getElementById('GL28IRSMARCTYPE').selectedIndex
                            						= document.getElementById('MARCTYPEdesc').selectedIndex">
                      
                       <%
							GlobalSQLStatement MARCType = new GlobalSQLStatement();
	                  		Foundation MARCTypeDesc = MARCType.getMARCDESC(e.getGL28IRSMARCTYPE());
	                  		System.out.println(MARCTypeDesc.getGL16DESC() +" MARCTypeDesc.GL16DESC()");
					  %>
					  <option value="<%=MARCTypeDesc.getGL16DESC()%>"><%=MARCTypeDesc.getGL16DESC()%></option>
					 <%--  <%
						}
					  %>  --%>
					  
                     <%
                 	GlobalSQLStatement MARCType2 = new GlobalSQLStatement();
						List<Foundation> MARCTypelist2 = MARCType2.getMARCNOTIN(e.getGL28IRSMARCTYPE());
						for (Foundation a : MARCTypelist2) {
					  %>
					  <option value="<%=a.getGL16DESC()%>"><%=a.getGL16DESC()%></option>
					  <%
						}
					  %> 
                      
                      <%-- <option value=""></option>
                      <%
                      GlobalSQLStatement marcname = new GlobalSQLStatement();
							List<Foundation> marcnamelist = marcname.getDept();
							for (Foundation a : marcnamelist) {
					  %>
					  <option value="<%=a.getGL11NAME()%>"><%=a.getGL11NAME()%></option>
					  <%
						}
					  %> --%>
				</select>
                </div>       
             </div>
             <!-- End Base Marc Type -->
             
             <div class="form-group">
                    	<!-- <div class="radio">  -->
                      		<label class="col-sm-2 ">System Generated Number</label>
                      		<div class="col-md-1"> 
  							<label>
    						<input type="radio" class="minimal" name="GL28IRSFLAG" id="GL28IRSFLAG" value="Y" <%=e.getGL28IRSFLAG().equals("Y")? "checked":""%>>
    					 	Yes
  							</label>
  							</div> 
  						
  							<div class="col-md-1"> 
  							<label>
    						<input type="radio" class="minimal" name="GL28IRSFLAG" id="GL28IRSFLAG" value="N" <%=e.getGL28IRSFLAG().equals("N")||e.getGL28IRSFLAG().trim().isEmpty()? "checked":""%>>
    					 	No
  							</label>
					  		</div>
						<!-- </div> -->
			  	 		</div>
       
       </div><!-- /.tab-pane -->
       </div><!-- /.tab-content -->
       
       <div class="tab-pane" id="tab_8">
       <div class="box-body">
       
       			<div class="form-group">
                  	<label class="col-sm-2">Finance Controller</label>
                  	<div class="col-sm-5 col-md-2">
                       <input type="text" class="form-control" id="patronIdFINHEAD" name="GL28FINHEAD" onblur="updateVendorName()" onkeyup="updateVendorNameWithClear()" value="<%=e.getGL28FINHEAD()%>">
                  	</div>
                  	<div class="col-md-1">
                  	<button type="button" class="btn btn-info selectPopup" id="btn-search" data-toggle="modal" data-target="#vendorModal" href="Modal_PatronSearch?action=FINHEAD">...</button>
                  	</div>
                  	<div class="col-md-5">
                  				<%
			             	 	 	System.out.println(e.getGL28FINHEAD() +" getGL28FINHEAD");
		             	 	   		String patronNameFINHEAD;
		             	 	 		if(e.getGL28FINHEAD().trim().isEmpty()){
		             	 	 			patronNameFINHEAD = "";
		             	 	 		}else{
				                  		GlobalSQLStatement FINHEAD = new GlobalSQLStatement();
			                  			Foundation finhead = FINHEAD.getPatron(e.getGL28FINHEAD());
			                  			System.out.println(finhead.getGL14NAME() +" finhead.getGL14NAME()");
			                  			patronNameFINHEAD = finhead.getGL14NAME();
		             	 	 		}
		             	 	 		System.out.println(patronNameFINHEAD +" patronNameFINHEAD");
							   %>
                       <input type="text" class="form-control" id="patronNameFINHEAD" name="patronName" value="<%=patronNameFINHEAD%>" readonly>
                  	</div>
             	</div> 
       
       			<div class="form-group">
              		<label class="col-sm-2">Tel. Ext</label>
                	<div class="col-sm-1 col-md-2">
                    	<input type="text" class="form-control" id="GL28FINEXT" name="GL28FINEXT" value="<%=e.getGL28FINEXT()%>">
             		</div>
       			</div>
       			
       			<div class="form-group">
              		<label class="col-sm-2">Fund Code</label>
                	<div class="col-sm-1 col-md-2">
                    	<input type="text" class="form-control" id="GL28FUND" name="GL28FUND" value="<%=e.getGL28FUND()%>">
             		</div>
       			</div>
             
       </div><!-- /.tab-pane -->
       </div><!-- /.tab-content -->
       
       
       </div><!-- nav-tabs-custom -->
       </div><!-- /. -->
       </div> <!-- /.col -->
       
       </div><!-- /.row -->
       <!-- END CUSTOM TABS -->
       <div class="modal-footer">
            <input type="submit" name="Save" value="Save" style="width: 80px" class="btn btn-primary" id="Save">
            <button type="button" style="width: 80px" class="btn btn-default antoclose" data-dismiss="modal">Cancel</button>
            </div>
       </form>
       
       
        		
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
            </div>
          </div><!-- /.row -->
          
        </section><!-- /.content -->
        
        <!-- Modal HTML for ADD-->  
        
        <div class="modal fade" id="AddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:1100px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 		<!-- Modal END -->
 		
 		<!-- Modal HTML for VIEW-->  
        
        <div class="modal fade" id="ViewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:1100px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 		<!-- Modal END -->
 		
 		<!-- Modal HTML for EDIT-->  
        
        <div class="modal fade" id="EditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:1100px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 		<!-- Modal END -->
        
         <!-- Modal HTML for DELETE-->  
        
        <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:400px;">
                <div class="modal-content">
		 		 <!-- Remote content load here -->
		  		</div>
			</div>
		</div>
 		<!-- Modal END -->
 		
 		<!-- Search Criteria Modal-->  
        
    <div class="modal fade" id="vendorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
         <div class="modal-dialog" style="width:750px;">
             <div class="modal-content">
		 	 <!-- Remote content load here -->
		  	 </div>
		</div>
	</div>
	<!-- ./End Search Criteria Modal -->
 		
 	
    
  </body>
</html>

