package cn.qianfg.dao;

import cn.qianfg.polo.User;

public interface UserDao {
    //验证登录
    public User login(User user);
}
