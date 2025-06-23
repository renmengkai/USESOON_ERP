import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { getAllValidCustomer } from '/@/views/erp/customer/customer.api';
import { getDictItemsByCode } from '@/utils/dict';
import { getAllProductList } from '@/views/erp/product/product.api';

const paybackOption = getDictItemsByCode('payback_method') || [];
const validCustomer = await getAllValidCustomer();
const customerList = validCustomer.map((item) => ({
  value: item.id,
  label: item.name,
}));
const productList = await getAllProductList();
export const columns: BasicColumn[] = [
  {
    title: '订单编号',
    dataIndex: 'id',
    width: 80,
  },
  {
    title: '客户名称',
    dataIndex: 'customerId',
    width: 80,
    customRender: ({ record }) => {
      let returnText = '';
      for (let i = 0; i < validCustomer.length; i++) {
        if (validCustomer[i].id === record.customerId) {
          returnText = validCustomer[i].name;
        }
      }
      return returnText;
    },
  },
  {
    title: '商品名称',
    dataIndex: 'productInfo',
    width: 60,
    customRender: ({ record }) => {
      let returnText = '';
      for (let i = 0; i < productList.length; i++) {
        if (productList[i].id === record.productId) {
          returnText = productList[i].name;
        }
      }
      return returnText;
    },
  },
  {
    title: '商品规格',
    dataIndex: 'specification',
    width: 60,
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
    title: '商品数量',
    dataIndex: 'quantity',
    width: 50,
  },
  {
    title: '订单金额',
    dataIndex: 'totalSalePrice',
    width: 50,
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 120,
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'customerId',
    label: '客户名称',
    component: 'Select',
    colProps: { span: 8 },
    componentProps: {
      options: customerList,
    },
  },
];

export const formSchema: FormSchema[] = [
  {
    field: 'orderTitle1',
    label: '商品信息',
    component: 'Divider',
    slot: 'orderTitle1',
    componentProps: {
      //是否虚线
      dashed: false,
      //分割线标题的位置（left | right | center）
      orientation: 'left',
      //文字是否显示为普通正文样式
      plain: true,
      //水平还是垂直类型（horizontal | vertical）
      type: 'horizontal',
    },
  },
  {
    label: 'id',
    field: 'id',
    component: 'Input',
    show: false,
  },
  {
    field: 'customerId',
    label: '客户名称',
    component: 'ApiSelect',
    required: true,
    componentProps: {
      api: getAllValidCustomer,
      labelField: 'name',
      valueField: 'id',
    },
  },
  {
    field: 'productInfo',
    label: '商品名称',
    component: 'Cascader',
    required: true,
    slot: 'productInfo',
  },
  {
    field: 'salePrice',
    label: '商品售价',
    component: 'InputNumber',
    slot: 'salePrice',
    required: true,
  },
  {
    field: 'quantity',
    label: '商品数量',
    component: 'InputNumber',
    slot: 'quantity',
    required: true,
  },
  {
    field: 'remark',
    label: '备注',
    component: 'InputTextArea',
    componentProps: {
      placeholder: '请输入订单备注信息',
    },
  },
  {
    field: 'orderTitle2',
    label: '交付信息',
    component: 'Divider',
    slot: 'orderTitle2',
    componentProps: {
      //是否虚线
      dashed: false,
      //分割线标题的位置（left | right | center）
      orientation: 'left',
      //文字是否显示为普通正文样式
      plain: true,
      //水平还是垂直类型（horizontal | vertical）
      type: 'horizontal',
    },
  },
  {
    field: 'estimatedDeliveryDate',
    label: '预估交付日期',
    component: 'DatePicker',
    slot: 'estimatedDeliveryDate',
  },
  {
    field: 'actualDeliveryDate',
    label: '实际交付日期',
    component: 'DatePicker',
    slot: 'actualDeliveryDate',
  },
  {
    field: 'deliveryRemark',
    label: '交付备注信息',
    component: 'InputTextArea',
    slot: 'deliveryRemark',
  },
  {
    field: 'orderTitle3',
    label: '回款信息',
    component: 'Divider',
    slot: 'orderTitle3',
    componentProps: {
      //是否虚线
      dashed: false,
      //分割线标题的位置（left | right | center）
      orientation: 'left',
      //文字是否显示为普通正文样式
      plain: true,
      //水平还是垂直类型（horizontal | vertical）
      type: 'horizontal',
    },
  },
  {
    field: 'estimatedPaybackDate',
    label: '预估回款日期',
    component: 'DatePicker',
    slot: 'estimatedPaybackDate',
  },
  {
    field: 'actualPaybackDate',
    label: '实际回款日期',
    component: 'DatePicker',
    slot: 'actualPaybackDate',
  },
  {
    field: 'paybackMethod',
    label: '回款方式',
    component: 'RadioGroup',
    componentProps: {
      options: paybackOption,
    },
  },
  {
    field: 'paybackRemark',
    label: '回款备注信息',
    component: 'InputTextArea',
    slot: 'paybackRemark',
  },
  {
    field: 'tags',
    label: '订单标签',
    component: 'Input',
    slot: 'tags',
  },
];
