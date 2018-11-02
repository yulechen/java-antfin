package com.antfin.question.sort;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class RecordBuffer {

    private LinkedBlockingQueue<Record> queue =new LinkedBlockingQueue(1024);

    private AtomicBoolean  done =new AtomicBoolean(false);


    public  void  addObject(Record object){
        queue.offer(object);
    }

    public Record getObject(){
        return queue.poll();
    }

    public boolean isDone() {
        return done.get();
    }

    public void setDone() {
        this.done.set(true);
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
