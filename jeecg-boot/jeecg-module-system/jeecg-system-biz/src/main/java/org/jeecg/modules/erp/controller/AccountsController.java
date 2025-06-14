package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Accounts;
import org.jeecg.modules.erp.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司账户相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/accounts")
public class AccountsController {
    @Autowired
    private IAccountsService accountsService;

    @GetMapping("/list")
    public List<Accounts> list() {
        return accountsService.list();
    }

    @GetMapping("/{id}")
    public Accounts get(@PathVariable String id) {
        return accountsService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Accounts accounts) {
        return accountsService.save(accounts);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Accounts accounts) {
        return accountsService.updateById(accounts);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return accountsService.removeById(id);
    }
}