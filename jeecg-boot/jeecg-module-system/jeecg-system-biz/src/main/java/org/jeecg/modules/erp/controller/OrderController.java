package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
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
    public List<Order> list() {
        return orderService.list();
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable String id) {
        return orderService.getById(id);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Order order) {
        return orderService.updateById(order);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        return orderService.removeById(id);
    }
}