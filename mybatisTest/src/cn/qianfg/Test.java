package cn.qianfg;

import cn.qianfg.service.UserService;
import cn.qianfg.service.impl.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        UserService us=new UserServiceImpl();
        System.out.println(us.queryAllUser());
    }
}
