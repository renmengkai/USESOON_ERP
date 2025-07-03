export interface GrowCardItem {
  icon: string;
  title: string;
  value?: number | string;
  total: number | string;
  weekPercent?: number;
  dayPercent?: number;
  color?: string;
  action?: string;
  footer?: string;
  desc?: string;
}

export const growCardList: GrowCardItem[] = [
  {
    title: '访问数',
    icon: 'visit-count|svg',
    value: 2000,
    total: 120000,
    color: 'green',
    action: '月',
  },
  {
    title: '成交额',
    icon: 'total-sales|svg',
    value: 20000,
    total: 500000,
    color: 'blue',
    action: '月',
  },
  {
    title: '下载数',
    icon: 'download-count|svg',
    value: 8000,
    total: 120000,
    color: 'orange',
    action: '周',
  },
  {
    title: '成交数',
    icon: 'transaction|svg',
    value: 5000,
    total: 50000,
    color: 'purple',
    action: '年',
  },
];

export const chartCardList: GrowCardItem[] = [
  {
    title: '总销售额（本年内）',
    icon: 'total-sales|svg',
    total: '126560.20',   // 总销售额
    value: '234.56',   // 日均销售额
    weekPercent: -12,  // 周浮动比例
    dayPercent: 10,    // 日浮动比例
    footer: '月均销售额：',
  },
  {
    title: '总订单量（本年内）',
    icon: 'order-quantity|svg',
    total: '8846',   // 总订单量
    value: '1234',   // 日订单量
    weekPercent: -12,  // 周浮动比例
    dayPercent: 10,    // 日浮动比例
    color: 'blue',
    footer: '月均订单量：',
  },
  {
    title: '总毛利（本年内）',
    icon: 'gross-profit|svg',
    value: '60%',
    total: '6560',
    color: 'orange',
    footer: '利润率：',
  },
  {
    title: '账户余额',
    icon: 'account-balance|svg',
    total: 223733.38,
    value: 5,
    footer: '账户数量：',
  },
];
export const bdcCardList: GrowCardItem[] = [
  {
    title: '受理量',
    icon: 'ant-design:info-circle-outlined',
    total: 100,
    value: 60,
    footer: '今日受理量',
  },
  {
    title: '办结量',
    icon: 'ant-design:info-circle-outlined',
    value: 54,
    total: 87,
    color: 'blue',
    footer: '今日办结量',
  },
  {
    title: '用户受理量',
    icon: 'ant-design:info-circle-outlined',
    value: 13,
    total: 15,
    color: 'orange',
    footer: '用户今日受理量',
  },
  {
    title: '用户办结量',
    icon: 'ant-design:info-circle-outlined',
    total: 9,
    value: 7,
    footer: '用户今日办结量',
  },
];

