package com.atguigu.springcloud.controller;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-18 10:43
 */
@RestController

public class PaymentController
{
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create( @RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        if(result > 0)
        {
            return new CommonResult(200,"插入成功 ","插入成功"+result);
        }else{
            return new CommonResult(444,"插入失败","失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功 ","查询成功"+payment);
        }else{
            return new CommonResult(444,"查询失败","失败"+payment);
        }
    }
}
