	
<%
		String trxn = request.getParameter("trxn");
		String due = request.getParameter("due");
		String action = request.getParameter("action");
		String code = request.getParameter("code");
		String distribution = request.getParameter("distribution");
		String docno = request.getParameter("docno");
		
		System.out.println("Trxn: " + trxn);
		System.out.println("distribution: " + distribution);
		System.out.println("docno: " + docno);
		if(action.equals("AddSelectedTrxn")){
	%>
	 <tr id='"<%=trxn%>"'>
	 <td style="display:none"><%=docno%></td>
	 	<td><%=trxn%></td><td><%=code%></td><td><%=due%></td>
		<td><a title='Edit' class='btn btn-default btn-sm' data-toggle='modal' onclick="editlist(this)">
			<i class='glyphicon glyphicon-pencil'></i>
			<span class='dist'><%=distribution%></span></a>
		</td>
		<td><button class='btn btn-warning btn-sm' onclick='deleteTrxn(this)' title='Delete'><i class='glyphicon glyphicon-trash'></i></button></td></tr>
	<%
		}else{
	%>
<div class="modal-body">
								<div>
									<div style="text-align:center">Distribution</div>
									<div style="text-align:center">Transaction#  <%=trxn %> |Due  <%=due %></div>
									<div style="text-align:center"><input type="text" class="form-control distribution" id="deduction" value="<%=distribution%>"></div>
								</div>
								</div>
								<div class="modal-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-info" onclick="changeDistribution(<%=trxn %>)">Save</button>
						<button type="button" class="btn btn-info" data-dismiss="modal">
							<span aria-hidden="true">Cancel</span>
						</button>
					</div>
				</div>
			</div>
	
		
	<%
		}
	%>