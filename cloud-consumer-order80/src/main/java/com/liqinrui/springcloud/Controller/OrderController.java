package com.liqinrui.springcloud.Controller;

import com.liqinrui.springcloud.entities.CommonResult;
import com.liqinrui.springcloud.entities.Payment;
import com.liqinrui.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    // 单机版
//    public static final String PAYMENT_URL="http://localhost:8001";

    // 集群版  只认微服务名称
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    RestTemplate restTemplate;
    @Resource
    LoadBalancer loadBalancer;
    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }


    @GetMapping("/consumer/payment/lb")
    public String getPaymentUrl(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        System.out.println(serviceInstances.get(0).getPort()+","+serviceInstances.get(1).getPort());
        ServiceInstance instances = loadBalancer.instances(serviceInstances);
        URI uri = instances.getUri();
        String object = restTemplate.getForObject(uri + "/payment/lb", String.class);
        return object;
    }


    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String object = restTemplate.getForObject("http://localhost:8001/payment/zipkin", String.class);
        return object;
    }
}
