package com.antfin.question.lazysingleton;

import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeSingleton {

    private static UnsafeSingleton singleton;
    public static AtomicInteger count= new AtomicInteger(0);

    private UnsafeSingleton() {
        count.getAndIncrement();
    }
    public static UnsafeSingleton getSingleton() {
        if (singleton == null) {
            singleton = new UnsafeSingleton();

        }
        return singleton;
    }

}