package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class null implements RowMapper<Patron> {
  public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
    ChargingServiceImpl.access$4(ChargingServiceImpl.this, rs.getString("GL14NAME"));
    ChargingServiceImpl.access$5(ChargingServiceImpl.this, rs.getString("GL14PATR"));
    ChargingServiceImpl.this.details = rs.getString("CI02DDATE");
    return null;
  }
}
