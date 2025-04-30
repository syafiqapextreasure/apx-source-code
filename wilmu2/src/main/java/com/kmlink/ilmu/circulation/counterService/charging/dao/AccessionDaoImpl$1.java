/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.dao;

import com.kmlink.ilmu.circulation.counterService.charging.model.Accession;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class AccessionDaoImpl$1
implements RowMapper<Accession> {
    AccessionDaoImpl$1() {
    }

    public Accession mapRow(ResultSet rs, int rowNum) throws SQLException {
        Accession accession = new Accession();
        accession.setAccessionNo(rs.getString("CT03DOCNO"));
        accession.setStatus(rs.getString("CT03STAT"));
        accession.setItemCat(rs.getString("CT03ICAT"));
        accession.setItemBranch(rs.getString("CT03LOCA"));
        accession.setMatNo(rs.getString("CT03MATNO"));
        accession.setSMD(rs.getString("CT03SMD"));
        return accession;
    }
}
