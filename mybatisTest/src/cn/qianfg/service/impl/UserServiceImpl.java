package cn.qianfg.service.impl;

import cn.qianfg.dao.UserDao;
import cn.qianfg.pojo.User;
import cn.qianfg.service.UserService;
import cn.qianfg.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao ud;
    @Override
    public List<User> queryAllUser() {
        //获取数据库连接
        SqlSession ss= MybatisUtil.init().getSqlSession();
        //自动形成dao层接口实现类
        ud=ss.getMapper(UserDao.class);
        List<User> userList=ud.queryAllUser();
        ss.close();

        return userList;
    }
}
