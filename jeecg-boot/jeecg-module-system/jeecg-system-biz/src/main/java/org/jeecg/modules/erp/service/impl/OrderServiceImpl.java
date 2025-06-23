package org.jeecg.modules.erp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Order;
import org.jeecg.modules.erp.mapper.OrderMapper;
import org.jeecg.modules.erp.service.IOrderService;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
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
        Map<String, Object> stockInfo = stockService.deductStock(order.getProductId(), order.getQuantity(), userName, realName);
        // 计算金额等值
        calculatedAmount(order, stockInfo);
        return this.save(order);
    }

    private static void calculatedAmount(Order order, Map<String, Object> stockInfo) {
        Map<String, Integer> stockResult = (Map<String, Integer>) stockInfo.get("stockResult");
        List<Map<String, Object>> costList = (List<Map<String, Object>>) stockInfo.get("costList");
        String stockResultJson = JSON.toJSONString(stockResult);
        log.info("库存扣减结果: {}", stockResultJson);
        order.setStockResult(stockResultJson);
        // 计算订单金额等值
        BigDecimal totalSalePrice = order.getSalePrice().multiply(new BigDecimal(order.getQuantity()));
        order.setTotalSalePrice(totalSalePrice);
        // 计算总成本
        BigDecimal totalCostPrice = new BigDecimal(0);
        for (Map<String, Object> costMap : costList){
            JSONObject jsonObject = JSONObject.from(costMap);
            BigDecimal costPrice = jsonObject.getBigDecimal("costPrice");
            Integer quantity = jsonObject.getInteger("quantity");
            BigDecimal totalCost = costPrice.multiply(new BigDecimal(quantity));
            totalCostPrice = totalCostPrice.add(totalCost);
        }
        order.setTotalCostPrice(totalCostPrice);
        // 计算利润
        order.setTotalMargins(totalSalePrice.subtract(totalCostPrice));
        if (totalCostPrice.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal profitMargin = totalSalePrice.subtract(totalCostPrice)
                .divide(totalCostPrice, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
            order.setProfitMargin(profitMargin);
        } else {
            order.setProfitMargin(BigDecimal.ZERO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(String id) {
        // 查询当前订单
        Order order = this.getById(id);
        JSONObject stockResult = JSONObject.parseObject(order.getStockResult());
        // 重置库存
        stockService.addStock(stockResult);
        // 保存订单
        this.removeById(id);
    }

    @Override
    public void updateOrder(Order order) {
        // 查询当前订单信息进行对比
        Order oldOrder = this.getById(order.getId());
        if(!oldOrder.getQuantity().equals(order.getQuantity()) || 
                !oldOrder.getProductId().equals(order.getProductId()) || 
                !oldOrder.getSalePrice().equals(order.getSalePrice())){
            // 如果修改信息包括库存信息，则进行库存恢复
            JSONObject stockResult = JSONObject.parseObject(oldOrder.getStockResult());
            stockService.addStock(stockResult);
            // 扣库存
            Map<String, Object> stockInfo = stockService.deductStock(order.getProductId(), order.getQuantity(), order.getOpter(), order.getOpterName());
            calculatedAmount(order, stockInfo);
        }
        // 更新
        this.updateById(order);
    }
}