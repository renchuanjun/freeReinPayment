package org.open.utils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 任传君
 * @create 2018-11-16 10:36
 * JAVA 实现行级锁
 **/
public class JavaRowLock {

    private ConcurrentHashMap lockedMap = new ConcurrentHashMap(16);

    private static JavaRowLock singObj = null;

    private JavaRowLock() {
    }

    public synchronized static JavaRowLock getJavaRowLock(){
        if(null == singObj ) singObj = new JavaRowLock();
         return singObj ;
    }

    public synchronized void lock(String id) throws InterruptedException{
        while(lockedMap.containsKey(id)){
            wait();
        }
        lockedMap.put(id,"");
    }

    public synchronized void unLock(String id){
        if(lockedMap.containsKey(id)){
            lockedMap.remove(id);
            notifyAll();
        }
    }


}
