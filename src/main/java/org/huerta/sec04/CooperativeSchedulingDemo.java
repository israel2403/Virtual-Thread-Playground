package org.huerta.sec04;

import org.huerta.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeSchedulingDemo {

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    public static final Logger log = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);

    public static void main(String[] args) {
        var builder = Thread.ofVirtual();
        var t1 = builder.unstarted(()-> demo(1));
        var t2 = builder.unstarted(()-> demo(2));
        var t3 = builder.unstarted(()-> demo(3));

        t1.start();
        t2.start();
        t3.start();
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static void demo(int threadNumber){
        log.info("thread-{} started", threadNumber);
        for (int i = 0; i < 10; i++) {
            log.info("thread-{} is printing {}. Thread: {}", threadNumber,i, Thread.currentThread());
            if ((threadNumber == 1 && i % 2 == 0) || (threadNumber == 2)){
                Thread.yield();
            }
        }
        log.info("thread-{} ended", threadNumber);
    }
}
