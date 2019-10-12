package cn.qianfg.service;

import cn.qianfg.pojo.FenYe;
import cn.qianfg.pojo.News;
import cn.qianfg.pojo.QueryNews;

import java.util.List;

public interface NewsService {
    /**
     * 增加新闻
     *
     * @param news
     * @return
     */
    public int addNews(News news);

    /**
     * 删除新闻
     *
     * @param id
     * @return
     */
    public int deleteNews(int id);

    /**
     * 修改新闻
     *
     * @param news
     * @return
     */
    public int updateNews(News news);

    /**
     * 查询新闻
     *
     * @param id
     * @return
     */
    public News queryNewsById(int id);

    /**
     * 多条件分页查询
     * @param fy
     * @return
     */
    public List queryNewsByFy(FenYe fy,String pg,QueryNews queryNews);
}
