package com.edu.aop;

import com.edu.ioc.IocContext;
import com.edu.ioc.UserCtrl;
import com.edu.ioc.UserService;

/**
 *
 * Created by zhangxuan on 2019/3/22.
 */
@Asspect
public class UserAsspect extends AbstractAsspect {

    @PointCuut(path = "com.edu.ioc.UserService",method = "getUser")
    public void testAop(){

    }

    @Override
    public void doBefore() {
        System.out.println("提问:下面的年龄对不对?");
    }

    @Override
    public void doAfter() {
        System.out.println("旭兵:对啊!");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        UserCtrl userCtrl = (UserCtrl) IocContext.applicationContext.get(UserCtrl.class);

        userCtrl.getUser();

    }
}
