package cn.qianfg.test;

import cn.qianfg.pojo.Student;
import cn.qianfg.util.RedisUtil;
import cn.qianfg.util.SerUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Student> stus = new ArrayList<Student>();
        Student stu1 = new Student();
        stu1.setName("张三");
        stu1.setAge(10);
        stu1.setSex("男");
        stus.add(stu1);

        Student stu2 = new Student();
        stu1.setName("李四");
        stu1.setAge(12);
        stu1.setSex("女");
        stus.add(stu2);

        Student stu3 = new Student();
        stu1.setName("王五");
        stu1.setAge(13);
        stu1.setSex("男");
        stus.add(stu3);

        Jedis jd = RedisUtil.init().getJedis();
        String status = jd.set("stus".getBytes(), SerUtil.getByte(stus));
        System.out.println(status);

//        Jedis jd = RedisUtil.init().getJedis();
//        List<Student> stus = (List<Student>) SerUtil.getObject(jd.get("stus".getBytes()));
//        System.out.println(stus);
    }
}
