package com.antfin.question.sort;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Startup {

    private Map<String/**group id**/, List<Record>> sortMap = new ConcurrentSkipListMap();

    private ConcurrentLinkedQueue<File> filesQueue ;

    private RecordBuffer pool = new RecordBuffer();

    private boolean  isSortFinish =false;

    public void startProducer() throws ExecutionException, InterruptedException {
        Thread t = new Thread(()->{
            // 10 个线程读取文件
            ExecutorService producerExcutors = Executors.newFixedThreadPool(10);
            List<Future> futures =new ArrayList<>();
            while(!filesQueue.isEmpty()){
                Producer p = new Producer();
                p.setPool(pool);
                Future<?> submit = producerExcutors.submit(() -> {
                    p.produce(filesQueue.poll());
                });
                futures.add(submit);
            }
            // 所有线程完成读操作
            waitUntilFinish(futures);
            pool.setDone();
            producerExcutors.shutdown();
        });
        t.start();


    }

    public void startConsumer(){
        Thread t = new Thread(()->{
            // 4个线程排序
            List<Future> futures =new ArrayList<>();
            ExecutorService consumerExcutors = Executors.newFixedThreadPool(4);
            for (int i = 0; i <4 ; i++) {
                Consumer consumer =new Consumer();
                consumer.setPool(pool);
                consumer.setSortMap(sortMap);
                Future<?> submit = consumerExcutors.submit(() -> {
                    consumer.consume();
                });
                futures.add(submit);

            }

            // 所有线程完成读操作
            waitUntilFinish(futures);
            isSortFinish=true;
            consumerExcutors.shutdown();
        });
        t.start();

    }

    private void waitUntilFinish(List<Future> futures){
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         Startup main =new Startup();
         //test path
         main.setRecordDir("/Users/chenq/Downloads/test/");
         main.startProducer();
         main.startConsumer();

         // wait for sort finish.
         while(!main.isSortFinish){
             Thread.sleep(1000);
         }
         main.sortMap.forEach((k,v)->{
           //  System.out.println(k+":"+v.size());
             System.out.println(k+"      :"+v.stream().min( (v1,v2)->{
                 if(v1.getQuota()-v2.getQuota()>0){
                     return 1;
                 }else{
                     return -1;
                 }
             }).get());
         });

    }

    private void setRecordDir(String dir) {
        this.filesQueue= new ConcurrentLinkedQueue(FileUtil.listFiles(dir));
    }
}
