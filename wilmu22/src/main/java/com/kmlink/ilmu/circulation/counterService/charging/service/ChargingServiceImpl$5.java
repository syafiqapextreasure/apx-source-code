/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class ChargingServiceImpl$5
implements RowMapper<Patron> {
    ChargingServiceImpl$5() {
    }

    public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChargingServiceImpl.this.msBorrowerName = rs.getString("GL14NAME");
        ChargingServiceImpl.this.msBorrowerID = rs.getString("GL14PATR");
        ChargingServiceImpl.this.details = rs.getString("CI02DDATE");
        return null;
    }
}
