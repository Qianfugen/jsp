package cn.qianfg.service;

import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.News;
import cn.qianfg.polo.QueryNews;

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
     * @param ids
     * @return
     */
    public int deleteNews(String[] ids);

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
     * 查询所有
     * @return
     */
    public List queryAll();

    /**
     * 模糊查询
     */
    public List queryNewsByLike(QueryNews queryNews);

    /**
     * 多条件分页查询
     * @param fy
     * @param pg
     * @param queryNews
     * @return
     */
    public List queryNewsByFy(FenYe fy, String pg, QueryNews queryNews);

    /**
     * 查询符合要求的记录数
     * @param queryNews
     * @return
     */
    public int queryNewsCount(QueryNews queryNews);
}
