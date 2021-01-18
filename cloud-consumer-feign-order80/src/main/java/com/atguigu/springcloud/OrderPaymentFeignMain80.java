package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderPaymentFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderPaymentFeignMain80.class,args);
        System.out.println("OrderPaymentFeignMain80启动成功！");
    }
}
