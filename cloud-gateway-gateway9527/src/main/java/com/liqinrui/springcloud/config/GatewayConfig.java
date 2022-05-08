package com.liqinrui.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();

        builder.route("path_route_liqinrui", predicateSpec -> predicateSpec.path("/guonei").uri("https://news.baidu.com/guonei")).build();
        return builder.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        builder.route("path_route_liqinrui2", predicateSpec -> predicateSpec.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return builder.build();
    }
}
