package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Stock;
import org.jeecg.modules.erp.mapper.StockMapper;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 库存服务实现类
 */
@Slf4j
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    /**
     * 添加库存
     * @param stockId 库存ID
     */
    @Override
    public void addStock(String stockId) {

    }

    /**
     * 检查库存
     * @param productId 商品ID
     * @param quantity 商品数量
     */
    @Override
    public void checkStock(String productId, Integer quantity) {

    }

    /**
     * 扣除库存
     * @param productId 商品ID
     * @param quantity 商品数量
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> deductStock(String productId, Integer quantity, String userName, String realName) {
        // 按照操作时间或者创建时间顺序扣库存
        // 查询该产品的库存
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<Stock>()
                .eq("product_id", productId)
                .orderByAsc("crte_time")
                .orderByAsc("opt_time");
        List<Stock> stockList = this.list(queryWrapper);
        // 遍历库存列表
        List<Stock> updatedStocks = new java.util.ArrayList<>();
        Map<String, Object> stockInfo = new HashMap<>();
        Map<String, Integer> stockResult = new HashMap<>();
        List<Map<String, Object>> costList = new ArrayList<>();
        int remainingQuantity = quantity;
        for (Stock stock : stockList) {
            if (remainingQuantity <= 0) break;
            int currentStockQuantity = stock.getQuantity();
            int deductStockQuantity;
            if (currentStockQuantity >= remainingQuantity) {
                // 当前库存满足剩余扣除数量
                stock.setQuantity(currentStockQuantity - remainingQuantity);
                deductStockQuantity = remainingQuantity;
                remainingQuantity = 0;
            } else {
                // 当前库存不足，扣除全部当前库存数量
                stock.setQuantity(0);
                remainingQuantity -= currentStockQuantity;
                deductStockQuantity = currentStockQuantity;
            }
            // 设置更新时间和操作人信息（假设操作人信息从上下文获取）
            stock.setOptTime(new Date());
            stock.setOpter(userName); // 替换为实际的操作人ID
            stock.setOpterName(realName); // 替换为实际的操作人姓名
            updatedStocks.add(stock);
            stockResult.put(stock.getId(), deductStockQuantity);
            costList.add(new HashMap<>() {{
                put("id", stock.getId());
                put("quantity", deductStockQuantity);
                put("costPrice", stock.getCostPrice());
            }});
        }
        if (remainingQuantity > 0) {
            // 剩余数量无法满足扣除需求
            throw new RuntimeException("库存不足");
        }
        // 批量更新数据库中的库存记录
        if (!updatedStocks.isEmpty()) {
            this.updateBatchById(updatedStocks);
        }
        stockInfo.put("stockResult", stockResult);
        stockInfo.put("costList", costList);
        return stockInfo;
    }
}