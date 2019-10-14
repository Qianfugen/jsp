package cn.qianfg.service.impl;

import cn.qianfg.dao.NewsTypeDao;
import cn.qianfg.pojo.NewsType;
import cn.qianfg.service.NewsTypeService;
import cn.qianfg.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {
    @Autowired
    public NewsTypeDao ntd;
    @Override
    public List<NewsType> queryAllNewsType() {
        List<NewsType> newsTypeList=ntd.queryAllNewsType();
        return newsTypeList;
    }
}
