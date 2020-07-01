package hua.lee.classloader;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class<?> clazz = Class.forName("hua.lee.classloader.NormalUser");
        System.out.println("系统自带 ClassLoader=" + clazz.getClassLoader());
        Pocket pocket = (Pocket) clazz.newInstance();
        System.out.println("系统自带："+pocket.getBalance());

        //没有重写 loadClass，维持双亲委派机制
        LocalClassLoader lcl = new LocalClassLoader();
        //我们加个后缀名，这样双亲委托查找失败了，就调用LocalClassLoader.findClass 方法加载
        //其实 findClass 方法还是加载 NormalUser类，只是换了个位置和方式：
        //defineClass("hua.lee.classloader.NormalUser", clazzBytes, 0, size);
        Class<?> clazzOut02 = lcl.loadClass("hua.lee.classloader.NormalUser_Temp");

        //双亲委托机制会自动搜寻父类，
        //查找到 hua.lee.classloader.NormalUser 的 Class 实例，就返回该对象
        Class<?> clazzOut01 = lcl.loadClass("hua.lee.classloader.NormalUser");


        System.out.println("clazzOut01 自定义 ClassLoader=" + clazzOut01.getClassLoader());
        pocket = (Pocket) clazzOut01.newInstance();
        System.out.println("clazzOut01 自定义加载器获取余额："+pocket.getBalance());

        System.out.println("clazzOut02 自定义 ClassLoader=" + clazzOut02.getClassLoader());
        pocket = (Pocket) clazzOut02.newInstance();
        System.out.println("clazzOut02 自定义加载器获取余额："+pocket.getBalance());

        System.out.println(clazz==clazzOut01);
        System.out.println(clazz==clazzOut02);
        System.out.println(clazzOut01==clazzOut02);
    }
}
