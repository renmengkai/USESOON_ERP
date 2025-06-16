import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { render } from '@/utils/common/renderUtils';

export const columns: BasicColumn[] = [
  {
    title: '客户名称',
    dataIndex: 'name',
    width: 120,
  },
  {
    title: '联系人',
    dataIndex: 'personName',
    width: 80,
  },
  {
    title: '联系电话',
    dataIndex: 'phone',
    width: 80,
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: 100,
  },
  {
    title: '地址',
    dataIndex: 'address',
    width: 200,
  },
  {
    title: '客户等级',
    dataIndex: 'level',
    width: 50,
    customRender: ({ record }) => {
      return record.level ? (record.level == '1' ? 'VIP客户' : '普通客户') : '';
    },
  },
  {
    title: '客户状态',
    dataIndex: 'isValid',
    width: 50,
    customRender: ({ record }) => {
      return record.isValid ? (record.isValid == '1' ? '启用' : '停用') : '';
    },
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 200,
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '客户名称',
    component: 'Input',
    colProps: { span: 6 },
  },
  {
    field: 'level',
    label: '客户等级',
    component: 'Select',
    colProps: { span: 6 },
    componentProps: {
      options: [
        { label: 'VIP客户', value: '9' },
        { label: '普通客户', value: '1' },
      ],
    },
  },
  {
    field: 'isValid',
    label: '客户状态',
    component: 'Select',
    colProps: { span: 6 },
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
    field: 'name',
    label: '客户名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'personName',
    label: '联系人',
    component: 'Input',
    required: false,
  },
  {
    field: 'phone',
    label: '联系电话',
    component: 'Input',
    required: false,
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
    field: 'level',
    label: '客户等级',
    component: 'Select',
    required: true,
    defaultValue: '1',
    componentProps: {
      options: [
        { label: 'VIP客户', value: '9' },
        { label: '普通客户', value: '1' },
      ],
    },
  },
  {
    field: 'isValid',
    label: '客户状态',
    component: 'Switch',
    required: true,
    defaultValue: '1',
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
    componentProps: {
      placeholder: '请输入备注信息',
      rows: 6,
    },
  },
];
