package org.jeecg.modules.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.TokenUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.erp.entity.Order;
import org.jeecg.modules.erp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

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
    public Result<Boolean> save(HttpServletRequest request, @RequestBody @Valid Order order) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法保存订单信息。");
                return Result.error(401, "用户未登录，无法保存订单信息。");
            }
            String tenantId = oConvertUtils.getString(TokenUtils.getTenantIdByRequest(request));
            order.setTenantId(tenantId);
            order.setCrter(loginUser.getUsername());
            order.setCrterName(loginUser.getRealname());
            order.setCrteTime(new Date());
            boolean result = orderService.createOrder(order, loginUser.getUsername(), loginUser.getRealname());
            log.info("用户 {} 成功保存订单 ID: {}", loginUser.getUsername(), order.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("保存订单失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid Order order) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法更新订单信息。");
                return Result.error(401, "用户未登录，无法更新订单信息。");
            }
            order.setOpter(loginUser.getUsername());
            order.setOpterName(loginUser.getRealname());
            order.setOptTime(new Date());
            boolean result = orderService.updateById(order);
            log.info("用户 {} 成功更新订单 ID: {}", loginUser.getUsername(), order.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("更新订单失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam(name = "id") String id) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法删除订单。");
                return Result.error(401, "用户未登录，无法删除订单信息。");
            }
            orderService.deleteOrder(id);
            log.info("用户 {} 成功删除订单 ID: {}", loginUser.getUsername(), id);
            return Result.ok();
        } catch (Exception e) {
            log.error("删除订单失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}