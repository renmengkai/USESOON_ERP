import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { getAllProductTree,getAllProductList } from '/@/views/erp/product/product.api';
import { allSupplierList } from '/@/views/erp/supplier/supplier.api';
import { render } from '@/utils/common/renderUtils';

const productTree = await getAllProductTree();
const productList = await getAllProductList();
const supplierList = await allSupplierList();
export const columns: BasicColumn[] = [
  {
    title: '供应商名称',
    dataIndex: 'supplierNumber',
    width: 100,
    customRender({ record }) {
      let returnText = '';
      supplierList.forEach((item) => {
        if (item.id === record.supplierNumber) {
          returnText = item.name;
        }
      });
      return returnText;
    },
  },
  {
    title: '商品类型',
    dataIndex: 'type',
    width: 60,
    customRender: ({ record }) => {
      return render.renderDict(record.type, 'product_type');
    },
  },
  {
    title: '商品名称',
    dataIndex: 'productId',
    width: 80,
    customRender({ record }) {
      let returnText = '';
      productList.forEach((item) => {
        if (item.id === record.productId) {
          returnText = item.name;
        }
      });
      return returnText;
    },
  },
  {
    title: '商品规格',
    dataIndex: 'specification',
    width: 80,
    customRender({ record }) {
      let returnText = '';
      productList.forEach((item) => {
        if (item.id === record.productId) {
          returnText = item.specification;
        }
      });
      return returnText;
    },
  },
  {
    title: '批次',
    dataIndex: 'batch',
    width: 40,
  },
  {
    title: '数量',
    dataIndex: 'quantity',
    width: 40,
  },
  {
    title: '单价（元）',
    dataIndex: 'costPrice',
    width: 50,
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 150,
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
    field: 'productInfo',
    label: '商品信息',
    component: 'Cascader',
    componentProps: {
      placement: 'bottomLeft',
      showSearch: true,
      options: productTree,
    },
  },
  {
    label: '商品批次',
    field: 'batch',
    component: 'Input',
    required: true,
  },
  {
    label: '商品成本价',
    field: 'costPrice',
    component: 'InputNumber',
    required: true,
    componentProps: {
      precision: 2,
      addonAfter: '元',
      // formatter: (value) => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ','),
    },
  },
  {
    label: '商品数量',
    field: 'quantity',
    component: 'InputNumber',
    required: true,
    componentProps: {
      min: 0,
      addonAfter: '件',
    },
  },
  {
    label: '备注',
    field: 'remark',
    component: 'InputTextArea',
    required: false,
    componentProps: {
      placeholder: '请输入备注信息',
      rows: 6,
    },
  },
];
