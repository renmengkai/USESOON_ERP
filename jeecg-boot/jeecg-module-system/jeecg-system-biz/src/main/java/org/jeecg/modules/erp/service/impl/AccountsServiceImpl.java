package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Accounts;
import org.jeecg.modules.erp.mapper.AccountsMapper;
import org.jeecg.modules.erp.service.IAccountsService;
import org.springframework.stereotype.Service;


/**
 * 银行账户 实现类
 * @author: jeecg-boot
 */
@Slf4j
@Service
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, Accounts>  implements IAccountsService {
}
