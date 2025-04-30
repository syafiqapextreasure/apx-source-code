<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					com.ilmu.cataloging.Template_Maint.*,
					java.util.List, 
					com.ilmu.global.*,java.text.*,java.util.*"%>

<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>	
<script>

</script>
<style>
.modal:nth-of-type(even) {
    z-index: 1052 !important;
}
.modal-backdrop.show:nth-of-type(even) {
    z-index: 1051 !important;
}

.isDisabled {
  color: currentColor;
  cursor: not-allowed;
  opacity: 0.5;
  text-decoration: none;
    pointer-events: none;
}
</style>
</head>
<jsp:include page="ActionJavascript.jsp" />
<%
System.out.println("Testing");
	String action = request.getParameter("action");
	System.out.println(action);
	int tplID = 0;
	String controlNoInput = null;
	List<BO_Record> CT02LEADER = null;
	List<BO_Record> BORecord = null;
	List<Template_Maintenance> DefaultRecord = null;
	List<String> DefaultTag = null;
	BO_Record info = null;
	String createby, createtime, createdate, modiby, modidate, moditime, status, matno, ctype;
	createby= createtime=createdate=modiby= modidate= moditime = status= matno = ctype="";
	String userName = (String) session.getAttribute("username");
	String typecode = "";
	System.out.println("action" + action);
	 Date dNow = new Date( );
     SimpleDateFormat date = new SimpleDateFormat ("dd/MM/yyyy");
     SimpleDateFormat time = new SimpleDateFormat ("hh:mm:ss a");
     String lblNumbering = null;
     List<BO_Record> Leader = null;
     String module = request.getParameter("module");
	if(action.equals("Index")){
		controlNoInput = request.getParameter("matno");
		System.out.println("controlNoInput: " + controlNoInput);
		CT02LEADER = BO_Record.getLeader(controlNoInput, Handler.tblName(module));
		info = BO_Record.getIndexCTMATM(controlNoInput, Handler.tblName(module));
		createby = info.getCT02CREBY();
		createtime = info.getCT02CRTIME();
		createdate = info.getCT02CREDATE();
		modiby = info.getCT02IDXBY();
		modidate = info.getCT02IDXDATE();
		moditime = info.getCT02IDXTIME();
		//modidate = info.getCT02IDXDATE();
		matno = info.getCT02MATNO();
		ctype=Tag_Handler.getMsBibType(info.getCT02TYPE(), Handler.bibType(module));
		status = "Index Record";
		BORecord = BO_Record.getRetrievedData(controlNoInput, Handler.marcType(module), Handler.tblName(module));
		lblNumbering = "Control No. ";
		Leader = BO_Record.getLeader(controlNoInput, Handler.tblName(module));
		typecode = info.getCT02TYPE();
		//manda =BO_Record.getLeaderManda("000");
	}else if(action.equals("Buffer")){
		controlNoInput = request.getParameter("matno");
		BORecord = BO_Record.getCTWORK(controlNoInput, Handler.tblName(module));
		info = BO_Record.getBufferCTMATM(controlNoInput,Handler.tblName(module));
		createby = info.getCT02CREBY();
		createtime = info.getCT02CRTIME();
		createdate = info.getCT02CREDATE();
		modiby = info.getCT02IDXBY();
		moditime = info.getCT02IDXTIME();
		modidate = info.getCT02IDXDATE(); 
		matno = controlNoInput;
		status = "Buffer Record";
		//BO_Record getType = BO_Record.getIndexCTMATM(Handler.replaceWith0(request.getParameter("matno")));
		//If type does not exist, will add as cataloging by default
		if((info.getCT04TYPE())==null){
			ctype=Tag_Handler.getMsBibType("C", Handler.bibType(module));
			typecode = "C";
		}else{
			System.out.println("Type" + info.getCT04TYPE());
			ctype=Tag_Handler.getMsBibType(info.getCT04TYPE(), Handler.bibType(module));
			typecode = info.getCT04TYPE();
		}
		lblNumbering = "Buffer No. ";
		
	}else{
		
		CurrentUser user=new CurrentUser();
		tplID = Integer.parseInt(request.getParameter("TPLID"));
		BORecord = BO_Record.getBOList(tplID, Handler.marcType(module));
		DefaultTag = Template_Maintenance.getDefault(Handler.marcType(module));
		//createby = (String)session.getAttribute("username");
		createtime =  DateTime.getTodayTime();
		createdate = DateTime.getTodayDate();
		//modiby = (String)session.getAttribute("username");
		moditime =  DateTime.getTodayTime();
		modidate = DateTime.getTodayDate();
		status = "New Record";
		lblNumbering = "Buffer No. ";
		ctype=Tag_Handler.getMsBibType(request.getParameter("type"), Handler.bibType(module));
		typecode = request.getParameter("type");
		
	}

	
