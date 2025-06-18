package org.jeecg.modules.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.erp.entity.Product;
import org.jeecg.modules.erp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Result<IPage<Product>> list(Product product, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Product> queryWrapper = QueryGenerator.initQueryWrapper(product, req.getParameterMap());
        Page<Product> page = new Page<>(pageNo, pageSize);
        IPage<Product> pageList = productService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/{id}")
    public Result<Product> get(@PathVariable String id) {
        return Result.ok(productService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Product product) {
        return Result.ok(productService.save(product));
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody Product product) {
        return Result.ok(productService.updateById(product));
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        return Result.ok(productService.removeById(id));
    }
}