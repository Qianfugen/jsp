package cn.qianfg.dao.impl;

import cn.qianfg.basedao.BaseDao;
import cn.qianfg.dao.UserDao;
import cn.qianfg.polo.User;
import cn.qianfg.util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    public User login(User user) {
        List<Object> args = new ArrayList<Object>();
        String sql = "select * from t_user where name=? and pwd=?";
        args.add(user.getName());
        args.add(user.getPwd());
        List<User> us = query(sql, args);
        System.out.println("us的大小"+us.size());
        if (us.size() == 1) {
            return us.get(0);
        }
        return null;
    }

    /**
     * 获取结果集
     *
     * @param rs 查询结果集
     * @return 列表形式的对象集合
     */
    public List<User> getResult(ResultSet rs) {
        List<User> us = new ArrayList<User>();
        try {
            while (rs.next()) {
                //获取数据库各字段数据
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUserId(rs.getInt("user_id"));
                u.setName(rs.getString("name"));
                u.setPwd(rs.getString("pwd"));
                u.setAge(rs.getInt("age"));
                u.setSex(rs.getInt("sex"));
                us.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.init().closeAll(conn, ps, rs);
        }
        return us;
    }
}
