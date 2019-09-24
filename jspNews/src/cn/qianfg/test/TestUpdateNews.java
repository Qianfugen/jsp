package cn.qianfg.test;


import cn.qianfg.dao.NewsDao;
import cn.qianfg.dao.impl.NewsDaoImpl;
import cn.qianfg.polo.News;
import cn.qianfg.polo.QueryNews;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUpdateNews {
    public static void main(String[] args) {
        //测试queryNewsById
        System.out.println("测试queryNewsById");
        NewsDao nd = new NewsDaoImpl();
        List<News> newsList = new ArrayList<News>();
        News newsa = nd.queryNewsById(4444);
        System.out.println(newsa);

        //测试queryAll()
        System.out.println("测试queryAll()");
        newsList.clear();
        newsList=nd.queryAll();
        if (newsList != null) {
            for (News news : newsList) {
                System.out.println(news);
                System.out.println("********************************************");
            }
        }
        //测试模糊查询
        System.out.println("测试模糊查询");
        newsList.clear();
        QueryNews queryNews=new QueryNews();
        queryNews.setqTitle("666");
        queryNews.setqContext("");
        newsList=nd.queryNewsByLike(queryNews);
        if (newsList != null) {
            for (News news : newsList) {
                System.out.println(news);
                System.out.println("********************************************");
            }
        }

        //测试updateNews
        System.out.println("测试updateNews");
        News news = new News();
        if (newsList != null && newsList.size() != 0) {
            news = newsList.get(0);
            Date date = new Date();
            news.setTitle("GuangzhouNews");
            news.setNewsMan("Qianfg");
            news.setNewsDate(date);
            news.setTypeId(1);
            news.setPic("img/hehe..png");

            int flag = nd.updateNews(news);
            if (flag > 0) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }
        }
    }
}
