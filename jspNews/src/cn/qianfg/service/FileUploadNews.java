package cn.qianfg.service;

import cn.qianfg.polo.News;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface FileUploadNews {
    public News upload(List<FileItem> fs);
}
