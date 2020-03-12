package hua.lee.thread;

public interface ThreadFactory {
    Thread newThread(Runnable runnable);
}