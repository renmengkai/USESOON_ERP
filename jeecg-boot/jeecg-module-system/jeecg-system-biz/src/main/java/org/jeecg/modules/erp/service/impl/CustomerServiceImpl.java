package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Customer;
import org.jeecg.modules.erp.mapper.CustomerMapper;
import org.jeecg.modules.erp.service.ICustomerService;
import org.springframework.stereotype.Service;

/**
 * 客户服务实现类
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
}