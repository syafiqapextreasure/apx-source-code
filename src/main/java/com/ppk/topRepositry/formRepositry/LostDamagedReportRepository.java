package com.ppk.topRepositry.formRepositry;

import org.apache.catalina.manager.host.HostManagerServlet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostDamagedReportRepository  extends JpaRepository<com.ppk.topEntity.formsEntity.LostDamagedReport, Long>{

	public com.ppk.topEntity.formsEntity.LostDamagedReport save(com.ppk.topEntity.formsEntity.LostDamagedReport damagedReport);
}
