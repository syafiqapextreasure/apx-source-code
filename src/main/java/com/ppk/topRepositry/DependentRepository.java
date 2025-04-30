package com.ppk.topRepositry;

import com.ppk.topEntity.formsEntity.DependentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependentRepository extends JpaRepository<DependentEntity, Integer> {
    
    // Find all dependents by loginId
    List<DependentEntity> findByLoginId(String loginId);
    
    // Find all dependents by idPengguna
    List<DependentEntity> findByIdPengguna(String idPengguna);
} 