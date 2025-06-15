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

@Data
@TableName("erp_product")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP商品")
public class Product {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "商品编码")
    private String productCode;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品价格")
    private BigDecimal price;

    @Schema(description = "库存数量")
    private Integer stock;

    @Schema(description = "商品状态：0下架1上架")
    private String status;

    @Schema(description = "商品描述信息")
    private String description;

    @Schema(description = "商品创建时间")
    private Date createdTime;

    @Schema(description = "商品最后更新时间")
    private Date updatedTime;
}
