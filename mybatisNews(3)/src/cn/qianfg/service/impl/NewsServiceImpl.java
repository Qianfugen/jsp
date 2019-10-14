package cn.qianfg.service.impl;


import cn.qianfg.dao.NewsDao;
import cn.qianfg.pojo.FenYe;
import cn.qianfg.pojo.News;
import cn.qianfg.pojo.QueryNews;
import cn.qianfg.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao nd;

    @Override
    public int addNews(News news) {
        int flag = nd.addNews(news);
        return flag;
    }

    @Override
    public int deleteNews(int id) {
        int flag = nd.deleteNews(id);
        return flag;
    }

    @Override
    public int updateNews(News news) {
        int flag = nd.updateNews(news);
        return flag;
    }

    @Override
    public News queryNewsById(int id) {
        News news = nd.queryNewsById(id);
        return news;
    }

    @Override
    public List queryNewsByFy(FenYe fy, String pg, QueryNews queryNews) {
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

        return newsList;
    }
}
