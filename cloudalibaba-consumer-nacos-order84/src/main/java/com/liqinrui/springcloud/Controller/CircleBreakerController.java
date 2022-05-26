package com.liqinrui.springcloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.liqinrui.springcloud.entities.CommonResult;
import com.liqinrui.springcloud.entities.Payment;
import com.liqinrui.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CircleBreakerController.class);

    private static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")  // 没有任何配置
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")  // fallback 只负责业务异常
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")  //blockHandler只负责sentinel控制台的配置违规
    @SentinelResource(
            value = "fallback",
            blockHandler = "blockHandler",
            fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class}  //加入报该异常，不再有fallback方法兜底，没有降级效果了
    )
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4){
            throw new IllegalArgumentException("非法参数异常......");
        }else if (result.getData() == null){
            throw new NullPointerException("该ID没有对应记录,空指针异常");
        }
        return result;
    }

    // 本例是fallback
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水：blockException  "+blockException.getMessage(),payment);
    }


    //  openFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
