package com.antfin.question.spinlock;

public class SpinLock {

    private volatile int state=0;

    public void lock(){
       while(state==0){
           state=1;
       }
    }

    public void unlock(){
        state=0;
    }

    public static void main(String[] args) {
        SpinLock lock =new SpinLock();
        lock.lock();
          // doSome thing
        lock.unlock();
    }




}
