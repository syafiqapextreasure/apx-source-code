//Delete template name 
function deletePlugin(patronID) {
	/*
	 * var split = tplID.split(","); var tplID =split[0]; var tplName =
	 * split[1];
	 */
	swal({
		// title: 'Are you sure want to delete '+ tplName+'?',
		text : 'This record cannot be delete.',
		//type : 'warning',
		showCancelButton : false,
	// confirmButtonColor: '#3085d6',
	// cancelButtonColor: '#d33',
	// confirmButtonText: 'Yes, delete it!',
	// cancelButtonText: 'No, cancel !',
	// confirmButtonClass: 'confirm-class',
	// cancelButtonClass: 'cancel-class',
	// closeOnConfirm: false,
	// closeOnCancel: false
	}).then(function(dismiss) {

		if (dismiss === 'cancel') {
			swal('Cancelled', 'Your imaginary file is safe :)');
		}
	})

}

function deleteBranch(GL71BRNC) {

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteBranch?GL71BRNC="
								+ GL71BRNC;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete SMD
function deleteSMD(GL47SMD) {

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteSMD?GL47SMD=" + GL47SMD;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/15_SMD&ACTION=SMDTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete General Subject
function deleteSubj(GL54SUBJSTA) {

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteSubj?GL54SUBJSTA="
								+ GL54SUBJSTA;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														location.reload();
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Patron Status
function deleteStat(GL08STAT) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteStatus?GL08STAT="
								+ GL08STAT;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/16_PatronStatus&ACTION=StatusTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Item Category
function deleteItemCat(GL10ICAT) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteItemCat?GL10ICAT="
								+ GL10ICAT;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														/*location.reload();*/
														location.replace("template?MODULE=Foundation/13_ItemCategory&ACTION=ItemCatTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Location Code
function deleteLoca(GL05LOCA) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteLocation?GL05LOCA="
								+ GL05LOCA;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/06_LocationCode&ACTION=LocaTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Currency Code
function deleteCurrency(GL24FOREX) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteCurrency?GL24FOREX="
								+ GL24FOREX;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/04_CurrencyCode&ACTION=CurrencyTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Serial Frequency
function deleteSerial(GL49FREQ) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteSerial?GL49FREQ="
								+ GL49FREQ;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/17_SerialFrequency&ACTION=SerialTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Patron Category
function deletePatCat(GL07CATE) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeletePatCate?GL07CATE="
								+ GL07CATE;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/14_PatronCategory&ACTION=PatCategory.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled', 'error');

						}
					})
}

// Delete Vendor
function deleteVendor(GL39CODE) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteVendor?GL39CODE="
								+ GL39CODE;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/07_VendorCode&ACTION=VendorTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Patron Details
function deletePatDetail(GL14PATR) {

	// alert("Masukkk delete")

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeletePatDetail?GL14PATR="
								+ GL14PATR;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled', 'error');

						}
					})
}

// Delete Code Table
function deleteCode(CODE, FCODE) {
	// alert("masuk");
	// alert(CODE);
	// alert(FCODE);

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteCodeTable?CODE=" + CODE
								+ "&FCODE=" + FCODE;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/02_CodeTable&ACTION=CodeTable.jsp?value=" + FCODE);
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Course
function deleteCourse(GL12COURSE) {
	// alert("masuk");
	// alert(CODE);
	// alert(FCODE);

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteCourse?GL12COURSE="
								+ GL12COURSE;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/03_CourseCode&ACTION=CourseTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

// Delete Dept code
function deleteDept(GL11DEPT) {
	// alert("Test");

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteDept?GL11DEPT="
								+ GL11DEPT;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/05_DepartmentCode&ACTION=DeptTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}

/////del miscellaneous




//////////////

function deleteEligibility(GL27CATE, GL27ICAT, GL27SMD, GL27BRNC) {
	// alert("Test");

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	}).then(
			function() {
				debugger;
				var deleteUrl = "Handler_DeleteElig?GL27CATE1=" + GL27CATE
						+ "&GL27ICAT1=" + GL27ICAT + "&GL27SMD1=" + GL27SMD
						+ "&GL27BRNC1=" + GL27BRNC;

				$.ajax({
					url : deleteUrl,
					success : function(result) {
						debugger;
						var status = result.replace(/\s+/g, '');
						if (status == "ok") {
							swal('Successfully Deleted!',
									'No Active circulation transaction exist.');
							$('.swal2-confirm').click(function() {
								location.reload();
							});

						} else {
							swal('Unable to Delete',
									'Active circulation transaction exist.');
						}
					}
				});
			}, function(dismiss) {
				if (dismiss == 'cancel') {
					swal('', 'Cancelled', 'error');

				}
			})
}


//Delete doc status
/*function deletedocstatus(code) {
	// alert("Test");

	swal({
		// title: 'Do you wish to delete this record?',
		text : 'Do you wish to delete this record?',
		//type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes',
		cancelButtonText : 'No',
		confirmButtonClass : 'confirm-class',
		cancelButtonClass : 'cancel-class',
		closeOnConfirm : false,
		closeOnCancel : false
	})
			.then(
					function() {
						var deleteUrl = "Handler_DeleteDept?GL11DEPT="
								+ GL11DEPT;

						$
								.ajax({
									url : deleteUrl,
									success : function(result) {
										var status = result.replace(/\s+/g, '');
										if (status == "ok") {
											swal(
													'Successfully Deleted!',
													'The record has been successfully removed.');
											$('.swal2-confirm').click(
													function() {
														//location.reload();
														location.replace("template?MODULE=Foundation/05_DepartmentCode&ACTION=DeptTable.jsp");
													});

										} else {
											swal(
													'Not Deleted',
													'This record cannot be deleted.');
										}
									}
								});
					}, function(dismiss) {
						if (dismiss == 'cancel') {
							swal('', 'Cancelled');

						}
					})
}*/