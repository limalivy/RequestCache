package com.jnu.requestcache;

import java.util.ArrayList;
import java.util.List;

public class DataHolder<T> {
    private T value;
    private List<Observer<T>> observers = new ArrayList<>();

    public T getValue() {
        return value;
    }

    public void observe(Observer<T> observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserve(Observer<T> observer) {
        observers.remove(observer);
    }

    public void postVale(T value) {
        this.value = value;
        for (Observer<T> o : observers) {
            if (o != null) {
                o.onChange(value);
            }
        }
    }

}
