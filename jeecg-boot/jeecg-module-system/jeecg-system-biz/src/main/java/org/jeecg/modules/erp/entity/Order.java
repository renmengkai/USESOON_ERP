package org.jeecg.modules.erp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: ERP订单
 * @Author: renmk
 */
@Data
@TableName("erp_order")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP订单")
public class Order {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "客户ID")
    private String customerId;

    @Schema(description = "商品信息")
    private String productInfo;

    @Schema(description = "商品ID")
    private String productId;

    @Schema(description = "商品售价")
    private BigDecimal salePrice;

    @Schema(description = "商品数量")
    private Integer quantity;

    @Schema(description = "订单节点：订货、定金、收款、交货、收货（节点字典）")
    private String nodeCode;

    @Schema(description = "预估交付日期")
    private Date estimatedDeliveryDate;

    @Schema(description = "实际交付日期")
    private Date actualDeliveryDate;

    @Schema(description = "交付备注信息")
    private String deliveryRemark;

    @Schema(description = "预估回款日期")
    private Date estimatedPaybackDate;

    @Schema(description = "实际回款日期")
    private Date actualPaybackDate;

    @Schema(description = "回款方式（回款方式字典）")
    private String paybackMethod;

    @Schema(description = "回款备注信息")
    private String paybackRemark;

    @Schema(description = "订单标签，存储形式：tag1,tag2,tag3")
    private String tags;

    @Schema(description = "库存占用明细")
    private String stockResult;

    @Schema(description = "订单总售价")
    private BigDecimal totalSalePrice;

    @Schema(description = "订单总成本")
    private BigDecimal totalCostPrice;

    @Schema(description = "订单总利润")
    private BigDecimal totalMargins;

    @Schema(description = "订单利润率（%）")
    private BigDecimal profitMargin;

    @Schema(description = "是否有效：0否1是")
    private String isValid;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "操作人编号")
    private String opter;

    @Schema(description = "操作人姓名")
    private String opterName;

    @Schema(description = "操作时间")
    private Date optTime;

    @Schema(description = "创建人编号")
    private String crter;

    @Schema(description = "创建人姓名")
    private String crterName;

    @Schema(description = "创建时间")
    private Date crteTime;
}
