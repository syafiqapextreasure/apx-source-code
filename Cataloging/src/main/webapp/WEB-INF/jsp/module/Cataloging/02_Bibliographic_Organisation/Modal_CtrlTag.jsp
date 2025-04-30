	<%@ page import="com.ilmu.global.connection.DBConnection, 
					com.ilmu.cataloging.Bibliography.*, 
					java.util.List, com.ilmu.cataloging.Template_Maint.*,
					com.ilmu.global.*,java.text.*,java.util.*"%>
 <link rel="stylesheet" type="text/css" href="http://davidstutz.github.io/bootstrap-multiselect/dist/css/bootstrap-multiselect.css">
 <script type="text/javascript" src="http://davidstutz.github.io/bootstrap-multiselect/dist/js/bootstrap-multiselect.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/BO.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Cataloging/BO.js"></script>										

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
		String data=request.getParameter("data");
		
		System.out.println(subfields);
	
	%>
	
	<div class="modal-header">
		<h4 class="modal-title" id="myModalLabel">Edit Term</h4>
	</div>
	<div class="modal-body" style='height:60%;overflow:auto;'>
				<div class='errorMessage'></div>
		<form id="viewsubfform">
			<table id='subfTable'  style="width:100%" class = "table table-hover table-condensed">
				<thead class='titleTr'>
					<tr>
						<th>Tag</th>
						<th>Indi1</th>
						<th>Indi2</th>
						<th>Data</th>
						<th>
							<div class="btn-group pull-right">
								<button type="button" data-toggle="dropdown" class="btn btn-default dropdown-toggle">Action <span class="caret"></span></button>
								<ul class="dropdown-menu menu">
									<li>
										<a id='retrieveLink' data-toggle='modal' data-target='#termSearch' href='Term_Search?tag=<%=tag%>'>
											Term Search
										</a>
									</li>
								</ul>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr class="headingTr">
						<td id="tagValue"><%=tag %></td>
							<td><input size='1'  type='text' class='indiValue1 indiValues1' data-value= "indi<%=values %>" data-tag='<%=tag %>' data-indilvl='1' name="indi1_<%=lvl1id%>"onkeyup='updateIndi(this)' id='indi1' value='<%=indi1 %>'></td>
						<td><input size='1' type='text' class='indiValue2 indiValues2' data-indilvl='2' data-tag='<%=tag %>' onkeyup='updateIndi(this)' name="indi2_<%=lvl2id%>" id='indi2' value='<%=indi2 %>'><a id='showIndi' title='Indicator Help' data-id='<%=tag %>'>&nbsp;<span class='glyphicon glyphicon-search'></span></a></td>
						<td><%=tagdesc %></td>
						<td></td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td colspan="2">
							<input class="concatData" name='tag_<%=tagid%>'  data-autflag='<%=autflag %>'onfocus='getDesc(this);getData(this)' type='text' size="99" style="width:100%" value='<%=data%>'>
						</td>
					</tr>
			
				</tbody>
			</table>
		</form>
	</div> 
	<div class="modal-footer">

	<div style='text-align:center'id='showSubf'></div>
		<a class="btn btn-info reset">Reset</a>
			
		<a class='btn btn-info' id='saveCtrlTag' data-id="<%=tag%>" data-dismiss="modal">
			Close
		</a>

	</div>
	
	<div class="modal fade" id="termSearch" tabindex="-1" role="dialog" aria-labelledby="termSearch" data-backdrop="static">
	    <div class="modal-dialog" role="document">
			  <div class="modal-content">
			  <!-- Remote content load here -->
			  </div>
		</div>
	</div>
