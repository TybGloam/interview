package com.edu.ioc;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Ioc注入实现
 * Created by zhangxuan on 2019/3/22.
 */
public class IocUtils {


    public static void inject(){
        Map<Class<?>, Object> ioc = IocContext.applicationContext;

        try {
            for (Map.Entry<Class<?>,Object> entry : ioc.entrySet()) {
                Class<?> clazz = entry.getKey();
                Object value = entry.getValue();

                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(Autowired.class)){
                        Class<?> fieldClazz = field.getType();
                        field.setAccessible(true);
                        Object o = ioc.get(fieldClazz);

                        field.set(value,o);
                    }
                }


            }
        } catch (IllegalAccessException e) {


        }

    }
}
