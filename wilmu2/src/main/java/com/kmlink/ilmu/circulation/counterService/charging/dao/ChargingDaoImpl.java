/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 */
package com.kmlink.ilmu.circulation.counterService.charging.dao;

import com.kmlink.ilmu.circulation.counterService.charging.dao.ChargingDao;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ChargingDaoImpl
implements ChargingDao {
    private JdbcTemplate jdbcTemplate;

    public ChargingDaoImpl(DataSource dataSoruce) {
        this.jdbcTemplate = new JdbcTemplate(dataSoruce);
    }
}
