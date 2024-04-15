package com.tugalsan.api.union.client;

import java.util.concurrent.TimeoutException;

public record TGS_UnionExcuseVoid(Throwable excuse) {

    public static TGS_UnionExcuseVoid ofExcuse(CharSequence className, CharSequence funcName, Object excuse) {
        return ofExcuse(
                new RuntimeException(
                        "CLASS[" + className + "].FUNC[" + funcName + "].EXCUSE: " + excuse
                )
        );
    }

    public Throwable excuse() {
        if (excuse == null) {
            throw new UnsupportedOperationException("union is a void");
        }
        return excuse;
    }

    public static TGS_UnionExcuseVoid ofVoid() {
        return VOID;
    }
    final private static TGS_UnionExcuseVoid VOID = new TGS_UnionExcuseVoid(null);

    public <T> TGS_UnionExcuse<T> toExcuse() {
        return TGS_UnionExcuse.ofExcuse(excuse);
    }

    public static TGS_UnionExcuseVoid ofExcuse(Throwable excuse) {
        return new TGS_UnionExcuseVoid(excuse);
    }

    public boolean isVoid() {
        return excuse == null;
    }

    public boolean isExcuse() {
        return excuse != null;
    }

    public boolean isExcuseTimeout() {
        return excuse != null && excuse instanceof TimeoutException;
    }

    public boolean isExcuseInterrupt() {
        return excuse != null && excuse instanceof InterruptedException;
    }

    @Override
    public String toString() {
        if (isVoid()) {
            return TGS_UnionExcuse.class.getSimpleName() + "{excuse=" + excuse + '}';
        }
        return "";
    }
}
