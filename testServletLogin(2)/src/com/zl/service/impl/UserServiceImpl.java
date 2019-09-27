package com.zl.service.impl;

import com.zl.dao.UserDao;
import com.zl.dao.impl.UserDaoImpl;
import com.zl.pojo.User;
import com.zl.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao ud=new UserDaoImpl();
    /**
     * 验证用户名密码是否正确用户登录
     *
     * @param user
     */
    @Override
    public User login(User user) {
        if(user.getName()!=null &&!"".equals(user.getName())&&user.getPwd()!=null&&!"".equals(user.getPwd())){
            return ud.login(user);
        }
        return null;
    }
}
