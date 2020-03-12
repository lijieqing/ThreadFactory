package hua.lee.thread;

public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable,poolName);
    }

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("Test");
        factory.newThread(new Runnable(){
        
            @Override
            public void run() {
                System.out.println("hello Factory");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("AAAAAA"+20/0);
                
            }
        }).start();
    }

}
