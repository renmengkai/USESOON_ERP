package org.jeecg.modules.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.erp.entity.Purchase;
import org.jeecg.modules.erp.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Result<IPage<Purchase>> list(Purchase purchase, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Purchase> queryWrapper = QueryGenerator.initQueryWrapper(purchase, req.getParameterMap());
        Page<Purchase> page = new Page<>(pageNo, pageSize);
        IPage<Purchase> pageList = purchaseService.page(page, queryWrapper);
        return Result.ok(pageList);
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