%>
	<div class="bibdetails">
	<table class="table-hover table-condensed table-fixed" id="rcrddetails"  style="border-collapse:collapse;color:black;width:100%">
	<tbody>
		<tr>
			<td>Created By: <span class="Cuser"><%=createby %></span></td>
		    <td>Created Date: <span class="Cdate"><%=createdate %></span></td>
		    <td>Created Time: <span class="Ctime"><%=createtime %></span></td>
		</tr>
		<tr>
			<td>Modified By:<span class="Muser"><%=modiby  %></span><input type='hidden' id='newUser'></td>
		    <td>Modified Date: <span class="Mdate"><%=modidate %></span><input type='hidden' class='newModidate' value='<%=date.format(dNow)%> '></td>
		    <td>Modified Time: <span class="Mtime"><%=moditime %></span><input type='hidden' class='newModitime' value='<%=time.format(dNow)%> '></td>
		</tr>
		<tr>
		 <td class="orimatno"><%=lblNumbering %>: <span class='Cmatno'><%=matno %></span></td>
		 <td>Status: <span class='Cstatus'><%=status %></span></td>
		 <td>Type:<span class='Ctype'><%=ctype %></span><input type="hidden" class="typecode" value="<%=typecode%>"></td>
		</tr>
		</tbody>
	</table>
	</div>
	<table class='table table-condensed table-fixed BO_Record' id='BO_Record' style='border-collapse:collapse;color:black;width:100%'>
		<col width='5%'><col width='5%'><col width='5%'>
		<thead>
		<tr class='titleTr'>
			<th>Tag</th><th>Indi1</th><th>Indi2</th><th>Data</th>
			<th>
				<div class='btn-group pull-right'><button type='button' data-toggle='dropdown' class='btn btn-default dropdown-toggle'>Action <span class='caret'></span></button>
					<ul class='dropdown-menu menu'>
						<li class=''><a id='duplicateCopy' class='disableDuplicate' style='cursor:pointer'>Duplicate Copy</a></li>
						<li class=''><a id='addTag' data-toggle='modal' data-target='#addNewRecord'>Add Tag</a></li>
						<li class=''><a id='searchAccNo' href='#'>Accession No</a></li>
						 <li class="divider"></li>
						<li><a id='addBORcrd' href='#'>Save</a></li>
						<li class=''><a id='indexBO' href='#' class='isDisabled'>Index</a></li>
						<li class=''><a id='saveindex' href='#' >Save and Index</a></li>
						 <li class="divider"></li>
						<li  class=''><a id='modify' href='#' onclick="modifyRcrd()">Modify Record</a></li>
						<li  class=''><a id='unindex' href='#' class='isDisabled'>Unindex and Modify</a></li>
						<li class=''><a id='delete' href='#' class='disableDelete'>Delete</a></li>
						 <li class="divider"></li>
						<li class=''><a id='viewisbd' data-toggle='modal' data-target='#isbdview'>ISBD View</a></li>
						<li class=''><a id='viewmarc' data-toggle='modal' data-target='#marcview'>Marc View</a></li>
						<li class=''><a id='cancelDupCopy' class='disableCancelDuplicate isDisabled' style='cursor:pointer'>Cancel</a></li>
						<li class=''><a id='security' class="disableSecurity isDisabled" data-toggle='modal' data-target='#securityModal' href='Modal_BOSecurity?ctrlno=<%=matno %>' >Security</a></li>
					</ul>
				</div>
			</th>
		</tr>
		</thead>
		<tbody>

			<%
				int inc = 0;
			if(action.equals("Index")){
				for(BO_Record i: CT02LEADER)
				{
					System.out.println("www" + i.getCT02LEADER());
					if( !(i.getCT02LEADER()).trim().isEmpty()){
			%>			
					<tr class="<%=inc%>">
						<td class='tagValue'><span class="tag">000</span>&nbsp;<span class="glyphicon glyphicon-question-sign desc" title="LEADER"></span></td>
						<td><input type='text' size='1' maxlength='1' class="form-control indi1" value="#"></td>
						<td><input type='text' size='1' maxlength='1' class="form-control indi2" value="#"></td>
						<td><input type='text' value='<%= i.getCT02LEADER() %>' class="form-control data controltag" size='100' data-manda=<%=i.getGL17MANDA()%> data-autflag='<%=i.getGL17AUTFLAG()%>' disabled ></td>
						<td>
							<a data-toggle='modal' onclick='ctrlTags(this)' data-target='#ctrlTagsForm' data-tagid='<%=inc%>' data-template="000" class='btn btn-info btn-sm editdata'title='Add Control Fields' value="000"><span class='glyphicon glyphicon-edit' title='Control Tag'></span></a>
							<a id= "retrieveLinks" class='btn  btn-primary btn-sm' data-toggle='modal' data-target='#termSearch' href='Term_Search?tag=000&action=direct_termsearch&tagid=<%=inc%>' title="Term Search">
								<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
							</a>
							<a class='btn btn-dull btn-sm delete' title='Delete Field' ><span class='glyphicon glyphicon-trash'></span></a>
						</td>
					</tr>
				<%
					inc++;
					}
				
				}
			}

			%>
			
			<%
			  ArrayList<String> myList = new ArrayList<String>();
				for(BO_Record i: BORecord){
					
				    myList.add(i.getTAG());
					String data = "";
					String indi1 = "";
					String indi2 = "";
				
					//Check if there is default data
					if(DefaultTag!=null){
						System.out.println("DefaultTag" + DefaultTag);
					if(DefaultTag.contains(i.getTAG())){
						String defaultRecord = Template_Maintenance.getDefaultData(i.getTAG());
						System.out.println("DefaultTag" + defaultRecord + i.getTAG());
						if(!defaultRecord.trim().isEmpty()&& !defaultRecord.trim().contains("NULL") && defaultRecord.trim().contains("|")){
							data = defaultRecord.substring(defaultRecord.indexOf("|"));
							System.out.println("DataConv1" + data);
							String indicators = defaultRecord.substring(0 , defaultRecord.indexOf("|"));
							indi1 = indicators.substring(0,1);
							indi2 = indicators.substring(1,2);
							System.out.println("Data" + indi2 + "Data1" + i.getCTTPLSUBF ());
							if(data.trim().isEmpty()){
								//data = i.getCTTPLSUBF ();
								data =  (i.getCTTPLSUBF ()).replace("\"", "&#34;").replace("\'", "&#39;");
								/* if(i.getCTTPLSUBF ().contains("\"")){
									data = "'"+i.getCTTPLSUBF ()+"'";
								}else{
										data = '"'+i.getCTTPLSUBF ()+'"';
								} */
								System.out.println("DataConv2" + data);
							}
							if(indi1.trim().isEmpty()){
								indi1 = i.getINDI1 ();
							}
							if(indi2.trim().isEmpty()){
								indi2 = i.getINDI2 ();
							}
					}else{
						//data = i.getCTTPLSUBF ();
						data =  (i.getCTTPLSUBF ()).replace("\"", "&#34;").replace("\'", "&#39;");
						System.out.println("DataConv3" + data);
						indi1 = i.getINDI1 ();
						indi2 = i.getINDI2 ();
					}
					}else{
					//	data = i.getCTTPLSUBF ();
						data =  (i.getCTTPLSUBF ()).replace("\"", "&#34;").replace("\'", "&#39;");
						System.out.println("DataConv4" + data);
						indi1 = i.getINDI1 ();
						indi2 = i.getINDI2 ();
					}
					System.out.println("Datas" + data);
					}else{
						//data = i.getCTTPLSUBF ();
						data =  (i.getCTTPLSUBF ()).replace("\"", "&#34;").replace("\'", "&#39;");
						System.out.println("DataConv5" + data);
						indi1 = i.getINDI1 ();
						indi2 = i.getINDI2 ();
						
					}
					System.out.println("Datas3ww" + data);
			%>
			<tr class="<%=inc%>">
				<td class='tagValue'><span class="tag"><%=i.getTAG()%></span>&nbsp;<span class="glyphicon glyphicon-question-sign desc" title="<%=i.getTAGDESC() %>"></span></td>
				<td><input type='text' size='1' maxlength='1' class="form-control indi1" value="<%=indi1.trim() %>"></td>
				<td><input type='text' size='1' maxlength='1' class="form-control indi2" value="<%=indi2.trim() %>"></td>
				<%
					String disable = "";
					String rawDataClass = "";
					System.out.println("Tag" + i.getTAG());
					if(i.getTAG().equals("000")||i.getTAG().equals("006")||i.getTAG().equals("007")||i.getTAG().equals("008")){
						disable = "disabled";
						rawDataClass = "controltag";
					}else{
						rawDataClass = "rawtag";
					}
				%>

				<td><input type="text" value='<%=data.trim()%>' class="form-control data <%=rawDataClass %>" size='100' data-manda=<%=i.getGL17MANDA()%> data-autflag='<%=i.getGL17AUTFLAG()%>' <%=disable %> ></td>
				<td>
					<%
					if(i.getTAG().equals("000")||i.getTAG().equals("006")||i.getTAG().equals("007")||i.getTAG().equals("008")){
						String ctrltpl = "";
						System.out.println("Testing ctrltag" + action);
						if(action.equals("Index")){
							if(i.getTAG().equals("006")){
								ctrltpl = i.getCT02T006();
							}else if(i.getTAG().equals("007")){
								ctrltpl = i.getCT02T007();
							}else if(i.getTAG().equals("008")){
								ctrltpl = i.getCT02T008();
							}else{
								ctrltpl = i.getCT02T008();
							}
						}else if(action.equals("Buffer")){
							ctrltpl =i.getCT04MARC();
						}
					%>
					<a data-toggle='modal' onclick='ctrlTags(this)' data-target='#ctrlTagsForm' data-tagid='<%=inc%>' data-template="<%=ctrltpl %>" class='btn btn-info btn-sm editdata'title='Add Control Fields' value="<%=i.getTAG()%>"><span class='glyphicon glyphicon-edit' title='Control Tag'></span></a>
					<%
					}else{
					%>
					<a data-toggle='modal' data-autflag='<%=i.getGL17AUTFLAG()%>' data-target='#viewSubfields' data-template="<%=i.getCT04MARC() %>" onclick="openModal(this)" class='btn btn-info btn-sm editdata openModal' data-tagdesc="<%=i.getTAGDESC() %>" data-tagid="<%=inc%>"><span class='glyphicon glyphicon-pencil' title='View Subfields'></span></a>
					<%
					}
					%>
					<a id= "retrieveLinks" class='btn  btn-primary btn-sm' data-toggle='modal' data-target='#termSearch' href='Term_Search?tag=<%=i.getTAG()%>&action=direct_termsearch&tagid=<%=inc%>' title="Term Search">
						<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
					</a>
					<a class='btn btn-dull btn-sm delete' title='Delete Field' ><span class='glyphicon glyphicon-trash'></span></a>
					<%
						if(i.getGL17AUTFLAG()!=null){
						if(i.getGL17AUTFLAG().equals("Y") && status.equals("New Record")){
						String desc = BO_Record.getLinkTagDesc();
					%>
					<a class='btn btn-sm linkage' style="background-color:666699;color:white" title='Linkage' onclick="linkage(this)"><span class='glyphicon glyphicon-link'></span></a>
					<input type="hidden" value="<%=desc%>" id="linktagdesc">
					<%
						}
						}
					%>
					<%	if ((i.getGL17REPEAT()).equals("Y")){%>
						
					<a class='btn btn-dull btn-sm duplicateTag' title='Duplicate Tag' style="background-color:#00CED1;color:white" onclick="duplicateTag(this)"><span class='glyphicon glyphicon-duplicate'></span></a>
					<%
					}
					%>
				</td>
			</tr>
			
		<%
				inc++;
				}
		%>
		</tbody>
	</table>
	
	<div class="modal fade" style="z-index:1050" id="viewSubfields" tabindex="-1" role="dialog" aria-labelledby="viewSubfields" data-backdrop="static" style="z-index: 1400;">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div id="subfList"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="addNewRecord" tabindex="-1" role="dialog" aria-labelledby="addNewRecord" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" id='modalTag'>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" style="z-index:1050" id="marcview" tabindex="-1" role="dialog" aria-labelledby="isbdview" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content" id='marccontent'  style="padding:10px">
			  </div>
		</div>
	</div>
	
		
	<div class="modal fade" style="z-index:1050" id="isbdview" tabindex="-1" role="dialog" aria-labelledby="isbdview" data-backdrop="static">
	    <div class="modal-dialog" style="width:4in" role="document">
			  <div class="modal-content" id='isbdcontent' >
			  </div>
		</div>
	</div>
	
	
	<div class="modal fade" id="termSearch" tabindex="-1" role="dialog" aria-labelledby="termSearch" data-backdrop="static" style="z-index: 1600;">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content" id="termsearchcontent">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	`
	<div class="modal fade" style="z-index:1050" id="ctrlTagsForm" tabindex="-1" role="dialog" aria-labelledby="viewSubfields" data-backdrop="static">
	    <div class="modal-dialog" style="width:80%" role="document">
			  <div class="modal-content">
			  <div id="ctrlTagDisplay"></div>
			  </div>
		</div>
	</div>
	
	<div class="modal fade" id="titleSearch" tabindex="-1" role="dialog" aria-labelledby="titleSearch"  data-backdrop="static">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content title">
			<!-- Remote content load here -->
			</div>
		</div>
	</div>	
	
		<div class="modal fade" id="authlink" tabindex="-1" role="dialog" aria-labelledby="authlink" data-backdrop="static">
	    <div class="modal-dialog modal-lg" role="document">
			  <div class="modal-content" id="modalauthlink">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
	
	<div class="modal fade" id="securityModal" tabindex="-1" role="dialog" aria-labelledby="authlink" data-backdrop="static">
	    <div class="modal-dialog modal-sm"" role="document">
			  <div class="modal-content" id="securityRcrd" style="height:30%">

			  </div>
		</div>
	</div>
	