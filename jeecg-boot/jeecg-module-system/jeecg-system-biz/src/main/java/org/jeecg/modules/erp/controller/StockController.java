package org.jeecg.modules.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.erp.entity.Stock;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Result<IPage<Stock>> list(Stock stock, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Stock> queryWrapper = QueryGenerator.initQueryWrapper(stock, req.getParameterMap());
        Page<Stock> page = new Page<>(pageNo, pageSize);
        IPage<Stock> pageList = stockService.page(page, queryWrapper);
        return Result.ok(pageList);
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

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        return Result.ok(stockService.removeById(id));
    }
}