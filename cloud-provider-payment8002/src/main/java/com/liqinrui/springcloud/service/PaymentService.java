package com.liqinrui.springcloud.service;

import com.liqinrui.springcloud.entities.Payment;

public interface PaymentService {

    int createPayment(Payment payment);

    Payment getPaymentById(Long id);
}
