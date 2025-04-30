<%-- <%@ page import="com.ilmu.foundation.global.*,com.ilmu.foundation.CurrencyCode.*, java.util.List" %> --%>
					
<!-- Include Required Prerequisites -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/acquisition/crddbtNote.js"></script>

<style>
	.modal-body {
		max-height: 100%;
	    overflow-y: auto;
	}
</style>


<!-- Modal content-->	
		      <div class="modal-header">
		        <h5 class="modal-title" id="modalName" align="center">New messagefff</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form role="form" class="form-horizontal">
		          <div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3">
						<label>Note Type</label> 
					</div>
					<div class="col-sm-4">
					<select class="form-control" id="noteType" name="noteType" onchange="updateNoteTypePlaceholder()">
						<option value="C" selected>Credit Note</option>
						<option value="D">Debit Note</option>
					</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3">
						<label for="noteNo">Note No</label>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="note_no" id="note_no" placeholder="Credit Note" required>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3"><label>Vendor</label></div>
					<div class="col-sm-3">
					<input type="text" class="form-control" id="vendor" name="vendor" onblur="updateVendorName()"
							onkeyup="updateVendorNameWithClear()" placeholder="Vendor Code" required>						
					</div>			  
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" id="search_vendor_btn" data-toggle="modal" 
						data-target="#vendorSearch" href="../../../include/shared/Modal_Vendor.jsp">...</button>
					</div>
					<div class="col-sm-4">
						<div id="div-vendorName"></div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3"><label>Received Date</label></div>
					<div class="col-sm-3">
						<input class="datepicker form-control" type="text" id="received_date" name="received_date" readonly placeholder="Select Date" required>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3"><label>Currency</label></div>
					<div class="col-sm-5">
						<select class="form-control" id="currency" name="currency" onchange="updateCurrency();" onblur="updateLocalAmount();">

						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3"><label>Currency Rate</label></div>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="exchangeRate" readonly style="background: transparent; border: none;">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-8">
						<fieldset class="amount-border">
							<legend class="amount-border">Amount</legend>
							<div class="form-group">
								<div class="col-sm-1"><input type="radio" name="add-selection" value="foreignAmount" checked></div>
								<div class="col-sm-2"><label>Foreign</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control decimal validate-double" id="foreign-amount" name="foreign-amount" style="width:150px" onkeyup="updateLocalAmount()" required>						
								</div>	
							</div>
							<div class="form-group">
								<div class="col-sm-1"><input type="radio" name="add-selection" value="localAmount"></div>
								<div class="col-sm-2"><label>Local</label></div>
								<div class="col-sm-2">
									<input type="text" class="form-control decimal validate-double" id="local-amount" name="local-amount" style="width:150px" readonly onkeyup="updateForeignAmount()" required>						
								</div>
							</div>
						</fieldset>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-3"><label>Remark</label></div>
					<div class="col-sm-5">
						<textarea class="form-control" style="resize:none" rows="5" id="remark" name="remark"></textarea>
					</div>
				</div>
		        </form>
		      </div>
		      <div class="modal-footer">
		      	<button type="button" class="btn btn-primary">Save</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
		        
		      </div>
<!-- End Modal content -->