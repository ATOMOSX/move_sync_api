package com.movesync.move_sync_api.infrastructurecross.util;

import java.util.UUID;

public class IdGenerator {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String shortUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static String prefixedId(String prefix) {
        return prefix + "-" + shortUUID().toUpperCase();
    }
}
