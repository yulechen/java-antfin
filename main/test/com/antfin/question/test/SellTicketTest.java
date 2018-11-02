package com.antfin.question.test;

import com.antfin.question.spinlock.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class SellTicketTest {

    @Test
    public void spinLockSellTest() throws ExecutionException, InterruptedException {
        final int total= 1000;
        for(int i=0 ;i<20;i++){
            Ticket ticket =new Ticket(total);
            ThreadUtils.runUtilFinish(32,total,()->{
                ticket.spinLockSell();
            });
            Assert.assertEquals(0,ticket.getTotal());
        }
    }

    @Test
    public void synchronizedSellTest() throws ExecutionException, InterruptedException {
        final int total= 1000;
        for(int i=0 ;i<20;i++){
            Ticket ticket =new Ticket(total);
            ThreadUtils.runUtilFinish(32,total,()->{
                ticket.synchronizedSell();
            });
            Assert.assertEquals(0,ticket.getTotal());
        }
    }

    @Test
    public void unsafeSellTest() throws ExecutionException, InterruptedException {
        final int total= 1000;
        for(int i=0 ;i<20;i++){
            Ticket ticket =new Ticket(total);
            ThreadUtils.runUtilFinish(32,total,()->{
                ticket.unsafeSell();
            });
            Assert.assertEquals(0,ticket.getTotal());
        }
    }


}
