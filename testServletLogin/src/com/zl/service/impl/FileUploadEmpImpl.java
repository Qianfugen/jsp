package com.zl.service.impl;

import com.zl.pojo.Emp;
import com.zl.service.FileUploadEmp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class FileUploadEmpImpl  implements FileUploadEmp {
    @Override
    public Emp upload(List<FileItem> fs) {
        Emp emp =new  Emp();
        try {
            for(FileItem f:fs){
                if(f.isFormField()){//是普通表单元素
                    //拿出普通表单元素 name属性
                    String inputName=f.getFieldName();
                    String inputValue=f.getString("utf-8");//获取value属性值
                    if("empNo".equals(inputName)){
                        emp.setEmpNo(new Integer(inputValue));
                    }
                    if("eName".equals(inputName)){
                        emp.seteName(inputValue);
                    }
                    if("job".equals(inputName)){
                        emp.setJob(inputValue);
                    }
                    if("mgr".equals(inputName)){
                        if(inputValue!=null &&!"".equals(inputValue)) {
                            emp.setMgr(new Integer(inputValue));
                        }
                    }
                    if("hireDate".equals(inputName)){
                        if(inputValue!=null &&!"".equals(inputValue)){
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            emp.setHireDate(sdf.parse(inputValue));
                        }
                    }
                    if("sal".equals(inputName)){
                        if(inputValue!=null &&!"".equals(inputValue)){
                            emp.setSal(new Double(inputValue));
                        }
                    }
                    if("comm".equals(inputName)){
                        if(inputValue!=null &&!"".equals(inputValue)){
                            emp.setComm(new Double(inputValue));
                        }
                    }
                    if("deptNo".equals(inputName)){
                        emp.setDeptNo(new Integer(inputValue));
                    }
                    //用来保证用户没有选头像的时候默认使用原来的头像
                    if("imgPic".equals(inputName)){
                        emp.setPic(inputValue);
                    }
                }else{
                    //上传框里面的数据
                    //获取用户上传文件的源文件名称
                    String oldName=f.getName();//aaa.png
                    if(oldName!=null &&!"".equals(oldName)&&f.getSize()>0) {
                        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                        //上传图片复制到服务器的该地址
                        File newFile = new File("D:/img/" + newName);
                        //写
                        f.write(newFile);
                        //保存图片路径到Emp中
                        emp.setPic("../img/" + newName);
                        /**
                         * http://localhost:8090/img/aaa.png
                         * http://localhost:8090/jspEmp/main.jsp
                         */
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}
