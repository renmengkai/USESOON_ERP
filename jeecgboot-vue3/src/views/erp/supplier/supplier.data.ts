import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '供应商名称',
    dataIndex: 'supplierName',
    width: 150,
  },
  {
    title: '供应商类型',
    dataIndex: 'supplierType',
    width: 150,
  },
  {
    title: '联系人',
    dataIndex: 'contact',
    width: 150,
  },
  {
    title: '联系电话',
    dataIndex: 'phone',
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
    field: 'supplierName',
    label: '供应商名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'supplierType',
    label: '供应商类型',
    component: 'Select',
    colProps: { span: 8 },
    componentProps: {
      options: [
        { label: '本地供应商', value: 'local' },
        { label: '国际供应商', value: 'international' },
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
    field: 'supplierName',
    label: '供应商名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'supplierType',
    label: '供应商类型',
    component: 'Select',
    required: true,
    componentProps: {
      options: [
        { label: '本地供应商', value: 'local' },
        { label: '国际供应商', value: 'international' },
      ],
    },
  },
  {
    field: 'contact',
    label: '联系人',
    component: 'Input',
    required: true,
  },
  {
    field: 'phone',
    label: '联系电话',
    component: 'Input',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
  },
];