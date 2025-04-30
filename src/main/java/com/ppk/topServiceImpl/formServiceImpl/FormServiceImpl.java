package com.ppk.topServiceImpl.formServiceImpl;


import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.ppk.topEntity.formsEntity.ProposalFormEntity;
import com.ppk.topRepositry.formRepositry.ProposalFormRepository;
import com.ppk.topService.formService.FormService;
import com.ppk.utilities.LogUtil;

@Service	
public class FormServiceImpl  implements FormService{
 private static Logger logger=LogUtil.getLogger(FormServiceImpl.class);
 
 @Autowired
 private ProposalFormRepository formRepository;
 
	
 @Override
 public ProposalFormEntity saveProposalForm(ProposalFormEntity frontedFormData) {
     logger.info("Request received in saveProposalForm method.");

     if (ObjectUtils.isEmpty(frontedFormData)) {
         logger.warn("Received empty form data. Throwing exception.");
         throw new IllegalArgumentException("Form data is empty or null.");
     }

     try {
         ProposalFormEntity savedFormData = formRepository.save(frontedFormData);
         if (ObjectUtils.isEmpty(savedFormData)) {
             logger.error("Failed to save form data to the database. Returned null.");
             throw new RuntimeException("Failed to save form data.");
         }

         logger.info("Form data successfully saved in the database: {}");
         return savedFormData;

     } catch (Exception e) {
         logger.error("An error occurred while saving form data: {}", e.getMessage(), e);
         throw new RuntimeException("An unexpected error occurred while saving the form data.", e);
     }
 }


}
