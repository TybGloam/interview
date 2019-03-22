package com.edu.ioc;

/**
 * Created by zhangxuan on 2019/3/22.
 */
@Component
public class UserService {

    public User getUser(){
        return new User("旭兵",33);
    }
}
