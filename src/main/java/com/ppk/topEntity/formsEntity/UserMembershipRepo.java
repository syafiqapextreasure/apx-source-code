package com.ppk.topEntity.formsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMembershipRepo extends JpaRepository<UserMembership, Long> {

}
