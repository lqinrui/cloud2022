package com.liqinrui.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.hystrix.metric.HystrixCommandStartStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

/**
 * @author lqinrui
 * @date 2022-05-06 16:56:14
 * @description
 */

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashBoardMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoardMain9001.class, args);
    }

}
