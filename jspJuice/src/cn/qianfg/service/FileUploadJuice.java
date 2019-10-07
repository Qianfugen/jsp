package cn.qianfg.service;

import cn.qianfg.polo.Juice;
import org.apache.commons.fileupload.FileItem;

import java.util.List;

public interface FileUploadJuice {
    public Juice upload(List<FileItem> fs);
}
