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
import org.jeecg.modules.erp.entity.Accounts;
import org.jeecg.modules.erp.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * 公司账户相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/accounts")
@Validated
public class AccountsController {
    @Autowired
    private IAccountsService accountsService;

    @GetMapping("/list")
    public Result<IPage<Accounts>> list(Accounts accounts, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<Accounts> queryWrapper = QueryGenerator.initQueryWrapper(accounts, req.getParameterMap());
        Page<Accounts> page = new Page<>(pageNo, pageSize);
        IPage<Accounts> pageList = accountsService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/{id}")
    public Result<Accounts> get(@PathVariable String id) {
        return Result.ok(accountsService.getById(id));
    }

    @PostMapping("/save")
    public Result<Boolean> save(HttpServletRequest request, @RequestBody @Valid Accounts accounts) {
        try {
            String tenantId = oConvertUtils.getString(TokenUtils.getTenantIdByRequest(request));
            accounts.setTenantId(tenantId);
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法保存账户信息。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            accounts.setCrter(loginUser.getUsername());
            accounts.setCrterName(loginUser.getRealname());
            accounts.setCrteTime(new Date());
            boolean result = accountsService.save(accounts);
            log.info("用户 {} 成功保存账户 ID: {}", loginUser.getUsername(), accounts.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("保存账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid Accounts accounts) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法更新账户信息。");
                return Result.error(401, "用户未登录，无法更新账户信息。");
            }
            accounts.setOpter(loginUser.getUsername());
            accounts.setOpterName(loginUser.getRealname());
            accounts.setOptTime(new Date());
            boolean result = accountsService.updateById(accounts);
            log.info("用户 {} 成功更新账户 ID: {}", loginUser.getUsername(), accounts.getId());
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
                return Result.error(401, "用户未登录，无法删除账户信息。");
            }
            boolean result = accountsService.removeById(id);
            log.info("用户 {} 成功删除账户 ID: {}", loginUser.getUsername(), id);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }
}