package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
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
    public Result<List<Supplier>> list() {
        return Result.ok(supplierService.list());
    }

    @GetMapping("/{id}")
    public Result<Supplier> get(@PathVariable String id) {
        return Result.ok(supplierService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Supplier supplier) {
        return Result.ok(supplierService.save(supplier));
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody Supplier supplier) {
        return Result.ok(supplierService.updateById(supplier));
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        return Result.ok(supplierService.removeById(id));
    }
}