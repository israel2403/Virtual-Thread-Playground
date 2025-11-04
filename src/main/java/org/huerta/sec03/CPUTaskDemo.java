package org.huerta.sec03;

import org.huerta.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CPUTaskDemo {
     public static final Logger log = LoggerFactory.getLogger(CPUTaskDemo.class);

    public static final int TASKS_COUNT = 1;

    public static void main(String[] args) {
        demo(Thread.ofVirtual());
    }

    public static void demo(Thread.Builder builder){
        var latch = new CountDownLatch(TASKS_COUNT);
        for (int i = 0; i < TASKS_COUNT; i++) {
            builder.start(()->{
                Task.cpuIntensive(45);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
