/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.jdbc.core.JdbcTemplate
 */
package com.kmlink.ilmu.circulation.counterService.charging.service;

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import com.kmlink.ilmu.circulation.counterService.charging.service.PatronService;
import java.text.DecimalFormat;
import org.springframework.jdbc.core.JdbcTemplate;

public class PatronServiceImpl
implements PatronService {
    private String msPatronCategory;
    private DecimalFormat df2 = new DecimalFormat(".##");
    private double msGetTotalFines = 0.0;
    private double msGetTotalFinesPaid = 0.0;
    private double msGetTotalFinesOutstanding = 0.0;
    private int msNoGetItemOverdue = 0;
    private JdbcTemplate jdbcTemplate;
    String errmessage = "";

    @Override
    public Patron checkPatron(String patronId) {
        return null;
    }
}
