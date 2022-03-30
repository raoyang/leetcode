package org.sunshine.lc.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String args[]) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 10, TimeUnit.HOURS, new LinkedBlockingQueue<>(3));
        for(int i = 0 ; i < 13; i ++){
            threadPoolExecutor.execute(()->{
                System.out.println("打印：" + Thread.currentThread().getName());
            });
        }

        Thread.sleep(10000);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }
}
