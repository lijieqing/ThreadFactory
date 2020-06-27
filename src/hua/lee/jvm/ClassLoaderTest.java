package hua.lee.jvm;

public class ClassLoaderTest extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}

class Constant{
    public static void main(String[] args) {
        int[] a = new int[]{10,20,30,40};
        Integer[] b = new Integer[]{10,20,30,40};
        Integer temp1 = b[0];
        Integer temp2 = new Integer(10);
        Integer temp3 = 10;
        System.out.println(temp1==temp2);
        System.out.println(temp1==temp3);
    }
}