export const table = {
  dataSource: [
    { reBizCode: '1', type: '转移登记', acceptBy: '张三', acceptDate: '2019-01-22', curNode: '任务分派', flowRate: 60 },
    { reBizCode: '2', type: '抵押登记', acceptBy: '李四', acceptDate: '2019-01-23', curNode: '领导审核', flowRate: 30 },
    { reBizCode: '3', type: '转移登记', acceptBy: '王武', acceptDate: '2019-01-25', curNode: '任务处理', flowRate: 20 },
    { reBizCode: '4', type: '转移登记', acceptBy: '赵楼', acceptDate: '2019-11-22', curNode: '部门审核', flowRate: 80 },
    { reBizCode: '5', type: '转移登记', acceptBy: '钱就', acceptDate: '2019-12-12', curNode: '任务分派', flowRate: 90 },
    { reBizCode: '6', type: '转移登记', acceptBy: '孙吧', acceptDate: '2019-03-06', curNode: '任务处理', flowRate: 10 },
    { reBizCode: '7', type: '抵押登记', acceptBy: '周大', acceptDate: '2019-04-13', curNode: '任务分派', flowRate: 100 },
    { reBizCode: '8', type: '抵押登记', acceptBy: '吴二', acceptDate: '2019-05-09', curNode: '任务上报', flowRate: 50 },
    { reBizCode: '9', type: '抵押登记', acceptBy: '郑爽', acceptDate: '2019-07-12', curNode: '任务处理', flowRate: 63 },
    { reBizCode: '20', type: '抵押登记', acceptBy: '林有', acceptDate: '2019-12-12', curNode: '任务打回', flowRate: 59 },
    { reBizCode: '11', type: '转移登记', acceptBy: '码云', acceptDate: '2019-09-10', curNode: '任务签收', flowRate: 87 },
  ],
  columns: [
    {
      title: '业务号',
      align: 'center',
      dataIndex: 'reBizCode',
    },
    {
      title: '业务类型',
      align: 'center',
      dataIndex: 'type',
    },
    {
      title: '受理人',
      align: 'center',
      dataIndex: 'acceptBy',
    },
    {
      title: '受理时间',
      align: 'center',
      dataIndex: 'acceptDate',
    },
    {
      title: '当前节点',
      align: 'center',
      dataIndex: 'curNode',
    },
    {
      title: '办理时长',
      align: 'center',
      dataIndex: 'flowRate',
    },
  ],
  ipagination: {
    current: 1,
    pageSize: 5,
    pageSizeOptions: ['10', '20', '30'],
    showTotal: (total, range) => {
      return range[0] + '-' + range[1] + ' 共' + total + '条';
    },
    showQuickJumper: true,
    showSizeChanger: true,
    total: 0,
  },
};
export const table1 = {
  dataSource: [
    { reBizCode: 'A001', type: '转移登记', acceptBy: '张四', acceptDate: '2019-01-22', curNode: '任务分派', flowRate: 12 },
    { reBizCode: 'A002', type: '抵押登记', acceptBy: '李吧', acceptDate: '2019-01-23', curNode: '任务签收', flowRate: 3 },
    { reBizCode: 'A003', type: '转移登记', acceptBy: '王三', acceptDate: '2019-01-25', curNode: '任务处理', flowRate: 24 },
    { reBizCode: 'A004', type: '转移登记', acceptBy: '赵二', acceptDate: '2019-11-22', curNode: '部门审核', flowRate: 10 },
    { reBizCode: 'A005', type: '转移登记', acceptBy: '钱大', acceptDate: '2019-12-12', curNode: '任务签收', flowRate: 8 },
    { reBizCode: 'A006', type: '转移登记', acceptBy: '孙就', acceptDate: '2019-03-06', curNode: '任务处理', flowRate: 10 },
    { reBizCode: 'A007', type: '抵押登记', acceptBy: '周晕', acceptDate: '2019-04-13', curNode: '部门审核', flowRate: 24 },
    { reBizCode: 'A008', type: '抵押登记', acceptBy: '吴有', acceptDate: '2019-05-09', curNode: '部门审核', flowRate: 30 },
    { reBizCode: 'A009', type: '抵押登记', acceptBy: '郑武', acceptDate: '2019-07-12', curNode: '任务分派', flowRate: 1 },
    { reBizCode: 'A0010', type: '抵押登记', acceptBy: '林爽', acceptDate: '2019-12-12', curNode: '部门审核', flowRate: 16 },
    { reBizCode: 'A0011', type: '转移登记', acceptBy: '码楼', acceptDate: '2019-09-10', curNode: '部门审核', flowRate: 7 },
  ],
  columns: [
    {
      title: '业务号',
      align: 'center',
      dataIndex: 'reBizCode',
    },
    {
      title: '受理人',
      align: 'center',
      dataIndex: 'acceptBy',
    },
    {
      title: '发起时间',
      align: 'center',
      dataIndex: 'acceptDate',
    },
    {
      title: '当前节点',
      align: 'center',
      dataIndex: 'curNode',
    },
    {
      title: '超时时间',
      align: 'center',
      dataIndex: 'flowRate',
    },
  ],
  ipagination: {
    current: 1,
    pageSize: 5,
    pageSizeOptions: ['10', '20', '30'],
    showTotal: (total, range) => {
      return range[0] + '-' + range[1] + ' 共' + total + '条';
    },
    showQuickJumper: true,
    showSizeChanger: true,
    total: 0,
  },
};
