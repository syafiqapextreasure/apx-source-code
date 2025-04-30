

<div class="modal-header">
	<h5 class="modal-title" id="modalName" align="center">form</h5>
	<button type="button" id="closeModalAddHilang" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<div class="modal-body">
  <form role="form" class="form-horizontal" id="formlaporbahanhilang" method="post">
    <div class="padding-15" id="patronSearchPanel123">
        <div class="row pwidth-100">
            <div class="col-xs-12">
                <div class="margin-btm-5"></div>
                <div class="form-horizontal">
                    <div class="form-group">
    
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>No Perolehan</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="hilangnoperolehan" name="hilangnoperolehan" disabled>
							</div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Judul</label>
                            </div>
                            <div class="col-sm-8">
                            	<textarea id="hilangjudul" name="hilangjudul" rows="3" cols="65" disabled>
								</textarea>
								<!-- <input type="text" class="form-control" id="hilangjudul" name="hilangjudul" disabled> -->
							</div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Tarikh Pinjaman</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="hilangtarikhpinjam" name="hilangtarikhpinjam" disabled>
							</div>
                        </div>
      					 <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Masa Pinjaman</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="hilangmasapinjam" name="hilangmasapinjam" disabled>
							</div>
                        </div>
						<div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Tarikh Tamat Tempoh</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="hilangtarikhpulang" name="hilangtarikhpulang" disabled>
							</div>
                        </div>
                        <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Kos Buku ** (RM)
                            	<p style="color: red; font-size: small;">Tidak termasuk caj perkhidmatan dan caj denda lewat</p>
                            	</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="hilanghargabuku" name="hilanghargabuku" disabled>
							</div>
                   		</div>
                   		
                   		<div class="col-md-12" style="margin-bottom: 5px;">
                   		</div>
                   		<div class="col-md-12" style="margin-bottom: 5px;">
                   		</div>
                   		
                   		<div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-12">
                            	<label class="errmsg text-primary"></label>
                            </div>
                   		</div>
						<!-- <div class="col-md-12" style="margin-bottom: 5px;">
                            <div class="col-md-3">
                            	<label>Masa Tamat Tempoh</label>
                            </div>
                            <div class="col-sm-4">
								<input type="text" class="form-control" id="hilangmasapulang" name="hilangmasapulang" disabled>
							</div>
                        </div> -->
                    </div>
                    

                </div>
               
            </div>
        </div>
        <div class="margin-btm-15"></div>
        <div class="form-group modal-footer">
	       	<button type="button" id="save" class="btn btn-primary save">Lapor</button>
			<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal">Batal</button>
		</div>
    </div>
    </form>
</div>