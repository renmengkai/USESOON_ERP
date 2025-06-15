import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '商品名称',
    dataIndex: 'productName',
    width: 150,
  },
  {
    title: '商品类型',
    dataIndex: 'productType',
    width: 150,
  },
  {
    title: '价格',
    dataIndex: 'price',
    width: 150,
  },
  {
    title: '库存',
    dataIndex: 'stock',
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
    field: 'productName',
    label: '商品名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'productType',
    label: '商品类型',
    component: 'Select',
    colProps: { span: 8 },
    componentProps: {
      options: [
        { label: '食品', value: 'food' },
        { label: '电子产品', value: 'electronics' },
      ],
    },
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
    field: 'productName',
    label: '商品名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'productType',
    label: '商品类型',
    component: 'Select',
    required: true,
    componentProps: {
      options: [
        { label: '食品', value: 'food' },
        { label: '电子产品', value: 'electronics' },
      ],
    },
  },
  {
    field: 'price',
    label: '价格',
    component: 'InputNumber',
    required: true,
  },
  {
    field: 'stock',
    label: '库存',
    component: 'InputNumber',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
  },
];