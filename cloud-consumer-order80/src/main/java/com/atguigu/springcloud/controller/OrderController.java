package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {

    //不能写死
//    private final String PAYMENT_URL="http://localhost:8001";

    //负载均衡
    private final String PAYMENT_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("consumer/payment/create")
    public CommonResult create(Payment payment){
        return  restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);
    }


    @GetMapping("consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }


    @GetMapping(value = "consumer/discover")
    public Object discover(){
        List<String> services= discoveryClient.getServices();
        for (String s:services){
            System.out.println(s);
        }

        List<ServiceInstance> instances=discoveryClient.getInstances("order-consumer-order80");

        for (ServiceInstance s:instances){
            System.out.println(s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
        }

        return this.discoveryClient;
    }
}
