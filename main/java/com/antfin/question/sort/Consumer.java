package com.antfin.question.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Consumer {

    private Map<String/**group id**/ ,List<Record> /** group content**/> sortMap;

    private RecordBuffer pool ;

    public Map<String, List<Record>> getSortMap() {
        return sortMap;
    }

    public void setSortMap(Map<String, List<Record>> sortMap) {
        this.sortMap = sortMap;
    }

    public RecordBuffer getPool() {
        return pool;
    }

    public void setPool(RecordBuffer pool) {
        this.pool = pool;
    }


    public   void consume(){
        while(!pool.isDone() || !pool.isEmpty()) {
            Record object = pool.getObject();
            if (null != object) {
                List<Record> sortObjects = sortMap.get(object.getGroupId());
                if (null == sortObjects) {
                    sortObjects = new ArrayList<>();
                }
                sortObjects.add(object);
                sortMap.put(object.getGroupId(), sortObjects);
            }
        }
    }

}
