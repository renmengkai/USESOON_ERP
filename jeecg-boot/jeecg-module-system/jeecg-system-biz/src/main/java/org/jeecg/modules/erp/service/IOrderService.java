package org.jeecg.modules.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.erp.entity.Order;

/**
 * 订单服务接口
 */
public interface IOrderService extends IService<Order> {
    boolean createOrder(Order order, String userName, String realName);
    void deleteOrder(String id);
    void updateOrder(Order order);
}