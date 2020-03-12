package hua.lee.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAppThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = true;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getLogger();
    

    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }
    public MyAppThread(Runnable r, String poolName) {
        super(r, poolName + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
        
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SERVER, "uncaught in thread " + t.getName(),e.getMessage());
            }
        });
    }

    @Override
    public void run() {
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "Created " + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exiting " + getName());
            }
        }
    }
}
