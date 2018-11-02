package com.antfin.question.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

public class SpinLock {

    private AtomicInteger state =new AtomicInteger(0);

    public void lock(){
       while(!state.compareAndSet(0,1));
    }

    public void unlock(){
        state.set(0);
    }

 }

