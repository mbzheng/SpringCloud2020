package com.atguigu.cloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class eurekaServer7001Main {
    public static void main(String[] args) {
        SpringApplication.run(eurekaServer7001Main.class,args);
        System.out.println("eurekaServer7001Main启动成功");
    }
}
