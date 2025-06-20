import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { getAllProductTree } from '/@/views/erp/product/product.api';

const productTree = await getAllProductTree();

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
    label: 'id',
    field: 'id',
    component: 'Input',
    show: false,
  },
  {
    field: 'productInfo',
    label: '商品信息',
    component: 'Cascader',
    required: true,
    componentProps: {
      placement: 'bottomLeft',
      showSearch: true,
      options: productTree,
    },
  },
  {
    field: 'orderNumber',
    label: '商品售价',
    component: 'Input',
    required: true,
  },
  {
    field: 'customerName',
    label: '商品数量',
    component: 'Input',
    required: true,
  },
  {
    field: 'amount',
    label: '订单金额',
    component: 'InputNumber',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
  },
]
