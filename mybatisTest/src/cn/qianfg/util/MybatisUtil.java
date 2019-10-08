package cn.qianfg.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtil {
    private SqlSessionFactory ssf;
    private static MybatisUtil mu=new MybatisUtil();
    private MybatisUtil(){
        //通过SqlSessionFactory加载主配置文件
        InputStream is=MybatisUtil.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        ssf=new SqlSessionFactoryBuilder().build(is);
    }
    public static MybatisUtil init(){
        return mu;
    }
    public SqlSession getSqlSession(){
        return ssf.openSession();
    }
}
