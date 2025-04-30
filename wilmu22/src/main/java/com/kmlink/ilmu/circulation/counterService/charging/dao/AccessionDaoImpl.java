/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 *  org.springframework.jdbc.core.RowMapper
 */
package com.kmlink.ilmu.circulation.counterService.charging.dao;

import com.kmlink.ilmu.circulation.counterService.charging.dao.AccessionDao;
import com.kmlink.ilmu.circulation.counterService.charging.model.Accession;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class AccessionDaoImpl
implements AccessionDao {
    private JdbcTemplate jdbcTemplate;

    public AccessionDaoImpl(DataSource dataSoruce) {
        this.jdbcTemplate = new JdbcTemplate(dataSoruce);
    }

    @Override
    public Accession findAccessionById(String accsionNo) {
        Accession result = new Accession();
        try {
            result = (Accession)this.jdbcTemplate.queryForObject("SELECT * FROM CTDOCM WHERE CT03DOCNO =?", new Object[]{accsionNo}, (RowMapper)new RowMapper<Accession>(){

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
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String findAccessionBranchByLocation(String itemLoca) {
        String query = "SELECT GL05BRNC FROM GLLOCA WHERE GL05LOCA=?";
        String itemBranch = (String)this.jdbcTemplate.queryForObject(query, new Object[]{itemLoca}, String.class);
        return itemBranch;
    }
}
