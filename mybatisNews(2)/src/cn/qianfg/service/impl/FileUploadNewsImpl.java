package cn.qianfg.service.impl;

import cn.qianfg.pojo.News;
import cn.qianfg.service.FileUploadNews;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class FileUploadNewsImpl implements FileUploadNews {
    @Override
    public News upload(List<FileItem> fs) {
        News news=new News();
        try{
            for(FileItem f:fs){
                if(f.isFormField()){//普通表单元素
                    String inputName=f.getFieldName();//获得name属性
                    String inputValue=f.getString("utf-8");//获得value值
                    switch(inputName){
                        case "id":news.setId(Integer.valueOf(inputValue));
                        break;
                        case "newsId":news.setNewsId(Integer.valueOf(inputValue));
                        break;
                        case "title":news.setTitle(inputValue);
                        break;
                        case "context":news.setContext(inputValue);
                        break;
                        case "newsMan":news.setNewsMan(inputValue);
                        break;
                        case "newsDate":
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            news.setNewsDate(sdf.parse(inputValue));
                            break;
                        case "typeId":news.setTypeId(Integer.valueOf(inputValue));
                        break;
                        case "pic":news.setPic(inputValue);
                        break;
                        default:break;
                    }
                }else {//文件类型
                    String oldName=f.getName();
                    String newName= UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."));
                    File newFile=new File("C:/Users/root/Desktop/img/"+newName);
                    f.write(newFile);
                    news.setPic("../img/"+newName);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return news;
    }
}
