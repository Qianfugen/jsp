package cn.qianfg.service.impl;

import cn.qianfg.dao.UserDao;
import cn.qianfg.dao.impl.UserDaoImpl;
import cn.qianfg.polo.User;
import cn.qianfg.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao ud=new UserDaoImpl();
    @Override
    public User login(User user) {
        if(user!=null && user.getName()!=null&&!"".equals(user.getName())&&user.getPwd()!=null&&!"".equals(user.getPwd())){
            return ud.login(user);
        }
        return null;
    }
}
