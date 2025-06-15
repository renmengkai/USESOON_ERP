package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
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
    public List<Customer> list() {
        return customerService.list();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable String id) {
        return customerService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Customer customer) {
        return customerService.updateById(customer);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return customerService.removeById(id);
    }
}