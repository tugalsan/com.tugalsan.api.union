package com.tugalsan.api.union.server;

public class TS_UnionUtils {

    public static <R> R throwIfRuntimeException(Throwable t) {
        if (t instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
