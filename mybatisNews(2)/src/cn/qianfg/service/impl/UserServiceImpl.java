package cn.qianfg.service.impl;

import cn.qianfg.dao.NewsDao;
import cn.qianfg.dao.UserDao;
import cn.qianfg.pojo.User;
import cn.qianfg.service.UserService;
import cn.qianfg.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    private UserDao ud;
    @Override
    public User login(User user) {
        SqlSession ss = MybatisUtil.init().getSqlSession();
        ud = ss.getMapper(UserDao.class);
        User user1=null;
        if(user!=null && user.getName()!=null&&!"".equals(user.getName())&&user.getPwd()!=null&&!"".equals(user.getPwd())){
            user1=ud.login(user);
        }
        ss.close();
        return user1;
    }
}
