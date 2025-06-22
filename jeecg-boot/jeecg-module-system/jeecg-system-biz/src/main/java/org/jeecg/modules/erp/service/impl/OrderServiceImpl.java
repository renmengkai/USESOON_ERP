package org.jeecg.modules.erp.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Order;
import org.jeecg.modules.erp.mapper.OrderMapper;
import org.jeecg.modules.erp.service.IOrderService;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 订单服务实现类
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IStockService stockService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder(Order order, String userName, String realName) {
        // 扣库存
        Map<String, Integer> stockResult = stockService.deductStock(order.getProductId(), order.getQuantity(), userName, realName);
        String stockResultJson = JSON.toJSONString(stockResult);
        log.info("库存扣减结果: {}", stockResultJson);
        order.setStockResult(stockResultJson);
        // 保存订单
        return this.save(order);
    }
}