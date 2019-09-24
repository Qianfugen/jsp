package cn.qianfg.dao.impl;

import cn.qianfg.basedao.BaseDao;
import cn.qianfg.dao.NewsDao;
import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.News;
import cn.qianfg.polo.QueryNews;
import cn.qianfg.util.JDBCUtil;

import javax.sql.rowset.JdbcRowSet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl extends BaseDao implements NewsDao {
    @Override
    public List getResultObject(ResultSet rs) {
        List<News> newsList = new ArrayList<News>();
        if (rs != null) {
            try {
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setNewsId(rs.getInt("newsid"));
                    news.setTitle(rs.getString("title"));
                    news.setContext(rs.getString("context"));
                    news.setNewsMan(rs.getString("newsman"));
                    news.setNewsDate(rs.getDate("newsdate"));
                    news.setTypeId(rs.getInt("typeid"));
                    news.setPic(rs.getString("pic"));
                    newsList.add(news);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }

    @Override
    public int addNews(News news) {
        String sql = "insert into news(newsid,title,context,newsman,newsdate,typeid,pic) values(?,?,?,?,?,?,?)";
        List<Object> args = new ArrayList<Object>();
        args.add(news.getNewsId());
        args.add(news.getTitle());
        args.add(news.getContext());
        args.add(news.getNewsMan());
        args.add(new java.sql.Date(news.getNewsDate().getTime()));
        args.add(news.getTypeId());
        args.add(news.getPic());

        return update(sql, args);
    }

    @Override
    public int deleteNews(int id) {
        String sql = "delete from news where id=?";
        List<Object> args = new ArrayList<Object>();
        args.add(id);

        return update(sql, args);
    }

    @Override
    public int updateNews(News news) {
        String sql = "update news set newsid=?,title=?,context=?,newsman=?,newsdate=?,typeid=?,pic=? where id=?";
        List<Object> args = new ArrayList<Object>();
        System.out.println("id为:" + news.getId());
        args.add(news.getNewsId());
        args.add(news.getTitle());
        args.add(news.getContext());
        args.add(news.getNewsMan());
        if (news.getNewsDate() != null) {
            args.add(new java.sql.Date(news.getNewsDate().getTime()));
        } else {
            args.add(null);
        }
        args.add(news.getTypeId());
        args.add(news.getPic());
        args.add(news.getId());

        return update(sql, args);
    }

    @Override
    public News queryNewsById(int id) {
        String sql = "select * from news where id=?";
        List<Object> args = new ArrayList<Object>();
        args.add(id);
        List<News> newsList = query(sql, args);
        if (newsList != null && newsList.size() != 0) {
            return newsList.get(0);
        }

        return null;
    }

    @Override
    public List queryAll() {
        String sql = "select * from news";

        return query(sql, null);
    }

    @Override
    public List queryNewsByLike(QueryNews queryNews) {
        String qTitle = queryNews.getqTitle();
        String qContext = queryNews.getqContext();
        String sql = "select * from news where 1=1";
        List<Object> args = new ArrayList<Object>();
        //判断标题
        if (qTitle != null && !"".equals(qTitle)) {
            sql += " and title like concat('%',concat(?,'%'))";
            args.add(qTitle);
        }
        //判断内容
        if (qContext != null && !"".equals(qContext)) {
            sql += " and context like concat('%',concat(?,'%'))";
            args.add(qContext);
        }

        return query(sql, args);
    }

    @Override
    public List<News> queryNewsByFy(FenYe fy) {
        List<Object> args = new ArrayList<Object>();
        String sql = "select * from (select e.*, rownum r from (select * from news where 1=1 ";
        if (fy.getQueryNews().getqTitle() != null && !"".equals(fy.getQueryNews().getqTitle())) {
            sql += " and title like concat('%', concat(?, '%')) ";
            args.add(fy.getQueryNews().getqTitle());
        }
        if (fy.getQueryNews().getqContext() != null && !"".equals(fy.getQueryNews().getqContext())) {
            sql += " and context like concat('%', concat(?, '%'))";
            args.add(fy.getQueryNews().getqContext());
        }
        sql += ") e) where r>? and r<=?";
        args.add(fy.getRowsStart());
        args.add(fy.getRowsEnd());
        return query(sql,args);
    }

    @Override
    public int queryNewsCount(QueryNews queryNews) {
        int count=0;
        conn= JDBCUtil.init().getConnection();
        String sql="select count(1) from news where 1=1 ";
        String qTitle = queryNews.getqTitle();
        String qContext = queryNews.getqContext();
        List<Object> args = new ArrayList<Object>();
        //判断标题
        if (qTitle != null && !"".equals(qTitle)) {
            sql += " and title like concat('%',concat(?,'%'))";
            args.add(qTitle);
        }
        //判断内容
        if (qContext != null && !"".equals(qContext)) {
            sql += " and context like concat('%',concat(?,'%'))";
            args.add(qContext);
        }
        try{
            ps=conn.prepareStatement(sql);
            for(int i=0;i<args.size();i++){
                ps.setObject(i+1,args.get(i));
            }
            rs=ps.executeQuery();
            rs.next();
            count=rs.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.init().closeAll(conn,ps,rs);
        }
        return count;
    }
}
