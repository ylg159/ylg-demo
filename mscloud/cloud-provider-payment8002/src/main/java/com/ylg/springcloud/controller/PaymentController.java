package com.ylg.springcloud.controller;

import com.ylg.springcloud.entities.CommonResult;
import com.ylg.springcloud.entities.Payment;
import com.ylg.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****result: " + result);

        if(result > 0){
            return new CommonResult(200, "succes, port : " + serverPort , result);
        }else{
            return new CommonResult(444, "fault");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****result: " + payment);

        if(payment != null){
            return new CommonResult(200, "succes, port : " + serverPort , payment);
        }else{
            return new CommonResult(444, "fault" + id);
        }
    }
}
