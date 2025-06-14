package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
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
    public List<Stock> list() {
        return stockService.list();
    }

    @GetMapping("/{id}")
    public Stock get(@PathVariable String id) {
        return stockService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Stock stock) {
        return stockService.updateById(stock);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return stockService.removeById(id);
    }
}