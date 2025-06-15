package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.erp.entity.Product;
import org.jeecg.modules.erp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public Result<List<Product>> list() {
        return Result.ok(productService.list());
    }

    @GetMapping("/{id}")
    public Result<Product> get(@PathVariable String id) {
        return Result.ok(productService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Product product) {
        return Result.ok(productService.save(product));
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody Product product) {
        return Result.ok(productService.updateById(product));
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        return Result.ok(productService.removeById(id));
    }
}