package org.jeecg.modules.erp.controller;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.erp.model.AnalysisInfo;
import org.jeecg.modules.erp.service.IAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 统计查询相关
 *
 * @author: renmk
 */
@Slf4j
@RestController
@RequestMapping("/erp/analysis")
public class AnalysisController {

    @Autowired
    private IAnalysisService analysisService;

    @GetMapping("/aggregate")
    public Result<List<AnalysisInfo>> aggregate() {
        return Result.ok(analysisService.getAggregate());
    }

    @GetMapping("/order/list")
    public Result<List<Map<String, String>>> order() {
        return Result.ok(analysisService.getOrderList());
    }

    @GetMapping("/sales/list")
    public Result<List<Map<String, String>>> sales() {
        return Result.ok(analysisService.getSalesList());
    }
}