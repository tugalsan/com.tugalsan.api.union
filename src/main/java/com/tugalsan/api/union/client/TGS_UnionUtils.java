package com.tugalsan.api.union.client;

public class TGS_UnionUtils {

    @Deprecated //DO NOT USE IT FOR InterruptedException
    public static void throwAsRuntimeException(Throwable t) {
        throw new RuntimeException(t);
    }

    @Deprecated //DO NOT USE IT FOR InterruptedException
    public static <R> R throwAsRuntimeException_alsoReturns(Throwable t) {
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
