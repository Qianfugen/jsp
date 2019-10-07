package cn.qianfg.service.impl;

import cn.qianfg.polo.Juice;
import cn.qianfg.service.FileUploadJuice;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class FileUploadJuiceImpl implements FileUploadJuice {
    @Override
    public Juice upload(List<FileItem> fs) {
        Juice ju=new Juice();
        try{
            for(FileItem f:fs){
                if(f.isFormField()){//普通表单元素
                    String inputName=f.getFieldName();//获得Name属性
                    String inputValue=f.getString("utf-8");//获得value值
                    switch(inputName){
                        case "id":ju.setId(new Integer(inputValue));
                        break;
                        case "code":ju.setCode(new Integer(inputValue));
                        break;
                        case "name":ju.setName(inputValue);
                        break;
                        case "price":ju.setPrice(new Double(inputValue));
                        break;
                        case "amount":ju.setAmount(new Integer(inputValue));
                        break;
                        case "buyTime":
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            ju.setBuyTime(sdf.parse(inputValue));
                            break;
                        case "pic":ju.setPic(inputValue);
                        break;
                        default:break;
                    }
                }else{//文件类型
                    String oldName=f.getName();
                    String newName= UUID.randomUUID().toString()+ oldName.substring(oldName.lastIndexOf("."));
                    File newFile=new File("C:/Users/root/Desktop/juiceImg/"+newName);
                    f.write(newFile);
                    ju.setPic("/juiceImg/"+newName);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ju;
    }
}
