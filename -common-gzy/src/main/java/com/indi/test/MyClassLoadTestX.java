package com.indi.test;

import java.io.*;

/**
 * Created by Administrator on 2019/3/8.
 */
public class MyClassLoadTestX {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader pc1 = new MyClassLoader("D:\\ProgramFiles\\eclipseNew\\workspace\\cp-lib\\bin");
        Class c = pc1.loadClass("com.indi.test.TestClassLoad");
        System.out.println(c.newInstance());
    }
}

class TestClassLoad{
    @Override
    public String toString() {
        return "类加载成功";
    }
}

class MyClassLoader extends ClassLoader{
    private String classPath;
    public MyClassLoader(String classPath){
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null)
            throw new ClassNotFoundException();
        else
            return defineClass(name, classData, 0, classData.length);
    }

    private byte[] getData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try{
            System.out.println(path);
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1){
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}