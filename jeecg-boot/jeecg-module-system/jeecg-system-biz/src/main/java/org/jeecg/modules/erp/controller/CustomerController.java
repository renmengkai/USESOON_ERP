package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.erp.entity.Customer;
import org.jeecg.modules.erp.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户相关
 * 
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public Result<List<Customer>> list() {
        return Result.ok(customerService.list());
    }

    @GetMapping("/{id}")
    public Result<Customer> get(@PathVariable String id) {
        return Result.ok(customerService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Customer customer) {
        return Result.ok(customerService.save(customer));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Customer customer) {
        return Result.ok(customerService.updateById(customer));
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        return Result.ok(customerService.removeById(id));
    }
}