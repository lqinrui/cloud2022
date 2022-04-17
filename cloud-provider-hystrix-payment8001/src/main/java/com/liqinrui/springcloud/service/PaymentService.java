package com.liqinrui.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

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
}
