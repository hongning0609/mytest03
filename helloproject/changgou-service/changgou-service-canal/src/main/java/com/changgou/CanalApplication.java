package com.changgou;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName com.changgou.CanalApplication
 * @Description 监控数据库
 * @Author 传智播客
 * @Date 15:20 2020/2/23
 * @Version 2.1
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableCanalClient
@EnableFeignClients(basePackages = "com.changgou.content.feign")
public class CanalApplication {

    public static void main(String[] args) {

        SpringApplication.run(CanalApplication.class, args);

    }
}
