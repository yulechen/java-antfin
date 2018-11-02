package com.antfin.question.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public  abstract  class ThreadUtils {

    public static void  runUtilFinish(int threadSize,int taskSize,Runnable r) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
        List<Future> futures =new ArrayList<>();
        for(int i=0;i<taskSize;i++){
            Future<?> submit = executorService.submit(r);
            futures.add(submit);
        }
        for (Future future : futures) {
            future.get();
        }
        executorService.shutdown();
    }
}
