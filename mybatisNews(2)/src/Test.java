import cn.qianfg.pojo.*;
import cn.qianfg.service.NewsService;
import cn.qianfg.service.NewsTypeService;
import cn.qianfg.service.UserService;
import cn.qianfg.service.impl.NewsServiceImpl;
import cn.qianfg.service.impl.NewsTypeServiceImpl;
import cn.qianfg.service.impl.UserServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        NewsService ns=new NewsServiceImpl();
        //测试deleteNews()
//        System.out.println(ns.deleteNews(70));
        //测试addNews()
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-dd");
//        News news=new News();
//        news.setNewsId(111);
//        news.setTitle("柯南");
//        news.setContext("真正的凶犯");
//        news.setNewsMan("工藤新一");
//        try{
//            news.setNewsDate(sdf.parse("2019-10-08"));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        news.setTypeId(3);
//        news.setPic("../img/jdaj.png");
//        System.out.println(news);
//        System.out.println(ns.addNews(news));

        //测试updateNews()和queryNewsById()
//        News news=ns.queryNewsById(26);
//        System.out.println(news);
//        news.setPic("../img/7758258.png");
//        news.setTitle("毛利小五郎");
//        System.out.println(ns.updateNews(news));

        //测试queryNewsByFy()
//        FenYe fy=new FenYe();
//        String pg="1";
//        QueryNews queryNews=new QueryNews();
//        queryNews.setqTitle("柯南");
//        queryNews.setqContext("凶犯");
//        fy.setQueryNews(null);
//        List<News> newsList=ns.queryNewsByFy(fy,pg,queryNews);
//        for(News news:newsList){
//            System.out.println(news);
//        }

        //测试queryAllNewsType()
//        NewsTypeService nts=new NewsTypeServiceImpl();
//        List<NewsType> newsTypeList=nts.queryAllNewsType();
//        System.out.println(newsTypeList);

        UserService us=new UserServiceImpl();
        User user=new User();
        user.setName("qianfg");
        user.setPwd("666");
        System.out.println(us.login(user));
    }
}
