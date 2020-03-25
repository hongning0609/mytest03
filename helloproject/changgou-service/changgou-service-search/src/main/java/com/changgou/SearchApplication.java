package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @ClassName SearchApplication
 * @Description 检索启动类
 * @Author 传智播客
 * @Date 11:50 2020/2/24
 * @Version 2.1
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.changgou.goods.feign"})
@EnableElasticsearchRepositories(basePackages = {"com.changgou.search.dao"})
public class SearchApplication {

    public static void main(String[] args) {
        // java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
        // 存在jar的冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchApplication.class, args);
    }
}
