package cn.qianfg.service.impl;


import cn.qianfg.dao.NewsDao;
import cn.qianfg.pojo.FenYe;
import cn.qianfg.pojo.News;
import cn.qianfg.pojo.QueryNews;
import cn.qianfg.service.NewsService;
import cn.qianfg.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private NewsDao nd;

    @Override
    public int addNews(News news) {
        SqlSession ss = MybatisUtil.init().getSqlSession();
        nd = ss.getMapper(NewsDao.class);
        int flag = nd.addNews(news);
        ss.commit();
        ss.close();
        return flag;
    }

    @Override
    public int deleteNews(int id) {
        SqlSession ss = MybatisUtil.init().getSqlSession();
        nd = ss.getMapper(NewsDao.class);
        int flag = nd.deleteNews(id);
        ss.commit();
        ss.close();
        return flag;
    }

    @Override
    public int updateNews(News news) {
        SqlSession ss = MybatisUtil.init().getSqlSession();
        nd = ss.getMapper(NewsDao.class);
        int flag = nd.updateNews(news);
        ss.commit();
        ss.close();
        return flag;
    }

    @Override
    public News queryNewsById(int id) {
        SqlSession ss = MybatisUtil.init().getSqlSession();
        nd = ss.getMapper(NewsDao.class);
        News news = nd.queryNewsById(id);
        ss.close();
        return news;
    }

    @Override
    public List queryNewsByFy(FenYe fy, String pg, QueryNews queryNews) {
        SqlSession ss = MybatisUtil.init().getSqlSession();
        nd = ss.getMapper(NewsDao.class);
        fy.setRowsCount(nd.queryNewsCount(queryNews));

        Integer page = null;
        System.out.println("总页数:" + fy.getPageCount());
        if (pg != null) {
            page = Integer.valueOf(pg);
            if (page <= 0) {
                page = 1;
            }
            if (page > fy.getPageCount()) {
                page = fy.getPageCount();
            }
        } else {
            page = 1;
        }
        fy.setPage(page);
        fy.setQueryNews(queryNews);
        List<News> newsList = nd.queryNewsByFy(fy);
        ss.close();

        return newsList;
    }
}
