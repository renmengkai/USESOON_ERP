package org.jeecg.modules.erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.erp.model.AnalysisInfo;

import java.util.List;
import java.util.Map;

/**
 * 统计服务接口
 */
public interface IAnalysisService extends IService<AnalysisInfo> {
    List<AnalysisInfo> getAggregate();
    List<Map<String, String>> getSalesList();
    List<Map<String, String>> getOrderList();
}