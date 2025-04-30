function getValue(cate_type) {
	var str = cate_type.options[cate_type.selectedIndex].getAttribute('value');
	var e = document.getElementById("cate_type");
	var strUser = e.options[e.selectedIndex].value;
	document.getElementById("cate-id").value = strUser;

}
function resetForm() {
	document.getElementById("current_form").reset();
	selectCriterion();
}

// Send vendor info
function send_vendor_info() {
	// Hide the search form
	$('#search_vendor').collapse("hide");
	// Show the result form
	$('#result_vendor').collapse("show").height("auto");
	return false;
}

// Search vendor result from SearchVendor.jsp
$(document)
		.ready(
				function() {
					// Div collapse
					$("#search").click(function() {
						$("#search_vendor").collapse("show");
						$("#result_vendor").collapse("hide");
					});

					$("#result").click(function() {
						$("#search_vendor").collapse("hide");
						$("#result_vendor").collapse("show");
					});

					$('#btn_submit')
							.click(
									function() {
										ticks = [];
										debugger;
										searchType = $(
												"input[name=criteriaOptions]:checked")
												.val()

										if (searchType === "cate") {
											ticks = $(
													'input.categories[type=checkbox]:checked')
													.map(function(_, el) {
														return $(el).val();
													}).get();
										} else if (searchType === "icate") {
											ticks = $(
													'input.items[type=checkbox]:checked')
													.map(function(_, el) {
														return $(el).val();
													}).get();
										} else if (searchType === "smd") {
											ticks = $(
													'input.smds[type=checkbox]:checked')
													.map(function(_, el) {
														return $(el).val();
													}).get();
										} else if (searchType === "branch") {
											ticks = $(
													'input.branchs[type=checkbox]:checked')
													.map(function(_, el) {
														return $(el).val();
													}).get();
										}

										$.get("SearchEligCriteria", {
											criterierList : ticks,
											searchType : searchType
										}, function(data_vendor) {
											$("#display_vendor").html(
													data_vendor);
										});
									});

					$('input[name=criteriaOptions]').on("change", function() {
						selectCriterion();
					});

					$('.modal[id$="Search"]').on("hidden.bs.modal", function() {
						debugger;

						$.get("SearchEligCriteria", {
							criterierList : ticks,
							searchType : searchType
						}, function(data_vendor) {
							$("#display_vendor").html(data_vendor);
						});

						$(".modal-content").html("");
					});
				});

function selectCriterion() {
	var searchType = $("input[name=criteriaOptions]:checked").val();

	$.get("CriterionSelection", {
		type : searchType
	}, function(data_vendor) {
		$(".search-panel").html(data_vendor);
	});
}