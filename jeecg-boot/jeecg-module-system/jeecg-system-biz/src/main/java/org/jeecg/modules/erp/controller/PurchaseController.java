package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
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
    public List<Purchase> list() {
        return purchaseService.list();
    }

    @GetMapping("/{id}")
    public Purchase get(@PathVariable String id) {
        return purchaseService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Purchase purchase) {
        return purchaseService.updateById(purchase);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return purchaseService.removeById(id);
    }
}