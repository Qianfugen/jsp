package cn.qianfg.service;

import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.Juice;
import cn.qianfg.polo.Query;

import java.util.List;

public interface JuiceService {
    /**
     * 增
     */
    public int addJuice(Juice juice);

    /**
     * 删
     */
    public int deleteJuice(String[] ids);

    /**
     * 改
     */
    public int updateJuice(Juice juice);

    /**
     * 根据Id查询
     */
    public Juice queryJuiceById(int id);

    /**
     * 模糊查询
     */
    public List<Juice> queryJuiceByLike(Query query);

    /**
     * 查询所有
     */
    public List<Juice> queryAllJuice();

    /**
     * 分页查询
     */
    public List<Juice> queryJuiceByFy(FenYe fy,String pg,Query query);

    /**
     * 查询记录总数
     */
    public int queryJuiceCount(Query query);
}
