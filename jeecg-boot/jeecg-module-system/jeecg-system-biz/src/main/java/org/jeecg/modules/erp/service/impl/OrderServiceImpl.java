package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Order;
import org.jeecg.modules.erp.mapper.OrderMapper;
import org.jeecg.modules.erp.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现类
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}