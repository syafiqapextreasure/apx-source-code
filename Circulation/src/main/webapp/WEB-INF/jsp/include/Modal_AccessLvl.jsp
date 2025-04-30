<script>
$(document).ready(function(){
	$('#accessLvl').modal({
	    backdrop: 'static',
	    keyboard: false
	})
});
</script>
<style>
.modal {
  text-align: center;
  padding: 0!important;
}

.modal:before {
  content: '';
  display: inline-block;
  height: 100%;
  vertical-align: middle;
  margin-right: -4px;
}

.modal-dialog {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}
</style>
<!-- Modal -->
  <div class="modal fade" id="accessLvl" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body" style="text-align:center">
          <h4>The user does not permission to access this module</h4>
        </div>
      </div>
      
    </div>
  </div>