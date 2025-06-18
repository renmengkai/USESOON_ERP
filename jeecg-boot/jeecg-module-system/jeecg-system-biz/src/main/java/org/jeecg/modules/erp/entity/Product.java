package org.jeecg.modules.erp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("erp_product")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP商品")
public class Product {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品供货商")
    private String supplierNumber;

    @Schema(description = "商品类型")
    private String type;

    @Schema(description = "商品规格")
    private String specification;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "商品状态：0下架1上架")
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
