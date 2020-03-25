package com.changgou.test;

import com.changgou.util.FastDFSClient;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileOutputStream;
import java.net.InetSocketAddress;

/**
 * @ClassName FastDFSTest
 * @Description 文件操作相关测试
 * @Author 传智播客
 * @Date 14:38 2020/2/20
 * @Version 2.1
 **/
public class FastDFSTest {


    // 测试文件下载
    @Test
    public void testDownloadFile() throws Exception{
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF5OJyqANQGkAAL6RQQtvo0401.jpg";
        byte[] bytes = FastDFSClient.downloadFile(group_name, remote_filename);

        // 将文件写入本地  springmvc课程：fileupload.jar  commons-io.jar
        IOUtils.write(bytes, new FileOutputStream("d:/83.jpg"));
    }

    // 测试文件删除
    @Test
    public void testDeleteFile() throws Exception{
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF5OJyqANQGkAAL6RQQtvo0401.jpg";

        FastDFSClient.deleteFile(group_name, remote_filename);
    }

    // 获取存储服务器信息
    @Test
    public void testGetFileInfo() throws Exception{
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF5OLE2AWBHsAAL6RQQtvo0851.jpg";

        FileInfo fileInfo = FastDFSClient.getFileInfo(group_name, remote_filename);
        System.out.println("文件上传日期：" + fileInfo.getCreateTimestamp());
        System.out.println("文件所在服务器地址：" + fileInfo.getSourceIpAddr());
        System.out.println("文件大小：" + fileInfo.getFileSize());
    }

    // 获取存储服务器信息
    @Test
    public void testGetStorageServer() throws Exception{
        String group_name = "group1";
        StorageServer storageServerInfo = FastDFSClient.getStorageServerInfo(group_name);
        InetSocketAddress inetSocketAddress = storageServerInfo.getInetSocketAddress();
        String hostAddress = inetSocketAddress.getAddress().getHostAddress();
        int port = inetSocketAddress.getPort();
        int storePathIndex = storageServerInfo.getStorePathIndex();
        System.out.println("地址：" + hostAddress + "---端口：" + port + "---脚标：" + storePathIndex);
    }

    // 获取存储服务器信息
    @Test
    public void testGetStorageServers() throws Exception{
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF5OLE2AWBHsAAL6RQQtvo0851.jpg";
        ServerInfo[] infos = FastDFSClient.getStorageServerInfos(group_name, remote_filename);
        ServerInfo info = infos[0];
        System.out.println("地址：" + info.getIpAddr() + "端口：" + info.getPort());
    }
}
