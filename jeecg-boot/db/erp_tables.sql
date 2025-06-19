
-- ----------------------------
-- Table structure for erp_accounts
-- ----------------------------
DROP TABLE IF EXISTS `erp_accounts`;
CREATE TABLE `erp_accounts` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `type` varchar(255) DEFAULT NULL COMMENT '账户类型：1.银行账户；2.支付宝账户；3.微信账户；对应字典account_type',
  `name` varchar(255) DEFAULT NULL COMMENT '账户名称',
  `number` varchar(255) DEFAULT NULL COMMENT '账户号',
  `open_bank` varchar(255) DEFAULT NULL COMMENT '账户开户行（仅限银行账户类型）',
  `owner` varchar(255) DEFAULT NULL COMMENT '账户所有人',
  `balance` decimal(10,2) DEFAULT NULL COMMENT '账户余额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_valid` varchar(255) DEFAULT NULL COMMENT '是否有效：0否1是',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP银行账户';

-- ----------------------------
-- Table structure for erp_customer
-- ----------------------------
DROP TABLE IF EXISTS `erp_customer`;
CREATE TABLE `erp_customer` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `person_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `level` varchar(255) DEFAULT NULL COMMENT '客户等级：1.VIP客户；2.普通客户',
  `is_valid` varchar(255) DEFAULT NULL COMMENT '客户状态：0停用1启用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP客户';

-- ----------------------------
-- Table structure for erp_order
-- ----------------------------
DROP TABLE IF EXISTS `erp_order`;
CREATE TABLE `erp_order` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `is_valid` varchar(255) DEFAULT NULL COMMENT '订单状态：0停止1正常',
  `order_date` datetime DEFAULT NULL COMMENT '下单日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP订单';

-- ----------------------------
-- Table structure for erp_product
-- ----------------------------
DROP TABLE IF EXISTS `erp_product`;
CREATE TABLE `erp_product` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `supplier_number` varchar(255) DEFAULT NULL COMMENT '商品供货商',
  `type` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `specification` varchar(255) DEFAULT NULL COMMENT '商品规格',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_valid` varchar(255) DEFAULT NULL COMMENT '商品状态：0下架1上架',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP商品';

-- ----------------------------
-- Table structure for erp_purchase
-- ----------------------------
DROP TABLE IF EXISTS `erp_purchase`;
CREATE TABLE `erp_purchase` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `supplier_number` varchar(255) DEFAULT NULL COMMENT '商品供货商',
  `type` varchar(255) DEFAULT NULL COMMENT '商品类型',
  `specification` varchar(255) DEFAULT NULL COMMENT '商品规格',
  `product_id` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `batch` varchar(255) DEFAULT NULL COMMENT '商品批次',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '商品成本价',
  `quantity` varchar(255) DEFAULT NULL COMMENT '商品数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP采购单';

-- ----------------------------
-- Table structure for erp_stock
-- ----------------------------
DROP TABLE IF EXISTS `erp_stock`;
CREATE TABLE `erp_stock` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `product_id` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `batch` varchar(255) DEFAULT NULL COMMENT '商品批次',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '商品成本价',
  `quantity` int DEFAULT NULL COMMENT '库存数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP库存';

-- ----------------------------
-- Table structure for erp_supplier
-- ----------------------------
DROP TABLE IF EXISTS `erp_supplier`;
CREATE TABLE `erp_supplier` (
  `id` varchar(36) NOT NULL COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '供应商名称',
  `type` varchar(255) DEFAULT NULL COMMENT '供应商类型 1：国内供应商，2：国外供应商',
  `person_name` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `tax_id` varchar(255) DEFAULT NULL COMMENT '税务登记号：供应商的税务登记号或统一社会信用代码',
  `bank_account` varchar(255) DEFAULT NULL COMMENT '银行账户',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户银行',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_valid` varchar(255) DEFAULT NULL COMMENT '是否有效：0否1是',
  `tenant_id` varchar(255) DEFAULT NULL COMMENT '租户ID',
  `opter` varchar(255) DEFAULT NULL COMMENT '操作人编号',
  `opter_name` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `crter` varchar(255) DEFAULT NULL COMMENT '创建人编号',
  `crter_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `crte_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ERP供货商';