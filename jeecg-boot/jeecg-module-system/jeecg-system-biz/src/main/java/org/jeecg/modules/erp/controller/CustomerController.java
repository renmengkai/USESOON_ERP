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
import org.jeecg.modules.erp.entity.Customer;
import org.jeecg.modules.erp.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 客户相关
 * 
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public Result<IPage<Customer>> list(Customer customer, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Customer> queryWrapper = QueryGenerator.initQueryWrapper(customer, req.getParameterMap());
        Page<Customer> page = new Page<>(pageNo, pageSize);
        IPage<Customer> pageList = customerService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/getAllValidCustomer")
    public Result<List<Customer>> getAllValidCustomer() {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>().eq("is_valid", "1");
        return Result.ok(customerService.list(queryWrapper));
    }

    @GetMapping("/{id}")
    public Result<Customer> get(@PathVariable String id) {
        return Result.ok(customerService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(HttpServletRequest request, @RequestBody @Valid Customer customer) {
        try {
            String tenantId = oConvertUtils.getString(TokenUtils.getTenantIdByRequest(request));
            customer.setTenantId(tenantId);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法保存客户信息。");
                return Result.error(401, "用户未登录，无法保存客户信息。");
            }
            customer.setCrter(loginUser.getUsername());
            customer.setCrterName(loginUser.getRealname());
            customer.setCrteTime(new Date());
            boolean result = customerService.save(customer);
            log.info("用户 {} 成功保存客户 ID: {}", loginUser.getUsername(), customer.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("保存客户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid Customer customer) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法更新客户信息。");
                return Result.error(401, "用户未登录，无法更新客户信息。");
            }
            customer.setOpter(loginUser.getUsername());
            customer.setOpterName(loginUser.getRealname());
            customer.setOptTime(new Date());
            boolean result = customerService.updateById(customer);
            log.info("用户 {} 成功更新客户 ID: {}", loginUser.getUsername(), customer.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("更新账客户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法删除客户。");
                return Result.error(401, "用户未登录，无法删除客户信息。");
            }
            boolean result = customerService.removeById(id);
            log.info("用户 {} 成功删除客户 ID: {}", loginUser.getUsername(), id);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除客户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}