package com.edu.ioc;

/**
 * Created by zhangxuan on 2019/3/22.
 */
@Component
public class UserCtrl {

    @Autowired
    private UserService userService;

    public void getUser(){
        userService.getUser();
    }

    public static void main(String[] args) {
        UserCtrl ctrl = (UserCtrl) IocContext.applicationContext.get(UserCtrl.class);

        ctrl.getUser();
    }

}
