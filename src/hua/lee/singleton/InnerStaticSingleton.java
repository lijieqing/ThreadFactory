package hua.lee.singleton;

public class InnerStaticSingleton {
    private String name;

    private static class SingletonHolder {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
    public static InnerStaticSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
