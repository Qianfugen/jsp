package cn.qianfg;

import cn.qianfg.dao.JuiceDao;
import cn.qianfg.dao.impl.JuiceDaoImpl;
import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.Juice;
import cn.qianfg.polo.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Test {
    private static void printResult(int flag){
        if(flag>0){
            System.out.println("执行成功");
        }else{
            System.out.println("执行失败");
        }
    }
    private static void printList(List<Juice> juiceList){
        for(Juice ju:juiceList){
            System.out.println(ju);
        }
    }

    public static void main(String[] args) {
        JuiceDao jd=new JuiceDaoImpl();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //测试增加
//        Juice juice=new Juice();
//        juice.setCode(1013);
//        juice.setName("萝卜汁");
//        juice.setPrice(12.00);
//        juice.setAmount(200);
//        try {
//            juice.setBuyTime(sdf.parse("2019-09-22"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        juice.setPic("img/2.png");

//        printResult(jd.addJuice(juice));

        //测试删除
//        printResult(jd.deleteJuice(13));

        //测试id查找和修改
//        Juice juice=jd.queryJuiceById(1);
//        System.out.println(juice);
//        juice.setCode(8888);
//        juice.setName("香蕉汁");
//        try {
//            juice.setBuyTime(sdf.parse("2019-09-18"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        printResult(jd.updateJuice(juice));

        //测试模糊查询
//        Query query=new Query();
//        query.setqName("汁");
//        try {
//            query.setqStartDate(sdf.parse("2019-09-20"));
//            query.setqEndDate(sdf.parse("2019-09-21"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        List<Juice> juiceList=jd.queryJuiceByLike(query);
//        printList(juiceList);

        //测试分页查询
        Query query=new Query();
        query.setqName("汁");
        try {
            query.setqStartDate(sdf.parse("2018-09-20"));
            query.setqEndDate(sdf.parse("2019-09-23"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int count=jd.queryJuiceCount(query);
        System.out.println("总记录数:"+count);
        FenYe fy=new FenYe();
        //查询记录总数
        fy.setRowsCount(count);
        fy.setPage(2);
        //开始条数
//        fy.setRowStart(0);
        //结束条数
//        fy.setRowEnd(3);
        //查询条件
        fy.setQuery(query);
        List<Juice> juiceList= jd.queryJuiceByFy(fy);
        System.out.println(juiceList);


    }
}
