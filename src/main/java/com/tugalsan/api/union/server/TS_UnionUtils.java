package com.tugalsan.api.union.server;

public class TS_UnionUtils {

    public static <R> R throwAsRuntimeException(Throwable t) {
        if (t instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        throw new RuntimeException(t);
    }
}
