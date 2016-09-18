package com.viifly.wba.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CounterServiceImpl implements CounterService{
    private ConcurrentHashMap<String, AtomicLong> counterMap = new ConcurrentHashMap();

    @Override
    public long getCount(String key) {
        AtomicLong c = counterMap.get(key);
        if (c != null) {
            return c.get();
        }
        return 0;
    }

    @Override
    public long increaseAndGetCount(String key) {
        AtomicLong c = counterMap.get(key);
        if (c == null) {
            c = new AtomicLong(0);
            counterMap.put(key, c);
        }
        return c.getAndIncrement();
    }
}
