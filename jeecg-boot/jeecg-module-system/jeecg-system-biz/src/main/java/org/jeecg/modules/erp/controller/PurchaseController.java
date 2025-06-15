package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.erp.entity.Purchase;
import org.jeecg.modules.erp.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进货单接口
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/purchase")
public class PurchaseController {
    @Autowired
    private IPurchaseService purchaseService;

    @GetMapping("/list")
    public Result<List<Purchase>> list() {
        return Result.ok(purchaseService.list());
    }

    @GetMapping("/{id}")
    public Result<Purchase> get(@PathVariable String id) {
        return Result.ok(purchaseService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Purchase purchase) {
        return Result.ok(purchaseService.save(purchase));
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody Purchase purchase) {
        return Result.ok(purchaseService.updateById(purchase));
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        return Result.ok(purchaseService.removeById(id));
    }
}