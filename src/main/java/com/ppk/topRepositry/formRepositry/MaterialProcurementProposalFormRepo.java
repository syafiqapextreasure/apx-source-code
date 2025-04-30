package com.ppk.topRepositry.formRepositry;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import com.ppk.topEntity.formsEntity.ProposalFormEntity;
import com.ppk.topEntity.formsEntity.dto.MaterialProcurementProjection;

@Repository
public interface MaterialProcurementProposalFormRepo  extends JpaRepository<MaterialProcurementProposalForm, Long>{
public MaterialProcurementProposalForm save(MaterialProcurementProposalForm form);
public MaterialProcurementProposalForm findByEmail(String email);
//@Query(value = "SELECT id, judul, pengarang FROM portal_dump.ciusrr", nativeQuery = true)
//	List<MaterialProcurementProposalForm> fetchRequiredRecommendationField();
 
}
