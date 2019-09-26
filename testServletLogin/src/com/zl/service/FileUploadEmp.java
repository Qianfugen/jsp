package com.zl.service;

import com.zl.pojo.Emp;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface FileUploadEmp {
    public Emp upload(List<FileItem> fs);
}
