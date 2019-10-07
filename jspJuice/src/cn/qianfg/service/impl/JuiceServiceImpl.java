package cn.qianfg.service.impl;

import cn.qianfg.dao.JuiceDao;
import cn.qianfg.dao.impl.JuiceDaoImpl;
import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.Juice;
import cn.qianfg.polo.Query;
import cn.qianfg.service.JuiceService;

import java.util.List;

public class JuiceServiceImpl implements JuiceService {
    JuiceDao jd=new JuiceDaoImpl();
    /**
     * 增
     *
     * @param juice
     */
    @Override
    public int addJuice(Juice juice) {
        return jd.addJuice(juice);
    }

    /**
     * 删
     *
     * @param ids
     */
    @Override
    public int deleteJuice(String[] ids) {
        int flag=-1;
        if(ids!=null&&ids.length>0){
            for(String id:ids){
                jd.deleteJuice(new Integer(id));
            }
            flag=1;
        }
        return flag;
    }

    /**
     * 改
     *
     * @param juice
     */
    @Override
    public int updateJuice(Juice juice) {
        return jd.updateJuice(juice);
    }

    /**
     * 根据Id查询
     *
     * @param id
     */
    @Override
    public Juice queryJuiceById(int id) {
        return jd.queryJuiceById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Juice> queryAllJuice() {
        return jd.queryAllJuice();
    }

    /**
     * 模糊查询
     *
     * @param query
     */
    @Override
    public List<Juice> queryJuiceByLike(Query query) {
        return jd.queryJuiceByLike(query);
    }

    /**
     * 分页查询
     *
     * @param fy
     */
    @Override
    public List<Juice> queryJuiceByFy(FenYe fy,String pg,Query query) {
        //设置总记录数,这样才能获取总页数
        fy.setRowsCount(queryJuiceCount(query));
        //处理页码
        Integer page=null;
        if(pg!=null){
            page=new Integer(pg);
            if(page<1){
                page=1;
            }
            if(page>fy.getPageCount()){
                page=fy.getPageCount();
            }
        }else {
            page=1;
        }

        //设置页码
        fy.setPage(page);
        //设置查询条件
        fy.setQuery(query);

        return jd.queryJuiceByFy(fy);
    }

    /**
     * 查询记录总数
     *
     * @param query
     */
    @Override
    public int queryJuiceCount(Query query) {
        return jd.queryJuiceCount(query);
    }
}
