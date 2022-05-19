package com.liqinrui.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowLimitController.class);

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


    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("testD 测试 RT");

//        LOGGER.info("testD  异常比例");
//        int age = 10 / 0;
        return "---------testD";
    }
}
