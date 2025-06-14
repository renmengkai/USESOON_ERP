package org.jeecg.modules.erp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ERP 银行账户实体类
 */
@Data
@TableName("erp_accounts")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description="ERP银行账户")
public class Accounts {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "账户类型：1.银行账户；2.支付宝账户；3.微信账户；对应字典account_type")
    private String type;

    @Schema(description = "账户名称")
    private String name;

    @Schema(description = "账户号")
    private String number;

    @Schema(description = "账户开户行（仅限银行账户类型）")
    private String openBank;

    @Schema(description = "账户所有人")
    private String owner;

    @Schema(description = "账户余额")
    private BigDecimal balance;

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
