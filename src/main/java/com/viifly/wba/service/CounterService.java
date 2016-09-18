package com.viifly.wba.service;

public interface CounterService {
    long getCount(String key);
    long increaseAndGetCount(String key);
}
