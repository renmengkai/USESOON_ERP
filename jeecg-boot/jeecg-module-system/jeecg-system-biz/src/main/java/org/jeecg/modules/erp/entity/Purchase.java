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
 * ERP 采购单实体类
 */
@Data
@TableName("erp_purchase")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP采购单")
public class Purchase {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "采购编号")
    private String purchaseNo;

    @Schema(description = "供应商名称")
    private String supplierName;

    @Schema(description = "商品ID")
    private String productId;

    @Schema(description = "采购数量")
    private Integer quantity;

    @Schema(description = "采购成本价")
    private BigDecimal costPrice;

    @Schema(description = "采购日期")
    private Date purchaseDate;

    @Schema(description = "采购状态：0否1是")
    private String isValid;

    @Schema(description = "备注信息")
    private String remark;
}