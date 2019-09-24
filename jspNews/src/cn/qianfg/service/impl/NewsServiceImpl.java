package cn.qianfg.service.impl;


import cn.qianfg.dao.NewsDao;
import cn.qianfg.dao.impl.NewsDaoImpl;
import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.News;
import cn.qianfg.polo.QueryNews;
import cn.qianfg.service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private NewsDao nd=new NewsDaoImpl();
    @Override
    public int addNews(News news) {
        return nd.addNews(news);
    }

    @Override
    public int deleteNews(String[] ids) {
        if(ids!=null && ids.length!=0) {
            for (String id : ids) {
                nd.deleteNews(Integer.valueOf(id));
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int updateNews(News news) {
        return nd.updateNews(news);
    }

    @Override
    public News queryNewsById(int id) {
        return nd.queryNewsById(id);
    }

    @Override
    public List queryAll() {
        return nd.queryAll();
    }

    @Override
    public List queryNewsByLike(QueryNews queryNews) {
        return nd.queryNewsByLike(queryNews);
    }

    @Override
    public List queryNewsByFy(FenYe fy, String pg, QueryNews queryNews) {
        fy.setRowsCount(queryNewsCount(queryNews));
        Integer page=null;
        System.out.println("总页数:"+fy.getPageCount());
        if(pg!=null){
            page=Integer.valueOf(pg);
            if(page<=0){
                page=1;
            }
            if(page>fy.getPageCount()){
                page=fy.getPageCount();
            }
        }else{
            page=1;
        }
        fy.setPage(page);
        fy.setQueryNews(queryNews);

        return nd.queryNewsByFy(fy);
    }

    @Override
    public int queryNewsCount(QueryNews queryNews) {
        return nd.queryNewsCount(queryNews);
    }
}
