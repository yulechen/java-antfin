package com.antfin.question.test;

import com.antfin.question.spinlock.Ticket;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SellTicketTest {

    @Test
    public void sellTicketsTests() throws ExecutionException, InterruptedException {
        for(int i=0 ;i<20;i++){
            sell();
        }
    }


    private void sell() throws ExecutionException, InterruptedException {
        final Ticket ticket =new Ticket();
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        List<Future> futures =new ArrayList<>();
        for(int i=0;i<1000;i++){
            Future<?> submit = executorService.submit(new Runnable() {
                @Override
                public void run() {
//                    ticket.sell();
                    ticket.sellSynchronized();

                }
            });
            futures.add(submit);
        }
        for (Future future : futures) {
            future.get();
        }
        if(ticket.getTotal()>0)
          System.out.println("剩余票数:"+ticket.getTotal());
        executorService.shutdown();
    }
}
