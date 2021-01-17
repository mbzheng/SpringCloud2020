package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.net.URI;
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
    private LoadBalancer loadBalancer;//自己写的轮询

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

    @GetMapping("consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> response= restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else{
            return new CommonResult(444,"操作失败","c");
        }
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

    @GetMapping(value = "consumer/payment/lb")
    public String getPaymentLb(){
      List<ServiceInstance>  instances=  discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

      if(instances==null||instances.size()<=0){
          return  null;
      }
      ServiceInstance serviceInstance=loadBalancer.instances(instances);
      URI uri=serviceInstance.getUri();
      return restTemplate.getForObject(uri+"payment/lb",String.class);
    }
}
