package com.liqinrui.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    //======服务降级
    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_OK,id："+id+"\t"+"O(n_n)哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
        // 超时情况逻辑
//        int timeNumber = 5;
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_TimeOut,id："+id+"\t"+"O(n_n)哈哈~   耗时";
        // 程序错误 情况
//        int age = 10/0;
//        return "线程池："+Thread.currentThread().getName()+"  paymentInfo_TimeOut,id："+id+"\t"+"O(n_n)哈哈~   耗时";
    }

    public String paymentInfo_TimeOutHandle(Integer id){
        return "线程池："+Thread.currentThread().getName()+"  系统繁忙或者运行报错，请稍后再试,id："+id+"\to(╥﹏╥)o";
    }


    //============服务熔断

    /**
     *
     * @param id
     * @return
     * @description  在时间窗口期10秒钟之内，10次请求次数失败率达到60%触发服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")  // 失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*********id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，/(ㄒoㄒ)/~~   id："+id;
    }
}
