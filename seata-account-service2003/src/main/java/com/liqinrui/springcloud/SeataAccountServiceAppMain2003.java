package com.liqinrui.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SeataAccountServiceAppMain2003 {

    public static void main(String[] args) {
        SpringApplication.run(SeataAccountServiceAppMain2003.class, args);
    }
}
