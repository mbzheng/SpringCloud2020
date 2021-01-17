package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther zzyy
 * @create 2020-02-18 10:43
 */
@RestController

public class PaymentController
{
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private  String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create( @RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        if(result > 0)
        {
            return new CommonResult(200,"插入成功 "+serverPort,"插入成功"+result);
        }else{
            return new CommonResult(444,"插入失败"+serverPort,"失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功 "+serverPort,"查询成功"+payment);
        }else{
            return new CommonResult(444,"查询失败"+serverPort,"失败"+payment);
        }
    }
    @GetMapping(value = "/payment/discover")
    public Object discover(){
      List<String> services= discoveryClient.getServices();
      for (String s:services){
          System.out.println(s);
      }

      List<ServiceInstance> instances=discoveryClient.getInstances("cloud-payment-service");

      for (ServiceInstance s:instances){
          System.out.println(s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
      }

      return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}


