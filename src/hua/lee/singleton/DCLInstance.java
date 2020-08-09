package hua.lee.singleton;

public class DCLInstance {
    private static volatile DCLInstance instance;

    private DCLInstance() {
    }

    public static DCLInstance getInstance() {
        if (instance == null) {
            synchronized (DCLInstance.class) {
                if (instance == null) {
                    instance = new DCLInstance();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        DCLInstance ins = DCLInstance.getInstance();
        //ins.test(0);
//        try {
//            ins.test(0);
//        } catch (Exception e) {
//            System.out.println("we get exception：" + e.getMessage());
//        }
//        System.out.println("end");

        String name = "A";
        // \u000d name="B";
        System.out.println(name);
    }

    public void test(int divided) {
        System.out.println(100 / divided);
    }
}
