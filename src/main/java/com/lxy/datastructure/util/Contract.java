package com.lxy.datastructure.util;

public class Contract {

    public static void unreachable() {
        fail("Unreachable");
    }

    public static void fail(String message) {
        throw new RuntimeException("Contract failed: " + message);
    }

    public static void require(boolean value, String message) {
        if (!value) {
            fail(message);
        }
    }
}
