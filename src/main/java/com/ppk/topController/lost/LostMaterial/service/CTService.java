package com.ppk.topController.lost.LostMaterial.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CTService {

    private final JdbcTemplate jdbcTemplate;

    public CTService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean CT_AccMaint(
            String CT03DOCNO, String CT03MATNO, String CT03LOCA, String CT03ICAT, String CT03VEND, 
            String CT03COND, String CT03INVOICE, String CT03SMD, String CT03INVDATE, String currency, 
            String CT03COPYNO, String CT03VOL, String CT03RATE, String copyType, String onthefly, 
            String foreignCost, String localCost, String sCost, String hCost) {

        // Convert date format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String currentDate = formatter.format(new Date());

        // Create parameter map
        Map<String, Object> params = new HashMap<>();
        params.put("CT03DOCNO", CT03DOCNO);
        params.put("CT03MATNO", CT03MATNO);
        params.put("CT03LOCA", CT03LOCA);
        params.put("CT03ICAT", CT03ICAT);
        params.put("CT03VEND", CT03VEND);
        params.put("CT03COND", CT03COND);
        params.put("CT03INVOICE", CT03INVOICE);
       // params.put("CT03INVDATE", DateTime.displayToDBFormat(CT03INVDATE)); // Convert Date
        params.put("CT03COPYNO", CT03COPYNO);
        params.put("CT03ONTHEFLY", onthefly);
        params.put("CT03VOLUME", CT03VOL);
        params.put("CT03ORG", copyType);
        params.put("CT03STAT", "F");
        params.put("CT03CRDATE", currentDate);
        params.put("CT03LCOST", Double.parseDouble(localCost));
        params.put("CT03FCOST", Double.parseDouble(foreignCost));
        params.put("CT03HCHAR", Double.parseDouble(hCost));
        params.put("CT03SCHAR", Double.parseDouble(sCost));
        params.put("CT03RATE", Double.parseDouble(CT03RATE));

        if (!CT03SMD.isEmpty()) {
            params.put("CT03SMD", CT03SMD);
        }
        if (!currency.isEmpty()) {
            params.put("CT03FOREX", currency);
        }

        // Create SQL Query
//        String sql = createInsertQuery("CTDOCM", params);

        // Execute the query
//        return jdbcTemplate.update(sql) > 0;
        return true;
    }

    public static String createInsertQuery(String table, Map<String, String> map, Map<String, Integer> intMap, Map<String, Double> doubleMap) {
        StringBuilder query = new StringBuilder("INSERT INTO ").append(table).append(" (");
        StringBuilder values = new StringBuilder(" VALUES (");

        // Append String values
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                query.append(entry.getKey()).append(", ");
                values.append(entry.getValue().isEmpty() ? "NULL" : "'" + entry.getValue() + "'").append(", ");
            }
        }

        // Append Integer values
        if (intMap != null) {
            for (Map.Entry<String, Integer> entry : intMap.entrySet()) {
                query.append(entry.getKey()).append(", ");
                values.append(entry.getValue()).append(", ");
            }
        }

        // Append Double values
        if (doubleMap != null) {
            for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                query.append(entry.getKey()).append(", ");
                values.append(entry.getValue()).append(", ");
            }
        }

        // Remove trailing commas
        if (query.charAt(query.length() - 2) == ',') {
            query.setLength(query.length() - 2);
            values.setLength(values.length() - 2);
        }

        query.append(")").append(values).append(")");
        return query.toString();
    }

}

