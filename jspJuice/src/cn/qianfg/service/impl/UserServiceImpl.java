package cn.qianfg.service.impl;

import cn.qianfg.dao.UserDao;
import cn.qianfg.dao.impl.UserDaoImpl;
import cn.qianfg.polo.User;
import cn.qianfg.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao ud=new UserDaoImpl();
    @Override
    public User login(User user) {
        return ud.login(user);
    }
}
