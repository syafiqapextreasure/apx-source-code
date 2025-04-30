package com.ppk.topServiceImpl.userServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ppk.topEntity.formsEntity.GetFndBranch;
import com.ppk.topEntity.formsEntity.UserMembership;
import com.ppk.topEntity.formsEntity.dto.DropDownValueDto;
import com.ppk.topRepositry.formRepositry.GetFndBranchRepo;
import com.ppk.topService.formService.ppkMembershipRegistrationForm.PpkMembershipRegistrationForm;
import com.ppk.utilities.LogUtil;

@Service
public class PpkMembershipRegistrationFormImpl implements PpkMembershipRegistrationForm {

	@Autowired
	private GetFndBranchRepo branchRepo; 

	private Logger logUtil = LogUtil.getLogger(PpkMembershipRegistrationFormImpl.class);

	@Override
	public List<GetFndBranch> getFndBranchCodeandDesc() {
		logUtil.info("Starting getFndBranchCodeandDesc() method");
		List<GetFndBranch> branchList = null;
		try {
			branchList = branchRepo.getFndBranchCodeandDescDb();
			if (branchList == null) {
				logUtil.warn("Branch list is null");
			} else if (branchList.isEmpty()) {
				logUtil.warn("Branch list is empty");
			} else {
				logUtil.info("Retrieved branch list successfully, size: " + branchList.size());
			}
		} catch (Exception e) {
			logUtil.error("An error occurred while retrieving the branch list", e);
			throw e;
		}
		logUtil.info("Ending getFndBranchCodeandDesc() method");
		return branchList;
	}

	@Override
	public UserMembership saveMembeshipForm(UserMembership user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DropDownValueDto> getDropValueForRegistration() {
		logUtil.info("Starting getDropValueForRegistration() method");
		List<DropDownValueDto> branchList = null;
		try {
			branchList = branchRepo.getDropDownValueForRegistration();
			if (branchList == null) {
				logUtil.warn("Branch list is null");
			} else if (branchList.isEmpty()) {
				logUtil.warn("Branch list is empty");
			} else {
				logUtil.info("Retrieved branch list successfully, size: " + branchList.size());
			}
		} catch (Exception e) {
			logUtil.error("An error occurred while retrieving the branch list", e);
			throw e;
		}
		logUtil.info("Ending getFndBranchCodeandDesc() method");
		return branchList;
	}

	 
}