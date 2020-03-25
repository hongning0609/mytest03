package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @ClassName FastDFSClient
 * @Description 操作FastDFS的工具类（api操作）
 * @Author 传智播客
 * @Date 11:35 2020/2/20
 * @Version 2.1
 **/
public class FastDFSClient {

    /**
     * @author 栗子
     * @Description 初始化FastDFS的连接信息
     * @Date 11:40 2020/2/20
     * @param null
     * @return
     **/
    static {
        String filename = "fdfs_client.conf"; // 指定附件名称
        String conf_filename = new ClassPathResource(filename).getPath();
        try {
            // 需要加载FastDFS配置文件
            ClientGlobal.init(conf_filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 栗子
     * @Description 附件上传
     * @Date 11:55 2020/2/20
     * @param fastDFSFile
     * @return java.lang.String[]
     **/
    public static String[] uploadFile(FastDFSFile fastDFSFile){
        byte[] file_buff = fastDFSFile.getContent();    // 文件内容
        String file_ext_name = fastDFSFile.getExt();    // 文件扩展名
        NameValuePair[] meta_list = new NameValuePair[1]; // 附件备注
        meta_list[0] = new NameValuePair(fastDFSFile.getAuthor());
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件上传
            // 数组中包含的内容：文件所在的组 + 文件具体的目录
            String[] uploadResult = storageClient.upload_file(file_buff, file_ext_name, meta_list);
            return uploadResult;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 文件上传完成后，需要回显。在浏览器回显图片：地址url
    // www.360buy.com(ip:port)/images/xxx.jpg
    /**
     * @author 栗子
     * @Description 获取服务器地址
     * @Date 14:13 2020/2/20
     * @param
     * @return java.lang.String
     **/
    public static String getTrackerUrl(){
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取附件所在的服务器地址
            String hostAddress = trackerServer.getInetSocketAddress().getAddress().getHostAddress();
            // 4、获取服务器端口
            int port = ClientGlobal.getG_tracker_http_port();
            // 5、拼接url  http://192.168.211.132:8080/件所在的组/文件具体的目录
            String url = "http://" + hostAddress + ":" + port;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author 栗子
     * @Description 文件下载
     * @Date 14:37 2020/2/20
     * @param group_name
     * @param remote_filename
     * @return byte[]
     **/
    public static byte[] downloadFile(String group_name, String remote_filename){
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件下载
            byte[] bytes = storageClient.download_file(group_name, remote_filename);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @author 栗子
     * @Description 附件删除
     * @Date 14:46 2020/2/20
     * @param group_name        图片所在的组
     * @param remote_filename   图片所在的路径
     * @return void
     **/
    public static void deleteFile(String group_name, String remote_filename){
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件删除
            storageClient.delete_file(group_name, remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author 栗子
     * @Description 获取附件信息
     * @Date 14:50 2020/2/20
     * @param group_name
     * @param remote_filename
     * @return org.csource.fastdfs.FileInfo
     **/
    public static FileInfo getFileInfo(String group_name, String remote_filename){
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、获取附件信息
            FileInfo file_info = storageClient.get_file_info(group_name, remote_filename);
            return file_info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author 栗子
     * @Description 获取一个存储服务器信息
     * @Date 14:57 2020/2/20
     * @param groupName
     * @return org.csource.fastdfs.StorageServer
     **/
    public static StorageServer getStorageServerInfo(String groupName){
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取存储服务器信息
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer, groupName);
            return storeStorage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @author 栗子
     * @Description 获取多个存储服务器信息
     * @Date 14:57 2020/2/20
     * @param groupName
     * @return org.csource.fastdfs.StorageServer
     **/
    public static ServerInfo[] getStorageServerInfos(String groupName, String filename){
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器服务端
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取存储服务器信息
            ServerInfo[] storages = trackerClient.getFetchStorages(trackerServer, groupName, filename);
            return storages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
