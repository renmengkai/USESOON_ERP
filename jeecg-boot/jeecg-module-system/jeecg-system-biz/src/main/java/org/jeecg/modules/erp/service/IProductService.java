package org.jeecg.modules.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.erp.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * 商品服务接口
 */
public interface IProductService extends IService<Product> {
    List<Map<String, Object>> getAllProductTree();
}