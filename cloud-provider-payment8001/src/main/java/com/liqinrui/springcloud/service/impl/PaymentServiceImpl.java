package com.liqinrui.springcloud.service.impl;

import com.liqinrui.springcloud.dao.PaymentDao;
import com.liqinrui.springcloud.entities.Payment;
import com.liqinrui.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public int createPayment(Payment payment) {
        return paymentDao.createPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
