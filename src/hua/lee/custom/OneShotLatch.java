package hua.lee.custom;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OneShotLatch {
    private final Sync sync = new Sync();
    public void signal(){
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException{
        sync.acquireSharedInterruptibly(0);
    }
    private class Sync extends AbstractQueuedSynchronizer{
        private static final long serialVersionUID = 1229612274290488163L;

        @Override
        protected int tryAcquireShared(int arg) {
            return getState()==1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }

    }
}