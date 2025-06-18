import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { allSupplierList } from '@/views/erp/supplier/supplier.api';
import { render } from '@/utils/common/renderUtils';

const supplierList = await allSupplierList();
export const columns: BasicColumn[] = [
  {
    title: '商品名称',
    dataIndex: 'name',
    width: 150,
  },
  {
    title: '商品供应商',
    dataIndex: 'supplierName',
    width: 150,
    customRender: ({ record }) => {
      let returnText = '';
      for (let i = 0; i < supplierList.length; i++) {
        if (supplierList[i].id === record.supplierNumber) {
          returnText = supplierList[i].name;
        }
      }
      return returnText;
    },
  },
  {
    title: '商品类型',
    dataIndex: 'type',
    width: 150,
    customRender: ({ record }) => {
      return render.renderDict(record.type, 'product_type');
    },
  },
  {
    title: '商品规格',
    dataIndex: 'specification',
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
    field: 'name',
    label: '商品名称',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'type',
    label: '商品类型',
    component: 'JDictSelectTag',
    colProps: { span: 8 },
    componentProps: {
      dictCode: 'product_type',
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
    label: '商品名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'supplierNumber',
    label: '商品供货商',
    component: 'ApiSelect',
    required: true,
    componentProps: {
      api: allSupplierList,
      labelField: 'name',
      valueField: 'id',
    },
  },
  {
    field: 'type',
    label: '商品类型',
    component: 'JDictSelectTag',
    required: true,
    componentProps: {
      dictCode: 'product_type',
    },
  },
  {
    field: 'specification',
    label: '商品规格',
    component: 'Input',
    required: true,
  },
  {
    field: 'isValid',
    label: '商品状态',
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
