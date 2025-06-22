import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { getAllValidCustomer } from '/@/views/erp/customer/customer.api';
import { getDictItemsByCode } from '@/utils/dict';

const paybackOption = getDictItemsByCode('payback_method') || [];
export const columns: BasicColumn[] = [
  {
    title: '订单类型',
    dataIndex: 'orderType',
    width: 150,
  },
  {
    title: '订单编号',
    dataIndex: 'orderNumber',
    width: 150,
  },
  {
    title: '客户名称',
    dataIndex: 'customerName',
    width: 150,
  },
  {
    title: '订单金额',
    dataIndex: 'amount',
    width: 150,
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 200,
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'orderNumber',
    label: '订单编号',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'customerName',
    label: '客户名称',
    component: 'Input',
    colProps: { span: 8 },
  },
];

export const formSchema: FormSchema[] = [
  {
    field: 'orderTitle',
    label: '商品信息',
    component: 'Divider',
    slot: 'orderTitle',
    componentProps: {
      //是否虚线
      dashed: true,
      //分割线标题的位置（left | right | center）
      orientation: 'left',
      //文字是否显示为普通正文样式
      plain: true,
      //水平还是垂直类型（horizontal | vertical）
      type: 'horizontal',
    },
  },
  {
    field: 'customerId',
    label: '客户名称',
    component: 'ApiSelect',
    required: true,
    componentProps: {
      api: getAllValidCustomer,
      labelField: 'name',
      valueField: 'id',
    },
  },
  {
    field: 'productInfo',
    label: '商品名称',
    component: 'Cascader',
    required: true,
    slot: 'productInfo',
  },
  {
    field: 'salePrice',
    label: '商品售价',
    component: 'InputNumber',
    slot: 'salePrice',
    required: true,
  },
  {
    field: 'quantity',
    label: '商品数量',
    component: 'InputNumber',
    slot: 'quantity',
    required: true,
  },
  {
    field: 'orderTitle',
    label: '交付信息',
    component: 'Divider',
    slot: 'orderTitle',
  },
  {
    field: 'estimatedDeliveryDate',
    label: '预估交付日期',
    component: 'DatePicker',
    slot: 'estimatedDeliveryDate',
  },
  {
    field: 'actualDeliveryDate',
    label: '实际交付日期',
    component: 'DatePicker',
    slot: 'actualDeliveryDate',
  },
  {
    field: 'deliveryRemark',
    label: '交付备注信息',
    component: 'InputTextArea',
    slot: 'deliveryRemark',
  },
  {
    field: 'orderTitle',
    label: '回款信息',
    component: 'Divider',
    slot: 'orderTitle',
  },
  {
    field: 'estimatedPaybackDate',
    label: '预估回款日期',
    component: 'DatePicker',
    slot: 'estimatedPaybackDate',
  },
  {
    field: 'actualPaybackDate',
    label: '实际回款日期',
    component: 'DatePicker',
    slot: 'actualPaybackDate',
  },
  {
    field: 'paybackMethod',
    label: '回款方式',
    component: 'RadioGroup',
    componentProps: {
      options: paybackOption,
    },
  },
  {
    field: 'paybackRemark',
    label: '回款备注信息',
    component: 'InputTextArea',
    slot: 'paybackRemark',
  },
  {
    field: 'tags',
    label: '订单标签',
    component: 'Input',
    slot: 'tags',
  },
];
