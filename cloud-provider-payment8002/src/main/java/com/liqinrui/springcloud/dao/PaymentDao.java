package com.liqinrui.springcloud.dao;

import com.liqinrui.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int createPayment(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
