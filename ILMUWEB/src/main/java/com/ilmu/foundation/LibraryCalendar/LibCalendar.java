/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.LibraryCalendar;

import com.ilmu.foundation.LibraryCalendar.CalendarTypeEnum;
import java.io.Serializable;

public class LibCalendar
implements Serializable {
    private static final long serialVersionUID = 1L;
    public String type;
    public String title;
    public String start;
    public String color;
    public String abbr;
    public String branch;

    public LibCalendar(String start, String branch, String title, String holType) {
        switch (holType) {
            case "1": {
                this.color = CalendarTypeEnum.STATE.getColor();
                this.abbr = CalendarTypeEnum.STATE.getAbbr();
                this.type = CalendarTypeEnum.STATE.getType();
                break;
            }
            case "2": {
                this.color = CalendarTypeEnum.FEDERAL.getColor();
                this.abbr = CalendarTypeEnum.FEDERAL.getAbbr();
                this.type = CalendarTypeEnum.FEDERAL.getType();
                break;
            }
            case "3": {
                this.color = CalendarTypeEnum.TERMBREAK.getColor();
                this.abbr = CalendarTypeEnum.TERMBREAK.getAbbr();
                this.type = CalendarTypeEnum.TERMBREAK.getType();
                break;
            }
            case "4": {
                this.color = CalendarTypeEnum.WEEKEND.getColor();
                this.abbr = CalendarTypeEnum.WEEKEND.getAbbr();
                this.type = CalendarTypeEnum.WEEKEND.getType();
            }
        }
        this.branch = branch;
        this.title = title;
        this.start = this.formatDate(start);
    }

    public LibCalendar(String start, String branch, String title) {
        this.color = CalendarTypeEnum.EVENT.getColor();
        this.abbr = CalendarTypeEnum.EVENT.getAbbr();
        this.type = CalendarTypeEnum.EVENT.getType();
        this.branch = branch;
        this.title = title;
        this.start = this.formatDate(start);
    }

    private String formatDate(String date) {
        return String.valueOf(date.substring(0, 4)) + "-" + date.substring(4, 6) + "-" + date.substring(6);
    }

    public String getColor() {
        return this.color;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStart() {
        return this.start;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public String getBranch() {
        return this.branch;
    }
}
