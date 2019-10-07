package cn.qianfg.dao.impl;

import cn.qianfg.basedao.BaseDao;
import cn.qianfg.dao.UserDao;
import cn.qianfg.polo.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User login(User user) {
        String sql = "select * from t_user where name=? and pwd=?";
        List<Object> args = new ArrayList<Object>();
        List<User> userList = new ArrayList<User>();
        if (user != null) {
            args.add(user.getName());
            args.add(user.getPwd());
        }
        userList = query(sql, args);
        if (userList != null && userList.size() != 0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 以列表形式返回查询结果
     *
     * @param rs
     */
    @Override
    public List getResultObject(ResultSet rs) {
        List<User> userList = new ArrayList<User>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    User user = new User();
                    user.setName(rs.getString("name"));
                    user.setPwd(rs.getString("pwd"));
                    userList.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
}
