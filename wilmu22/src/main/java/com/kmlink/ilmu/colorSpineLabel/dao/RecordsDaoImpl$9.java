/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.colorSpineLabel.dao;

import com.kmlink.ilmu.colorSpineLabel.model.Records;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class RecordsDaoImpl$9
implements RowMapper<Records> {
    RecordsDaoImpl$9() {
    }

    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {
        Records itemCategory = new Records();
        itemCategory.setItemCategory(rs.getString("ItemCategory"));
        itemCategory.setItemCategoryDesc(rs.getString("Description"));
        return itemCategory;
    }
}
