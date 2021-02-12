package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacodProvider9001 {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacodProvider9001.class,args);
        System.out.println("AlibabaNacodProvider9001");
    }
}
