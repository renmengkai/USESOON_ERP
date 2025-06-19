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
import org.jeecg.modules.erp.entity.Product;
import org.jeecg.modules.erp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/getAllProductList")
    public Result<List<Product>> getAllProductList() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_valid", "1");
        return Result.ok(productService.list(queryWrapper));
    }

    @GetMapping("/getAllProductTree")
    public Result<List<Map<String, Object>>> getAllProductTree() {
        return Result.ok(productService.getAllProductTree());
    }

    @GetMapping("/{id}")
    public Result<Product> get(@PathVariable String id) {
        return Result.ok(productService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(HttpServletRequest request, @RequestBody @Valid Product product) {
        try {
            String tenantId = oConvertUtils.getString(TokenUtils.getTenantIdByRequest(request));
            product.setTenantId(tenantId);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法保存账户信息。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            product.setCrter(loginUser.getUsername());
            product.setCrterName(loginUser.getRealname());
            product.setCrteTime(new Date());
            boolean result = productService.save(product);
            log.info("用户 {} 成功保存账户 ID: {}", loginUser.getUsername(), product.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("保存账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid Product product) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法更新账户信息。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            product.setOpter(loginUser.getUsername());
            product.setOpterName(loginUser.getRealname());
            product.setOptTime(new Date());
            boolean result = productService.updateById(product);
            log.info("用户 {} 成功更新账户 ID: {}", loginUser.getUsername(), product.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("更新账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam(name = "id") String id) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法删除账户。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            boolean result = productService.removeById(id);
            log.info("用户 {} 成功删除账户 ID: {}", loginUser.getUsername(), id);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}