package com.antfin.question.test;

import com.antfin.question.lazysingleton.SafeSingleton;
import com.antfin.question.lazysingleton.UnsafeSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class LazySingletonTest {



    @Test
    public void testGetSafeInstance() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(160);
        List<Future> futures =new ArrayList<>();
        for(int i=0;i<100;i++){
            Future<?> submit = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    SafeSingleton.getSingleton();

                }
            });
            futures.add(submit);
        }
        // wait for task done
        for (Future future : futures) {
            future.get();
        }
        Assert.assertEquals(1,SafeSingleton.count.get());
    }

    @Test
    public void testUnsafeGetInstance() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(160);
        List<Future> futures =new ArrayList<>();
        for(int i=0;i<100;i++){
            Future<?> submit = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    UnsafeSingleton.getSingleton();

                }
            });
            futures.add(submit);
        }
        // wait for task done
        for (Future future : futures) {
            future.get();
        }
        Assert.assertEquals(1,UnsafeSingleton.count.get());
    }
}
