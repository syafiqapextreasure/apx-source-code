package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Charging;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class null implements RowMapper<Charging> {
  public Charging mapRow(ResultSet rs, int rowNum) throws SQLException {
    ChargingServiceImpl.this.GL27ELIG = rs.getInt("GL27ELIG");
    ChargingServiceImpl.access$0(ChargingServiceImpl.this, rs.getInt("GL27PLOAN"));
    ChargingServiceImpl.access$1(ChargingServiceImpl.this, rs.getString("GL27ALLOWOVD"));
    return ChargingServiceImpl.this.charging;
  }
}
