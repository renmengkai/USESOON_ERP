package org.jeecg.modules.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.erp.entity.Order;
import org.jeecg.modules.erp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/list")
    public Result<IPage<Order>> list(Order order, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Order> queryWrapper = QueryGenerator.initQueryWrapper(order, req.getParameterMap());
        Page<Order> page = new Page<>(pageNo, pageSize);
        IPage<Order> pageList = orderService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/{id}")
    public Result<Order> get(@PathVariable String id) {
        return Result.ok(orderService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody Order order) {
        return Result.ok(orderService.save(order));
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody Order order) {
        return Result.ok(orderService.updateById(order));
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        return Result.ok(orderService.removeById(id));
    }
}