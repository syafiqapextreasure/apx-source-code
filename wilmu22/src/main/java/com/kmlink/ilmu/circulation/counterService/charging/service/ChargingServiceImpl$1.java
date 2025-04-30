/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Charging;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class ChargingServiceImpl$1
implements RowMapper<Charging> {
    ChargingServiceImpl$1() {
    }

    public Charging mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChargingServiceImpl.this.GL27ELIG = rs.getInt("GL27ELIG");
        ChargingServiceImpl.this.msLoanPeriod = rs.getInt("GL27PLOAN");
        ChargingServiceImpl.this.msAllowOvd = rs.getString("GL27ALLOWOVD");
        return ChargingServiceImpl.this.charging;
    }
}
