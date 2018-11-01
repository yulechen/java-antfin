package com.antfin.question.spinlock;

public class Ticket {

    private int total=1000;

   private SpinLock lock = new SpinLock();

    public  int  sell(){
        try {
            lock.lock();
            total=total-1;
            return total;
        } finally {
            lock.unlock();
        }

    }

    public  synchronized  int  sellSynchronized(){
            lock.lock();
            total=total-1;
            return total;
    }

    public int getTotal() {
        return total;
    }
}
