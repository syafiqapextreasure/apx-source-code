/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.counterService.charging.repository;

import com.kmlink.ilmu.circulation.counterService.charging.model.Accession;

public interface AccessionRepository {
    public Accession findAccessionById(String var1);

    public String findAccessionBranchByLocation(String var1);
}
