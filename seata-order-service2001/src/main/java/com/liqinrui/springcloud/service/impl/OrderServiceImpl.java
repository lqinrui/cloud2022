package com.liqinrui.springcloud.service.impl;

import com.liqinrui.springcloud.dao.OrderDao;
import com.liqinrui.springcloud.domain.Order;
import com.liqinrui.springcloud.service.AccountService;
import com.liqinrui.springcloud.service.OrderService;
import com.liqinrui.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单-> 调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * @param order
     */
    @Override
    @GlobalTransactional(name = "fsp_tx_group",rollbackFor = Exception.class)
    public void create(Order order) {
        LOGGER.info("--------->开始新建订单");
        orderDao.create(order);
        LOGGER.info("--------->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        LOGGER.info("--------->订单微服务开始调用库存，做扣减end");

        LOGGER.info("订单微服务开始调用账户，做扣减Money,{},{}",order.getUserId(),order.getMoney());
        accountService.decrease(order.getUserId(), order.getMoney());
        LOGGER.info("--------->订单微服务开始调用账户，做扣减end");

        LOGGER.info("--------->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        LOGGER.info("修改订单状态结束");

        LOGGER.info("下订单结束了，O(∩_∩)O哈哈~");

    }
}
