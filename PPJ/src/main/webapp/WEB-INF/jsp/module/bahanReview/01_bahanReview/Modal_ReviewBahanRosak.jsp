<%--  <script>
            // Function to increase image size
            function enlargeImg(img) {
                img.style.transform = "scale(1.5)";
                img.style.transition =
                  "transform 0.25s ease";
            }
        </script>
        
        
        <%
        String reportno = request.getParameter("reportno");
    	System.out.println("reportno"+reportno);
        %> --%>
        
     

<div class="modal-header">
	<h5 class="modal-title" id="modalName" align="center"><b>Damage Item Maintenance</b></h5>
	<button type="button" id="closeModalItemDamage" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="modal-body">
  <form role="form" class="form-horizontal" id="formreviewitemdamage" method="post">
    <div class="padding-15" id="patronSearchPanel123">
        <div class="row pwidth-100">
            <div class="col-xs-12">
                <div class="margin-btm-5"></div>
                <div class="form-horizontal">
                    <div class="form-group">
    
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>User Report No</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="reportnumber" name="reportnumber" disabled>
							</div>
                        </div>
                        
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Accession Number</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="damageaccno" name="damageaccno" disabled>
							</div>
                        </div>
                        
                        
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Title</label>
                            </div>
                            <!-- <div class="col-sm-4">
								<input type="text" class="form-control" id="damagetitle" name="damagetitle" disabled>
							</div> -->
							<div class='col-sm-7'>
									<textarea class="form-control" rows="3" id="damagetitle" name="damagetitle" disabled> </textarea>
							</div>
                        </div>
                        
                         <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Patron Id</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="damagepatronid" name="damagepatronid" disabled>
							</div>
                        </div>
                        
                        
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Patron Name</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="damagepatronname" name="damagepatronname" disabled>
							</div>
                        </div>
                        
                        
                      <div class="col-md-12" style="margin-bottom: 5px;">
							<div class="col-sm-3"><label>Detail Damage</label></div>
								<div class='col-sm-7'>
									<textarea class="form-control" rows="3" id="detaildamage" name="detaildamage" disabled> </textarea>
							</div>
					</div> 
					
					<input type="hidden" class="form-control" id="damagedatereport" name="damagedatereport" disabled>
					<input type="hidden" class="form-control" id="damagetimereport" name="damagetimereport" disabled>
                        
                    <%-- <div class="col-md-12" style="margin-bottom: 5px;">
                    	<div class="col-md-3">
                        	<label>Picture</label>
                     	</div>
                        <div class="col-sm-4 pictures">
							<div class="fileinput fileinput-new" data-provides="fileinput">
		  					<div class="fileinput-new thumbnail" style="width: 150px; height: 150px;">
		  						<img src="${ pageContext.servletContext.contextPath }/ItemReviewPhoto?repno=?reportno" style="width:250px" class="picdmge" onclick="enlargeImg(this)" />
		    				</div>
		  						<div class="fileinput-preview fileinput-exists thumbnail" style="width:auto"></div>
		  						
							</div>
							</div>
                   </div> --%>
                   
                   
                   <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Amount</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="damageamount" name="damageamount">
							</div>
                        </div>
                        
                        
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Staff Remark</label>
                            </div>
                            <div class='col-sm-7'>
											<textarea class="form-control" rows="3" id="rosakcatatanstafremarks" name="rosakcatatanstafremarks"></textarea>
										</div>
                        </div>
                        
      
      	

                    </div>
                    

                </div>
               
            </div>
        </div>
        <div class="margin-btm-15"></div>
        <div class="form-group modal-footer">
	       	<button type="submit" id="save" class="btn btn-primary save">Save</button>
			<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
		</div>
    </div>
    </form>
</div>



<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">              
      <div class="modal-body">
      
      
      
      	<button type="button" class="close closebigimg " ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
      
      	<img src="${pageContext.servletContext.contextPath }/ItemReviewPhoto?repno=?reportno" style="width: 100%;" class="picdmge" />
      
      </div>
    </div>
  </div>
</div>