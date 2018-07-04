package com.jnu.requestcache;

public interface Callback<T> {
    void call(T value);
}
