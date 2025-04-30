/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.counterService.charging.repository;

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;

public interface PatronRepository {
    public Patron findPatronById(String var1);

    public Patron retrievePatronDetailById(String var1);
}
