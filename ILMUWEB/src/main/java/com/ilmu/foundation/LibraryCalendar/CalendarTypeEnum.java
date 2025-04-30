/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.LibraryCalendar;

public enum CalendarTypeEnum {
    STATE("1", "State", "rgb(0, 166, 90)"),
    FEDERAL("2", "Federal", "rgb(243, 156, 18)"),
    TERMBREAK("3", "Term Break", "rgb(0, 192, 239)"),
    WEEKEND("4", "Weekend", "rgb(221, 75, 57)"),
    EVENT("event", "Event", "rgb(60,141,188)");

    private String type;
    private String abbr;
    private String color;

    private CalendarTypeEnum(String type, String abbr, String color) {
        this.type = type;
        this.abbr = abbr;
        this.color = color;
    }

    public String getType() {
        return this.type;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public String getColor() {
        return this.color;
    }
}
