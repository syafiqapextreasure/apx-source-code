package com.ppk.topRepositry.formRepositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.formsEntity.LostMaterialForm;

@Repository
public interface LostMaterialFormRepository extends JpaRepository<LostMaterialForm, Long> {

	@Query(value = "SELECT patron_id, " +
            "       MAX(id) AS id, " +
            "       MAX(acquisition_number) AS acquisition_number, " +
            "       MAX(borrow_date) AS borrow_date, " +
            "       MAX(return_date) AS return_date, " +
            "       MAX(payment_method) AS payment_method, " +
            "       MAX(total_payment) AS total_payment " +
            "FROM lost_material_form " +
            "GROUP BY patron_id", nativeQuery = true)
public List<Object[]> getAllLostFormRequestUnique();

// New method to get data by patron_id
@Query(value = "SELECT patron_id, " +
               "       id, " +
               "       acquisition_number, " +
               "       borrow_date, " +
               "       return_date, " +
               "       payment_method, " +
               "       total_payment " +
               "FROM lost_material_form " +
               "WHERE patron_id = :patronId", nativeQuery = true)
List<Object[]> getLostFormRequestByPatronId(@Param("patronId") String patronId);
}
