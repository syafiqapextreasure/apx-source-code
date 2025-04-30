<%@ page contentType="text/html; charset=UTF-8"%>
<head>
<style>
.form-group {
	margin-bottom: 5px;
}
</style>
</head>
<div class="modal-content">
	<!-- Modal content-->
	<div class="modal-header"
		style="display: flex; align-items: center; justify-content: space-between;">
		<h5 class="modal-title" id="modalTagParameter" align="center"
			style="text-align: center; flex-grow: 1;">Add Tag Parameter</h5>
		<button type="button" id="closeModalAddTagParameter" class="close"
			data-dismiss="modal" aria-label="Close" style="margin-left: auto;">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>

	<form role="form" class="form-horizontal" id="formdataAddTagParameter"
		method="post">
		<br>
		<div class="form-group" id="addTagParameterView">
			<div class="col-sm-1"></div>
			<div class="row form-group">
				<div class="col-sm-3">
					<label>MARC:</label>
				</div>
				<div class="col-sm-3">
					<select class="form-control" id="fCodeH" name="fCodeH"
						aria-label="Default select example">
						<jsp:include page="../../../../module/Select_Fndcode.jsp">
							<jsp:param name="fcode" value="H" />
						</jsp:include>
					</select>
				</div>
			</div>

			<div class="col-sm-1"></div>
			<div class="row form-group">
				<div class="col-sm-3">
					<label>Tag:</label>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="tagParameterNo"
						name="tagParameterNo">
				</div>

				<div class="col-sm-2">
					<span>Revised from Tag:</span>
				</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" id="revisedTagNo"
						name="revisedTagNo">
				</div>
			</div>

			<div class="col-sm-1"></div>
			<div class="row form-group">
				<div class="col-sm-3">
					<span>Description:</span>
				</div>
				<div class="col-sm-7">
					<input type="text" class="form-control upCaseLetter"
						id="description" name="description">
				</div>
			</div>

			<div class="col-sm-1"></div>
			<div class="row form-group">
				<div class="col-sm-3">
					<span>Abbreviated Desc.</span>
				</div>
				<div class="col-sm-5">
					<input type="text" class="form-control upCaseLetter"
						id="abbreviatedDesc" name="abbreviatedDesc">
				</div>
			</div>
		</div>

		<div class="modal-body" id="detail">
			<ul class="nav nav-tabs" role="tablist">
				<li id="Parameters" class="active"><a href="#parameters-tab"
					aria-controls="parameters-tab" role="tab" data-toggle="tab">Parameters
				</a></li>
				<li id="Indicator1"><a href="#indicator-1-tab"
					aria-controls="indicator-1-tab" role="tab" data-toggle="tab"
					id="indi1">Indicator 1</a></li>
				<li id="Indicator2"><a href="#indicator-2-tab"
					aria-controls="indicator-2-tab" role="tab" data-toggle="tab">Indicator
						2</a></li>
				<li id="Subfield(a-j)"><a href="#subfieldaj-tab"
					aria-controls="subfieldaj-tab" role="tab" data-toggle="tab">Subfields
						(a-j)</a></li>
				<li id="Subfield(k-t)"><a href="#subfieldkt-tab"
					aria-controls="subfieldkt-tab" role="tab" data-toggle="tab">Subfields
						(k-t)</a></li>
				<li id="Subfield(u-z)"><a href="#subfielduz-tab"
					aria-controls="error-in-supply-tab" role="tab" data-toggle="tab">Subfields
						(u-z)</a></li>
				<li id="Subfield09"><a href="#subfield09-tab"
					aria-controls="subfield09-tab" role="tab" data-toggle="tab">Subfields
						(0-9)</a></li>
			</ul>
		</div>



		<!-- TAB CONTENT -->
		<div class="tab-content">
			<!-- TAB PARAMETER -->
			<div role="tabpanel" class="tab-pane pwidth-95 active"
				id="parameters-tab">
				<div class="margin-btm-15"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<label>Index Table:</label>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control upCaseLetter"
											id="indexTable" name="indexTable">
									</div>

									<div class="row">
										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxRepeatable" value="N" name="checkboxRepeatable">&nbsp;
												Repeatable</span>
										</div>

										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxAuthFlag" value="N" name="checkboxAuthFlag">&nbsp;
												Authority Flag</span>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Acronym:</span>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control upCaseLetter"
											id="acronym" name="acronym">
									</div>

									<div class="row">
										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxMandatory" value="N" name="checkboxMandatory">&nbsp;
												Mandatory</span>
										</div>

										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxCopyPaste" value="N" name="checkboxCopyPaste">&nbsp;
												Copy And Paste</span>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Authority Group:</span>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control upCaseLetter"
											id="authGroup" name="authGroup">
									</div>
									<div class="row">
										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxIndFlag" value="N" name="checkboxIndFlag">&nbsp;
												Index Flag</span>
										</div>

										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxParamLink" value="N" name="checkboxParamLink">&nbsp;
												PARAMIPS Link</span>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Field Length:</span>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="fieldLength"
											name="fieldLength">
									</div>
									<div class="row">
										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxKeyword" value="N" name="checkboxKeyword">&nbsp;
												Keyword</span>
										</div>

										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxMultimediaTag" value="N"
												name="checkboxMultimediaTag">&nbsp; Multimedia tag</span>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Index Language:</span>
									</div>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="indexLang"
											name="indexLang">
									</div>

									<div class="row">
										<div class="col-sm-3">
											<span><input type="checkbox" class="form-check-input"
												id="checkboxDuplicateCheck" value="N"
												name="checkboxDuplicateCheck">&nbsp; Duplicate Check</span>
										</div>
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Default Indicator:</span>
									</div>

									<div class="col-sm-9">
										<div class="row">
											<div class="col-sm-2">
												<span>Indicator 1</span>
											</div>
											<div class="col-sm-2">
												<select class="form-control" id="indicator-value-1"
													aria-label="Default select example">
													<%-- <jsp:include page="../../../../module/Indicator_Value.jsp"></jsp:include> --%>
												</select>
											</div>

											<div class="col-sm-2">
												<span>Indicator 2</span>
											</div>
											<div class="col-sm-2">
												<select class="form-control" id="indicator-value-2"
													aria-label="Default select example">
													<%-- <jsp:include page="../../../../module/Indicator_Value.jsp"></jsp:include> --%>
												</select>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Default Value:</span>
									</div>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="defaultValue"
											name="defaultValue">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-1"></div>
									<div class="col-sm-2">
										<span>Remark:</span>
									</div>
									<div class="col-sm-7">
										<input type="text" class="form-control upCaseLetter"
											id="remark" name="remark">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB PARAMETER -->

			<!-- TAB INDICATOR 1 -->
			<div role="tabpanel"
				class="tab-pane pwidth-95 read-input-only selected indi1"
				id="indicator-1-tab">
				<div class="margin-btm-15"></div>
				<div class="row ">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-1" name="chkbIndi1-1"
											value="#">&nbsp; Undefined(#)</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi101" type="text"
											id="chkbIndi1-11" name="chkbIndi1-11">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-2" value="0"
											name="chkbIndi1-2">&nbsp; 0</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi10" type="text"
											id="chkbIndi1-21" name="chkbIndi1-21">
									</div>
								</div>

								<div id="element" class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-3" value="1"
											name="chkbIndi1-3">&nbsp; 1</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi11" type="text"
											id="chkbIndi1-31" name="chkbIndi1-31">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-4" value="2"
											name="chkbIndi1-4">&nbsp; 2</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi12" type="text"
											id="chkbIndi1-41" name="chkbIndi1-41">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3" id="test">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-5" value="3"
											name="chkbIndi1-5">&nbsp; 3</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi13" type="text"
											id="chkbIndi1-51" name="chkbIndi1-51">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-6" value="4"
											name="chkbIndi1-6">&nbsp; 4</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi14" type="text"
											id="chkbIndi1-61" name="chkbIndi1-61">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-7" value="5"
											name="chkbIndi1-7">&nbsp; 5</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi15" type="text"
											id="chkbIndi1-71" name="chkbIndi1-71">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-8" value="6"
											name="chkbIndi1-8">&nbsp; 6</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi16" type="text"
											id="chkbIndi1-81" name="chkbIndi1-81">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-9" value="7"
											name="chkbIndi1-9">&nbsp; 7</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi17" type="text"
											id="chkbIndi1-91" name="chkbIndi1-91">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-10" value="8"
											name="chkbIndi1-10">&nbsp; 8</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi18" type="text"
											id="chkbIndi1-101" name="chkbIndi1-101">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi1-111" value="9"
											name="chkbIndi1-111">&nbsp; 9</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi19" type="text"
											id="chkbIndi1-1111" name="chkbIndi1-1111">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB INDICATOR 1 -->


			<!-- TAB INDICATOR 2 -->
			<div role="tabpanel"
				class="tab-pane pwidth-95 read-input-only selected indi2"
				id="indicator-2-tab">
				<div class="margin-btm-15"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id=chkbIndi2-1 value="#"
											name="chkbIndi2-1">&nbsp; Undefined(#)</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi202" type="text"
											id="chkbIndi2-11" name="chkbIndi2-11">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-2" value="0"
											name="chkbIndi2-2">&nbsp; 0</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi20" type="text"
											id="chkbIndi2-21" name="chkbIndi2-21">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-3" value="1"
											name="chkbIndi2-3">&nbsp; 1</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi21" type="text"
											id="chkbIndi2-31" name="chkbIndi2-31">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-4" value="2"
											name="chkbIndi2-4">&nbsp; 2</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi22" type="text"
											id="chkbIndi2-41" name="chkbIndi2-41">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-5" value="3"
											name="chkbIndi2-5">&nbsp; 3</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi23" type="text"
											id="chkbIndi2-51" name="chkbIndi2-51">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-6" value="4"
											name="chkbIndi2-6">&nbsp; 4</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi24" type="text"
											id="chkbIndi2-61" name="chkbIndi2-61">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-7" value="5"
											name="chkbIndi2-7">&nbsp; 5</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi25" type="text"
											id="chkbIndi2-71" name="chkbIndi2-71">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-8" value="6"
											name="chkbIndi2-8">&nbsp; 6</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi26" type="text"
											id="chkbIndi2-81" name="chkbIndi2-81">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-9" value="7"
											name="chkbIndi2-9">&nbsp; 7</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi27" type="text"
											id="chkbIndi2-91" name="chkbIndi2-91">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-10" value="8"
											name="chkbIndi2-10">&nbsp; 8</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi28" type="text"
											id="chkbIndi2-101" name="chkbIndi2-101">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-3">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbIndi2-111" value="9"
											name="chkbIndi2-111">&nbsp; 9</span>
									</div>
									<div class="col-sm-9">
										<input class="form-control indi29" type="text"
											id="chkbIndi2-1111" name="chkbIndi2-1111">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB INDICATOR 2 -->

			<!-- TAB SUBFIELD(a-j) -->
			<div role="tabpanel"
				class="tab-pane pwidth-95 read-input-only selected subfield"
				id="subfieldaj-tab">
				<div class="margin-btm-15"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-1" value="a"
											name="chkbSubf-tab1-1">&nbsp; a</span> <span
											class="read-input-only-manda-repeat sub-m-a"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-1m" value="N" name="chkbSubf-tab1-1m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-a"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-1r" value="N" name="chkbSubf-tab1-1r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control suba" type="text"
											id="chkbSubf-tab1-11" name="chkbSubf-tab1-11">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-2" value="b"
											name="chkbSubf-tab1-2">&nbsp; b</span> <span
											class="read-input-only-manda-repeat sub-m-b"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-2m" value="N" name="chkbSubf-tab1-2m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-b"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-2r" value="N" name="chkbSubf-tab1-2r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subb" type="text"
											id="chkbSubf-tab1-21" name="chkbSubf-tab1-21">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-3" value="c"
											name="chkbSubf-tab1-3">&nbsp; c</span> <span
											class="read-input-only-manda-repeat sub-m-c"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-3m" value="N" name="chkbSubf-tab1-3m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-c"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-3r" value="N" name="chkbSubf-tab1-3r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subc" type="text"
											id="chkbSubf-tab1-31" name="chkbSubf-tab1-31">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-4" value="d"
											name="chkbSubf-tab1-4">&nbsp; d</span> <span
											class="read-input-only-manda-repeat sub-m-d"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-4m" value="N" name="chkbSubf-tab1-4m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-d"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-4r" value="N" name="chkbSubf-tab1-4r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subd" type="text"
											id="chkbSubf-tab1-41" name="chkbSubf-tab1-41">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-5" value="e"
											name="chkbSubf-tab1-5">&nbsp; e</span> <span
											class="read-input-only-manda-repeat sub-m-e"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-5m" value="N" name="chkbSubf-tab1-5m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-e"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-5r" value="N" name="chkbSubf-tab1-5r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sube" type="text"
											id="chkbSubf-tab1-51" name="chkbSubf-tab1-51">
									</div>
								</div>


								<div class="form-group subf">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-6" value="f"
											name="chkbSubf-tab1-6">&nbsp; f</span> <span
											class="read-input-only-manda-repeat sub-m-f"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-6m" value="N" name="chkbSubf-tab1-6m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-f"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-6r" value="N" name="chkbSubf-tab1-6r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subf" type="text"
											id="chkbSubf-tab1-61" name="chkbSubf-tab1-61">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-7" value="g"
											name="chkbSubf-tab1-7">&nbsp; g</span> <span
											class="read-input-only-manda-repeat sub-m-g"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-7m" value="N" name="chkbSubf-tab1-7m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-g"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-7r" value="N" name="chkbSubf-tab1-7r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subg" type="text"
											id="chkbSubf-tab1-71" name="chkbSubf-tab1-71">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-8" value="h"
											name="chkbSubf-tab1-8">&nbsp; h</span> <span
											class="read-input-only-manda-repeat sub-m-h"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-8m" value="N" name="chkbSubf-tab1-8m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-h"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-8r" value="N" name="chkbSubf-tab1-8r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subh" type="text"
											id="chkbSubf-tab1-81" name="chkbSubf-tab1-81">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-9" value="i"
											name="chkbSubf-tab1-9">&nbsp; i</span> <span
											class="read-input-only-manda-repeat sub-m-i"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-9m" value="N" name="chkbSubf-tab1-9m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-i"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-9r" value="N" name="chkbSubf-tab1-9r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subi" type="text"
											id="chkbSubf-tab1-91" name="chkbSubf-tab1-91">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab1-10" value="j"
											name="chkbSubf-tab1-10">&nbsp; j</span> <span
											class="read-input-only-manda-repeat sub-m-j"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-10m" value="N" name="chkbSubf-tab1-10m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-j"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab1-10r" value="N" name="chkbSubf-tab1-10r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subj" type="text"
											id="chkbSubf-tab1-101" name="chkbSubf-tab1-101">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB SUBFIELD(a-j) -->

			<!-- TAB SUBFIELD(k-t) -->
			<div role="tabpanel"
				class="tab-pane pwidth-95 read-input-only selected subfield"
				id="subfieldkt-tab">
				<div class="margin-btm-15"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-11" value="k"
											name="chkbSubf-tab2-11">&nbsp; k</span> <span
											class="read-input-only-manda-repeat sub-m-k"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-11m" value="N" name="chkbSubf-tab2-11m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-k"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-11r" value="N" name="chkbSubf-tab2-11r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subk" type="text"
											id="chkbSubf-tab2-111" name="chkbSubf-tab2-111">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-12" value="l"
											name="chkbSubf-tab2-12">&nbsp; l</span> <span
											class="read-input-only-manda-repeat sub-m-l"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-12m" value="N" name="chkbSubf-tab2-12m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-l"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-12r" value="N" name="chkbSubf-tab2-12r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subl" type="text"
											id="chkbSubf-tab2-121" name="chkbSubf-tab2-121">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-13" value="m"
											name="chkbSubf-tab2-13">&nbsp; m</span> <span
											class="read-input-only-manda-repeat sub-m-m"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-13m" value="N" name="chkbSubf-tab2-13m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-m"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-13r" value="N" name="chkbSubf-tab2-13r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subm" type="text"
											id="chkbSubf-tab2-131" name="chkbSubf-tab2-131">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-14" value="n"
											name="chkbSubf-tab2-14">&nbsp; n</span> <span
											class="read-input-only-manda-repeat sub-m-n"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-14m" value="N" name="chkbSubf-tab2-14m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-n"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-14r" value="N" name="chkbSubf-tab2-14r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subn" type="text"
											id="chkbSubf-tab2-141" name="chkbSubf-tab2-141">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-15" value="o"
											name="chkbSubf-tab2-15">&nbsp; o</span> <span
											class="read-input-only-manda-repeat sub-m-o"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-15m" value="N" name="chkbSubf-tab2-15m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-o"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-15r" value="N" name="chkbSubf-tab2-15r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subo" type="text"
											id="chkbSubf-tab2-151" name="chkbSubf-tab2-151">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-16" value="p"
											name="chkbSubf-tab2-16">&nbsp; p</span> <span
											class="read-input-only-manda-repeat sub-m-p"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-16m" value="N" name="chkbSubf-tab2-16m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-p"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-16r" value="N" name="chkbSubf-tab2-16r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subp" type="text"
											id="chkbSubf-tab2-161" name="chkbSubf-tab2-161">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-17" value="q"
											name="chkbSubf-tab2-17">&nbsp; q</span> <span
											class="read-input-only-manda-repeat sub-m-q"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-17m" value="N" name="chkbSubf-tab2-17m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-q"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-17r" value="N" name="chkbSubf-tab2-17r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subq" type="text"
											id="chkbSubf-tab2-171" name="chkbSubf-tab2-171">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-18" value="r"
											name="chkbSubf-tab2-18">&nbsp; r</span> <span
											class="read-input-only-manda-repeat sub-m-r"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-18m" value="N" name="chkbSubf-tab2-18m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-r"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-18r" value="N" name="chkbSubf-tab2-18r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subr" type="text"
											id="chkbSubf-tab2-181" name="chkbSubf-tab2-181">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-19" value="s"
											name="chkbSubf-tab2-19">&nbsp; s</span> <span
											class="read-input-only-manda-repeat sub-m-s"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-19m" value="N" name="chkbSubf-tab2-19m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-s"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-19r" value="N" name="chkbSubf-tab2-19r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subs" type="text"
											id="chkbSubf-tab2-191" name="chkbSubf-tab2-191">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab2-20" value="t"
											name="chkbSubf-tab2-20">&nbsp; t</span> <span
											class="read-input-only-manda-repeat sub-m-t"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-20m" value="N" name="chkbSubf-tab2-20m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-t"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab2-20r" value="N" name="chkbSubf-tab2-20r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subt" type="text"
											id="chkbSubf-tab2-201" name="chkbSubf-tab2-201">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB SUBFIELD(k-t) -->


			<!-- TAB SUBFIELD(u-z) -->
			<div role="tabpanel"
				class="tab-pane pwidth-95 read-input-only selected subfield"
				id="subfielduz-tab">
				<div class="margin-btm-15"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab3-21" value="u"
											name="chkbSubf-tab3-21">&nbsp; u</span> <span
											class="read-input-only-manda-repeat sub-m-u"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-21m" value="N" name="chkbSubf-tab3-21m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-u"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-21r" value="N" name="chkbSubf-tab3-21r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subu" type="text"
											id="chkbSubf-tab3-211" name="chkbSubf-tab3-211">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab3-22" value="v"
											name="chkbSubf-tab3-22">&nbsp; v</span> <span
											class="read-input-only-manda-repeat sub-m-v"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-22m" value="N" name="chkbSubf-tab3-22m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-v"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-22r" value="N" name="chkbSubf-tab3-22r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subv" type="text"
											id="chkbSubf-tab3-221" name="chkbSubf-tab3-221">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab3-23" value="w"
											name="chkbSubf-tab3-23">&nbsp; w</span> <span
											class="read-input-only-manda-repeat sub-m-w"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-23m" value="N" name="chkbSubf-tab3-23m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-w"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-23r" value="N" name="chkbSubf-tab3-23r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subw" type="text"
											id="chkbSubf-tab3-231" name="chkbSubf-tab3-231">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab3-24" value="x"
											name="chkbSubf-tab3-24">&nbsp; x</span> <span
											class="read-input-only-manda-repeat sub-m-x"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-24m" value="N" name="chkbSubf-tab3-24m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-x"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-24r" value="N" name="chkbSubf-tab3-24r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subx" type="text"
											id="chkbSubf-tab3-241" name="chkbSubf-tab3-241">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab3-25" value="y"
											name="chkbSubf-tab3-25">&nbsp; y</span> <span
											class="read-input-only-manda-repeat sub-m-y"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-25m" value="N" name="chkbSubf-tab3-25m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-y"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-25r" value="N" name="chkbSubf-tab3-25r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control suby" type="text"
											id="chkbSubf-tab3-251" name="chkbSubf-tab3-251">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab3-26" value="z"
											name="chkbSubf-tab3-26">&nbsp; z</span> <span
											class="read-input-only-manda-repeat sub-m-z"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-26m" value="N" name="chkbSubf-tab3-26m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-z"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab3-26r" value="N" name="chkbSubf-tab3-26r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control subz" type="text"
											id="chkbSubf-tab3-261" name="chkbSubf-tab3-261">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB SUBFIELD(u-z) -->

			<!-- TAB SUBFIELD(0-9) -->
			<div role="tabpanel"
				class="tab-pane pwidth-95 read-input-only selected subfield"
				id="subfield09-tab">
				<div class="margin-btm-15"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-body">
							<div class="col-xs-12 form-horizontal">
								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-27" value="0"
											name="chkbSubf-tab4-27">&nbsp; 0</span> <span
											class="read-input-only-manda-repeat sub-m-0"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-27m" value="N" name="chkbSubf-tab4-27m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-0"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-27r" value="N" name="chkbSubf-tab4-27r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub0" type="text"
											id="chkbSubf-tab4-271" name="chkbSubf-tab4-271">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-28" value="1"
											name="chkbSubf-tab4-28">&nbsp; 1</span> <span
											class="read-input-only-manda-repeat sub-m-1"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-28m" value="N" name="chkbSubf-tab4-28m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-1"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-28r" value="N" name="chkbSubf-tab4-28r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub1" type="text"
											id="chkbSubf-tab4-281" name="chkbSubf-tab4-281">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-29" value="2"
											name="chkbSubf-tab4-29">&nbsp; 2</span> <span
											class="read-input-only-manda-repeat sub-m-2"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-29m" value="N" name="chkbSubf-tab4-29m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-2"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-29r" value="N" name="chkbSubf-tab4-29r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub2" type="text"
											id="chkbSubf-tab4-291" name="chkbSubf-tab4-291">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-30" value="3"
											name="chkbSubf-tab4-30">&nbsp; 3</span> <span
											class="read-input-only-manda-repeat sub-m-3"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-30m" value="N" name="chkbSubf-tab4-30m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-3"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-30r" value="N" name="chkbSubf-tab4-30r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub3" type="text"
											id="chkbSubf-tab4-301" name="chkbSubf-tab4-301">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-31" value="4"
											name="chkbSubf-tab4-31">&nbsp; 4</span> <span
											class="read-input-only-manda-repeat sub-m-4"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-31m" value="N" name="chkbSubf-tab4-31m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-4"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-31r" value="N" name="chkbSubf-tab4-31r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub4" type="text"
											id="chkbSubf-tab4-311" name="chkbSubf-tab4-311">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-32" value="5"
											name="chkbSubf-tab4-32">&nbsp; 5</span> <span
											class="read-input-only-manda-repeat sub-m-5"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-32m" value="N" name="chkbSubf-tab4-32m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-5"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-32r" value="N" name="chkbSubf-tab4-32r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub5" type="text"
											id="chkbSubf-tab4-321" name="chkbSubf-tab4-321">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-33" value="6"
											name="chkbSubf-tab4-33">&nbsp; 6</span> <span
											class="read-input-only-manda-repeat sub-m-6"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-33m" value="N" name="chkbSubf-tab4-33m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-6"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-33r" value="N" name="chkbSubf-tab4-33r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub6" type="text"
											id="chkbSubf-tab4-331" name="chkbSubf-tab4-331">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-34" value="7"
											name="chkbSubf-tab4-34">&nbsp; 7</span> <span
											class="read-input-only-manda-repeat sub-m-7"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-34m" value="N" name="chkbSubf-tab4-34m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-7"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-34r" value="N" name="chkbSubf-tab4-34r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub7" type="text"
											id="chkbSubf-tab4-341" name="chkbSubf-tab4-341">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-35" value="8"
											name="chkbSubf-tab4-35">&nbsp; 8</span> <span
											class="read-input-only-manda-repeat sub-m-8"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-35m" value="N" name="chkbSubf-tab4-35m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-8"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-35r" value="N" name="chkbSubf-tab4-35r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub8" type="text"
											id="chkbSubf-tab4-351" name="chkbSubf-tab4-351">
									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-5">
										<span style="padding-left: 58px"><input type="checkbox"
											class="form-check-input" id="chkbSubf-tab4-36" value="9"
											name="chkbSubf-tab4-36">&nbsp; 9</span> <span
											class="read-input-only-manda-repeat sub-m-9"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-36m" value="N" name="chkbSubf-tab4-36m">&nbsp;
											Mandatory</span> <span class="read-input-only-manda-repeat sub-r-9"><input
											type="checkbox" class="form-check-input"
											id="chkbSubf-tab4-36r" value="N" name="chkbSubf-tab4-36r">&nbsp;
											Repeatable</span>
									</div>
									<div class="col-sm-7">
										<input class="form-control sub9" type="text"
											id="chkbSubf-tab4-361" name="chkbSubf-tab4-361">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END TAB SUBFIELD(0-9) -->
		</div>

		<!-- END TAB CONTENT -->
		<div class="modal-footer form-horizontal">
			<button type="button" id="save" class="btn btn-primary">Save</button>
			<button type="button" id="close" class="btn btn-default"
				data-dismiss="modal">Cancel</button>
		</div>
	</form>
	<!-- END Modal content-->
</div>