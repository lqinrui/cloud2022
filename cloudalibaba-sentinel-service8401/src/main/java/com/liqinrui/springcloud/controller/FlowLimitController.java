package com.liqinrui.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        LOGGER.info("testD 测试 RT");

        LOGGER.info("testD  异常比例");
        int age = 10 / 0;
        return "---------testD";
    }

    /**
     * 热点限流配置
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "abc",blockHandler = "deal_testHotKey")
    public String testHotKey(
            @RequestParam(value = "p1",required = false) String p1,
            @RequestParam(value = "p2",required = false) String p2
    ){
        int age = 10/0;
        return "----------- testHotKey";
    }

    // 代替sentinel默认提示
    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "----------- deal_testHotKey,o(╥﹏╥)o";
    }

}
