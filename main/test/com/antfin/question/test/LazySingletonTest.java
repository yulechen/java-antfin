package com.antfin.question.test;

import com.antfin.question.lazysingleton.SafeSingleton;
import com.antfin.question.lazysingleton.UnsafeSingleton;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;


public class LazySingletonTest {

    @Test
    public void testGetSafeInstance() throws InterruptedException, ExecutionException {
        ThreadUtils.runUtilFinish(160,100 ,()->{
            SafeSingleton.getSingleton();
        });
        Assert.assertEquals(1,SafeSingleton.count.get());
    }

    @Test
    public void testUnsafeGetInstance() throws InterruptedException, ExecutionException {
        ThreadUtils.runUtilFinish(160,100 ,()->{
            UnsafeSingleton.getSingleton();
        });
        Assert.assertEquals(1,UnsafeSingleton.count.get());
    }
}
