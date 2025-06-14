import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '账户类型',
    dataIndex: 'accountType',
    width: 150,
  },
  {
    title: '账户名称',
    dataIndex: 'accountName',
    width: 150,
  },
  {
    title: '账户号',
    dataIndex: 'accountNumber',
    width: 150,
  },
  {
    title: '开户行',
    dataIndex: 'bankName',
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
    field: 'accountName',
    label: '账户名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'accountNumber',
    label: '账户号',
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
    field: 'accountType',
    label: '账户类型',
    component: 'Select',
    required: true,
    componentProps: {
      options: [
        { label: '银行账户', value: 'bank' },
        { label: '现金账户', value: 'cash' },
      ],
    },
  },
  {
    field: 'accountName',
    label: '账户名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'accountNumber',
    label: '账户号',
    component: 'Input',
    required: true,
  },
  {
    field: 'bankName',
    label: '开户行',
    component: 'Input',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
  },
];
