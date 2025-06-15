import { BasicColumn } from '@/components/Table';
import { FormSchema } from '@/components/Table';
import { render } from '@/utils/common/renderUtils';

export const columns: BasicColumn[] = [
  {
    title: '账户类型',
    dataIndex: 'type',
    width: 50,
    customRender: ({ record }) => {
      console.log('record.type', record.type);
      return render.renderDict(record.type, 'account_type');
    },
  },
  {
    title: '账户名称',
    dataIndex: 'name',
    width: 100,
  },
  {
    title: '账户号',
    dataIndex: 'number',
    width: 150,
  },
  {
    title: '开户行',
    dataIndex: 'openBank',
    width: 150,
  },
  {
    dataIndex: 'owner',
    title: '账户所有人',
    width: 100,
  },
  {
    dataIndex: 'balance',
    title: '账户余额',
    width: 80,
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
    label: '账户名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'number',
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
    label: '账户类型',
    field: 'type',
    required: true,
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: 'account_type',
    },
  },
  {
    field: 'name',
    label: '账户名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'number',
    label: '账户号',
    component: 'Input',
    required: true,
  },
  {
    field: 'openBank',
    label: '账户开户行',
    component: 'Input',
    ifShow: ({ values }) => {
      return values.type == '1';
    },
    required: ({ values }) => {
      return values.type == '1';
    },
  },
  {
    field: 'owner',
    label: '账户所有人',
    component: 'Input',
    required: true,
  },
  {
    field: 'balance',
    label: '账户余额',
    component: 'InputNumber',
    suffix: '元',
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
  {
    field: 'isValid',
    label: '是否启用',
    component: 'Switch',
    defaultValue: '1',
    componentProps: {
      //开关大小，可选值：default small
      size: 'default',
      //非选中时的内容
      unCheckedChildren: '停用',
      //非选中时的值
      unCheckedValue: '0',
      //选中时的内容
      checkedChildren: '启用',
      //选中时的值
      checkedValue: '1',
      //是否禁用
      disabled: false,
    },
  },
];
