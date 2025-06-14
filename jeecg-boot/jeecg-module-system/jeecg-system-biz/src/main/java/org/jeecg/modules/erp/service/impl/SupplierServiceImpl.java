package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Supplier;
import org.jeecg.modules.erp.mapper.SupplierMapper;
import org.jeecg.modules.erp.service.ISupplierService;
import org.springframework.stereotype.Service;

/**
 * 供应商服务实现类
 */
@Slf4j
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
}