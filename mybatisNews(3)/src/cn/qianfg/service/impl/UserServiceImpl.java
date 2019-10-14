package cn.qianfg.service.impl;

import cn.qianfg.dao.UserDao;
import cn.qianfg.pojo.User;
import cn.qianfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao ud;
    @Override
    public User login(User user) {
        if(user.getName()!=null &&!"".equals(user.getName())&&user.getPwd()!=null&&!"".equals(user.getPwd())){
            user=ud.login(user);
        }else{
            user=null;
        }
        return user;
    }
}
