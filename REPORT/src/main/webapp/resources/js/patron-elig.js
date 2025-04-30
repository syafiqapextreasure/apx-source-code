/* http://bootsnipp.com/snippets/featured/form-wizard-using-tabs */
/* http://stackoverflow.com/questions/12449890/reload-content-in-modal-twitter-bootstrap */

/*$('.modal:not(#searchModal):not([id$="Search"])').on("hidden.bs.modal",
		function() {
			debugger;
			window.location.reload();
			$(".modal-content").html("");
		});
*/

$(document).ready(function() {

					if ($('#eligTable')
							&& $('#eligTable').dataTable().fnSettings().aoData.length === 0) {
						$
								.get(
										'CheckRecords',
										function(responseText) {
											//alert("fgd" +responseText.trim());
											debugger;
											///alert(responseText.trim()+"err")
											if(responseText.trim()>=100){
												swal({   
													  text: 'Click Yes to retrieve all or clik No to retrieve first 100 records.',
													  showCancelButton: true,
													  confirmButtonText: 'Yes',
													  cancelButtonText: 'No',
													}).then(function() {
														window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=Y'
													},function(dismiss) {
														if (dismiss === 'cancel') {
															window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=N'
														 }	else{
															 window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=N'
														 }
												});
											}else{
												//alert("ddg");
												
											}
											/*if(responseText.trim()>=100){
												swal({   
													  text: 'Click Yes to retrieve all or clik No to retrieve first 100 records.',
													  showCancelButton: true,
													  confirmButtonText: 'Yes',
													  cancelButtonText: 'No',
													}).then(function() {
														window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=Y'
													},function(dismiss) {
														if (dismiss === 'cancel') {
															window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=N'
														  }	
												});
											}else{
												window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=Y'
											}*/
											/*var currentURL = document.URL;
											var params = currentURL.extract();
											alert(params.result); 
											alert(modal);
											var modal = responseText.replace(
													/\s+/g, '');
											if (modal === "none") {
												window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=""';
												// return false;
											} else if (modal === "need") {
											
												debugger;
												$('#elig_modal').click();
											}*/
										});
					}/*else{
						window.location.href = 'template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp&result=Y'
					}*/

					// Wizard
					$('a[data-toggle="tab"]').on('show.bs.tab', function(e) {

						var $target = $(e.target);

						if ($target.parent().hasClass('disabled')) {
							return false;
						}
					});

					$(".next-step").click(function(e) {

						var $active = $('.wizard .nav-tabs li.active');
						$active.next().removeClass('disabled');
						nextTab($active);

					});

					$(".next-step1")
							.click(
									function(e) {
										debugger;
										var suppress = false;

										if ($('.tickCate:checkbox:checked').length == 0
												|| $('.tickItem:checkbox:checked').length == 0
												|| $('.tickSmd:checkbox:checked').length == 0
												|| $('.tickBranch:checkbox:checked').length == 0)
											suppress = true;

										clearTable("indicatorTable");

										if (suppress) {
											swal('Please ensure all categories have been selected.');
											return false;
										} else {
											var $active = $('.wizard .nav-tabs li.active');
											$active.next().removeClass(
													'disabled');
											nextTab($active);
											populateIndTable();
										}
									});

					$(".next-step2")
							.click(
									function(e) {
										debugger;
										var allowNextTab = true;
										if ($('#indicatorTable tbody tr:not(.red-inline)').length) {
											$(
													'#loanPeriod,#overdueGP1,#overdueGP2,#overdueGP3,#numElig,#numRenewal')
													.each(
															function() {
																if (!$.trim($(
																		this)
																		.val())) {
																	swal("Please ensure all fields inserted.");
																	allowNextTab = false;
																	return false;
																}
															});
										} else {
											swal("Patron eligibility inserted already exist.")
											return false;
										}

										clearTable("finalTable");

										if (allowNextTab) {
											populateFinalTable();
											var $active = $('.wizard .nav-tabs li.active');
											$active.next().removeClass(
													'disabled');
											nextTab($active);
										}
									});

					$(".prev-step").click(function(e) {

						var $active = $('.wizard .nav-tabs li.active');
						prevTab($active);
					});
					
					$('body').on('hidden.bs.modal', '.modal', function () {
						  $(this).removeData('bs.modal');
						}); 
				});

