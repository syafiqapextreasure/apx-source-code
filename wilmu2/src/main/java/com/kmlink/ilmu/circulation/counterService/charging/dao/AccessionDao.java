/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.counterService.charging.dao;

import com.kmlink.ilmu.circulation.counterService.charging.model.Accession;

public interface AccessionDao {
    public Accession findAccessionById(String var1);

    public String findAccessionBranchByLocation(String var1);
}
