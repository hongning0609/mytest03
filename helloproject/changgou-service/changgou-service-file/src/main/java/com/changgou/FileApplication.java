package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName FileApplication
 * @Description 文件管理启动类
 * @Author 传智播客
 * @Date 11:15 2020/2/20
 * @Version 2.1
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})   // 开箱即用
@EnableEurekaClient
public class FileApplication {

    // 入口main方法  微服务程序：jar
    // java -jar -xms 256m -xmx 512m xxx.jar
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
