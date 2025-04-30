	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
 <link rel="stylesheet" type="text/css" href="http://davidstutz.github.io/bootstrap-multiselect/dist/css/bootstrap-multiselect.css">
 <script type="text/javascript" src="http://davidstutz.github.io/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>										

<style>
.modal:nth-of-type(even) {
    z-index: 1052 !important;
}
.modal-backdrop.show:nth-of-type(even) {
    z-index: 1051 !important;
}
</style>
<style>
#subfTable td{padding-bottom:15px;}
.titleTr{
  height:70px;  
  color:#f6f3f7; 
  background:#2c3e50;  
  border:0px solid;
}

.headingTr{
    height:30px;
    font-size:10pt;
} 

#termSearch{
	z-index: 1080 !important;
}

.disabled {
  color: grey; // ...whatever
}


#subfTable tbody{
  display:block;
  overflow:auto;
  height:200px;
  width:100%;
}
#subfTable thead tr{
  display:block;
  width:100%;
}
</style>

					
	<%
		String tag = request.getParameter("tag");
		String indi1 = request.getParameter("indi1");
		String lvl1id = request.getParameter("indiinc1");
		String lvl2id = request.getParameter("indiinc2");
		String indi2 = request.getParameter("indi2");
		String tagdesc = request.getParameter("tagdesc");
		String subfields = request.getParameter("subfields");
		String tagid = request.getParameter("tagid");
		String values = request.getParameter("value");
		String ctmstr=request.getParameter("ctmstr");
		String autflag=request.getParameter("autflag");
		String module = request.getParameter("module");
		
		System.out.println(tagid + "Tagid");
	
	%>
	
	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel">Edit Term</h4>
	</div>
	<div class="modal-body" style='min-height:30%;max-height:50%;overflow:auto;'>
				<div class='errorMessage'></div>
		<form id="viewsubfform">
			<table id='subfTable'  style="width:100%" class = "table table-hover table-condensed">
				<thead class='titleTr'>
					<tr>
						<th width="5%">Tag</th>
						<th width="5%">Indi1</th>
						<th>Indi2</th>
						<th>Data</th>
						<th>
							<div class="btn-group pull-right">
								<button type="button" data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action <span class="caret"></span></button>
								<ul class="dropdown-menu menu">
									<li>
										<a data-toggle='modal' data-target='#termSearch' href='Term_Search?tag=<%=tag%>&action=modal_termsearch&tagid=<%=tagid%>''>
											Term Search
										</a>
									</li>
									<li>
										<a id='addNewSubf' data-toggle='modal' data-target='#addSubfield' data-tagid="tag_<%=tagid%>" >
											Add Subfield
										</a>
									</li>
								</ul>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr class="headingTr">
					<td id="tagValue"  name='tag_<%=tagid%>'><%=tag %></td>
						<td><input size='1'  type='text' class='indiValue1 indiValues1' data-value= "indi<%=values %>" data-tag='<%=tag %>' data-indilvl='1' name="indi1_<%=lvl1id%>"onkeyup='updateIndi(this)' id='indi1' value='<%=indi1 %>'></td>
						<td><input size='1' type='text' class='indiValue2 indiValues2' data-indilvl='2' data-tag='<%=tag %>' onkeyup='updateIndi(this)' name="indi2_<%=lvl2id%>" id='indi2' value='<%=indi2 %>'><a id='showIndi' title='Indicator Help' data-id='<%=tag %>'>&nbsp;<span class='glyphicon glyphicon-search'></span></a></td>
						<td><%=tagdesc %></td>
						<td></td>
					</tr>
	
				<%
			
				String[] splitSubf = subfields.split("\\|");
			
				StringBuilder result = new StringBuilder();
				for(int i = 1; i < splitSubf.length; i++)
				{	String key = UUID.randomUUID().toString();
					String splitData = splitSubf[i].substring(0,1);
					String rawData = splitSubf[i].substring(1).trim();
					Subfield_Handler subfDesc = null;
					subfDesc = Subfield_Handler.getSubfDetails(splitData, tag, Handler.marcType(module));
					boolean subfexist = Subfield_Handler.subfieldExist(splitData, tag, Handler.marcType(module));
					if(subfexist){
					
		
			%>			<tr class="primarySubf" id="<%=tag %><%=splitData%><%=tagid%><%=key%>">
							<td>
								<a class='btn btn-success btn-xs sort' onclick="sortSubf('<%=tag %><%=splitData%><%=tagid%><%=key%>')">
									<span class="glyphicon glyphicon-sort"></span>
								</a>
								<input type="hidden" value="<%=tagid%>" id="rowcount" class="rowcount">
							</td>
							<td class="subf_<%=splitData%> subfields">
								<%=Handler.convertLowerCase(splitData)%>
							</td>
							<td colspan="2">
							<%-- <%
								if(rawData.length()>0){	
							%>
								<input class="concatData" data-autflag='<%=autflag%>' id="|<%=splitData%>" name='tag_<%=tagid%>' data-desc='<%=splitData%>-<%=subfDesc.getGL23DESC()%>' value="<%=rawData%>" onfocus='getDesc(this);getData(this)' type='text' size="99" style="width:100%">
							<%
								}else{
							%>
								<input class="concatData" data-autflag='<%=autflag%>' id="|<%=splitData%>" name='tag_<%=tagid%>' data-desc='<%=splitData%>-<%=subfDesc.getGL23DESC()%>' onfocus='getDesc(this);getData(this)' type='text' size="99" style="width:100%">
							<%
								}
							%> --%>
							
							<%
								String datatype = BO_Validation.dataType(tag, Handler.marcType(module));
								if(datatype.equals("TS")){
	
								if(rawData.length()>0){	
							%>
								<input class="concatData" data-autflag='<%=autflag%>' id="|<%=splitData%>" name='tag_<%=tagid%>' data-desc='<%=splitData%>-<%=subfDesc.getGL23DESC()%>' value="<%=rawData%>" onfocus='getDesc(this);getData(this)' type='text' size="99" style="width:100%"><%
								}else{
							%>
								<input class="concatData" data-autflag='<%=autflag%>' id="|<%=splitData%>" name='tag_<%=tagid%>' data-desc='<%=splitData%>-<%=subfDesc.getGL23DESC()%>' onfocus='getDesc(this);getData(this)' type='text' size="99" style="width:100%">

							<%
								}
								}else{
							
								if(rawData.length()>0){	
							%>
								<textarea class="concatData" data-autflag='<%=autflag%>' id="|<%=splitData%>" name='tag_<%=tagid%>' data-desc='<%=splitData%>-<%=subfDesc.getGL23DESC()%>' onfocus='getDesc(this);getData(this)' style="width:100%" cols="100"><%=rawData%></textarea>
							<%
								}else{
							%>
								<textarea class="concatData" data-autflag='<%=autflag%>' id="|<%=splitData%>" name='tag_<%=tagid%>' data-desc='<%=splitData%>-<%=subfDesc.getGL23DESC()%>' onfocus='getDesc(this);getData(this)' style="width:100%" cols="100"></textarea>
							<%
								}
								}
							%>
							</td>
							<td>
								<a class='btn btn-dull btn-sm' onclick='removeSubf(this)'>
									<span class="glyphicon glyphicon-trash"></span>
								</a>
								<%
									if((subfDesc.getGL23REPEAT()).equals("Y")){
								%>
									<a class='btn btn-default btn-sm' onclick="CloneSubfield('<%=tag %><%=splitData%><%=tagid%><%=key%>')">
									<span class="glyphicon glyphicon-duplicate" data-repeat="<%=subfDesc.getGL23REPEAT()%>"></span>
								</a>
								<%
									}
								%>
							</td>
						</tr>
			<%
					}
				} 
				
			%> 
							</tbody>
			</table>
		</form>
	</div> 
	<div class="modal-footer">

	<div style='text-align:center'id='showSubf'></div>			
		<a class='btn btn-info' id='saveSubfields' data-id="<%=tagid%>" data-dismiss="modal">
			OK
		</a>
		<a class="btn btn-dull reset">Reset</a>

	</div>
	
	<!-- <div class="modal fade" id="termSearch" tabindex="-1" role="dialog" aria-labelledby="termSearch" data-backdrop="static" style="z-index: 1600;">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  Remote content load here
			  </div>
		</div>
	</div> -->
	
	<div class="modal fade" id="addSubfield" tabindex="-1" role="dialog" aria-labelledby="termSearch" data-backdrop="static" style="z-index: 1600;">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content" id="modalSubf">
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