/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.colorSpineManagement.dao;

import com.kmlink.ilmu.colorSpineManagement.model.Records;
import java.util.List;

public interface ListDao {
    public List<Records> colourSequence();

    public List<Records> locationList();

    public List<Records> itemClassList();

    public List<Records> findRecordById(int var1);

    public int create(Records var1);

    public int update(Records var1);

    public int delete(int var1);
}
