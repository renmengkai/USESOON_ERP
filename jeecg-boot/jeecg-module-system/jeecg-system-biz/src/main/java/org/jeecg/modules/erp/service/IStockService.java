package org.jeecg.modules.erp.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.erp.entity.Stock;

import java.util.Map;

/**
 * 库存服务接口
 */
public interface IStockService extends IService<Stock> {
    Map<String, Object> deductStock(String productId, Integer quantity, String userName, String realName);

    void addStock(JSONObject stockResult);

    void checkStock(String productId, Integer quantity);
}