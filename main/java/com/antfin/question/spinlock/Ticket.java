package com.antfin.question.spinlock;

public class Ticket {
    private int total=1000;

    public Ticket(int total) {
        this.total = total;
    }

    private SpinLock lock = new SpinLock();

    public  int  spinLockSell(){
        try {
            lock.lock();
            total=total-1;
            return total;
        } finally {
            lock.unlock();
        }

    }

    public  synchronized int synchronizedSell(){
            total=total-1;
            return total;
    }


    public  int  unsafeSell(){
            total=total-1;
            return total;

    }

    public int getTotal() {
        return total;
    }
}
