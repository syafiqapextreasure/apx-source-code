	<tr class="data-template hide">
											<td>#number#</td>
													<td calculate-gst="#calculateGst#">#code#</td>
													<td class="ta-right" toFixed="2">#due#</td>
													<td class="ta-right"><a title="Edit" edit-distribution
														number="#number#" due="#due#"
														class="btn btn-default btn-sm"><i
															class="glyphicon glyphicon-pencil"></i> <span
															distribution-value ori-value="#distribution#"></span></a></td>

													<td class="ta-right" toFixed="2">#gst#</td>

													<td class="ta-right"><a class="btn btn-warning btn-sm"
														title="Remove" action="Remove" action-value="#number#"><i
															class="glyphicon glyphicon-trash"></i></a></td>
												<tr>
												<tr class="no-data-template hide">
													<td colspan="9">
														<div class="padding-5">
															<i class="glyphicon glyphicon-exclamation-sign"></i> No
															data found.
														</div>
													</td>
												<tr>
												<tr class="loading-template hide">
													<td colspan="9">
														<div class="padding-5">
															<i class="glyphicon glyphicon-hourglass"></i> Loading.
														</div>
													</td>
												<tr>