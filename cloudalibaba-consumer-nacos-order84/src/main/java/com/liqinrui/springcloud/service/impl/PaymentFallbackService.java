package com.liqinrui.springcloud.service.impl;

import com.liqinrui.springcloud.entities.CommonResult;
import com.liqinrui.springcloud.entities.Payment;
import com.liqinrui.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回, -----paymentFallbackService",new Payment(id,"errorSerial"));
    }
}
