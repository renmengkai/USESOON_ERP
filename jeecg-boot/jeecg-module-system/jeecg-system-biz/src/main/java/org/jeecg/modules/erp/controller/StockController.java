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
import org.jeecg.modules.erp.entity.Stock;
import org.jeecg.modules.erp.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * 库存相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @GetMapping("/list")
    public Result<IPage<Stock>> list(Stock stock, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Stock> queryWrapper = QueryGenerator.initQueryWrapper(stock, req.getParameterMap());
        Page<Stock> page = new Page<>(pageNo, pageSize);
        IPage<Stock> pageList = stockService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/{id}")
    public Result<Stock> get(@PathVariable String id) {
        return Result.ok(stockService.getById(id));
    }


    @PostMapping("/save")
    public Result<Boolean> save(HttpServletRequest request, @RequestBody @Valid Stock stock) {
        try {
            String tenantId = oConvertUtils.getString(TokenUtils.getTenantIdByRequest(request));
            stock.setTenantId(tenantId);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法保存账户信息。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            stock.setCrter(loginUser.getUsername());
            stock.setCrterName(loginUser.getRealname());
            stock.setCrteTime(new Date());
            boolean result = stockService.save(stock);
            log.info("用户 {} 成功保存账户 ID: {}", loginUser.getUsername(), stock.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("保存账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid Stock stock) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法更新账户信息。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            stock.setOpter(loginUser.getUsername());
            stock.setOpterName(loginUser.getRealname());
            stock.setOptTime(new Date());
            boolean result = stockService.updateById(stock);
            log.info("用户 {} 成功更新账户 ID: {}", loginUser.getUsername(), stock.getId());
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
            boolean result = stockService.removeById(id);
            log.info("用户 {} 成功删除账户 ID: {}", loginUser.getUsername(), id);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}