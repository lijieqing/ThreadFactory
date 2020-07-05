package hua.lee.jvm;

import java.lang.ref.*;
import java.lang.reflect.Field;

public class FinalizerTest {
    @Override
    protected void finalize() throws Throwable {
        //do something
        super.finalize();
    }
}

class Cow {

}

class ReferenceTest {
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        ReferenceQueue<Cow> referenceQueue = new ReferenceQueue<>();
        Cow c = new Cow();
        PhantomReference<Cow> softReference = new PhantomReference<Cow>(c, referenceQueue);
        //把对cow的强引用置为空
        c = null;

        System.out.println("获取影子引用下的Cow = " + softReference.get());

        //加入队列，这一步虚拟机会去做，但是因为时间上的问题，我们手动触发一下
        softReference.enqueue();

        Reference<? extends Cow> cow = referenceQueue.remove();

        Field field = Reference.class.getDeclaredField("referent");
        field.setAccessible(true);
        Object obj = field.get(cow);
        System.out.println("从释放队列中获取Cow = " + obj);

        cow.clear();

        obj = field.get(cow);
        System.out.println("从释放队列中获取Cow = " + obj);
    }
}

class RefTest {
    public static boolean isRun = true;
    public static void main(String[] args) throws Exception {
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        new Thread() {
            public void run() {
                while (isRun) {
                    Object obj = referenceQueue.poll();
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("gc will collect："
                                    + result.getClass() + "@"
                                    + result.hashCode() + "\t"
                                    + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
        isRun = false;
    }
}
