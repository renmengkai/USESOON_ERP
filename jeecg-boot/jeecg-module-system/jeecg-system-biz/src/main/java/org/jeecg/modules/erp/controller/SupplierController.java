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
import org.jeecg.modules.erp.entity.Supplier;
import org.jeecg.modules.erp.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 供应商相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/list")
    public Result<IPage<Supplier>> list(Supplier supplier, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Supplier> queryWrapper = QueryGenerator.initQueryWrapper(supplier, req.getParameterMap());
        Page<Supplier> page = new Page<>(pageNo, pageSize);
        IPage<Supplier> pageList = supplierService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/allSupplierList")
    public Result<List<Supplier>> allSupplierList() {
        QueryWrapper<Supplier> wrapper = new QueryWrapper<>();
        wrapper.eq("is_valid", "1");
        return  Result.ok(supplierService.list(wrapper));
    }

    @GetMapping("/{id}")
    public Result<Supplier> get(@PathVariable String id) {
        return Result.ok(supplierService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(HttpServletRequest request, @RequestBody @Valid Supplier supplier) {
        try {
            String tenantId = oConvertUtils.getString(TokenUtils.getTenantIdByRequest(request));
            supplier.setTenantId(tenantId);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法保存供应商信息。");
                return Result.error(401, "用户未登录，无法保存供应商信息。");
            }
            supplier.setCrter(loginUser.getUsername());
            supplier.setCrterName(loginUser.getRealname());
            supplier.setCrteTime(new Date());
            boolean result = supplierService.save(supplier);
            log.info("用户 {} 成功保存供应商 ID: {}", loginUser.getUsername(), supplier.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("保存供应商失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid Supplier supplier) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法更新供应商信息。");
                return Result.error(401, "用户未登录，无法更新供应商信息。");
            }
            supplier.setOpter(loginUser.getUsername());
            supplier.setOpterName(loginUser.getRealname());
            supplier.setOptTime(new Date());
            boolean result = supplierService.updateById(supplier);
            log.info("用户 {} 成功更新供应商 ID: {}", loginUser.getUsername(), supplier.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("更新供应商失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法删除供应商。");
                return Result.error(401, "用户未登录，无法删除供应商信息。");
            }
            boolean result = supplierService.removeById(id);
            log.info("用户 {} 成功删除供应商 ID: {}", loginUser.getUsername(), id);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除供应商失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}