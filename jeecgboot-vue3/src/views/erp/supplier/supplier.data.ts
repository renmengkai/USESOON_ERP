import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '供应商名称',
    dataIndex: 'name',
    width: 100,
  },
  {
    title: '供应商类型',
    dataIndex: 'type',
    width: 40,
    customRender: ({ record }) => {
      return record.type ? (record.type == '1' ? '国内供应商' : '国外供应商') : '';
    },
  },
  {
    title: '联系人',
    dataIndex: 'personName',
    width: 50,
  },
  {
    title: '联系电话',
    dataIndex: 'phone',
    width: 50,
  },
  {
    title: '地址',
    dataIndex: 'address',
    width: 150,
  },
  {
    title: '供应商状态',
    dataIndex: 'isValid',
    width: 40,
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
    label: '供应商名称',
    component: 'Input',
    colProps: { span: 6 },
  },
  {
    field: 'type',
    label: '供应商类型',
    component: 'Select',
    colProps: { span: 6 },
    componentProps: {
      options: [
        { label: '国内供应商', value: '1' },
        { label: '国外供应商', value: '2' },
      ],
    },
  },
  {
    field: 'isValid',
    label: '客户状态',
    component: 'Select',
    colProps: { span: 6 },
    defaultValue: '1',
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
    label: '供应商名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'type',
    label: '供应商类型',
    component: 'Select',
    defaultValue: '1',
    required: true,
    componentProps: {
      options: [
        { label: '国内供应商', value: '1' },
        { label: '国外供应商', value: '2' },
      ],
    },
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
    field: 'taxId',
    label: '税务登记号',
    component: 'Input',
    required: false,
  },
  {
    field: 'bankAccount',
    label: '银行账户',
    component: 'Input',
    required: false,
  },
  {
    field: 'bankName',
    label: '开户银行',
    component: 'Input',
    required: false,
  },
  {
    field: 'isValid',
    label: '供应商状态',
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
    componentProps: {
      placeholder: '请输入备注信息',
      rows: 6,
    },
  },
];
