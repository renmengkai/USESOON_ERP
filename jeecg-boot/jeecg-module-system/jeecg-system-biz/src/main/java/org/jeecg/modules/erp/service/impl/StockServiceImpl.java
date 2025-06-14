package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Stock;
import org.jeecg.modules.erp.mapper.StockMapper;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.stereotype.Service;

/**
 * 库存服务实现类
 */
@Slf4j
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {
}