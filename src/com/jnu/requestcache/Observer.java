package com.jnu.requestcache;

public interface Observer<T> {
    void onChange(T value);
}
