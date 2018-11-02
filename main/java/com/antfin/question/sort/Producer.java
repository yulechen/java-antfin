package com.antfin.question.sort;

import java.io.File;
import java.util.List;

public class Producer {

    private RecordBuffer pool ;

    public RecordBuffer getPool() {
        return pool;
    }

    public void setPool(RecordBuffer pool) {
        this.pool = pool;
    }

    public  void produce(File file){
        if(null == file){
            return;
        }

        List<String> stringList = FileUtil.readLines(file, "utf-8");
        for (String line : stringList) {
            String[] strArray = line.split(",");
            Record so= new Record();
            // no validate
            so.setId(strArray[0]);
            so.setGroupId(strArray[1]);

            so.setQuota(Float.parseFloat(strArray[2]));
           // System.out.println("p:"+so);
            pool.addObject(so);
        }

    }

}
