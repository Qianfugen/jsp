package cn.qianfg.test;


import cn.qianfg.dao.NewsDao;
import cn.qianfg.dao.impl.NewsDaoImpl;
import cn.qianfg.polo.News;

import java.util.Date;

public class TestAddNews {
    public static void main(String[] args) {
        NewsDao nd=new NewsDaoImpl();
        News news=new News();
        Date date=new Date();
        news.setNewsId(111);
        news.setTitle("GuangzhouNews");
        news.setContext("hahahah");
        news.setNewsMan("Qianfg");
        news.setNewsDate(date);
        news.setTypeId(1);
        news.setPic("img/hehe..png");

        int flag=nd.addNews(news);
        if(flag>0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
}
