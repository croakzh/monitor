package com.croakzh.controller;

import com.croakzh.controller.base.BaseCtrl;
import com.croakzh.webfront.service.IApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 *
 * @author croakzh
 */
@RestController
@Slf4j
public class FileUploadController extends BaseCtrl {

    private final IApplicationService applicationService;

    @Autowired
    public FileUploadController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * upload 文件上传方法
     * 如果 applicationid 存在，则进行服务器之间的上传
     *
     * @param file 文件
     * @return {@link Map}
     */
    @RequestMapping("/upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) {
        log.debug("Start upload file. params is {}.", file);

        Map<String, String> result = new HashMap<>(3);
        // 文件上传路径
        String savePath = getUploadPath();
        System.out.println("upload" + getUploadPath());
        // 重命名文件
        String id = getNow();
        String fileName = file.getOriginalFilename();
        String saveFileName = renameFile(id, fileName);

        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(savePath, saveFileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 返回值，这三个对象是ng-zorro那边需要的
         */
        result.put("url", String.format("http://localhost:8080/upload/%s", saveFileName));
        result.put("uid", id);
        result.put("name", fileName);
        return result;
    }


}
