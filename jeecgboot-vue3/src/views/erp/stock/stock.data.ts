import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '库存名称',
    dataIndex: 'stockName',
    width: 150,
  },
  {
    title: '库存类型',
    dataIndex: 'stockType',
    width: 150,
  },
  {
    title: '数量',
    dataIndex: 'quantity',
    width: 150,
  },
  {
    title: '单价',
    dataIndex: 'unitPrice',
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
    field: 'stockName',
    label: '库存名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'stockType',
    label: '库存类型',
    component: 'Select',
    colProps: { span: 8 },
    componentProps: {
      options: [
        { label: '原材料', value: 'raw' },
        { label: '成品', value: 'finished' },
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
    field: 'stockName',
    label: '库存名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'stockType',
    label: '库存类型',
    component: 'Select',
    required: true,
    componentProps: {
      options: [
        { label: '原材料', value: 'raw' },
        { label: '成品', value: 'finished' },
      ],
    },
  },
  {
    field: 'quantity',
    label: '数量',
    component: 'InputNumber',
    required: true,
  },
  {
    field: 'unitPrice',
    label: '单价',
    component: 'InputNumber',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
  },
];