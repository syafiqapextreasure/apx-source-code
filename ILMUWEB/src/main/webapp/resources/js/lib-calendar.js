$(document).ready(function() {
	
					
					//$("#forEndDate").hide();
					var calendar = $('#calendar')
							.fullCalendar(
									{
										header : {
											left : 'prev,next today',
											center : 'title',
											right : 'month'
										},
										events : {
											
											
											url : 'CalendarJsonServlet',
											type : 'POST',
											data : {
												libraryBranch : $("#branch option:selected").val()
											},
											success : function() {
												debugger;
											},
											error : function() {
												alert('there was an error while fetching holidays!');
											}
										},
										editable : true,
										droppable : true,
										drop : function(date, jsEvent, ui,
												resourceId) {
											debugger;

											var type = this.attributes[1].textContent;
											var abbr = this.innerText;
											var color = $(this).css(
													"background-color");
											var branch = $(
													"#branch option:selected")
													.val();
											var saveDate = moment(date).format(
													'YYYYMMDD');
											var calendarDate = moment(date)
													.format('YYYY-MM-DD');

											if (type === "event") {
												/* direct save once drop */
												var desc = this.innerHTML;
												var title = desc.substring(0,
														desc.indexOf('&nbsp;'));
												/* incase title empty */
												if (title != "") {
													title = title.trim();
												}
												doCreateEvent(saveDate,
														calendarDate, branch,
														title, type, color);
											} else if (type === "4") {
												debugger;
												/*
												 * direct save once drop for
												 * weekends the title/desc not
												 * require since it store
												 * "Weekend" that why remain
												 * using abbr variable only
												 */
												doCreateHoliday(saveDate,
														calendarDate, branch,
														abbr, type, color,
														abbr, "false", "0", "1");
											} else {
												/*
												 * need to click button to save
												 * so all the value need to be
												 * declare inside the modal as
												 * hidden for easy grab
												 */
												$('#createHolModal #abbr').val(
														abbr);
												$('#createHolModal #date').val(
														saveDate);
												$('#createHolModal #type').val(
														type);
												$('#createHolModal #color')
														.val(color);
												$('#createHolModal #calDate')
														.val(calendarDate);
												$('#createHolModal').modal(
														'show');
												
												if(color == "rgb(0, 192, 239)"){
													//alert("sembreak");
													$("#createHolModal #forEndDate").show();
													
													$('#endDateSembreak').datepicker({
														format: "dd/mm/yyyy",
														todayBtn: true,
														autoclose: true,
														todayHighlight: true,
													});
												}else{
													//alert("zxcv");
													$("#createHolModal #forEndDate").hide();
												}
											}
										},
										eventClick : function(calEvent,
												jsEvent, view) {
											debugger;

											var title = calEvent.title;
											var id = calEvent._id;
											var type = calEvent.type;
											var branch = calEvent.branch;
											var saveDate = moment(
													calEvent.start).format(
													'YYYYMMDD');

											$('#editCalendarModal #descr').val(
													title);
											$('#editCalendarModal #date').val(
													saveDate);
											$('#editCalendarModal #type').val(
													type);
											$('#editCalendarModal #eventId')
													.val(id);
											$('#editCalendarModal #branch')
													.val(branch);
											$('#editCalendarModal').modal(
													'show');
										},
										eventDragStart: function(event) {
									         originalDate = new Date(event.start);  // Make a copy of the event date
									    },
										eventDrop:function(event, dayDelta, minuteDelta){
											/*alert("jjj");
											alert( 'Event dropped!  Original date = ' + moment(originalDate).format('YYYYMMDD'));
											var start = $.fullCalendar.formatDate(event.start, "Y-MM-DD HH:mm:ss");
											var end = $.fullCalendar.formatDate(event.end, "Y-MM-DD HH:mm:ss");*/
											var title = event.title;
											var id = event.id;
											var branch = event.branch;
											var type = event.type;
											var newDate = moment(event.start).format('YYYYMMDD');
											var allBranch = document.getElementById("apply-all").checked === true ? "true": "false";
											
											
											/*alert("end" +end);
											alert("title" +title);
											alert("id" +id);
											alert("branch" +branch);
											alert("type" +type);
											alert("newDate" +newDate);
											alert("allBranch" +allBranch);*/
											
											$.get('Handler_DeleteAndAdd', {
												oldDate : moment(originalDate).format('YYYYMMDD'),
												branch : branch,
												type : type,
												title : title,
												allBranch : allBranch,
												newDate : newDate
											}, function(response) {
												debugger;
												var status = result.replace(/\s+/g, '');
												if (status === "ok") {
													debugger;
													fetchEvents();
												}
												location.replace("template?MODULE=Foundation/11_LibraryCalendar&ACTION=LibraryCalendar.jsp");
											});
											/*$.ajax({
												url:"update.php",
												type:"POST",
												data:{title:title, start:start, end:end, id:id},
												success:function()
												{
													calendar.fullCalendar('refetchEvents');
													alert("Event Updated");
												}
											});*/
										},
										/*viewRender: function (view, element) {
										},*/
									})

					$('#add-weekend').on('click', function(e) {
						/*
						 * We don't want this to act as a link so cancel the
						 * link action
						 */
						e.preventDefault();
						debugger;

						var type = "4";
						var abbr = "Weekend";
						var color = "rgb(221, 75, 57)";
						var branch = $("#branch option:selected").val();
						var day = $("#weekend option:selected").val();
						var allBranch = document.getElementById("apply-all").checked === true ? "true": "false";

						doCreateWeekend(day, branch, abbr, type, color, allBranch);
						
					});
					
					$('#clear-weekend').on('click', function(e) {
						var moment = $('#calendar').fullCalendar('getDate');
						var year = moment.format('YYYY');
						
						$.post("deleteWeekend", {
							year : year, 
						},function(data){
							}
						).fail(function(data){
							swal("error");
						}).success(function(data){   
							location.replace("template?MODULE=Foundation/11_LibraryCalendar&ACTION=LibraryCalendar.jsp");
						});
						
					});

					$('#deleteCalendarButton').on('click', function(e) {
						debugger;
						var id = $('#editCalendarModal #eventId').val();
						var type = $('#editCalendarModal #type').val();
						var date = $('#editCalendarModal #date').val();
						var title = $('#editCalendarModal #descr').val();
						var branch = $('#editCalendarModal #branch').val();

						if (type === "event") {
							$.get('Handler_DeleteEvent', {
								evntDate : date,
								evntBranch : branch,
								evntDesc : title
							}, function(response) {
								debugger;
								var status = response.replace(/\s+/g, '');
								if (status === 'ok') {
									removeEvent(id);
								}
							});
						} else {
							$.get('Handler_DeleteHoliday', {
								holDate : date,
								holBranch : branch,
								holDesc : title,
								holType : type
							}, function(response) {
								debugger;
								var status = response.replace(/\s+/g, '');
								if (status === 'ok') {
									removeEvent(id);
								}
							});
						}
					})

					function removeEvent(id) {
						$("#calendar").fullCalendar("removeEvents", id);
						$("#editCalendarModal #descr").val('');
						$("#editCalendarModal").modal('hide');
					}

					function updateEvent(id) {
						debugger;
						/* find the specific event by id */
						var eventTo = $("#calendar").fullCalendar(
								'clientEvents', id)[0];
						/* replace the title with newest */
						eventTo.title = $('#editCalendarModal #descr').val();
						calendar.fullCalendar("renderEvent", eventTo, true);
						$("#editCalendarModal #descr").val('');
						$("#editCalendarModal").modal('hide');
					}

					$('#submitEditButton').on('click', function(e) {
						debugger;
						var id = $('#editCalendarModal #eventId').val();
						var type = $('#editCalendarModal #type').val();
						var date = $('#editCalendarModal #date').val();
						var title = $('#editCalendarModal #descr').val();
						var branch = $('#editCalendarModal #branch').val();

						if (type === "event") {
							$.get('Handler_EditEvent', {
								evntDate : date,
								evntBranch : branch,
								evntDesc : title
							}, function(response) {
								var status = response.replace(/\s+/g, '');
								if (status === 'ok') {
									updateEvent(id);
								}
							});
						} else {
							$.get('Handler_EditHoliday', {
								holDate : date,
								holBranch : branch,
								holDesc : title,
								holType : type
							}, function(response) {
								var status = response.replace(/\s+/g, '');
								if (status === 'ok') {
									updateEvent(id);
								}
							});
						}
					});
					
					$('#endDateSembreak').change(function(){
						$('.enddate').css({"color" : ""});
						$("#endDateSembreak").css("border", "");
						$("#submitCreateButton").attr("disabled", false);
						
				    });

					$('#submitCreateButton').on('click',function(e) {
						//alert("CLICK HERE"+$("#endDateSembreak").val());
						var breakdate = $("#endDateSembreak").val();
						
						e.preventDefault();
						debugger;
						
						var given =  moment(moment($("#createHolModal #endDateSembreak").val(), 'DD/MM/YYYY').format("YYYYMMDD"), "YYYY-MM-DD");
						var current = moment($('#createHolModal #date').val(), "YYYY-MM-DD");
						
						if($('#createHolModal #type').val() == 3){
							if(breakdate == ""){
								$('.enddate').css({"color" : "red"});
								$("#endDateSembreak").css("border", "1px solid red");
								$("#submitCreateButton").attr("disabled", true);

							}else{
								
							doCreateHoliday(
									$('#createHolModal #date').val(),
									$('#createHolModal #calDate').val(),
									$("#branch option:selected").val(),
									$('#createHolModal #descr').val(),
									$('#createHolModal #type').val(),
									$('#createHolModal #color').val(),
									$('#createHolModal #abbr').val(),
									moment($("#createHolModal #endDateSembreak").val(), 'DD/MM/YYYY').format("YYYYMMDD"),
									document.getElementById("apply-all").checked === true ? "true": "false",
									moment.duration(given.diff(current)).asDays()+1);
							}
						}else{
							doCreateHoliday(
									$('#createHolModal #date').val(),
									$('#createHolModal #calDate').val(),
									$("#branch option:selected").val(),
									$('#createHolModal #descr').val(),
									$('#createHolModal #type').val(),
									$('#createHolModal #color').val(),
									$('#createHolModal #abbr').val(),
									"0",
									document.getElementById("apply-all").checked === true ? "true": "false",
									"0");
						}
						
						//alert(breakdate+"hkj");
						
						/*if(breakdate == ""){
							$('.enddate').css({"color" : "red"});
							$("#endDateSembreak").css("border", "1px solid red");
							$("#submitCreateButton").attr("disabled", true);

						}else{
							e.preventDefault();
							debugger;
							
							var given =  moment(moment($("#createHolModal #endDateSembreak").val(), 'DD/MM/YYYY').format("YYYYMMDD"), "YYYY-MM-DD");
							var current = moment($('#createHolModal #date').val(), "YYYY-MM-DD");
							
							if($('#createHolModal #type').val() == 3){
								doCreateHoliday(
										$('#createHolModal #date').val(),
										$('#createHolModal #calDate').val(),
										$("#branch option:selected").val(),
										$('#createHolModal #descr').val(),
										$('#createHolModal #type').val(),
										$('#createHolModal #color').val(),
										$('#createHolModal #abbr').val(),
										moment($("#createHolModal #endDateSembreak").val(), 'DD/MM/YYYY').format("YYYYMMDD"),
										document.getElementById("apply-all").checked === true ? "true": "false",
										moment.duration(given.diff(current)).asDays()+1);
							}else{
								doCreateHoliday(
										$('#createHolModal #date').val(),
										$('#createHolModal #calDate').val(),
										$("#branch option:selected").val(),
										$('#createHolModal #descr').val(),
										$('#createHolModal #type').val(),
										$('#createHolModal #color').val(),
										$('#createHolModal #abbr').val(),
										"0",
										document.getElementById("apply-all").checked === true ? "true": "false",
										"0");
							}
						}*/
						//if()
										/*
										 * We don't want this to act as a link
										 * so cancel the link action
										 */
										/*e.preventDefault();
										debugger;
										
										var given =  moment(moment($("#createHolModal #endDateSembreak").val(), 'DD/MM/YYYY').format("YYYYMMDD"), "YYYY-MM-DD");
										var current = moment($('#createHolModal #date').val(), "YYYY-MM-DD");
										
										if($('#createHolModal #type').val() == 3){
											doCreateHoliday(
													$('#createHolModal #date').val(),
													$('#createHolModal #calDate').val(),
													$("#branch option:selected").val(),
													$('#createHolModal #descr').val(),
													$('#createHolModal #type').val(),
													$('#createHolModal #color').val(),
													$('#createHolModal #abbr').val(),
													moment($("#createHolModal #endDateSembreak").val(), 'DD/MM/YYYY').format("YYYYMMDD"),
													document.getElementById("apply-all").checked === true ? "true": "false",
													moment.duration(given.diff(current)).asDays()+1);
										}else{
											doCreateHoliday(
													$('#createHolModal #date').val(),
													$('#createHolModal #calDate').val(),
													$("#branch option:selected").val(),
													$('#createHolModal #descr').val(),
													$('#createHolModal #type').val(),
													$('#createHolModal #color').val(),
													$('#createHolModal #abbr').val(),
													"0",
													document.getElementById("apply-all").checked === true ? "true": "false",
													"0");
										}*/
					});

					$('#branch,#branchDesc').on('change', function() {
						debugger;
						fetchEvents();
					});

					/*
					 * $('.fc-next-button').on('click', function() { debugger;
					 * fetchEvents(); });
					 * 
					 * $('.fc-prev-button').on('click', function() { debugger;
					 * fetchEvents(); });
					 */
					$('.fc-next-button').on('click', function() { 
						debugger;
						//fetchEvents(); 
					});
					
					$('.fc-prev-button').on('click', function() { 
						debugger;
						//fetchEvents();
					});

					function fetchEvents() {
						// TODO fetchEvents() method may be the cause of those
						// duplicate event
						/*
						 * grab events exist in database base on selected branch
						 */
						debugger;
						var events = {
							url : "CalendarJsonServlet",
							type : 'POST',
							data : {
								libraryBranch : $("#branch option:selected")
										.val()
							},
							success : function() {
								debugger;
							},
							error : function() {
								alert('there was an error while fetching holidays!');
							}
						};

						    $('#calendar').fullCalendar('removeEventSource', events);
						    $('#calendar').fullCalendar('addEventSource', events);
						    $('#calendar').fullCalendar('refetchEvents');
					}

					/* to create new Event button */
					$("#add-new-event")
							.click(
									function(e) {
										debugger;
										e.preventDefault();
										/*
										 * Get value and make sure it is not
										 * null
										 */
										var val = $("#new-event").val();
										if (val.length == 0) {
											return;
										}

										/* default event color */
										var currColor = "#3c8dbc";

										/* Create events */
										var event = $("<div />");
										/* Add color effect to button */
										event.css({
											"background-color" : currColor,
											"border-color" : currColor,
											color : "#FFFFFF"
										}).addClass("external-event event")
												.html(val + '	&nbsp;');

										/* add close button to event button */
										var close = $("<button />");
										close
												.css(
														{
															"background-color" : currColor,
															"border-color" : currColor,
															color : "#FFFFFF"
														})
												.addClass("remove-item")
												.html(
														'<span aria-hidden="true">&times;</span>');
										event.append(close);

										/* Add data-type for easy classify */
										event.attr('data-type', 'event');

										/*
										 * Prepand to existing event inside the
										 * box
										 */
										$("#external-events-listing").prepend(
												event);

										/* Add draggable funtionality */
										ini_events(event);

										/* Remove event from text input */
										$("#new-event").val("");

										/*
										 * Remove the element from the
										 * "Draggable Events" list
										 */
										$('.remove-item').on(
												'click',
												function() {
													debugger;
													$(this)[0].parentNode
															.remove();
												});
									});

					function doCreateWeekend(day, branch, abbr, type, color, allBranch) {
						debugger;
						var addWeekendUrl = "Handler_AddWeekend?holDay=" + day
								+ "&holBranch=" + branch + "&holDesc=" + abbr
								+ "&holType=" + type
								+ "&allBranch=" + allBranch;

						$.ajax({
							url : addWeekendUrl,
							success : function(result) {
								var status = result.replace(/\s+/g, '');
								if (status === "ok") {
									debugger;
									fetchEvents();
								}
								location.replace("template?MODULE=Foundation/11_LibraryCalendar&ACTION=LibraryCalendar.jsp");
							}
						});
					}

					function doCreateHoliday(saveDate, calendarDate, branch,
							descr, type, color, abbr, endDate, allBranch, different) {
						debugger;
						
						var addHolidayUrl = "Handler_AddHoliday?holDate="
								+ saveDate + "&holBranch=" + branch
								+ "&holDesc=" + descr + "&holType=" + type
								+ "&allBranch=" + allBranch + "&endDate=" + endDate
								+ "&different=" +different;

						$.ajax({
							url : addHolidayUrl,
							success : function(result) {
								var status = result.replace(/\s+/g, '');
								if (status === "ok") {
									debugger;
									$("#calendar").fullCalendar('refetchEvents',
											{
												title : descr,
												start : calendarDate,
												type : type,
												color : color,
												branch : branch,
												abbr : abbr,
												allDay : true,
											}, true);
								}
							}
						});
						$("#createHolModal #descr").val('');
						$("#createHolModal").modal('hide');
					}

					function doCreateEvent(date, calendarDate, branch, title,
							type, color) {
						debugger;
						var addEventUrl = "Handler_AddEvent?evntDate=" + date
								+ "&evntBranch=" + branch + "&evntDesc="
								+ title;

						$.ajax({
							url : addEventUrl,
							success : function(result) {
								var status = result.replace(/\s+/g, '');
								if (status == "ok") {
									debugger;
									$("#calendar").fullCalendar('renderEvent',
											{
												title : title,
												start : calendarDate,
												type : type,
												color : color,
												branch : branch,
												allDay : true,
											}, true)
								}
							}
						})
					}

					function ini_events(ele) {
						ele.each(function() {
							/* make the event draggable using jQuery UI */
							$(this).draggable({
								zIndex : 1070,
								revert : true, /*
												 * will cause the event to go
												 * back to its original position
												 * after the drag
												 */
								revertDuration : 0
							});
						})
					}
					ini_events($('#external-events div.external-event'));
					
					$('#createHolModal').on('hidden.bs.modal', function () {
						$(this).find('form').trigger('reset');
					});
					
					////term break required date
					/*$('#createHolForm').bootstrapValidator({
				        feedbackIcons: {
				            valid: 'glyphicon glyphicon-ok',
				            invalid: 'glyphicon glyphicon-remove',
				            validating: 'glyphicon glyphicon-refresh'
				        },
				        fields: {
				        	endDate: {
				                validators: {
				                    date: {
				                        //format: 'YYYY/MM/DD',
				                        message: 'Please Input Date'
				                    }
				                }
				            }
				        }
				    });*/
				});
