
/**
 * Javascript for Modal_SearchReviewLst
 * 
 */
$(document).ready(function() {

		/////setting when open
		$("#datepickerseachreviewlist").hide();
		$("#criteriaseachreviewlist").attr('maxlength','10');
		$('input[name=radioOptionseachreviewlist][value=rListNo]').attr('checked', true); 
		
		/////////////////////////// on radio button change //////////////////////////////////////////
		$('input[name=radioOptionseachreviewlist]').change(function(){
			var value = $( 'input[name=radioOptionseachreviewlist]:checked' ).val();
		    //alert(value);
			$("#criteriaseachreviewlist").val('');
			
			if(value == "oDate"){
				$("#datepickerseachreviewlist").show();
				$("#criteriaseachreviewlist").hide();
				$("#criteriaseachreviewlist").attr('maxlength','5');
			}else if(value == "rListNo"){
				$("#datepickerseachreviewlist").hide();
				$("#criteriaseachreviewlist").show();
				$("#criteriaseachreviewlist").attr('maxlength','10');
			}else{
				$("#datepickerseachreviewlist").hide();
				$("#criteriaseachreviewlist").show();
			}
		});
		
		///////////////////// input-startDate set Current Date  ///////////////////////////////////
		var today = new Date();  
		$('#input-startDateseachreviewlist').datepicker({
			//startDate : today,
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
		});
		
		///////////////////// input-endDate set Current Date  ///////////////////////////////////
		
		$('#input-endDateseachreviewlist').val(moment(today).format("DD/MM/YYYY"));
		$('#input-endDateseachreviewlist').datepicker({
			format: "dd/mm/yyyy",
			todayBtn: true,
			autoclose: true,
			todayHighlight: true,
		});
		
		////////clear all input fields in bootstrap modal when close modal //////////////////////
		$('.modal').on('hidden.bs.modal', function () {
		    $(this).find('form').trigger('reset');
		    $("#datepickerseachreviewlist").hide();
			$("#criteriaseachreviewlist").show();
		    
		});
		
		///////////////////////WHEN CLICK SEARCH //////////////////////////////////////////////
		$("#btn-submit-searchreview").click(function(){
			var getChecked = $('input[name=radioOptionseachreviewlist]:checked').val();
			var getCriteria = $("#criteriaseachreviewlist").val();
			var inputStartDate = moment($("#input-startDateseachreviewlist").val(), 'DD/MM/YYYY').format("YYYYMMDD");
			var inputEndDate = moment($("#input-endDateseachreviewlist").val(), 'DD/MM/YYYY').format("YYYYMMDD"); 
			
			searchVal(getChecked, getCriteria, inputStartDate, inputEndDate);
		});
		
		///////////////////////////function pass value //////////////////////////////////////////
		function searchVal(getChecked, getCriteria, inputStartDate, inputEndDate){
			//loader("#table-reviewList");
			// Hide the search form
			$('#search_reviewlist').collapse("hide");
			// Show the result form
			$('#result_reviewlist').collapse("show");
			
			$('#table-reviewList').DataTable( {
				responsive: true,
				scrollx: true,
				info: false,
				paging: true,
				ordering: false,
				destroy: true,
				searching:false,
				info: true,
				lengthChange: false,
			    ajax: {
			        url: "GetsearchreviewlistAcq",
				    data:{
				    	getChecked : getChecked,
				    	getCriteria : getCriteria,
				    	inputStartDate : inputStartDate,
				    	inputEndDate : inputEndDate,
				    },
			        type: "GET",
			        dataSrc: function (json) {
			            var return_data = new Array();
			           
			            for(var i=0;i< json.length; i++){
			              return_data.push({
			                'Review List No': json[i].title2, 
			                'Action' : '<button id="btnSelectgetlistno"  data-value="'+json[i].title2+'" class="btn btn-sm btn-default" data-original-title="Select"><i class="fa fa-check"></i> Select</button>',
			              })
			            }
			            return return_data;
			          },
			     },//This is the end of the AJAX object from the example above
			     	columns    : [
						{'data': 'Review List No'},
						{'data': 'Action'}
					],
			});
		}
		/////get list number///
		$('#table-reviewList tbody').on('click', '#btnSelectgetlistno', function () {
			var listnumber = $(this).attr("data-value");
			
			$("#closeModalseacehreview").click();
			$("#reviewListNo").val(listnumber);
			$("#reviewListNo").focus();
		});
		
		
		
		
	});