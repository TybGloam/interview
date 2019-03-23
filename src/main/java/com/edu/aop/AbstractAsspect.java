package com.edu.aop;

import org.mockito.cglib.proxy.Enhancer;
import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * aspect抽象类 切点需要继承该类 并实现前后置方法
 * Created by zhangxuan on 2019/3/22.
 */
public abstract class AbstractAsspect implements MethodInterceptor {

    /**
     * 要被代理的目标对象
     */
    private Class targetObject;

    /**
     * 被代理的方法名
     */
    private String proxyMethodName;

    public Object creatProxy(Class obj){
        this.targetObject = obj;
        //用来生成代理对象
        Enhancer enhancer = new Enhancer();
        //设置父类对象
        enhancer.setSuperclass(this.targetObject);
        //设置回调对象
        enhancer.setCallback(this);

        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result;
        String proxyMethod = getProxyMethodName();

        if (proxyMethod != null && proxyMethod.equals(method.getName())) {
            doBefore();
        }
        result = methodProxy.invokeSuper(o,objects);
        if (proxyMethod != null && proxyMethod.equals(method.getName())) {
            doAfter();
        }
        return result;
    }

    public abstract void doBefore();

    public abstract void doAfter();

    public String getProxyMethodName() {
        return proxyMethodName;
    }

    public void setProxyMethodName(String proxyMethodName) {
        this.proxyMethodName = proxyMethodName;
    }

}
