package cn.qianfg.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    //恶汉模式
    private static RedisUtil ru = new RedisUtil();
    //连接池配置
    private JedisPoolConfig jpc;
    //连接池对象
    private JedisPool jp;

    private RedisUtil() {
        jpc = new JedisPoolConfig();
        //最大空闲连接数
        jpc.setMaxIdle(8);
        //最大连接数
        jpc.setMaxTotal(40);
        //最大等待时长
        jpc.setMaxWaitMillis(5000);
        //确定连接可用
        jpc.setTestOnBorrow(true);
        jp = new JedisPool(jpc, "localhost", 6379);
    }

    public static RedisUtil init() {
        return ru;
    }

    public Jedis getJedis() {
        return jp.getResource();
    }
}
