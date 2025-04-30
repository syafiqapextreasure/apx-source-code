$(function () {

	var $orderPanel = $("#orderPanel");
	var $orderSearchMode = $orderPanel.find("#orderSearchMode");
	var $orderSearchInput = $orderSearchMode.find("#orderSearchInput");
	var $orderSearchInput2 = $orderSearchMode.find("#orderSearchInput_2");
	var $orderSearchBtn = $orderSearchMode.find("button[action=search]");
	var $orderListingContainer = $orderPanel.find("#orderListingContainer");
	alert("kk");
	
	var orderListingActionUrl = $orderListingContainer.attr("action-url");
	
	var orderListingTitleActionUrl = $orderListingContainer.attr("action-title-url");
	var orderListingViewActionUrl = $orderListingContainer.attr("action-view-url");
	var orderListingEditActionUrl = $orderListingContainer.attr("action-edit-url");
	var orderListingTotalNoteActionUrl = $orderListingContainer.attr("action-total-notes-url");
	var viewNoteActionUrl = $orderListingContainer.attr("action-view-note-url");
	var viewLetterActionUrl = $orderListingContainer.attr("action-view-letter-url");

	var $addOrderBtn = $orderListingContainer.find("button[action=add-order]");
	$addOrderBtn.click(function () {
		$(this).blur();
		var actionUrl = $addOrderBtn.attr("action-url");
		showAcquisitionModalWithIframe(actionUrl, "Add Order");
	});

	var removeOrder = function (orderNumber) {
		swal.queue([{
					title: "Remove Order",
					text: "Do you wish to remove this order?",
					showCancelButton: true,
					confirmButtonText: "Remove",
					cancelButtonText: "Cancel",
					type: 'warning',
					showLoaderOnConfirm: true,
					preConfirm: function () {
						return new Promise(function (resolve, reject) {
							var data = {
								Search: orderNumber
							};

							$.ajax({
								type: "POST",
								url: orderListingActionUrl,
								data: JSON.stringify({
									operation: "Delete",
									data: data
								}),
								dataType: "json",
								contentType: "application/json"
							}).done(function (result) {
								if (result.Success) {
									if (result.Action == "MESSAGE") {
										common.showResultModelMessage(result);
										getOrderListing(1);
									}
								} else {
									common.showResultModelMessage(result);
								}
							});
						});
					}
				}
			])
	};

	var getOrderListing = function (pageNumber) {
alert(orderListingActionUrl);
		orderListingPaging.loading();

		var searchMode = $orderListingContainer.attr("action-search-mode");
		var searchText = $orderListingContainer.attr("action-search-text");
		var searchText2 = $orderListingContainer.attr("action-search-text-2");

		if (searchText == "" && searchText2 == "") {
			searchMode = "";
		}

		var data = {
			Search: searchText,
			Search2: searchText2,
			SearchMode: searchMode,
			PageNumber: pageNumber
		};

		$.ajax({
			type: "POST",
			url: orderListingActionUrl,
			data: JSON.stringify({
				operation: "List",
				data: data
			}),
			dataType: "json",
			contentType: "application/json"
		}).done(function (result) {
			if (result.Success) {
				if (result.Action == "MESSAGE") {
					common.showResultModelMessage(result);
					orderListingPaging.reset();
				} else if (result.Action == "PAGING") {
					orderListingPaging.execute(result.Data, function (pageNumber) {
						// pageChangeCallback
						getOrderListing(pageNumber)
					}, function () {
						// renderCompletedCallback
						var $tbody = $(orderListingPaging.elements.pagingBody);
						var $trs = $tbody.find("tr");

						var controlNumbers = [];
						var orderNumbers = [];

						$.each($trs, function (i, tr) {
							var isModifiable = $(tr).attr("is-modifiable") == "true";
							var isOrdered = $(tr).attr("is-ordered") == "true";
							var orderNumber = $(tr).attr("order-number");
							var referenceNumber = $(tr).attr("reference-number");
							var $addToCartBtn = $(tr).find("td button[action=add-to-cart]");

							if (!isModifiable) {
								$(tr).find("td button[action=edit],td button[action=delete]").remove();
								$addToCartBtn.remove();
							}

							if (!isOrdered) {
								$(tr).find("td button[action=view-letter]").remove();
							}

							$addToCartBtn.removeClass("display-none");

							$(tr).find("td button[action]").click(function (e) {
								var action = $(this).attr("action");

								$(this).blur();

								if (action == "view") {
									showAcquisitionModalWithIframe("{0}/{1}".format(orderListingViewActionUrl, orderNumber), "View Order (Order Number: {0})".format(orderNumber));
								} else if (action == "edit" && isModifiable) {
									showAcquisitionModalWithIframe("{0}/{1}".format(orderListingEditActionUrl, orderNumber), "Edit Order (Order Number: {0})".format(orderNumber));
								} else if (action == "delete" && isModifiable) {
									removeOrder(orderNumber);
								} else if (action == "view-note") {
									showModalWithIframe("{0}/{1}".format(viewNoteActionUrl, orderNumber), "Notes (Order Number: {0})".format(orderNumber));
								} else if (action == "view-letter" && isOrdered) {
									var viewLetterActionUrlFinal = "{0}/?reference-number={1}".format(viewLetterActionUrl, encodeURIComponent(referenceNumber));
									showModalWithIframe(viewLetterActionUrlFinal, "View Letter (Reference Number: {0})".format(referenceNumber));
								}

								e.stopPropagation();
							});

							controlNumbers.push($(tr).attr("control-number"));
							orderNumbers.push($(tr).attr("order-number"));
						});

						if (orderNumbers.length > 0) {
							//load total notes
							$.ajax({
								type: "POST",
								url: orderListingTotalNoteActionUrl,
								data: JSON.stringify({
									operation: "Get",
									data: {
										Items: orderNumbers
									}
								}),
								dataType: "json",
								contentType: "application/json"
							}).done(function (result) {
								if (result.Success) {
									if (result.Action == "ITEM") {
										$.each(result.Data, function (i, d) {
											var $ttlnote = $trs.filter("[order-number={0}]".format(d.orderNumber)).find("span.badge[view-note-number]");
											$ttlnote.html(d.totalNotes);
											$ttlnote.removeClass("display-none");

										});
									}
								} else {
									common.showResultModelMessage(result);
								}

							});
						}

						if (controlNumbers.length > 0) {

							//load title
							$.ajax({
								type: "POST",
								url: orderListingTitleActionUrl,
								data: JSON.stringify({
									operation: "Get",
									data: {
										Items: controlNumbers
									}
								}),
								dataType: "json",
								contentType: "application/json"
							}).done(function (result) {
								if (result.Success) {
									if (result.Action == "ITEM") {
										$.each($trs.find("td[request-title]"), function (i, td) {
											$(td).html("").text($(td).attr("request-title"));
										});

										$.each(result.Data, function (i, d) {

											var $tdreq = $trs.filter("[control-number={0}]".format(d.controlNumber)).find("td[request-title]");

											if (d.srawExtract != null) {
												var e = d.srawExtract;

												var ttp = "Title: " + e.title;
												if (e.subtitle != null) {
													ttp += " | Subtitle: " + e.subtitle;
												}
												if (e.author != null) {
													ttp += " | Author: " + e.author;
												}
												var $ttl = $("<div>{0}</div>");
												$ttl.attr("title", ttp);

												if (e.title.length > 25) {
													$ttl.text(e.title.substring(0, 20) + "...");
												} else {
													$ttl.text(e.title);
												}

												$tdreq.html("").append($ttl);
											} else {
												$tdreq.html("").text($tdreq.attr("request-title"));
											}
										});
									}
								} else {
									common.showResultModelMessage(result);
								}

							});
						}
					});
				}
			} else {
				common.showResultModelMessage(result);
				orderListingPaging.reset();
			}
		});
	};
	alert("kk1");
	

	$orderSearchBtn.click(function () {

		var oldVal = $orderSearchInput.attr("old-value");
		var searchMode = $orderListingContainer.attr("action-search-mode");
		var searchInput = $orderSearchInput.val();
		var searchInput2 = $orderSearchInput2.val();

		if (searchMode == "") {
			searchInput = "";
			searchInput2 = "";
		}

		if (searchInput == "" && searchInput2 == "") {
			searchMode = "";
		}

		var oldValueStr = searchMode + "==" + searchInput + "-" + searchInput2;

		if (oldVal != oldValueStr) {
			$orderListingContainer.attr("action-search-text", searchInput);
			$orderListingContainer.attr("action-search-text-2", searchInput2);

			getOrderListing(1);
			$orderSearchInput.attr("old-value", oldValueStr);
		}
	});
	getOrderListing(1);

	var initSearchMode = function () {
		$orderSearchInput.val("");
		$orderSearchInput2.val("");

		var $btnTextValue = $orderSearchMode.find("button[data-toggle=dropdown] span.text-value");

		$orderSearchInput.val("");
		common.onEnter($orderSearchInput, function () {
			$orderSearchInput.trigger("blur");
		});

		var $ddls = $orderSearchMode.find("ul.dropdown-menu li");

		$ddls.click(function () {
			var t = $(this).attr("text");
			var v = $(this).attr("value");
			var p = $(this).attr("ph-value");
			var hasMaxlength = $(this).is("[input-maxlength]");

			$btnTextValue.html(t);
			$orderSearchInput.val("");
			$orderSearchInput.attr("option", v);
			$orderSearchInput.attr("placeholder", p);

			$orderSearchInput2.val("");
			$orderSearchInput2.attr("option", v);
			$orderSearchInput2.attr("placeholder", p);

			if (hasMaxlength) {
				var maxlength = $(this).attr("input-maxlength");
				$orderSearchInput.attr("maxlength", maxlength);
				$orderSearchInput2.attr("maxlength", maxlength);
			} else {
				$orderSearchInput.removeAttr("maxlength");
				$orderSearchInput2.removeAttr("maxlength");
			}

			$orderListingContainer.attr("action-search-mode", v);

			var $orderDateItems = $orderSearchMode.find("[visible-for=OrderDate]");

			if (v == "OrderDate") {
				$orderSearchInput.datepicker({
					format: 'dd/mm/yyyy',
					autoclose: true
				}).on("changeDate", function (selected) {
					$orderListingContainer.attr("action-search-text", $orderSearchInput.val());
					getOrderListing(1);

					var startDate = new Date(selected.date.valueOf());
					$orderSearchInput2.datepicker('setStartDate', startDate);
				}).on('clearDate', function (selected) {
					$orderSearchInput2.datepicker('setStartDate', null);
				}).on("hide", function () {
					$orderSearchInput.trigger("blur");
				});

				$orderSearchInput2.datepicker({
					format: 'dd/mm/yyyy',
					autoclose: true
				}).on("changeDate", function (selected) {
					$orderListingContainer.attr("action-search-text-2", $orderSearchInput2.val());
					getOrderListing(1);

					var endDate = new Date(selected.date.valueOf());
					$orderSearchInput.datepicker('setEndDate', endDate);
				}).on('clearDate', function (selected) {
					$orderSearchInput.datepicker('setStartDate', null);
				}).on("hide", function () {
					$orderSearchInput2.trigger("blur");
				});

				$orderDateItems.removeClass("display-none");
			} else {
				$orderDateItems.addClass("display-none");
				$orderSearchInput.datepicker("destroy");
				$orderSearchInput2.datepicker("destroy");
			}

			$orderSearchInput.focus();
		});

		var onBlurInput = function () {
			$orderSearchBtn.trigger("click");
		};

		$orderSearchInput.blur(function () {
			onBlurInput();
		});

		$orderSearchInput2.blur(function () {
			onBlurInput();
		});
	
	};

	initSearchMode();

	var showAcquisitionModalWithIframe = function (url, title) {
		var $modal = $("#acquisition-modal");
		var $title = $modal.find(".modal-title");
		var $body = $modal.find(".modal-body");
		var $loadingBody = $body.find(".loading-body");
		var $contentBody = $body.find(".content-body");

		$title.html(common.htmlEscape(title));
		var $container = $("<div modal-height='80' class='intrinsic-container'></div>");
		var $iframe = $("<iframe class='display-none' frameborder='0'></iframe>");

		$modal.modal("show");

		$loadingBody.show();
		$contentBody.html("");
		$contentBody.append($container.append($iframe));

		common.modalSize();

		$iframe.load(function () {
			$iframe.removeClass("display-none");
			$loadingBody.hide();
			common.modalSize();

			var $iframeContent = $iframe.contents();
			$iframeContent.find("button[data-dismiss=modal]").click(function () {
				$contentBody.html("");
				$modal.modal("hide");
			});

			var $orderForm = $iframeContent.find("#acquisitionOrderForm");
			var $orderCreatedBtn = $orderForm.find("#orderCreatedBtn[trigger-btn]");
			var $orderUpdatedBtn = $orderForm.find("#orderUpdatedBtn[trigger-btn]");
			var $searchCatalogBtn = $orderForm.find("#searchCatalogBtn[trigger-btn]");
			var $searchVendorBtn = $orderForm.find("#searchVendorBtn[trigger-btn]");
			var $searchPatronBtn = $orderForm.find("#searchPatronBtn[trigger-btn]");

			//-- created
			$orderCreatedBtn.click(function (e) {
				$addOrderBtn.trigger("click");
				getOrderListing(1);
			});

			//-- updated
			$orderUpdatedBtn.click(function (e) {
				getOrderListing(orderListingPaging.currentPage);
			});

			//-- search-catalog
			$searchCatalogBtn.click(function (e) {
				var modalStatus = $searchCatalogBtn.attr("modal-status");
				if (modalStatus == "open") {
					var searchUrl = $searchCatalogBtn.attr("action-search-url");
					$searchCatalogBtn.attr("control-number", "");
					showModalWithIframe(searchUrl, "Search Catalog", function (controlNumber) {
						$searchCatalogBtn.attr("modal-status", "close");
						$searchCatalogBtn.attr("control-number", controlNumber)
						$searchCatalogBtn.trigger("click");
					});
				}
			});

			//-- search-vendor
			$searchVendorBtn.click(function (e) {
				var modalStatus = $searchVendorBtn.attr("modal-status");
				if (modalStatus == "open") {
					var searchUrl = $searchVendorBtn.attr("action-search-url");
					$searchVendorBtn.attr("vendor-code", "");
					showModalWithIframe(searchUrl, "Search Vendor", function (vendorCode) {
						$searchVendorBtn.attr("modal-status", "close");
						$searchVendorBtn.attr("vendor-code", vendorCode)
						$searchVendorBtn.trigger("click");
					});
				}
			});

			//-- search-patron
			$searchPatronBtn.click(function (e) {
				var modalStatus = $searchPatronBtn.attr("modal-status");
				if (modalStatus == "open") {
					var searchUrl = $searchPatronBtn.attr("action-search-url");
					$searchPatronBtn.attr("patron-id", "");
					showModalWithIframe(searchUrl, "Search Patron", function (patronId) {
						$searchPatronBtn.attr("modal-status", "close");
						$searchPatronBtn.attr("patron-id", patronId)
						$searchPatronBtn.trigger("click");

					});
				}
			});
		});

		$iframe.attr("src", url);
	};

	var showModalWithIframe = function (url, title, callback) {
		var $modal = $("#search-modal");
		var $title = $modal.find(".modal-title");
		var $body = $modal.find(".modal-body");
		var $loadingBody = $body.find(".loading-body");
		var $contentBody = $body.find(".content-body");

		$title.html(common.htmlEscape(title));
		var $container = $("<div modal-height='80' class='intrinsic-container'></div>");
		var $iframe = $("<iframe class='display-none' frameborder='0'></iframe>");

		$modal.modal("show");

		$loadingBody.show();
		$contentBody.html("");
		$contentBody.append($container.append($iframe));

		common.modalSize();

		$iframe.load(function () {
			$iframe.removeClass("display-none");
			$loadingBody.hide();
			common.modalSize();

			var $iframeContent = $iframe.contents();

			if (title == "Search Catalog") {
				var $catalogSearchPanel = $iframeContent.find("#catalogSearchPanel");
				var $catalogSelectedBtn = $catalogSearchPanel.find("#catalogSelectedBtn[trigger-btn]");
				$catalogSelectedBtn.click(function () {
					var controlNumber = $catalogSearchPanel.attr("control-number");
					if (callback !== undefined) {
						callback(controlNumber);
					}
					$contentBody.html("");
					$modal.modal("hide");
				});
			} else if (title == "Search Vendor") {
				var $vendorSearchPanel = $iframeContent.find("#vendorSearchPanel");
				var $vendorSelectedBtn = $vendorSearchPanel.find("#vendorSelectedBtn[trigger-btn]");
				$vendorSelectedBtn.click(function () {
					var vendorCode = $vendorSearchPanel.attr("vendor-code");
					if (callback !== undefined) {
						callback(vendorCode);
					}
					$contentBody.html("");
					$modal.modal("hide");
				});
			} else if (title == "Search Patron") {
				var $patronSearchPanel = $iframeContent.find("#patronSearchPanel");
				var $patronSelectedBtn = $patronSearchPanel.find("#patronSelectedBtn[trigger-btn]");
				$patronSelectedBtn.click(function () {
					var patronId = $patronSearchPanel.attr("patron-id");
					if (callback !== undefined) {
						callback(patronId);
					}
					$contentBody.html("");
					$modal.modal("hide");

				});
			} else if (title.indexOf("Notes (Order Number:") >= 0) {
				var $orderNoteForm = $iframeContent.find("#orderNoteForm");
				var $noteUpdatedBtn = $orderNoteForm.find("#noteUpdatedBtn[trigger-btn]");
				$noteUpdatedBtn.click(function () {
					getOrderListing(orderListingPaging.currentPage);
				});
			}

			$iframeContent.find("button[data-dismiss=modal]").not("[btn-close-exclude]").click(function () {
				$contentBody.html("");
				$modal.modal("hide");
			});
		});

		$iframe.attr("src", url);

	};
});
