package com.edu.ioc;

/**
 * Created by zhangxuan on 2019/3/22.
 */
@Component
public class UserService {

    public User getUser(){
        return new User("赵","旭兵",33,"北京善义善美科技有限公司-技术中心-支付财务组","Java开发工程师");
    }
}
