package cn.qianfg.dao.impl;

import cn.qianfg.basedao.BaseDao;
import cn.qianfg.dao.UserDao;
import cn.qianfg.polo.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl  extends BaseDao implements UserDao {
    @Override
    public List getResultObject(ResultSet rs) {
        List<User> userList=new ArrayList<User>();
        if(rs!=null){
            try{
                while(rs.next()){
                    User user=new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setPwd(rs.getString("pwd"));
                    user.setBirth(rs.getDate("birth"));
                    user.setAge(rs.getInt("age"));
                    user.setSex(rs.getInt("sex"));
                    userList.add(user);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return userList;
    }

    /*
        验证登录
         */
    public User login(User user){
        String sql="select * from t_user where name=? and pwd=?";
        List<Object> args=new ArrayList<Object>();
        if(user!=null) {
            args.add(user.getName());
            args.add(user.getPwd());
        }

        List<User> userList=query(sql,args);
        if(userList!=null && userList.size()!=0){
            user=userList.get(0);
            return user;
        }
        return null;
    }
}
