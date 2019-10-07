package cn.qianfg.dao;

import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.Juice;
import cn.qianfg.polo.Query;

import java.util.List;

public interface JuiceDao {
    /**
     * 增
     */
    public int addJuice(Juice juice);

    /**
     * 删
     */
    public int deleteJuice(int id);

    /**
     * 改
     */
    public int updateJuice(Juice juice);

    /**
     * 根据Id查询
     */
    public Juice queryJuiceById(int id);

    /**
     * 查询所有
     */
    public List<Juice> queryAllJuice();
    /**
     * 模糊查询
     */
    public List<Juice> queryJuiceByLike(Query query);

    /**
     * 分页查询
     */
    public List<Juice> queryJuiceByFy(FenYe fy);

    /**
     * 查询记录总数
     */
    public int queryJuiceCount(Query query);
}
