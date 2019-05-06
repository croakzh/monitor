package com.croakzh.controller.base;

import com.croakzh.core.Constants;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * BaseCtrl基础继承类
 *
 * @author croakzh
 */
public class BaseCtrl {

    /**
     * 获得当前时间串 yyyyMMddHHmmss
     *
     * @return 时间串
     */
    public String getNow() {
        DateTimeFormatter dformatter = DateTimeFormatter.ofPattern(Constants.DEFAULT_TIMEFORMATTER);
        return LocalDateTime.now().format(dformatter);
    }

    /**
     * 重命名文件名
     *
     * @param fileName 文件名
     * @return 重命名后的文件名
     */
    public String renameFile(String now, String fileName) {
        String fileExtensionName = fileName;
        if (fileName.contains(Constants.MARK_DOT)) {
            fileExtensionName = fileName.substring(fileName.lastIndexOf(Constants.MARK_DOT) + 1);
        }
        String name = fileName.substring(0, fileName.lastIndexOf(Constants.MARK_DOT));
        String reFileName = now + "-" + name + "." + fileExtensionName;
        return reFileName;
    }

    /**
     * 获取上传路径
     * @return ../upload
     */
    public String getUploadPath() {
        return System.getProperty("user.dir") + File.separator + ".." + File.separator + "upload";
    }

}
