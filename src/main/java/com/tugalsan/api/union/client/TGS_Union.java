package com.tugalsan.api.union.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class TGS_Union<T> {

    private TGS_Union(T value, Throwable throwable) {
        this.value = value;
        this.throwable = throwable;
    }
    final public T value;
    final public Throwable throwable;

    public static <T> TGS_Union<T> of(Throwable value) {
        return new TGS_Union(null, value);
    }

    public static <T> TGS_Union<T> of(T value) {
        return value == null
                ? of(new NullPointerException())
                : new TGS_Union(value, null);
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isTimeout() {
        return throwable != null && throwable instanceof TimeoutException;
    }

    public boolean isInterruptedException() {
        return throwable != null && throwable instanceof InterruptedException;
    }

    public boolean isExecutionException() {
        return throwable != null && throwable instanceof ExecutionException;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
