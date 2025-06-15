package org.jeecg.modules.erp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * ERP 库存实体类
 */
@Data
@TableName("erp_stock")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP库存")
public class Stock {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "商品ID")
    private String productId;

    @Schema(description = "库存数量")
    private Integer quantity;

    @Schema(description = "仓库名称")
    private String warehouse;

    @Schema(description = "存放位置")
    private String location;

    @Schema(description = "最后更新时间")
    private Date lastUpdatedTime;

    @Schema(description = "备注信息")
    private String remark;
}