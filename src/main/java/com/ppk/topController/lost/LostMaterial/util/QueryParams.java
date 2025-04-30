package com.ppk.topController.lost.LostMaterial.util;

public class QueryParams {
    private int gl17Grid;
    private int ct06MatNo;
    private String ct06Tag;

    // Constructor
    public QueryParams(int gl17Grid, int ct06MatNo, String ct06Tag) {
        this.gl17Grid = gl17Grid;
        this.ct06MatNo = ct06MatNo;
        this.ct06Tag = ct06Tag;
    }

    // Getters
    public int getGl17Grid() {
        return gl17Grid;
    }

    public int getCt06MatNo() {
        return ct06MatNo;
    }

    public String getCt06Tag() {
        return ct06Tag;
    }
}
