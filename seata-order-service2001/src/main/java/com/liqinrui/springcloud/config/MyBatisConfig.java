package com.liqinrui.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.liqinrui.springcloud.dao"})
public class MyBatisConfig {
}
