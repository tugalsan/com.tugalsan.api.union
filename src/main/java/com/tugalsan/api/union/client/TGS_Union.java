package com.tugalsan.api.union.client;

import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class TGS_Union<T> {

    private TGS_Union(T value, Throwable throwable) {
        this.value = value;
        this.throwable = throwable;
    }
    final public T value;
    final public Throwable throwable;

    public static <T> TGS_Union<T> ofThrowable(CharSequence className, CharSequence funcName, Object errorContent) {
        var throwable = TGS_UnionUtils.toRuntimeException(className, funcName, errorContent);
        return ofThrowable(throwable);
    }

    public static <T> TGS_Union<T> ofThrowable(Throwable throwable) {
        return new TGS_Union(null, throwable);
    }

    public static <T> TGS_Union<T> ofEmpty() {
        return of(null);
    }

    public static <T> TGS_Union<T> of(T value) {
        return value == null
                ? ofThrowable(new NullPointerException("value is intruduced as null"))
                : new TGS_Union(value, null);
    }

    public boolean isEmpty() {
        return value == null;
    }

    public void ifPresent(Consumer<? super T> action) {
        if (value != null) {
            action.accept(value);
        }
    }

    public T orElseThrow() throws Throwable {
        if (value == null) {
            throw throwable;
        }
        return value;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isTimeout() {
        return throwable != null && throwable instanceof TimeoutException;
    }

    public boolean isInterrupt() {
        return throwable != null && throwable instanceof InterruptedException;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.value);
        hash = 59 * hash + Objects.hashCode(this.throwable);
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
        return Objects.equals(this.throwable, other.throwable);
    }

    @Override
    public String toString() {
        if (value == null) {
            return TGS_Union.class.getSimpleName() + "{" + "value=" + value + ", throwable=" + throwable + '}';
        }
        return String.valueOf(value);
    }
}
