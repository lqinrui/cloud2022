package com.liqinrui.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
public class OrderConsulController {

    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
        String object = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return object;
    }
}
