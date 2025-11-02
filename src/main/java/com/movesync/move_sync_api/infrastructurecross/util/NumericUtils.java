package com.movesync.move_sync_api.infrastructurecross.util;

public class NumericUtils {
    public static double roundTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static boolean isBetween(double value, double min, double max) {
        return value >= min && value <= max;
    }

    public static double percentage(double part, double total) {
        return (total == 0) ? 0 : (part * 100.0 / total);
    }
}
