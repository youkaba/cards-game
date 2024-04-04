package com.example.cardsgame.businesslogic.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentHashMapWrapper <T, U> {
    private final AtomicReference<Map<T, U>> aRef;

    public ConcurrentHashMapWrapper() {
        aRef = new AtomicReference<>(new HashMap<>());
    }

    public synchronized U put(T key, U value) {
        Map<T, U> map = aRef.get();
        Map<T, U> newMap = new HashMap<>(map);
        U oldValue = newMap.put(key, value);
        aRef.set(newMap);
        return oldValue;
    }

    public U get(T key) {
        return aRef.get().get(key);
    }

    public synchronized int size() {
        return aRef.get().size();
    }

    public Collection<U> values() {
        return aRef.get().values();
    }
}
