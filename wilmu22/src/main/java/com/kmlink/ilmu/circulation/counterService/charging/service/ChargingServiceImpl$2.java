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

class ChargingServiceImpl$2
implements RowMapper<Charging> {
    ChargingServiceImpl$2() {
    }

    public Charging mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChargingServiceImpl.this.GL07ELIG = rs.getInt("GL07ELIG");
        ChargingServiceImpl.this.limit = rs.getString("GL07FINELIMIT");
        if (ChargingServiceImpl.this.limit == null) {
            ChargingServiceImpl.this.msFineLimit = 0.0;
        } else {
            ChargingServiceImpl.this.msFineLimit = Double.parseDouble(rs.getString("GL07FINELIMIT"));
        }
        return ChargingServiceImpl.this.charging;
    }
}
