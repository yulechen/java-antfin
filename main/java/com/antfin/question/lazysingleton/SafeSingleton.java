package com.antfin.question.lazysingleton;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeSingleton {
    private volatile  static SafeSingleton singleton;
    public static AtomicInteger count= new AtomicInteger(0);
    private SafeSingleton(){
        count.getAndIncrement();
    }
    public static SafeSingleton getSingleton() {
    if (singleton == null) {  // volatile for this code.
        synchronized (SafeSingleton.class) {
        if (singleton == null) {  
            singleton = new SafeSingleton();
        }  
        }  
    }  
     return singleton;
    }

}