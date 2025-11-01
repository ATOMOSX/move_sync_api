package com.movesync.move_sync_api.infrastructurecross.util;

public class StringUtils {

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static String capitalizeWords(String text) {
        if (isNullOrEmpty(text)) return text;
        String[] words = text.trim().toLowerCase().split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String w : words) {
            capitalized.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1)).append(" ");
        }
        return capitalized.toString().trim();
    }

    public static String maskEmail(String email) {
        if (isNullOrEmpty(email) || !email.contains("@")) return email;
        String[] parts = email.split("@");
        String user = parts[0];
        String domain = parts[1];
        return user.charAt(0) + "***@" + domain;
    }

    public static String sanitize(String text) {
        return isNullOrEmpty(text) ? null : text.replaceAll("[\"']", "").trim();
    }
}
