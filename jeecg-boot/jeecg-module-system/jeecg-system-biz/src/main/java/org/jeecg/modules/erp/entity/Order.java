package org.jeecg.modules.erp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:  ERP订单
 * @Author:  renmk
 */
@Data
@TableName("erp_order")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP订单")
public class Order {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "订单编号")
    private String orderNo;

    @Schema(description = "客户名称")
    private String customerName;

    @Schema(description = "订单金额")
    private BigDecimal amount;

    @Schema(description = "订单状态：0否1是")
    private String status;

    @Schema(description = "下单日期")
    private Date orderDate;

    @Schema(description = "备注信息")
    private String remark;
}
