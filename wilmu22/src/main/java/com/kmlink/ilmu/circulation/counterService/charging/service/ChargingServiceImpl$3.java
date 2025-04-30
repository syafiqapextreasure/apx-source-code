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

class ChargingServiceImpl$3
implements RowMapper<Accession> {
    ChargingServiceImpl$3() {
    }

    public Accession mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChargingServiceImpl.this.accession.setDocNo(rs.getString("accessionNo"));
        ChargingServiceImpl.this.accession.setStatus(rs.getString("CT03STAT"));
        ChargingServiceImpl.this.accession.setItemCat(rs.getString("CT03ICAT"));
        ChargingServiceImpl.this.accession.setMatNo(rs.getString("CT03MATNO"));
        ChargingServiceImpl.this.accession.setSMD(rs.getString("CT03SMD"));
        ChargingServiceImpl.this.accession.setLocation(rs.getString("CT03LOCA"));
        ChargingServiceImpl.this.accession.setPatron(rs.getString("CT03PATR"));
        ChargingServiceImpl.this.accession.setCondition(rs.getString("CT03DDATE"));
        ChargingServiceImpl.this.accession.setItemBranch(rs.getString("GL05BRNC"));
        return ChargingServiceImpl.this.accession;
    }
}
