package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
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
    public List<Product> list() {
        return productService.list();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable String id) {
        return productService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Product product) {
        return productService.updateById(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return productService.removeById(id);
    }
}