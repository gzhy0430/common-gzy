package com.indi.design.proxy.homework.myprxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyProxy{
    public static final String ln = "\r\n";
    public static Object newProxyInstance(MyClassLoader classLoader,
                                          Class<?>[] interfaces, MyInvocationhandler h) {
        try{
//            动态生成源代码
            String src = generateSrc(interfaces);
//            通过java文件输出到磁盘
            String filePath = MyProxy.class.getResource("").getPath();
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();fw.close();

//            把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manager,
                    null, null, null, iterable);
            task.call();
            manager.close();

//            编译生成的class文件加载到JVM中
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(MyInvocationhandler.class);
            f.delete();
//            返回字节码重组以后得新的代理对象
            return c.newInstance(h);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
//        用代码写代码
        StringBuffer sb = new StringBuffer();
        sb.append("package com.indi.design.proxy.homework.myprxy;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        sb.append("MyInvocationhandler h;" + ln);
        sb.append("public $Proxy0(MyInvocationhandler h){this.h = h;}" + ln);

        for(Method m : interfaces[0].getMethods()){
            /*Class<?>[] params = m.getParameterTypes();
            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();
            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " +  paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if(i > 0 && i < params.length-1){
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }*/

            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(){" + ln);
            sb.append("try{" + ln);
                sb.append("Method m = " + interfaces[0].getName()
                + ".class.getMethod(\"" + m.getName() + "\", new Class[]{});" + ln);
//                sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
//                sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);

            sb.append("this.h.invoke(this, m, null);" + ln);
            sb.append("}catch (Throwable e){e.printStackTrace();}" + ln);
            sb.append("}" + ln);
        }
        sb.append("}");
//        System.out.println(sb.toString());
        return sb.toString();
    }
    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String src){
        char [] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}