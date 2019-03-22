package com.edu.ioc;



import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring Ioc容器
 * Created by zhangxuan on 2019/3/22.
 */
public class IocContext {

    public static final Map<Class<?>,Object> applicationContext = new ConcurrentHashMap<>();

    static {
        String packageName = "com.edu.ioc";
        try {
            init(packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init(String packageName) throws IOException {
        Enumeration<URL> resource = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));

        while (resource.hasMoreElements()){
            addClassByAnnotation(resource.nextElement().getPath(),packageName);
        }

        IocUtils.inject();
    }

    /**
     * 获取指定包路径下实现Component主键Bean的实例
     * @param path
     * @param packageName
     */
    private static void addClassByAnnotation(String path, String packageName) {
        try {
            File[] files = getClassfile(path);

            if (files != null){
                for (File file : files) {
                    String name = file.getName();
                    String fileName = file.getName();
                    if (file.isFile()){
                        Class<?> clazz = Class.forName(packageName + "." + fileName.substring(0, fileName.lastIndexOf(".")));

                        if (clazz.isAnnotationPresent(Component.class)){
                            applicationContext.put(clazz,clazz.newInstance());
                        }

                    }else {
                        addClassByAnnotation(file.getPath(), packageName + "."+fileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //根据filePath 获取所有文件
    private static File[] getClassfile(String filePath){
        return new File(filePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".class") || file.isDirectory();
            }
        });
    }


}
