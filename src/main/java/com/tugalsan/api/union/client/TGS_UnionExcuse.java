package com.tugalsan.api.union.client;

import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public record TGS_UnionExcuse<T>(T value, Throwable excuse) {

    public static <T> TGS_UnionExcuse<T> ofExcuse(CharSequence className, CharSequence funcName, Object excuse) {
        return ofExcuse(
                new RuntimeException(
                        "CLASS[" + className + "].FUNC[" + funcName + "].EXCUSE: " + excuse
                )
        );
    }

    public <T> TGS_UnionExcuse<T> toExcuse() {
        return TGS_UnionExcuse.ofExcuse(excuse);
    }

    public TGS_UnionExcuseVoid toExcuseVoid() {
        return TGS_UnionExcuseVoid.ofExcuse(excuse);
    }

    public static <T> TGS_UnionExcuse<T> ofExcuse(Throwable excuse) {
        return new TGS_UnionExcuse(null, excuse);
    }

    public static <T> TGS_UnionExcuse<T> ofEmpty_NullPointerException() {
        return of(null);
    }

    public static <T> TGS_UnionExcuse<T> of(T value) {
        return value == null
                ? ofExcuse(new NullPointerException("value is not introduced"))
                : new TGS_UnionExcuse(value, null);
    }

    public boolean isExcuse() {
        return excuse != null;
    }

    public void ifPresent(Consumer<? super T> action) {
        if (value != null) {
            action.accept(value);
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isExcuseTimeout() {
        return excuse != null && excuse instanceof TimeoutException;
    }

    public boolean isExcuseInterrupt() {
        return excuse != null && excuse instanceof InterruptedException;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.value);
        hash = 59 * hash + Objects.hashCode(this.excuse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TGS_UnionExcuse<?> other = (TGS_UnionExcuse<?>) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.excuse, other.excuse);
    }

    @Override
    public String toString() {
        if (value == null) {
            return TGS_UnionExcuse.class.getSimpleName() + "{" + "value=" + value + ", excuse=" + excuse + '}';
        }
        return String.valueOf(value);
    }
}
