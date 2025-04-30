package com.ppk.topRepositry.formRepositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import com.ppk.topEntity.formsEntity.ProposalFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * This interface provides CRUD operations and additional query methods
 * for interacting with the "proposal_form" table in the database.
 * @author SGC Technology
 * @version 3.0
 */
@Repository
public interface ProposalFormRepository extends JpaRepository<ProposalFormEntity, Long> {
   
}

