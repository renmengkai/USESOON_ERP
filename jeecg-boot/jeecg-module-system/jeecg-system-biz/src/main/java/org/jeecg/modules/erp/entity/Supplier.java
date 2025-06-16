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
 * ERP供货商
 */
@Data
@TableName("erp_supplier")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP供货商")
public class Supplier {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "供应商名称")
    private String name;

    @Schema(description = "联系人")
    private String personName;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "税务登记号：供应商的税务登记号或统一社会信用代码")
    private String taxId;

    @Schema(description = "银行账户")
    private String bankAccount;

    @Schema(description = "开户银行")
    private String bankName;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "是否有效：0否1是")
    private String isValid;

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