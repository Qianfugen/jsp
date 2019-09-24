package cn.qianfg.dao;


import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.News;
import cn.qianfg.polo.QueryNews;

import java.util.List;

public interface NewsDao {
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
     * @param newsId
     * @return
     */
    public int deleteNews(int newsId);

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
     * @return
     */
    public List<News> queryNewsByFy(FenYe fy);

    /**
     * 符合要求记录总数
     * @param queryNews
     * @return
     */
    public int queryNewsCount(QueryNews queryNews);

}
