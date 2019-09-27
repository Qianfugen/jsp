package com.zl.dao.impl;

import com.zl.basedao.BaseDao;
import com.zl.dao.UserDao;
import com.zl.pojo.User;
import com.zl.util.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 在该抽象方法中处理结果集,不同的dao类继承BaseDao都必须要重写该方法
     * 那个dao继承该类,那么结果集中的数据就是那个dao对应的表
     *
     * @param rs
     */
    @Override
    public List<User> getResultObject(ResultSet rs) {
        List<User> us=new ArrayList<User>();
        try {
            while(rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setPwd(rs.getString("pwd"));
                u.setAge(rs.getInt("age"));
                u.setSex(rs.getInt("sex"));
                u.setUserId(rs.getInt("user_id"));
                us.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.init().close(conn,ps,rs);
        }
        return us;
    }

    /**
     * 验证用户名密码是否正确用户登录
     *
     * @param user
     */
    @Override
    public User login(User user) {
        String sql="select * from t_user where name=? and pwd=?";
        List<Object> args=new ArrayList<Object>();
        args.add(user.getName());
        args.add(user.getPwd());
        List<User> us=query(sql,args);
        if(us.size()==1){
            return us.get(0);
        }
        return null;
    }
}
