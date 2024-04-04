package com.tugalsan.api.union.client;

public class TGS_UnionUtils {

    public static void throwAsRuntimeExceptionIfNotInterruptedException(Throwable t) {
        throw new RuntimeException(t);
    }

    public static <R> R throwAsRuntimeExceptionIfNotInterruptedException_returns(Throwable t) {
        throw new RuntimeException(t);
    }

    public static RuntimeException toRuntimeException(CharSequence className, CharSequence funcName, Object errorContent) {
        return new RuntimeException(TGS_UnionUtils.class + ".toRuntimeException->CLASS[" + className + "] -> FUNC[" + funcName + "] -> ERR: " + errorContent);
    }

    public static void thrw(CharSequence className, CharSequence funcName, Object errorContent) {
        throw toRuntimeException(className, funcName, errorContent);
    }

    public static <R> R thrwReturns(CharSequence className, CharSequence funcName, Object errorContent) {
        throw toRuntimeException(className, funcName, errorContent);
    }

    public static void runNothing() {
    }

    public static <R> R callNull() {
        return null;
    }

    public static <R> R callValue(R result) {
        return result;
    }
}
