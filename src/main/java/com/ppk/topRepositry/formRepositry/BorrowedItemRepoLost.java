package com.ppk.topRepositry.formRepositry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ppk.topEntity.formsEntity.LostBorrowedMaterial;

@Repository
public interface BorrowedItemRepoLost extends JpaRepository<LostBorrowedMaterial, Integer> {

}
