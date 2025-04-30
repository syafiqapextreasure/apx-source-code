package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Charging;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class null implements RowMapper<Charging> {
  public Charging mapRow(ResultSet rs, int rowNum) throws SQLException {
    ChargingServiceImpl.this.GL07ELIG = rs.getInt("GL07ELIG");
    ChargingServiceImpl.this.limit = rs.getString("GL07FINELIMIT");
    if (ChargingServiceImpl.this.limit == null) {
      ChargingServiceImpl.access$2(ChargingServiceImpl.this, 0.0D);
    } else {
      ChargingServiceImpl.access$2(ChargingServiceImpl.this, Double.parseDouble(rs.getString("GL07FINELIMIT")));
    } 
    return ChargingServiceImpl.this.charging;
  }
}
