package hua.lee.classloader;

import java.io.FileInputStream;
import java.io.IOException;

public class LocalClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "/Users/lijie/Desktop/NormalUser.class";
        try {
            FileInputStream ins = new FileInputStream(path);
            int size = ins.available();
            byte[] clazzBytes = new byte[size];
            if (ins.read(clazzBytes) > 0) {
                return defineClass("hua.lee.classloader.NormalUser", clazzBytes, 0, size);
            } else {
                throw new ClassNotFoundException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }
}
