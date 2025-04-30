package com.ppk.topService.formService;

import java.util.List;

import com.ppk.topEntity.errorEntity.UserNotFoundException;
import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import com.ppk.topEntity.formsEntity.ProposalFormEntity;
import com.ppk.topEntity.formsEntity.dto.MaterialProcurementProjection;

public interface MaterialProcurementProposalFormService {
	public MaterialProcurementProposalForm saveMaterialProposalForm(com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm form);
	public MaterialProcurementProposalForm getDataByEmailId(String email)throws UserNotFoundException;
	 public List<MaterialProcurementProposalForm> getAllMaterialRecommendationAdmin();

}
