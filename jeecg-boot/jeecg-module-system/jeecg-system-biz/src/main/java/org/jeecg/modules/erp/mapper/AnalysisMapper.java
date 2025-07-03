package org.jeecg.modules.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.erp.model.AnalysisInfo;

/**
 * 统计 Mapper 接口
 */
public interface AnalysisMapper extends BaseMapper<AnalysisInfo> {
    AnalysisInfo getSales();
    AnalysisInfo getOrders();
    AnalysisInfo getProfit();
    AnalysisInfo getAccounts();
}