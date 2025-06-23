import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { h } from 'vue';
import dayjs from 'dayjs';
import { getAllValidCustomer } from '/@/views/erp/customer/customer.api';
import { getDictItemsByCode } from '@/utils/dict';
import { getAllProductList } from '@/views/erp/product/product.api';
import { Tag } from 'ant-design-vue';

const paybackOption = getDictItemsByCode('payback_method') || [];
const nodeCodeOption = getDictItemsByCode('order_node') || [];
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
    width: 65,
  },
  {
    title: '客户名称',
    dataIndex: 'customerId',
    width: 70,
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
    width: 40,
  },
  {
    title: '订单金额',
    dataIndex: 'totalSalePrice',
    width: 40,
  },
  {
    title: '预估交付日期',
    dataIndex: 'estimatedDeliveryDate',
    width: 80,
    sorter: true,
    customRender: ({ record }) => {
    const estimatedDate = dayjs(record.estimatedDeliveryDate);
    const diffDays = estimatedDate.diff(dayjs(), 'day');
    const isUrgent = diffDays <= 7;
    return h(
      'span',
      {
        style: {
          color: isUrgent ? 'red' : '',
        },
      },
      estimatedDate.format('YYYY-MM-DD') + '（剩 '+diffDays+' 天）'
    );
    },
  },
  {
    title: '订单节点',
    dataIndex: 'nodeCode',
    width: 35,
    customRender: ({ record }) => {
      let returnText = '';
      const colors = ['gray', 'red', 'yellow', 'blue', 'green'];
      for (let i = 0; i < nodeCodeOption.length; i++) {
        if (nodeCodeOption[i].value === record.nodeCode) {
          returnText = nodeCodeOption[i].label;
        }
      }
      return h(Tag, { color: colors[parseInt(record.nodeCode) - 1] }, () => returnText);
    },
  },
  {
    title: '备注',
    dataIndex: 'remark',
    width: 120,
  },
  {
    title: '订单登记时间',
    dataIndex: 'crteTime',
    width: 70,
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
    label: '订单状态',
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
    field: 'nodeCode',
    label: '订单节点',
    component: 'JDictSelectTag',
    required: true,
    defaultValue: '1',
    componentProps: {
      dictCode: 'order_node',
    },
  },
  {
    field: 'orderTitle3',
    label: '交付信息',
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
    field: 'estimatedDeliveryDate',
    label: '预估交付日期',
    component: 'DatePicker',
    slot: 'estimatedDeliveryDate',
    required: true,
  },
  {
    field: 'actualDeliveryDate',
    label: '实际交付日期',
    component: 'DatePicker',
    slot: 'actualDeliveryDate',
    required: ({ values }) => {
      return values.nodeCode == '4' || parseInt(values.nodeCode) > 4;
    },
  },
  {
    field: 'deliveryRemark',
    label: '交付备注信息',
    component: 'InputTextArea',
    slot: 'deliveryRemark',
  },
  {
    field: 'orderTitle4',
    label: '回款信息',
    component: 'Divider',
    slot: 'orderTitle4',
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
    required: ({ values }) => {
      return values.nodeCode == '3' || parseInt(values.nodeCode) > 3;
    },
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
