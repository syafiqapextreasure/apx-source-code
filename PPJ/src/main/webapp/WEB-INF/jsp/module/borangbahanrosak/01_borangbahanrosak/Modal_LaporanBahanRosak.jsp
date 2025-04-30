<div class="modal-header">
							<h5 class="modal-title" id="modalName" align="center">form</h5>
					    	<button type="button" id="closeModalAdd" class="close" data-dismiss="modal" aria-label="Close">
					        	<span aria-hidden="true">&times;</span>
					        </button>
						</div>
						<form role="form" class="form-horizontal" id="formbahanrosak"  enctype="multipart/form-data">
						<br>
							
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<label>No Perolehan</label>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="rosaknoperolehan" name="rosaknoperolehan" disabled>
									</div>
							</div>
							
							
							<div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<label>Judul</label>
									</div>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="rosakjudul" name="rosakjudul" disabled>
									</div>
							</div>
							
							
							

							
							 <div class="form-group">
								<div class="col-sm-1"></div>
									<div class="col-sm-2"><label>Butiran Kerosakan</label></div>
										<div class='col-sm-7'>
											<textarea class="form-control" rows="3" id="rosakcatatan" name="rosakcatatan"></textarea>
										</div>
							</div>
							
							<!--<div class="form-group">
								<div class="col-sm-1"></div>
								<div class="col-sm-2"><label>Sila Lampirkan Bukti Kerosakan dalam bentuk gambar</label></div>
									<div class='col-sm-5'>
										<input type="file" class="form-control pickerosakan" id="pickerosakan" name="pickerosakan" accept=".jpg, .png"/>  																	
									</div>
							</div> -->
							
							
							
							<!-- END TAB CONTENT -->
							<div class="modal-footer">
							      	<button type="submit" id="save" class="btn btn-primary save">Lapor</button>
							        <button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Batal</button>
						        
							</div>	
						</form>
					<!-- END Modal content-->