package cn.qianfg.dao.impl;

import cn.qianfg.basedao.BaseDao;
import cn.qianfg.dao.JuiceDao;
import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.Juice;
import cn.qianfg.polo.Query;
import cn.qianfg.util.JDBCUtil;
import javafx.beans.binding.ObjectExpression;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JuiceDaoImpl extends BaseDao implements JuiceDao {

    /**
     * 增
     *
     * @param juice
     */
    @Override
    public int addJuice(Juice juice) {
        //构造sql
        String sql = "insert into juice(code,name,price,amount,buy_time,pic) values(?,?,?,?,?,?)";
        //构造参数列表
        List<Object> args = new ArrayList<Object>();
        args.add(juice.getCode());
        args.add(juice.getName());
        args.add(juice.getPrice());
        args.add(juice.getAmount());
        //java.util.Date转为java.sql.Date
        if (juice.getBuyTime() != null) {
            args.add(new java.sql.Date(juice.getBuyTime().getTime()));
        }
        args.add(juice.getPic());

        return update(sql, args);
    }

    /**
     * 删
     *
     * @param id
     */
    @Override
    public int deleteJuice(int id) {
        //构造sql
        String sql = "delete from juice where id=?";
        //构造参数列表
        List<Object> args = new ArrayList<Object>();
        args.add(id);

        return update(sql, args);
    }

    /**
     * 改
     *
     * @param juice
     */
    @Override
    public int updateJuice(Juice juice) {
        //构造sql
        String sql = "update juice set code=?,name=?,price=?,amount=?,buy_time=?,pic=? where id=?";
        //构造参数列表
        List<Object> args = new ArrayList<Object>();
        args.add(juice.getCode());
        args.add(juice.getName());
        args.add(juice.getPrice());
        args.add(juice.getAmount());
        //java.util.Date转为java.sql.Date
        if (juice.getBuyTime() != null) {
            args.add(new java.sql.Date(juice.getBuyTime().getTime()));
        }
        args.add(juice.getPic());
        //把id传入列表
        args.add(juice.getId());

        return update(sql, args);
    }

    /**
     * 根据Id查询
     *
     * @param id
     */
    @Override
    public Juice queryJuiceById(int id) {
        //构造sql语句
        String sql = "select * from juice where id=?";
        //构造参数列表
        List<Object> args = new ArrayList<Object>();
        args.add(id);
        List<Juice> juiceList = query(sql, args);
        if (juiceList != null && juiceList.size() != 0) {
            return juiceList.get(0);
        }
        return null;
    }

    /**
     * 查询所有
     */
    @Override
    public List<Juice> queryAllJuice() {
        String sql="select * from juice";
        return query(sql,null);
    }

    /**
     * 模糊查询
     */
    @Override
    public List<Juice> queryJuiceByLike(Query query) {
        String sql = "select * from juice where 1=1 ";
        List<Object> args = new ArrayList<Object>();
        //判断条件是否为空,然后构造sql,再填充参数列表
        if (query.getqName() != null && !"".equals(query.getqName())) {
            sql += "and name like concat('%',concat(?,'%')) ";
            args.add(query.getqName());
        }
        if (query.getqStartDate() != null) {
            sql += "and buy_time>=? ";
            args.add(new java.sql.Date(query.getqStartDate().getTime()));
        }
        if (query.getqEndDate() != null) {
            sql += "and buy_time<=?";
            args.add(new java.sql.Date(query.getqEndDate().getTime()));
        }
        return query(sql, args);
    }

    /**
     * 分页查询
     *
     * @param fy
     */
    @Override
    public List<Juice> queryJuiceByFy(FenYe fy) {
        List<Object> args = new ArrayList<Object>();
        String sql = "select * from (select e.*,ROWNUM r from (select * from juice where 1=1 ";
        if (fy.getQuery().getqName() != null && !"".equals(fy.getQuery().getqName())) {
            sql += "and name like concat('%',concat(?,'%')) ";
            args.add(fy.getQuery().getqName());
        }
        if (fy.getQuery().getqStartDate() != null && !"".equals(fy.getQuery().getqStartDate())) {
            sql += "and buy_time>=? ";
            args.add(new java.sql.Date(fy.getQuery().getqStartDate().getTime()));
        }
        if (fy.getQuery().getqEndDate() != null && !"".equals(fy.getQuery().getqEndDate())) {
            sql += "and buy_time<=? ";
            args.add(new java.sql.Date(fy.getQuery().getqEndDate().getTime()));
        }
        sql += ")e)e2 where r>? and r<=?";
        args.add(fy.getRowStart());
        args.add(fy.getRowEnd());
        return query(sql, args);
    }

    /**
     * 查询记录总数
     *
     * @param query
     */
    @Override
    public int queryJuiceCount(Query query) {
        List<Juice> juiceList=queryJuiceByLike(query);
        if(juiceList!=null){
            return juiceList.size();
        }
        return 0;
    }

    /**
     * 以列表形式返回查询结果
     *
     * @param rs
     */
    @Override
    public List getResultObject(ResultSet rs) {
        List<Juice> juiceList = new ArrayList<Juice>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    //把rs集合元素封装成Juice对象
                    Juice juice = new Juice();
                    juice.setId(rs.getInt("id"));
                    juice.setCode(rs.getInt("code"));
                    juice.setName(rs.getString("name"));
                    juice.setPrice(rs.getDouble("price"));
                    juice.setAmount(rs.getInt("amount"));
                    juice.setBuyTime(rs.getDate("buy_time"));
                    juice.setPic(rs.getString("pic"));
                    juiceList.add(juice);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //取得结果集,关闭数据库
                JDBCUtil.init().closeAll(conn, ps, rs);
            }
        }
        return juiceList;
    }
}
