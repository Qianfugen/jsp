package cn.qianfg.test;

import cn.qianfg.dao.NewsDao;
import cn.qianfg.dao.impl.NewsDaoImpl;
import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.News;
import cn.qianfg.polo.QueryNews;

import java.util.List;

public class TestFenYe {
    public static void main(String[] args) {
        NewsDao nd=new NewsDaoImpl();
        FenYe fy=new FenYe();
        fy.setPage(2);
        QueryNews queryNews=new QueryNews();
        queryNews.setqTitle("");
        queryNews.setqContext("");
        fy.setQueryNews(queryNews);

        List<News> newsList=nd.queryNewsByFy(fy);
        System.out.println(newsList);

        fy.setRowsCount(nd.queryNewsCount(queryNews));
        System.out.println(fy.getPageCount());

    }
}
