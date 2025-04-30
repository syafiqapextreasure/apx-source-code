/**
 * 
 */

		$(document).ready(function () {
			alert("ddd");
		   $('#title_resultss').DataTable({
		       responsive: true
		   });
			$('.panel-collapse').on('shown.bs.collapse', function (e) {
				   alert($(e.currentTarget).find('.panel-body').find('.table')[0].id);
				    var tableIdToUpdate = $(e.currentTarget).find('.panel-body').find('.table')[0].id;
				    $('#' + tableIdToUpdate).DataTable().columns.adjust().responsive.recalc();
				});
	
		});



	