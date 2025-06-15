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
 * ERP客户实体类
 */
@Data
@TableName("erp_customer")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP客户")
public class Customer {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "客户名称")
    private String name;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "客户等级：1.VIP客户；2.普通客户")
    private String level;

    @Schema(description = "客户状态：0停用1启用")
    private String status;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "操作人编号")
    private String opter;

    @Schema(description = "操作人姓名")
    private String opterId;

    @Schema(description = "操作时间")
    private Date optTime;

    @Schema(description = "创建人编号")
    private String crter;

    @Schema(description = "创建人姓名")
    private String crterName;

    @Schema(description = "创建时间")
    private Date crteTime;
}