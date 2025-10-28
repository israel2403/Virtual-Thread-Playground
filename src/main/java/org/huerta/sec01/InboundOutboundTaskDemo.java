package org.huerta.sec01;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10;

    public static void main(String[] args) {
        platformThreadDemo1();
    }

    private static void platformThreadDemo1(){
        for (int i = 0; i <  MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(()-> Task.ioIntensive(j));
            thread.start();
        }
    }

    private static void platformThreadDemo2(){
        for (int i = 0; i <  MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = Thread.ofPlatform().unstarted(()-> Task.ioIntensive(j));
            thread.start();
        }
    }
}
