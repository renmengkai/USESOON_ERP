package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.erp.mapper.AnalysisMapper;
import org.jeecg.modules.erp.model.AnalysisInfo;
import org.jeecg.modules.erp.service.IAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 统计服务实现类
 */
@Slf4j
@Service
public class AnalysisServiceImpl extends ServiceImpl<AnalysisMapper, AnalysisInfo> implements IAnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<AnalysisInfo> getAggregate() {

        // 查询销售信息
        analysisMapper.getSales();
        // 获取订单信息
        analysisMapper.getOrders();
        // 获取毛利信息
        analysisMapper.getProfit();
        // 获取账户信息
        analysisMapper.getAccounts();

        return List.of();
    }

    @Override
    public List<Map<String, String>> getSalesList() {

        return List.of();
    }

    @Override
    public List<Map<String, String>> getOrderList() {
        return List.of();
    }
}