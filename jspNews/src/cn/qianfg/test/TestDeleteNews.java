package cn.qianfg.test;


import cn.qianfg.dao.NewsDao;
import cn.qianfg.dao.impl.NewsDaoImpl;

public class TestDeleteNews {
    public static void main(String[] args) {
        NewsDao nd=new NewsDaoImpl();
        int flag=nd.deleteNews(111);
        if(flag>0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }

    }
}
