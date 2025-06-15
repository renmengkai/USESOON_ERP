package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.erp.entity.Stock;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @GetMapping("/list")
    public Result<List<Stock>> list() {
        return Result.ok(stockService.list());
    }

    @GetMapping("/{id}")
    public Result<Stock> get(@PathVariable String id) {
        return Result.ok(stockService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Stock stock) {
        return Result.ok(stockService.save(stock));
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody Stock stock) {
        return Result.ok(stockService.updateById(stock));
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        return Result.ok(stockService.removeById(id));
    }
}