package cn.qianfg.service.impl;

import cn.qianfg.dao.NewsTypeDao;
import cn.qianfg.pojo.NewsType;
import cn.qianfg.service.NewsTypeService;
import cn.qianfg.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    public NewsTypeDao ntd;
    @Override
    public List<NewsType> queryAllNewsType() {
        SqlSession ss= MybatisUtil.init().getSqlSession();
        ntd=ss.getMapper(NewsTypeDao.class);
        List<NewsType> newsTypeList=ntd.queryAllNewsType();
        ss.close();
        return newsTypeList;
    }
}
