<!DOCTYPE html>
<html>
<body>
	<div class="btn-group pull-right ">
		<div class="col-md-1">
			<button type="button" id="Reterive" class="btn btn-primary"
				title="Retrieve">Retrieve</button>
		</div>
	</div>
	<ul id="hhh" class="listree">
		<li>
			<div class="listree-submenu-heading">Patron Database</div>
			<ul class="listree-submenu-items">
				<li>
					<div class="listree-submenu-heading">
						Total number of patrons = <span
							class="displayNumberPatronDatabase"></span>
					</div>
					<ul class="listree-submenu-items">
						<li>
							<div class="listree-submenu-heading">
								Active membership = <span class="displayNumberActivePatron"></span>
							</div>
							<ul class="listree-submenu-items">
								<li>
									<div class="listree-submenu-heading">
										Total by Patron Status = <span
											class="displayNumberActivePatron"></span>
									</div>
									<ul id="activeMemberPatronStatus" class="listree-submenu-items">
									</ul>
								</li>
								<li>
									<div class="listree-submenu-heading">
										Total by Patron Branch = <span
											class="displayNumberActivePatron"></span>
									</div>
									<ul id="activeMemberPatronBranch" class="listree-submenu-items">
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Expired membership = <span class="displayNumberExpiredPatron"></span>
							</div>
							<ul class="listree-submenu-items">
								<li>
									<div class="listree-submenu-heading">
										Total by Patron Status = <span
											class="displayNumberExpiredPatron"></span>
									</div>
									<ul id="expiredMemberPatronStatus"
										class="listree-submenu-items">
									</ul>
								</li>
								<li>
									<div class="listree-submenu-heading">
										Total by Patron Branch = <span
											class="displayNumberExpiredPatron"></span>
									</div>
									<ul id="expiredMemberPatronBranch"
										class="listree-submenu-items">
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>
		<li>
			<div class="listree-submenu-heading">Catalogue Database</div>
			<ul class="listree-submenu-items">
				<li>
					<div class="listree-submenu-heading">
						Total catalogued records = <span
							id="displayTotalCatalogueDatabase"></span>
					</div>
					<ul class="listree-submenu-items">
						<li>
							<div class="listree-submenu-heading">
								Total Catalogued records = <span id="displayTotalCatalogueRec"></span>
							</div>
							<ul class="listree-submenu-items">
								<li>
									<div>
										Indexed records = <span id="displayTotalIdxCatalogueRec"></span>
									</div>
									<div>
										Unindexed records = <span id="displayTotalUnindxCatalogueRec"></span>
									</div>
								</li>
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Total Acquisitions records = <span
									id="displayTotalAcquisitionsRec"></span>
							</div>
							<ul class="listree-submenu-items">
								<li>
									<div>
										Indexed records = <span id="displayTotalIdxAcquisitionsRec"></span>
									</div>
									<div>
										Unindexed records = <span
											id="displayTotalUnindxAcquisitionsRec"></span>
									</div>
								</li>
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Total Serials records = <span id="displayTotalSerialsRec"></span>
							</div>
							<ul class="listree-submenu-items">
								<li>
									<div>
										Indexed records = <span id="displayTotalIdxSerialsRec"></span>
									</div>
									<div>
										Unindexed records = <span id="displayTotalUnindxSerialsRec"></span>
									</div>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<div class="listree-submenu-heading">Accession Information</div>
					<ul class="listree-submenu-items">
						<li>
							<div class="listree-submenu-heading">Accession Number</div>
							<ul class="listree-submenu-items">
								<li>
									<div>
										First used = <span id="displayFirstUsedAccNum"></span>
									</div>
									<div>
										Last used = <span id="displayLastUsedAccNum"></span>
									</div>
								</li>
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Total by Item Category = <span
									class="displayTotalAccessionNumber"></span>
							</div>
							<ul class="listree-submenu-items"
								id="totalbyItemCategoryAccession">
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Total by Item Status = <span class="displayTotalAccessionNumber"></span>
							</div>
							<ul class="listree-submenu-items">
								<li id="totalbyItemStatusAccession"></li>
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Total by SMD = <span class="displayTotalAccessionNumber"></span>
							</div>
							<ul class="listree-submenu-items" id="totalBySMD">
							</ul>
						</li>
						<li>
							<div class="listree-submenu-heading">
								Total by Item Location = <span
									class="displayTotalAccessionNumber"></span>
							</div>
							<ul class="listree-submenu-items" id="totalByItemLocation">
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
	<script
		src="${pageContext.request.contextPath}/js/Foundation/Enquiry/AAGE0150.js"></script>
</body>
</html>