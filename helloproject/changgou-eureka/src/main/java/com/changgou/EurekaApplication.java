package com.changgou;


/**
 * @ClassName EurekaApplication
 * @Description 启动类
 * @Author 传智播客
 * @Date 11:33 2020/2/19
 * @Version 2.1
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        // 只需要run一下就能够发布一个spring应用
        SpringApplication.run(EurekaApplication.class, args);
    }
}
