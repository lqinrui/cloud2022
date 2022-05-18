package com.liqinrui.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lqinrui
 * @date 2022-05-18 15:00:13
 * @description
 */

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        // 测试 阈值类型 ：线程数
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName()+"/t      .....testA");
        return "-----------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-----------testB";
    }

}
