package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileController
 * @Description 文件操作
 * @Author 传智播客
 * @Date 14:14 2020/2/20
 * @Version 2.1
 **/
@RestController
@CrossOrigin    // 解决跨域访问问题
public class FileController {

    /**
     * @author 栗子
     * @Description 文件上传
     * @Date 14:20 2020/2/20
     * @param file
     * @return java.lang.String
     **/
    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) throws Exception{
        // 1、将数据封装到文件对象中  commons-io.jar
        String filename = file.getOriginalFilename();   // 文件原始名称
        byte[] bytes = file.getBytes();                 // 文件内容
        String ext = FilenameUtils.getExtension(filename);  // 文件扩展名
        FastDFSFile fastDFSFile = new FastDFSFile(filename, bytes, ext);
        // 2、将附件进行上传
        String[] result = FastDFSClient.uploadFile(fastDFSFile);
        // 3、拼接附件地址
        String url = FastDFSClient.getTrackerUrl();
        String path = url + "/" + result[0] + "/" + result[1];
        return path;
    }
}
