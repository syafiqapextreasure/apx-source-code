/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.dao;

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class PatronDaoImpl$2
implements RowMapper<Patron> {
    PatronDaoImpl$2() {
    }

    public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patron patron = new Patron();
        patron.setPatronName(rs.getString("GL14NAME"));
        patron.setPatronCate(rs.getString("GL14CATE"));
        patron.setPatronStatus(rs.getString("GL14STAT"));
        patron.setPatronBranchDesc(rs.getString("GL14BRNC"));
        patron.setPatronMemDate(rs.getString("GL14MEMDATE"));
        patron.setPatronExpDate(rs.getString("GL14EXPDATE"));
        return patron;
    }
}
