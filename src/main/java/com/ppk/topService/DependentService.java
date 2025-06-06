package com.ppk.topService;

import com.ppk.topEntity.formsEntity.DependentEntity;
import com.ppk.topEntity.formsEntity.DependentDetails;

import java.util.List;
import java.util.Optional;

public interface DependentService {
    
    List<DependentEntity> getAllDependents();
    
    Optional<DependentEntity> getDependentById(Integer id);
    
    List<DependentEntity> getDependentsByLoginId(String loginId);
    
    List<DependentEntity> getDependentsByIdPengguna(String idPengguna);
    
    DependentEntity saveDependent(DependentEntity dependent);
    
    void deleteDependent(Integer id);

    // Added method to get dependents by user ID as used in FormController
    List<DependentDetails> getDependentsByUserId(String userId);
} 