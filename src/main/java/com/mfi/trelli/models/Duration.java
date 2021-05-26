package com.mfi.trelli.models;

public enum Duration {
    six(6), twelve(12);

    private int durationVal;

    Duration(int durationVal) {
        this.durationVal = durationVal;
    }

    public int getDurationVal() {
        return durationVal;
    }
}
