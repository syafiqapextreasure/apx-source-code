/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.tagParameter;

import com.kmlink.ilmu.tagParameter.Indicator1;
import com.kmlink.ilmu.tagParameter.Indicator2;
import com.kmlink.ilmu.tagParameter.Subfields;
import com.kmlink.ilmu.tagParameter.TagParameter;
import java.util.List;

public class TagContainer {
    private List<TagParameter> params;
    private List<Indicator1> indicator1;
    private List<Indicator2> indicator2;
    private List<Subfields> subfields;

    public TagContainer(List<TagParameter> params, List<Indicator1> indicator1, List<Indicator2> indicator2, List<Subfields> subfields) {
        this.params = params;
        this.indicator1 = indicator1;
        this.indicator2 = indicator2;
        this.subfields = subfields;
    }

    public List<TagParameter> getParams() {
        return this.params;
    }

    public List<Indicator1> getIndicator1() {
        return this.indicator1;
    }

    public List<Indicator2> getIndicator2() {
        return this.indicator2;
    }

    public List<Subfields> getSubFields() {
        return this.subfields;
    }
}
