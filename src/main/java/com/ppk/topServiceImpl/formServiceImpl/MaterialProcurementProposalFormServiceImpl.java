package com.ppk.topServiceImpl.formServiceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ppk.topEntity.errorEntity.UserNotFoundException;
import com.ppk.topEntity.formsEntity.MaterialProcurementProposalForm;
import com.ppk.topEntity.formsEntity.ProposalFormEntity;
import com.ppk.topEntity.formsEntity.dto.MaterialProcurementProjection;
import com.ppk.topRepositry.formRepositry.MaterialProcurementProposalFormRepo;
import com.ppk.topService.formService.MaterialProcurementProposalFormService;
import com.ppk.utilities.LogUtil;

@Service
public class MaterialProcurementProposalFormServiceImpl implements MaterialProcurementProposalFormService {

    private static final Logger logger = LogUtil.getLogger(MaterialProcurementProposalFormServiceImpl.class);

    @Autowired
    private MaterialProcurementProposalFormRepo formRepository;

    @Autowired
    private MaterialProcurementProposalFormRepo  materialProcurementProposalFormRepo;
    @Override
    public MaterialProcurementProposalForm saveMaterialProposalForm(MaterialProcurementProposalForm form) {
        logger.info("Request received to save material procurement proposal form.");

        if (ObjectUtils.isEmpty(form)) {
            logger.warn("Received empty form data. Throwing exception.");
            throw new IllegalArgumentException("Form data is empty or null.");
        }

        try {
            MaterialProcurementProposalForm savedForm = formRepository.save(form);
            if (ObjectUtils.isEmpty(savedForm)) {
                logger.error("Failed to save form data to the database. Returned null.");
                throw new RuntimeException("Failed to save form data.");
            }

            logger.info("Material procurement proposal form saved successfully with ID: {}", savedForm.getId());
            return savedForm;

        } catch (Exception e) {
            logger.error("An error occurred while saving material procurement proposal form: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred while saving the form data.", e);
        }
    }

    @Override
    public MaterialProcurementProposalForm getDataByEmailId(String email) throws UserNotFoundException {
        logger.info("Request received to retrieve data for email: {}", email);
        MaterialProcurementProposalForm dbFormDataByEmail = null;
        try {
            // Fetch data from the repository
            dbFormDataByEmail = formRepository.findByEmail(email);

            // Check if the data is empty
            if (ObjectUtils.isEmpty(dbFormDataByEmail)) {
                logger.warn("No records found for the provided email: {}", email);
                throw new UserNotFoundException("No records found for email: " + email);
            }

            logger.info("Data successfully retrieved for email: {}", email);
            return dbFormDataByEmail;

        } catch (UserNotFoundException ex) {
            logger.error("User not found exception: {}", ex.getMessage());
            throw ex; // Re-throw the UserNotFoundException to maintain proper exception handling
        } catch (Exception ex) {
            logger.error("An unexpected error occurred while fetching data for email: {}. Error: {}", email, ex.getMessage(), ex);
            throw new RuntimeException("An unexpected error occurred. Please try again later.");
        }
    }

	@Override
	public List<MaterialProcurementProposalForm> getAllMaterialRecommendationAdmin() {
	
	       try {
	           List<MaterialProcurementProposalForm> results = formRepository.findAll();
	           if (results.isEmpty()) {
	               logger.warn("No data found in MaterialProcurementRepository.");
	           } else {
	               logger.info("Successfully fetched {} records.", results.size());
	           }
	           return results;
	       } catch (Exception e) {
	           logger.error("An error occurred while fetching data from MaterialProcurementRepository: {}", e.getMessage(), e);
	           throw e; // Re-throwing the exception to propagate it to the calling code.
	       }
	   }
	
	
public Optional<MaterialProcurementProposalForm> getMaterialById(Long id) {
	return this.formRepository.findById(id);
}






    
	

}
