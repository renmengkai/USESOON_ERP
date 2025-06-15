package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.erp.entity.Order;
import org.jeecg.modules.erp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<List<Order>> list() {
        return Result.ok(orderService.list());
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

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        return Result.ok(orderService.removeById(id));
    }
}