package com.tugalsan.api.union.client;

import java.util.concurrent.TimeoutException;

public record TGS_UnionExcuse(Throwable excuse) {

    public static TGS_UnionExcuse ofExcuse(CharSequence className, CharSequence funcName, Object excuse) {
        return ofExcuse(
                new RuntimeException(
                        "CLASS[" + className + "].FUNC[" + funcName + "].EXCUSE: " + excuse
                )
        );
    }

    public static  TGS_UnionExcuse ofExcuse(Throwable excuse) {
        return new TGS_UnionExcuse( excuse);
    }

    public boolean isExcuseNot() {
        return excuse == null;
    }

    public boolean isExcuse() {
        return !isExcuseNot();
    }
    public boolean isExcuseTimeout() {
        return excuse != null && excuse instanceof TimeoutException;
    }

    public boolean isExcuseInterrupt() {
        return excuse != null && excuse instanceof InterruptedException;
    }

    
}
