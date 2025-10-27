package org.huerta.sec01;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10;

    public static void main(String[] args) {
        platformThreadDemo();
    }

    private static void platformThreadDemo(){
        for (int i = 0; i <  MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(()-> Task.ioIntensive(j));
            thread.start();
        }
    }
}
