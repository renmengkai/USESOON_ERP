import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '客户名称',
    dataIndex: 'customerName',
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
    title: '邮箱',
    dataIndex: 'email',
    width: 150,
  },
  {
    title: '地址',
    dataIndex: 'address',
    width: 200,
  },
  {
    title: '客户等级',
    dataIndex: 'customerLevel',
    width: 150,
  },
  {
    title: '客户状态',
    dataIndex: 'status',
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
    field: 'customerName',
    label: '客户名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'contact',
    label: '联系人',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'phone',
    label: '联系电话',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'email',
    label: '邮箱',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'address',
    label: '地址',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'customerLevel',
    label: '客户等级',
    component: 'Select',
    colProps: { span: 8 },
    componentProps: {
      options: [
        { label: 'VIP客户', value: 'VIP' },
        { label: '普通客户', value: '普通' },
      ],
    },
  },
  {
    field: 'status',
    label: '客户状态',
    component: 'Select',
    colProps: { span: 8 },
    componentProps: {
      options: [
        { label: '停用', value: '0' },
        { label: '启用', value: '1' },
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
    field: 'customerName',
    label: '客户名称',
    component: 'Input',
    required: true,
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
    field: 'email',
    label: '邮箱',
    component: 'Input',
    required: false,
  },
  {
    field: 'address',
    label: '地址',
    component: 'Input',
    required: false,
  },
  {
    field: 'customerLevel',
    label: '客户等级',
    component: 'Select',
    required: true,
    componentProps: {
      options: [
        { label: 'VIP客户', value: 'VIP' },
        { label: '普通客户', value: '普通' },
      ],
    },
  },
  {
    field: 'status',
    label: '客户状态',
    component: 'Switch',
    required: true,
    componentProps: {
      checkedChildren: '启用',
      unCheckedChildren: '停用',
      checkedValue: '1',
      unCheckedValue: '0',
    },
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
    required: false,
  },
];