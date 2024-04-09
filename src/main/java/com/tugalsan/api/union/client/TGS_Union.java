package com.tugalsan.api.union.client;

import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public record TGS_Union<T>(T value, Throwable excuse) {

    public static <T> TGS_Union<T> ofExcuse(CharSequence className, CharSequence funcName, Object excuse) {
        return ofExcuse(
                new RuntimeException(
                        "CLASS[" + className + "].FUNC[" + funcName + "].EXCUSE: " + excuse
                )
        );
    }

    public static <T> TGS_Union<T> ofExcuse(Throwable excuse) {
        return new TGS_Union(null, excuse);
    }

    public static <T> TGS_Union<T> ofEmpty_NullPointerException() {
        return of(null);
    }

    public static <T> TGS_Union<T> of(T value) {
        return value == null
                ? ofExcuse(new NullPointerException("value is not introduced"))
                : new TGS_Union(value, null);
    }

    @Deprecated //USE isExcuse
    public boolean isEmpty() {
        return value == null;
    }

    @Deprecated //USE isPresent
    public boolean isExcuseNot() {
        return excuse == null;
    }

    public boolean isExcuse() {
        return !isExcuseNot();
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
        return !isEmpty();
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
        final TGS_Union<?> other = (TGS_Union<?>) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.excuse, other.excuse);
    }

    @Override
    public String toString() {
        if (value == null) {
            return TGS_Union.class.getSimpleName() + "{" + "value=" + value + ", excuse=" + excuse + '}';
        }
        return String.valueOf(value);
    }
}
