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

class PatronDaoImpl$1
implements RowMapper<Patron> {
    PatronDaoImpl$1() {
    }

    public Patron mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patron patron = new Patron();
        patron.setPatronName(rs.getString("GL14NAME"));
        patron.setPatronCate(rs.getString("GL14CATE"));
        patron.setPatronStatus(rs.getString("GL14STAT"));
        patron.setPatronBranch(rs.getString("GL14BRNC"));
        patron.setPatronMemDate(rs.getString("GL14MEMDATE"));
        patron.setPatronExpDate(rs.getString("GL14EXPDATE"));
        patron.setPatronDept(rs.getString("GL14DEPT"));
        return patron;
    }
}
