package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Product;
import org.jeecg.modules.erp.mapper.ProductMapper;
import org.jeecg.modules.erp.service.IProductService;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现类
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
}