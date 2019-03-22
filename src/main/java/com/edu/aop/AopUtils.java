package com.edu.aop;

import com.edu.ioc.IocContext;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

/**
 *
 * Created by zhangxuan on 2019/3/22.
 */
public class AopUtils {

    public static void initAop(String packageName) {
        try {
            Enumeration<URL> resource = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resource.hasMoreElements()){
                addAopByAnno(resource.nextElement().getPath(),packageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addAopByAnno(String path, String packageName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File[] files = IocContext.getClassfile(path);

        if (files != null){
            for (File file : files) {
                String fileName = file.getName();
                if (file.isFile()){
                    Class<?> clazz = Class.forName(packageName + "." + fileName.substring(0, fileName.lastIndexOf(".")));
                    //假如是个asspect类
                    if (clazz.isAnnotationPresent(Asspect.class)){
                        Method[] methods = clazz.getMethods();
                        for (Method method : methods) {
                            if (method.isAnnotationPresent(PointCuut.class)){
                                PointCuut pointCuut = (PointCuut) method.getAnnotations()[0];
                                String path1 = pointCuut.path();
                                String method1 = pointCuut.method();
                                //目标方法clazz
                                Class<?> tarClazz = Class.forName(path1);


                                //创建代理对象的父对象
                                AbstractAsspect proxy = (AbstractAsspect) clazz.newInstance();
                                proxy.setProxyMethodName(method1);

                                Object o = proxy.creatProxy(tarClazz);

                                IocContext.applicationContext.put(tarClazz,o);

                            }

                        }
                    }
                }else {
                    addAopByAnno(file.getPath(), packageName + "."+fileName);
                }



            }
        }

    }
}
