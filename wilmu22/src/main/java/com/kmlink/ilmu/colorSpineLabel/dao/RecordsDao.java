/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.colorSpineLabel.dao;

import com.kmlink.ilmu.colorSpineLabel.model.Records;
import java.util.List;

public interface RecordsDao {
    public List<Records> retrieveByAccessionNo(String var1, String var2);

    public List<Records> retrieveByAccessionRange(String var1, String var2, String var3);

    public List<Records> retrieveByCallNo(String var1, String var2);

    public List<Records> retrieveByAccessionDateRange(String var1, String var2, String var3);

    public List<Records> retrieveByControlNo(String var1, String var2);

    public List<Records> retrieveByItemCategory(String var1, String var2);

    public List<Records> retrieveBySMD(String var1, String var2);

    public List<Records> retrieveByTitle(String var1, String var2);

    public List<Records> pickListItemCategory();

    public List<Records> pickListSMD();

    public List<Records> filterByBranch();

    public List<Records> retrieveForLabel(String var1, String var2);

    public List<Records> findColour(String var1);

    public List<Records> getAllColour(String var1);

    public List<Records> findItemClass(String var1);

    public List<Records> findCutterColour(String var1);
}
