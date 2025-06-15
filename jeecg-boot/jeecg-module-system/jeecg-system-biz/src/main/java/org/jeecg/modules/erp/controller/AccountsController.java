package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
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
import java.util.List;

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
    public Result<List<Accounts>> list() {
        return Result.ok(accountsService.list());
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
                return Result.error(401, "用户未登录，无法保存账户信息。");
            }
            boolean result = accountsService.updateById(accounts);
            log.info("用户 {} 成功更新账户 ID: {}", loginUser.getUsername(), accounts.getId());
            return Result.ok(result);
        } catch (Exception e) {
            log.error("更新账户失败：{}", e.getMessage(), e);
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (loginUser == null) {
                log.warn("用户未登录，无法删除账户。");
                return Result.error(401, "用户未登录，无法保存账户信息。");
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