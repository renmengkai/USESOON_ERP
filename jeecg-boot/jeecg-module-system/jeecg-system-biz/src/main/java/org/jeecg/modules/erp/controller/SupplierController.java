package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.entity.Supplier;
import org.jeecg.modules.erp.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/list")
    public List<Supplier> list() {
        return supplierService.list();
    }

    @GetMapping("/{id}")
    public Supplier get(@PathVariable String id) {
        return supplierService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Supplier supplier) {
        return supplierService.updateById(supplier);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return supplierService.removeById(id);
    }
}