function nextTab(elem) {
	$(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
	$(elem).prev().find('a[data-toggle="tab"]').click();
}

/*
 * https://stackoverflow.com/questions/14502156/get-the-id-of-the-tab-div-being-activated
 */
$(function() {
	$("#infoTab")
			.tabs(
					{
						beforeActivate : function(event, ui) {
							debugger;

							var old = ui.oldPanel.selector;
							var clickNew = ui.newPanel.selector;

							if (old === "#tab1") {
								var valid = true;

								if ($('td[id^="GL38RATE"]:not([id^="GL38RATE1"]').length) {
									// only GL38RATE1 can have 0.00
									$(
											'td[id^="GL38RATE"]:not([id^="GL38RATE1"]')
											.each(
													function(index) {
														debugger;
														var num = index + 1;
														if (num % 2 != 0) {
															document
																	.getElementById(this.id).style.background = "";
														} else {
															document
																	.getElementById(this.id).style.background = "#f9f9f9";
														}

														if (this.innerText === "0.00") {
															document
																	.getElementById(this.id).style.background = "#F27A60";
															valid = false;
														}
													});
								}

								if (!$('#finesTable tbody tr:nth-child(1)')
										.find('td[id^="GL38RATE"]:empty').length
										&& !$.trim($('#maxFines').val())) {
									debugger;
									if (!$("#tab1").hasClass("complete")) {
										$("#tab1").removeClass("complete");
									}
									event.preventDefault();
									swal("Please ensure Maximum fines for late return value inserted.");
								}

								if ($('#finesTable tbody tr:nth-child(1)')
										.find('td[id^="GL38RATE"]:empty').length
										&& !$.trim($('#maxFines').val())) {
									debugger;
									$('#maxFines').val("0.00");
								}

								if (!valid) {
									if ($("#tab1").hasClass("complete")) {
										$("#tab1").removeClass("complete");
									}
									event.preventDefault();
									swal("Please ensure the rate in fines schedule properly inserted.");
								} else {
									debugger;
									if (!$("#tab1").hasClass("complete")) {
										$("#tab1").addClass("complete");
									}
								}
							} else if (old === "#tab2") {
								var valid = true;

								$("#lostFee,#timesCost")
										.each(
												function() {
													if (!$.trim($(this).val())) {
														if ($("#tab2")
																.hasClass(
																		"complete")) {
															$("#tab2")
																	.removeClass(
																			"complete");
														}
														valid = false;
														event.preventDefault();
														swal("Please ensure all fields inserted.");
														return false;
													}
												});

								if (valid) {
									if (!$("#tab2").hasClass("complete")) {
										$("#tab2").addClass("complete");
									}
								}

							} else if (old === "#tab3") {
								$("#finesGP1,#finesGP2,#finesGP3").each(function() {
									if (!$.trim($(this).val())) {
										if ($("#tab3").hasClass("complete")) {
											$("#tab3").removeClass("complete");
										}
										valid = false;
										event.preventDefault();
										swal("Please ensure all fields inserted.");
										return false;
									}
								});

								if (valid) {
									if (!$("#tab3").hasClass("complete")) {
										$("#tab3").addClass("complete");
									}
								}
							}
						}
					});
});

function checkGracePeriod() {
	var valid = true;

	$("#finesGP1,#finesGP2,#finesGP3").each(function() {
		if (!$.trim($(this).val())) {
			if ($("#tab3").hasClass("complete")) {
				$("#tab3").removeClass("complete");
			}
			valid = false;
			event.preventDefault();
			swal("Please ensure all fields inserted.");
		}
	});

	if (valid) {
		if (!$("#tab3").hasClass("complete")) {
			$("#tab3").addClass("complete");
		}
	}
}

$('input[name=inFinesOptions]').on('change', function() {
	var includeFine = $("input[name=inFinesOptions]:checked").val();

	if (includeFine === "N") {
		$('#lostFee').val("0.00");
		$('#timesCost').val("0");
	} else {
		$('#lostFee').val("");
		$('#timesCost').val("1");
	}
});

/* jQuery for select all checkbox for each category */
$('input[id^="selectAll"]').change(function() {
	debugger;
	if ($(this)[0].id === "selectAll1") {
		$('.tickCate').prop('checked', $(this).prop("checked"));
	} else if ($(this)[0].id === "selectAll2") {
		$('.tickItem').prop('checked', $(this).prop("checked"));
	} else if ($(this)[0].id === "selectAll3") {
		$('.tickSmd').prop('checked', $(this).prop("checked"));
	} else {
		$('.tickBranch').prop('checked', $(this).prop("checked"));
	}
});

$(
		"input[type='number']:not([placeholder]):not([id='#loanPeriod']):not([id='#numElig'])")
		.keydown(function(e) {
			if (e.key == 'e' || e.key == '.' || e.key == '-')
				return false;
		});

$('#loanPeriod').keyup(function(e) {
	if (this.value.substring(0, 1) == "0") {
		this.value = this.value.replace(/^0+/g, '');
		return false;
	} else if (e.key == 'e' || e.key == '.' || e.key == '-') {
		return false;
	}
});

$('#numElig').keyup(function(e) {
	if (this.value.substring(0, 1) == "0") {
		this.value = this.value.replace(/^0+/g, '');
		return false;
	} else if (e.key == 'e' || e.key == '.' || e.key == '-') {
		return false;
	}
	maxLengthCheck(this);
});

function maxLengthCheck(obj) {
	if (obj.value.length > obj.max.length)
		obj.value = obj.value.slice(0, obj.max.length)
}

$('.clear').click(function() {
	debugger;
	$('#finesTable tr').slice(2).remove();
	document.getElementById("GL38START1").textContent = '';
	document.getElementById("GL38STOP1").textContent = '';
	document.getElementById("GL38RATE1").textContent = '';
	document.getElementById("calcFine1").textContent = '';
	document.getElementById("totalFines1").textContent = '';
	$("#regeneratefine").val("Y");
})

$('#finesTable tbody').on('keyup', 'td', function() {
	calcFinesSchedule(this);
});

function calcFinesSchedule(e) {
	clearTimeout(window.updatetimer);
	window.updatetimer = setTimeout(
			// can later change into servlet and use .get AJAX
			function() {
				debugger;
				if (e.id.startsWith("GL38RATE")) {
					decimalTwoPlaces(e);
				}

				var elTable = document.getElementById("finesTable");
				var row = e.parentNode.rowIndex;
				var elRow = elTable.rows[row];
				var elLateFrom = parseInt(elRow.cells[1].textContent);
				var elLateTo = parseInt(elRow.cells[2].textContent);
				var elRate = parseFloat(elRow.cells[3].textContent);
				var elTableLength = elTable.rows.length;

				if (!isNaN(elLateFrom) && !isNaN(elLateTo)) {
					var clearRow;

					if (elLateFrom > elLateTo) {
						clearRow = elRow;
						if (e.id.startsWith("GL38START")) {
							elLateFrom = Number.NaN;
						} else if (e.id.startsWith("GL38STOP")) {
							elLateTo = Number.NaN;
						}
					} else if (row !== elTableLength - 1) {
						var nextRow = elTable.rows[row + 1];
						var elNextLateFrom = parseInt(nextRow.cells[1].textContent);
						if (elNextLateFrom <= elLateTo) {
							nextRow.cells[1].textContent = elLateTo + 1;
							var elNextLateTo = parseInt(nextRow.cells[2].textContent);
							if (!isNaN(elNextLateTo)
									&& elNextLateFrom <= elNextLateTo) {
								clearRow = nextRow;
							}
						} else if (elNextLateFrom > elLateTo + 1) {
							nextRow.cells[1].textContent = elLateTo + 1;
						}
					}

					if (typeof clearRow !== "undefined") {
						swal('Column Late To must greater than Column Late From.');
						clearRow.cells[2].textContent = '';
						clearRow.cells[4].textContent = '';
						clearRow.cells[5].textContent = '';
					}
				}

				if (!isNaN(elLateTo) && !isNaN(elLateFrom) && !isNaN(elRate)) { // ensure
					/*
					 * data exist before calculate
					 */
					var currRowFine = 0.00;
					var totalFines = 0.00;

					elRow.cells[4].textContent = ''; // clear the total fine
					// for this range
					elRow.cells[5].textContent = ''; // clear accumulated
					// fine for this range

					if (elLateTo > elLateFrom) {
						currRowFine = (elLateTo - elLateFrom + 1) * elRate;
					} else if (elLateTo === elLateFrom) {
						currRowFine = 0.00;
					}

					for (var i = 1; i < elTableLength; i++) {
						if (i === row) {
							/*
							 * change range effect it current and next
							 * accumulated fines only so can grab previous range
							 * accumulated fines
							 */
							totalFines = (i === 1) ? 0
									: parseFloat(elTable.rows[i - 1].cells[5].textContent);
							/*
							 * for row index 1 the previous row is the header
							 */
							totalFines += currRowFine;
							elRow.cells[4].textContent = currRowFine;
							/*
							 * set new total fine for this range
							 */
							elRow.cells[5].textContent = totalFines.toFixed(2);
							/*
							 * set new accumulated fine for this range
							 */
						} else if (i > row) {
							if (elTable.rows[i].cells[4].innerHTML) {
								totalFines += parseFloat(elTable.rows[i].cells[4].textContent);
								/*
								 * retrieve existing total fine since there is
								 * no changes on rate nor range occurred
								 */
								elTable.rows[i].cells[5].textContent = totalFines
										.toFixed(2);
							}
						}
					}

					var currRow = elTableLength - 1 === 0 ? 1
							: elTableLength - 1;
					var nextLateFrom = elLateTo + 1;

					if (document.getElementById("totalFines" + currRow).textContent != '') {
						var newRowMarkup = $('<tr class="rowId"><td class="rowNo">'
								+ elTableLength
								+ '</td><td id="GL38START'
								+ elTableLength
								+ '">'
								+ nextLateFrom
								+ '</td><td contenteditable="true" id="GL38STOP'
								+ elTableLength
								+ '"></td><td contenteditable="true" id="GL38RATE'
								+ elTableLength
								+ '"><td class="hidden" id="calcFine'
								+ elTableLength
								+ '"><td id="totalFines'
								+ elTableLength + '"></td></tr>');
						newRowMarkup.insertAfter('#finesTable tbody>tr:last');
					}
				}
			}, 600); // delay after user types
}

$("#maxFines,#lostFee").on('keyup', function() {
	decimalTwoPlaces(this)
});

function decimalTwoPlaces(e) {
	clearTimeout(window.updatetimer);
	window.updatetimer = setTimeout(function() {
		/*
		 * since this function is share so it should encounter for both html and
		 * direct .val() value retriever
		 */
		var val = typeof e.value === "undefined" ? e.textContent != null ? $
				.trim(e.textContent) : "" : $.trim(e.value);
		if (val.indexOf(',') > -1) {
			val = val.replace(',', '.');
		}
		var num = parseFloat(val).toFixed(2);
		if (isNaN(num)) {
			num = '';
		}
		typeof e.value === "undefined" ? e.textContent = num : e.value = num;
	}, 600);
};

function clearTable(name) {
	debugger;
	$("#" + name + " tbody").remove();
}

function populateIndTable() {
	debugger;
	$('#loading').show();

	var tickCateList = [];
	var tickItemList = [];
	var tickSmdList = [];
	var tickBranchList = [];

	$('.tickCate:checkbox:checked').map(function(_, el) {
		tickCateList.push($(el).val());
	}).get();
	$('.tickItem:checkbox:checked').map(function(_, el) {
		tickItemList.push($(el).val());
	}).get();
	$('.tickSmd:checkbox:checked').map(function(_, el) {
		tickSmdList.push($(el).val());
	}).get();
	$('.tickBranch:checkbox:checked').map(function(_, el) {
		tickBranchList.push($(el).val());
	}).get();

	$.get('CheckIndicator', {
		patCatList : tickCateList,
		iCatList : tickItemList,
		smdList : tickSmdList,
		branchList : tickBranchList
	}, function(responseText) {
		$('#loading').hide();
		var indTable = document.getElementById("indicatorTable");
		var elTbody = document.createElement('tbody');
		elTbody.innerHTML = responseText;
		indTable.appendChild(elTbody);
	});
}

function populateFinalTable() {
	var loanPeriod = $('#loanPeriod').val();
	var loanType = $("input[name=loanTypeOptions]:checked").val() === 'D' ? "day(s)"
			: "hour(s)";
	var numElig = $('#numElig').val();
	var numRenewal = $('#numRenewal').val();
	var overdueGP1 = $('#overdueGP1').val();
	var overdueGP2 = $('#overdueGP2').val();
	var overdueGP3 = $('#overdueGP3').val();
	var allowOverdue = $("input[name=doOverdueOptions]:checked").val();
	var allowReserve = $("input[name=doReserveOptions]:checked").val();

	var elTbody = document.createElement('tbody');

	$('#indicatorTable tbody tr:not(.red-inline)')
			.each(
					function(index) {
						debugger;
						var cate = $(this).closest("tr").find("#indPatronCat")
								.text();
						var icat = $(this).closest("tr").find("#indICat")
								.text();
						var smd = $(this).closest("tr").find("#indSmd").text();
						var brnc = $(this).closest("tr").find("#indBranch")
								.text();

						var elTR = document.createElement('tr');
						elTR.className = "clickable-row";

						var newHTML = '<td class="col10 col-font">'
								+ (index + 1)
								+ '</td><td class="col10 col-font" id="finalPatronCat">'
								+ cate.substring(0, cate.indexOf(","))
								+ '</td><td class="col10 col-font" id="finalICat">'
								+ icat.substring(0, icat.indexOf(","))
								+ '</td><td class="col10 col-font" id="finalSmd">'
								+ smd.substring(0, smd.indexOf(","))
								+ '</td><td class="col10 col-font" id="finalBranch">'
								+ brnc.substring(0, brnc.indexOf(","))
								+ '</td><td class="col10 col-font">'
								+ loanPeriod + ' ' + loanType
								+ '</td><td class="col10 col-font">' + numElig
								+ '</td><td class="col10 col-font">'
								+ numRenewal
								+ '</td><td class="col10 col-font">'
								+ allowOverdue
								+ '</td><td class="col10 col-font">'
								+ allowReserve + '</td></tr>';
						elTR.innerHTML = newHTML;
						elTbody.appendChild(elTR);
					})

	var finalTable = document.getElementById("finalTable");
	finalTable.appendChild(elTbody);

	$('.clickable-row').click(function() {
		debugger;
		var cate = $(this).closest("tr").find("#finalPatronCat").text();
		var icat = $(this).closest("tr").find("#finalICat").text();
		var smd = $(this).closest("tr").find("#finalSmd").text();
		var brnc = $(this).closest("tr").find("#finalBranch").text();

		document.getElementById("showCate").value = cate;
		document.getElementById("showItem").value = icat;
		document.getElementById("showSmd").value = smd;
		document.getElementById("showBranch").value = brnc;
	});
}

$(".finish")
		.on(
				"click",
				function(e) {
					debugger;
					var allowSubmit = true;
					var tabsToFill = "";
					/*
					 * need to check this in case if the Fines Grace Period
					 * directly clicked Finish (no tab checking function run)
					 */
					checkGracePeriod();

					$("div[id^='tab']")
							.each(
									function() {
										debugger;

										if (!$(this).hasClass("complete")) {

											allowSubmit = false;

											switch (this.id) {
											case "tab1":
												tabsToFill === "" ? tabsToFill += " Fines Schedule tab"
														: tabsToFill += ", Fines Schedule tab";
												break;
											case "tab2":
												tabsToFill === "" ? tabsToFill += " Lost Fee Setting tab"
														: tabsToFill += ", Lost Fee Setting tab";
												break;
											case "tab3":
												tabsToFill === "" ? tabsToFill += " Fines Grace Period tab"
														: tabsToFill += " and Fines Grace Period tab";
												break;
											}
										}
									});

					if (!allowSubmit) {
						e.preventDefault();
						swal('Please ensure' + tabsToFill
								+ ' fields properly inserted.');
					}
					if (allowSubmit) {
						var cateList = $('#finalTable tbody td:nth-child(2)')
								.map(function() {
									return ($(this).text());
								}).get();
						var icatList = $('#finalTable tbody td:nth-child(3)')
								.map(function() {
									return ($(this).text());
								}).get();
						var smdList = $('#finalTable tbody td:nth-child(4)')
								.map(function() {
									return ($(this).text());
								}).get();
						var branchList = $('#finalTable tbody td:nth-child(5)')
								.map(function() {
									return ($(this).text());
								}).get();

						var lateFromList = $(
								'#finesTable tbody td:nth-child(2)').map(
								function() {
									return ($(this).text());
								}).get();
						var lateToList = $('#finesTable tbody td:nth-child(3)')
								.map(function() {
									return ($(this).text());
								}).get();
						var rateList = $('#finesTable tbody td:nth-child(4)')
								.map(function() {
									return ($(this).text());
								}).get();
						var fineList = $('#finesTable tbody td:nth-child(6)')
								.map(function() {
									return ($(this).text());
								}).get();

						var loanPeriod = $('#loanPeriod').val();
						var eligNum = $('#numElig').val();
						var renewNum = $('#numRenewal').val();
						var oGracePeriod1 = $('#overdueGP1').val();
						var oGracePeriod2 = $('#overdueGP2').val();
						var oGracePeriod3 = $('#overdueGP3').val();
						var maxFines = $('#maxFines').val();
						var lostFeeProcess = $('#lostFee').val();
						var fGracePeriod1 = $('#finesGP1').val();
						var fGracePeriod2 = $('#finesGP2').val();
						var fGracePeriod3 = $('#finesGP3').val();
						var timesCost = $('#timesCost').val();
						var loanType = $("input[name=loanTypeOptions]:checked")
								.val()
						var overdue = $("input[name=doOverdueOptions]:checked")
								.val();
						var reserve = $("input[name=doReserveOptions]:checked")
								.val();
						var includeFine = $(
								"input[name=inFinesOptions]:checked").val();
						var recby = $("#liferayLogin").val();

						/*$.get('AddEligibility', {
							cates : cateList,
							icats : icatList,
							smds : smdList,
							brnchs : branchList,
							periodOfLoan : loanPeriod,
							eligibilityNum : eligNum,
							typeOfLoan : loanType,
							renewalNum : renewNum,
							overGP1 : oGracePeriod1,
							overGP2 : oGracePeriod2,
							overGP3 : oGracePeriod3,
							maxFines : maxFines,
							inFine : includeFine,
							doOverdue : overdue,
							timesCostBy : timesCost,
							lostFee : lostFeeProcess,
							fineGP1 : fGracePeriod1,
							fineGP2 : fGracePeriod2,
							fineGP3 : fGracePeriod3,
							doReserve : reserve,
							lateFroms : lateFromList,
							lateTos : lateToList,
							rates : rateList,
							fines : fineList,
							recby : recby
						}, function(responseText) {
							swal("Successfully Added");
						});*/
					
						/*alert("Start");
						alert("cateList" +cateList);
						alert("icatList" +icatList);
						alert("smdList" +smdList);
						alert("branchList" +branchList);
						alert("loanPeriod" +loanPeriod);
						alert("eligNum" +eligNum);
						alert("loanType" +loanType);
						alert("renewNum" +renewNum);
						alert("oGracePeriod1" +oGracePeriod1);
						alert("oGracePeriod2" +oGracePeriod2);
						alert("oGracePeriod3" +oGracePeriod3);
						alert("maxFines" +maxFines);
						alert("includeFine" +includeFine);
						alert("overdue" +overdue);
						alert("timesCost" +timesCost);
						alert("lostFeeProcess" +lostFeeProcess);
						alert("fGracePeriod1" +fGracePeriod1);
						alert("fGracePeriod2" +fGracePeriod2);
						alert("fGracePeriod3" +fGracePeriod3);
						alert("reserve" +reserve);
						alert("lateFromList" +lateFromList);
						alert("rateList" +rateList);
						alert("fineList" +fineList);
						alert("recby" +recby);
						alert("END");*/
						
						$.get('AddEligibility', {
							cates : cateList,
							icats : icatList,
							smds : smdList,
							brnchs : branchList,
							periodOfLoan : loanPeriod,
							eligibilityNum : eligNum,
							typeOfLoan : loanType,
							renewalNum : renewNum,
							overGP1 : oGracePeriod1,
							overGP2 : oGracePeriod2,
							overGP3 : oGracePeriod3,
							maxFines : maxFines,
							inFine : includeFine,
							doOverdue : overdue,
							timesCostBy : timesCost,
							lostFee : lostFeeProcess,
							fineGP1 : fGracePeriod1,
							fineGP2 : fGracePeriod2,
							fineGP3 : fGracePeriod3,
							doReserve : reserve,
							lateFroms : lateFromList,
							lateTos : lateToList,
							rates : rateList,
							fines : fineList,
							recby : recby
						}, function(responseText) {
							//alert("Added");
							swal("Successfully Added");
							/*setTimeout(function(){
								location.reload();
						    }, 2000);*/
						});
					}
				});

$(".edit").on("click", function() {
	debugger;
	var cate = $('#category').val();
	var icat = $('#itemCategory').val();
	var smd = $('#smdCode').val();
	var branch = $('#branch').val();

	var lateFromList = $('#finesTable tbody td:nth-child(2)').map(function() {
		return ($(this).text());
	}).get();
	var lateToList = $('#finesTable tbody td:nth-child(3)').map(function() {
		return ($(this).text());
	}).get();
	var rateList = $('#finesTable tbody td:nth-child(4)').map(function() {
		return ($(this).text());
	}).get();
	var fineList = $('#finesTable tbody td:nth-child(6)').map(function() {
		return ($(this).text());
	}).get();
	
	if(rateList.length >= 1){
		$("#regeneratefine").val("Y");
	}else{
		$("#regeneratefine").val("N");
	}

	var loanPeriod = $('#loanPeriod').val();
	var eligNum = $('#numElig').val();
	var renewNum = $('#numRenewal').val();
	var oGracePeriod1 = $('#overdueGP1').val();
	var oGracePeriod2 = $('#overdueGP2').val();
	var oGracePeriod3 = $('#overdueGP3').val();
	var maxFines = $('#maxFines').val();
	var lostFeeProcess = $('#lostFee').val();
	var fGracePeriod1 = $('#finesGP1').val();
	var fGracePeriod2 = $('#finesGP2').val();
	var fGracePeriod3 = $('#finesGP3').val();
	var timesCost = $('#timesCost').val();
	var loanType = $("input[name=loanTypeOptions]:checked").val()
	var overdue = $("input[name=doOverdueOptions]:checked").val();
	var reserve = $("input[name=doReserveOptions]:checked").val();
	var includeFine = $("input[name=inFinesOptions]:checked").val();
	var regeneratefine = $("#regeneratefine").val();
	
	var recby = $("#liferayLogin").val();

	/*alert("edit phase");
	alert("category = " + cate + ", itemCategory = " + icat + ", smd = " + smd + ", branch = " + branch);
	alert("lateFrom = " + lateFromList + ", lateTo = " + lateToList + ", rate = " + lateToList + ", fine = " + fineList);
	alert("loanPeriod = " + loanPeriod + ", elig = " + eligNum + ", renew = " + renewNum + ", overdue1 = " + oGracePeriod1);
	alert("overdue2 = " + oGracePeriod2 + ", overdue3 = " + oGracePeriod3 + ", lostFee = " + lostFeeProcess + ", finesGP1 = " + fGracePeriod1);
	alert("finesGP2 = " + fGracePeriod2 + ", finesGP13 = " + fGracePeriod3 + ", timesCost = " + timesCost + ", loanType = " + loanType);
	alert("overdue = " + overdue + ", reserve = " + reserve + ", includeFine = " + includeFine + " ,maxFines" + maxFines);*/
	
	/*$.get('DeleteEligibility', {
		cates : cate,
		icats : icat,
		smds : smd,
		brnchs : branch,
	}, function(responseText) {
		$('#ajaxResponse').text(responseText);
	});
*/
	$.get('EditEligibility', {
		category : cate,
		icat : icat,
		smd : smd,
		branch : branch,
		cates : [ cate ],
		icats : [ icat ],
		smds : [ smd ],
		brnchs : [ branch ],
		periodOfLoan : loanPeriod,
		eligibilityNum : eligNum,
		typeOfLoan : loanType,
		renewalNum : renewNum,
		overGP1 : oGracePeriod1,
		overGP2 : oGracePeriod2,
		overGP3 : oGracePeriod3,
		maxFines : maxFines,
		inFine : includeFine,
		doOverdue : overdue,
		timesCostBy : timesCost,
		lostFee : lostFeeProcess,
		fineGP1 : fGracePeriod1,
		fineGP2 : fGracePeriod2,
		fineGP3 : fGracePeriod3,
		doReserve : reserve,
		lateFroms : lateFromList,
		lateTos : lateToList,
		rates : rateList,
		fines : fineList,
		regeneratefine : regeneratefine,
		recby : recby
	}, function(responseText) {
		
		//$('#ajaxResponse').text(responseText);
		//$('#eligTable').reload();
		 //$('#eligTable').html(responseText);
		swal("Successfully Updated");
		setTimeout(function(){
			location.reload();
	    }, 2000);	
		/*setTimeout(function () {
			location.reload();
		});}, 1000);*/
		
		//alert("sss");
		//$('#eligTable').DataTable().ajax.reload();
		/*var table = $('#eligTable').DataTable();
		table.ajax.reload()*/
	});
});
