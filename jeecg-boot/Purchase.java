
    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "商品ID")
    private String productId;

    @Schema(description = "商品数量")
    private Integer productNumber;

    @Schema(description = "商品批次")
    private String batch;

    @Schema(description = "商品成本价(每个批次一个成本价)")
    private BigDecimal costPrice;

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
