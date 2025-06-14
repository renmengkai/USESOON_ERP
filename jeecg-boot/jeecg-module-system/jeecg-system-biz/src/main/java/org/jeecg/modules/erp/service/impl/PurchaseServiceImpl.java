package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Purchase;
import org.jeecg.modules.erp.mapper.PurchaseMapper;
import org.jeecg.modules.erp.service.IPurchaseService;
import org.springframework.stereotype.Service;

/**
 * 采购服务实现类
 */
@Slf4j
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {
}