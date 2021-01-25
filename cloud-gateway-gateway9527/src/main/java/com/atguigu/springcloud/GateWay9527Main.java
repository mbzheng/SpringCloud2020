package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class GateWay9527Main {
    public static void main(String[] args) {
        SpringApplication.run(GateWay9527Main.class,args);
        System.out.println("GateWay9527Main启动成功");
    }
}
