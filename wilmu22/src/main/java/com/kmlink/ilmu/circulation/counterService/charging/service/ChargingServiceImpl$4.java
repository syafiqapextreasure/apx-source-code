/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Accession;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class ChargingServiceImpl$4
implements RowMapper<Accession> {
    ChargingServiceImpl$4() {
    }

    public Accession mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChargingServiceImpl.this.itemStatCode = rs.getString("CODE");
        ChargingServiceImpl.this.status = rs.getString("DESCRIPTION");
        return ChargingServiceImpl.this.accession;
    }
}
