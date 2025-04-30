$.fn.hasAttr = function (name) {
	return this.attr(name) !== undefined;
};

$.fn.outerHtml = function () {
	return $(this).clone().wrap('<div></div>').parent().html();
};

if (!String.prototype.format) {
	String.prototype.format = function () {
		var args = arguments;
		return this.replace(/{(\d+)}/g, function (match, number) {
			return typeof args[number] != 'undefined' ? args[number] : match;
		});
	};
};

var common = {
	showResultModelMessage : function (result) {
		swal({
			title : result.Data.Title,
			text : result.Data.Message,
			type : result.Data.Status
		})
	},
	onEnter : function (textbox, callback) {
		$(textbox).keyup(function (e) {
			if (e.which == 13 || e.keyCode == 13) {

				if (callback !== undefined) {
					callback();
				}

				return false;
			}

			return true;
		});
	},

	htmlEncode : function (value) {
		return $('<div/>').text(value).html();
	},

	htmlDecode : function (value) {
		return $('<div/>').html(value).text();
	},

	htmlEscape : function (str) {
		if (str === undefined || str == null) {
			return null;
		}

		return str.replace(/&/g, '&amp;')
		.replace(/"/g, '&quot;')
		.replace(/'/g, '&#39;')
		.replace(/</g, '&lt;')
		.replace(/>/g, '&gt;');

	},
	getPaymentRounded : function (num) {
		var t1 = parseFloat(num.toFixed(2).split(".")[0]);
		var t2 = parseFloat("0." + num.toFixed(2).split(".")[1]);

		var n1 = parseInt(t2.toFixed(2).substring(2, 3));

		var n2 = parseInt(t2.toFixed(2).substring(3, 4));

		if (n2 > 5) {
			n1 += 1;
			n2 = 0;

			if (n1 > 9) {
				t1 += 1;
				n1 = 0;
			}
		} else if (n2 < 5) {
			n2 = 0;
		}

		return parseFloat("{0}.{1}{2}".format(t1, n1, n2));
	},
	roundNumber : function (num, scale) {
		if (!("" + num).includes("e")) {
			return  + (Math.round(num + "e+" + scale) + "e-" + scale);
		} else {
			var arr = ("" + num).split("e");
			var sig = ""
				if (+arr[1] + scale > 0) {
					sig = "+";
				}
				return  + (Math.round(+arr[0] + "e" + sig + (+arr[1] + scale)) + "e-" + scale);
		}
	},

	isNumber : function (evt, element) {

		var charCode = (evt.which) ? evt.which : evt.keyCode;

		if ((charCode != 45 || $(element).val().indexOf('-') != -1) && (charCode != 46 || $(element).val().indexOf('.') != -1) && ((charCode < 48 && charCode != 8) || charCode > 57)) {

			return false;

		} else {
			return true;
		}
	},
	isString : function (obj) {
		return toString.call(obj) == '[object String]';
	},
	objectifyForm : function (formArray) {

		returnArray = {};
		for (var i = 0; i < formArray.length; i++) {
			returnArray[formArray[i]['name']] = formArray[i]['value'];
		}
		return returnArray;
	},

	getFieldInputs : function (form) {
		var data = {};

		var $inputs = $(form).find("input[type=text][field-input], input[type=hidden][field-input], textarea[field-input]");

		$.each($inputs, function (v, input) {
			var name = $(input).attr("name");
			var value = $(input).val();

			if ($(input).hasAttr("field-integer")) {
				value = parseInt(value);
			}

			data[name] = value;
		});

		var $checkboxes = $(form).find("input[type=checkbox][field-input]");

		$.each($checkboxes, function (v, input) {
			var name = $(input).attr("name");
			var value = $(input).prop('checked');
			data[name] = value;
		});

		var $selects = $(form).find("select[field-input]");

		$.each($selects, function (v, input) {
			var name = $(input).attr("name");
			var value = $(input).val();
			data[name] = value;
		});

		return data;
	},

	setGlobalModal : function (title, status, body, callback, showCancel) {
		var $modal = $("#global-modal");
		var $title = $modal.find("#global-modal-title");
		var $body = $modal.find("#global-modal-body");
		var $okBtn = $modal.find("#global-modal-ok");
		var $cancelBtn = $modal.find("#global-modal-cancel");

		var $spanTitle = $("<span></span>").append($("<i class='fa fa-comment-o'></i>")).append($("<span>&nbsp;{0}</span> ".format(title)));

		$title.html("");
		$title.append($spanTitle);

		var $spanBody = $("<div class='alert alert-{0}' role='alert'></div>".format(status.toLowerCase())).append($("<span>{0}</span>".format(body)));

		$body.html("");
		$body.append($spanBody);

		$okBtn.unbind()
		$okBtn.click(function () {
			if (callback === undefined) {
				$modal.modal('hide');
			} else {
				$modal.modal('hide');
				callback();
			}
		});

		if (showCancel === undefined) {
			$cancelBtn.hide();
		} else if (showCancel) {
			$cancelBtn.show();
		}

		$modal.modal("show");
	},

	hideGlobalModal : function () {
		var $modal = $("#global-modal");
		$modal.modal("hide");
	},

	showLoading : function () {
		swal({
			title : "<i class='fa fa-spin fa-spinner'></i> Loading",
			type : "info",
			html : "Please wait..",
			showConfirmButton : false,
			allowOutsideClick : false,
			allowEscapeKey : false
		})
	},
	hideLoading : function () {
		swal.close();
	},
	initDefaultValue : function () {
		var $selectVs = $("select[init-value]");

		$.each($selectVs, function (i, selectV) {
			var value = $(selectV).attr("init-value");

			if (value != "") {
				$(selectV).val(value);
			}
		});

		var $inputVs = $("input[type=text][init-value], input[type=hidden][init-value]");

		$.each($inputVs, function (v, inputV) {
			var value = $(inputV).attr("init-value");
			$(inputV).val(value);
		});

		var $checkboxVs = $("input[type=checkbox][init-value]");

		$.each($checkboxVs, function (i, checkboxV) {
			var value = $(checkboxV).attr("init-value");

			if (value != "") {
				$(checkboxV).prop('checked', (value == "true") ? true : false);
			}
		});
	},

	init : function () {

		$(".numeric").keypress(function (event) {
			return common.isNumber(event, this)
		});

		$(".upper").keyup(function (event) {
			$(this).val($(this).val().toUpperCase());

			return true;
		});

		$("[title]").tooltip();
	},

	bindValidation : function (form, data, ajaxOperationUrl) {
		var $form = $(form);
		$.each(data, function (i, d) {
			var $field = $form.find("[name={0}]".format(d.FieldName));
			var $fgroup = $field.parent().parent();

			var $errorPlacement = $field;
			if ($fgroup.is("[error-helper]")) {
				$errorPlacement = $fgroup.find("[error-placement]");
			}

			var toBind = null;
			if ($field.is("input[type=text]")) {
				toBind = "blur";
			} else if ($field.is("select")) {
				toBind = "change";
			}

			if (toBind != null) {

				//add maxlength attr
				$.each(d.Items, function (n, item) {
					if (item.hasOwnProperty("MaxLength")) {
						$field.attr("maxlength", item.MaxLength);
					} else if (item.Type == "DbNumeric") {
						$field.addClass("numeric");
					}
				});

				common.init();

				$field.bind(toBind, function () {
					var fieldValue = $.trim($field.val());
					var hasError = false;

					$fgroup.find("span.help-block").remove();
					$fgroup.removeClass("has-error");

					$.each(d.Items, function (n, item) {
						if (item.Type == "DbNotNull") {
							if (fieldValue == "") {
								var $helpBlock = $("<span />");
								$helpBlock.addClass("help-block");

								if ($field.is("select")) {
									$helpBlock.html(item.Message.replace("cannot be empty.", "must be selected."));
								} else {
									$helpBlock.html(item.Message);
								}

								$errorPlacement.after($helpBlock);
								hasError = true;
							}

						} else if (item.Type == "DbLength") {
							if (item.hasOwnProperty("MinLength")) {
								if (fieldValue.length < item.MinLength) {
									var $helpBlock = $("<span />");
									$helpBlock.addClass("help-block");
									$helpBlock.html(item.MinLengthMessage);
									$errorPlacement.after($helpBlock);
									hasError = true;
								}
							}

							if (item.hasOwnProperty("MaxLength")) {

								$field.attr("max-length", item.MaxLength);

								if (fieldValue.length > item.MaxLength) {
									var $helpBlock = $("<span />");
									$helpBlock.addClass("help-block");
									$helpBlock.html(item.MaxLengthMessage);
									$errorPlacement.after($helpBlock);
									hasError = true;
								}
							}
						} else if (item.Type == "DbNumeric") {
							if (fieldValue.length > 0 && !$.isNumeric(fieldValue)) {
								var $helpBlock = $("<span />");
								$helpBlock.addClass("help-block");
								$helpBlock.html(item.Message);
								$errorPlacement.after($helpBlock);
								hasError = true;
							}

						} else if (item.Type == "DbValidValues") {
							if ($.inArray(fieldValue, item.ValidValues) < 0) {
								var $helpBlock = $("<span />");
								$helpBlock.addClass("help-block");
								$helpBlock.html(item.Message);
								$errorPlacement.after($helpBlock);
								hasError = true;
							}

						} else if (item.Type == "DbMoreThanZero") {
							if (fieldValue.length > 0 && $.isNumeric(fieldValue) && fieldValue <= 0) {
								var $helpBlock = $("<span />");
								$helpBlock.addClass("help-block");
								$helpBlock.html(item.Message);
								$errorPlacement.after($helpBlock);
								hasError = true;
							}
						} else if (item.Type == "DbAjaxValidate") {
							if (ajaxOperationUrl !== undefined) {
								var data = {
									Name : d.FieldName,
									Value : fieldValue
								};

								$.ajax({
									type : "POST",
									url : ajaxOperationUrl,
									data : JSON.stringify({
										operation : "Validate",
										data : data
									}),
									dataType : "json",
									contentType : "application/json"
								}).done(function (result) {
									if (result.Success && result.Action == "MESSAGE") {
										if (result.Data.Status == 'error') {
											var $helpBlock = $("<span />");
											$helpBlock.addClass("help-block");
											$helpBlock.html(result.Data.Message);
											$errorPlacement.after($helpBlock);
											hasError = true;

											$fgroup.addClass("has-error");
										}
									}
								});
							}
						} else if (item.Type == "DbAjaxGetText") {

							if ($field.is("[text-field-name]") && ajaxOperationUrl !== undefined) {
								var $textField = $form.find("[text-field][name={0}]".format($field.attr("text-field-name")));
								$textField.val("Loading..");

								var data = {
									Name : d.FieldName,
									Value : fieldValue
								};

								$.ajax({
									type : "POST",
									url : ajaxOperationUrl,
									data : JSON.stringify({
										operation : "GetValue",
										data : data
									}),
									dataType : "json",
									contentType : "application/json"
								}).done(function (result) {
									if (result.Success && result.Action == "MESSAGE") {
										if (result.Data.Status == 'success') {
											if ($textField.is("input")) {
												$textField.val(result.Data.Message);
											}
										}
									}
								});
							}
						}
					});

					if (hasError) {

						$fgroup.addClass("has-error");
					}
				});
			}
		});
	},

	renderValidation : function ($form, resultData) {
		var $formHasErrors = $form.find(".has-error");
		$formHasErrors.removeClass("has-error");
		$formHasErrors.find(".help-block").remove();

		$.each(resultData, function (i, data) {

			var $fieldInput = $form.find("[field-input][name={0}]".format(data.FieldName));
			var $fgroup = $fieldInput.parent().parent();
			var $errorPlacement = $fgroup;

			var hasErrorPlacement = false;

			$fgroup.find(".help-block").remove();

			if ($fgroup.is("[error-helper]")) {
				$errorPlacement = $fgroup.find("[error-placement]");
				$fgroup.addClass("has-error");
				hasErrorPlacement = true;
			} else {
				$fgroup.addClass("has-error");
			}

			var $helpBlock = $("<span />");
			$helpBlock.addClass("help-block");
			$helpBlock.html(data.Message);

			if (hasErrorPlacement) {
				$errorPlacement.after($helpBlock);
			} else {
				$fieldInput.after($helpBlock);
			}
		});

		swal({
			title : "Form Incomplete",
			type : "error",
			text : "Please fill in required fields",
			showCloseButton : true,
		})
	},
	modalSize : function () {
		var $mheights = $("[modal-height]")
			$mheights.each(function (i, v) {
				var height = parseInt($(v).attr("modal-height"));
				var fheight = (height / 100) * $(window).height();
				$(v).height(fheight);
			});

		$("[resize-height]").each(function (i, v) {
			var svalue = $(v).attr("resize-height").split(",");
			var height = parseInt(svalue[0]);
			var $target = $(svalue[1]);
			var h = (height / 100) * $target.height();
			$(v).height(h);
		});

		$(window).unbind("resize");
		$(window).resize(function () {
			common.modalSize();
		});
	}
};

$(function () {
	$(document).ajaxComplete(function (event, xhr, settings) {
		common.init();
	});

	common.initDefaultValue();
	common.init();
});
