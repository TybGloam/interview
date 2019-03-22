package com.edu.aop;

/**
 * Created by zhangxuan on 2019/3/22.
 */
@Asspect
public class UserAsspect extends AbstractAsspect {

    @PointCuut(path = "com.edu.ioc.UserService",method = "getUser")
    public void testAop(){

    }

    @Override
    public void doBefore() {
        System.out.println("旭兵你说下面的年龄对不对?");
    }

    @Override
    public void doAfter() {
        System.out.println("旭兵:对啊!");
    }
}
