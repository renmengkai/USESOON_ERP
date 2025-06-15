<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" showFooter :title="getTitle" width="800px" @ok="handleSubmit">
    <BasicForm @register="registerForm">
      <!-- 新增客户名称选择框 -->
      <template #customerName="{ model, field }">
        <a-select v-model:value="model[field]" show-search placeholder="请选择客户名称" :options="customerOptions" class="w-full" />
      </template>

      <!-- 商品信息 -->
      <template #supplier="{ model, field }">
        <a-select v-model:value="model[field]" show-search placeholder="请选择供货商" :options="supplierOptions" class="w-full" />
      </template>
      <template #category="{ model, field }">
        <a-cascader v-model:value="model[field]" :options="categoryOptions" placeholder="请选择商品分类" class="w-full" />
      </template>
      <template #batch="{ model, field }">
        <a-select v-model:value="model[field]" show-search placeholder="请选择商品批次" :options="batchOptions" class="w-full">
          <template #option="{ label, value, cost }">
            <span>{{ label }} (成本价: ¥{{ cost }})</span>
          </template>
        </a-select>
      </template>
      <template #sellingPrice="{ model, field }">
        <a-input-number v-model:value="model[field]" :min="0" :precision="2" placeholder="请输入售价" class="w-full" @change="calculateProfit" />
      </template>
      <template #specification="{ model, field }">
        <a-select v-model:value="model[field]" show-search placeholder="请选择商品规格" :options="specificationOptions" class="w-full" />
      </template>
      <template #quantity="{ model, field }">
        <a-input-number v-model:value="model[field]" :min="1" placeholder="请输入商品数量" class="w-full" />
      </template>

      <!-- 订单节点 -->
      <template #estimatedDeliveryDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" />
      </template>
      <template #actualDeliveryDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" />
      </template>
      <template #deliveryNotes="{ model, field }">
        <a-textarea v-model:value="model[field]" rows="3" placeholder="请输入交付相关备注信息" />
      </template>
      <template #estimatedPaymentDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" />
      </template>
      <template #actualPaymentDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" />
      </template>
      <template #paymentMethod="{ model, field }">
        <a-radio-group v-model:value="model[field]">
          <a-radio value="cash">现金</a-radio>
          <a-radio value="transfer">转账</a-radio>
          <a-radio value="check">支票</a-radio>
          <a-radio value="other">其他</a-radio>
        </a-radio-group>
      </template>
      <template #paymentNotes="{ model, field }">
        <a-textarea v-model:value="model[field]" rows="3" placeholder="请输入回款相关备注信息" />
      </template>

      <!-- 订单标签 -->
      <template #selectedTags="{ model, field }">
        <a-select
          v-model:value="model[field]"
          mode="multiple"
          placeholder="请选择或输入标签"
          :options="tagOptions"
          :token-separators="['，']"
          class="w-full"
        />
      </template>
    </BasicForm>
  </BasicDrawer>
</template>

<script lang="ts">
  import { defineComponent, ref, computed, unref } from 'vue';
  import { BasicForm, useForm } from '/@/components/Form';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';

  export default defineComponent({
    name: 'OrderDrawer',
    components: { BasicDrawer, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const isUpdate = ref(true);

      const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
        labelWidth: 120,
        schemas: [
          {
            field: 'customerName',
            label: '客户名称',
            component: 'Input',
            slot: 'customerName',
          },
          {
            field: 'supplier',
            label: '供货商',
            component: 'Input',
            slot: 'supplier',
          },
          {
            field: 'category',
            label: '商品分类',
            component: 'Input',
            slot: 'category',
          },
          {
            field: 'batch',
            label: '商品批次',
            component: 'Input',
            slot: 'batch',
          },
          {
            field: 'sellingPrice',
            label: '商品售价',
            component: 'InputNumber',
            slot: 'sellingPrice',
          },
          {
            field: 'specification',
            label: '商品规格',
            component: 'Input',
            slot: 'specification',
          },
          {
            field: 'quantity',
            label: '商品数量',
            component: 'InputNumber',
            slot: 'quantity',
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
            field: 'deliveryNotes',
            label: '交付备注信息',
            component: 'InputTextArea',
            slot: 'deliveryNotes',
          },
          {
            field: 'estimatedPaymentDate',
            label: '预估回款日期',
            component: 'DatePicker',
            slot: 'estimatedPaymentDate',
          },
          {
            field: 'actualPaymentDate',
            label: '实际回款日期',
            component: 'DatePicker',
            slot: 'actualPaymentDate',
          },
          {
            field: 'paymentMethod',
            label: '回款方式',
            component: 'Input',
            slot: 'paymentMethod',
          },
          {
            field: 'paymentNotes',
            label: '回款备注信息',
            component: 'InputTextArea',
            slot: 'paymentNotes',
          },
          {
            field: 'selectedTags',
            label: '订单标签',
            component: 'Input',
            slot: 'selectedTags',
          },
        ],
        showActionButtonGroup: false,
      });

      const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
        resetFields();
        setDrawerProps({ confirmLoading: false });
        isUpdate.value = !!data?.isUpdate;

        if (unref(isUpdate)) {
          setFieldsValue({
            ...data.record,
          });
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '新增订单' : '编辑订单'));

      async function handleSubmit() {
        try {
          const values = await validate();
          setDrawerProps({ confirmLoading: true });
          // TODO: 调用API保存或更新订单信息
          console.log(values);
          closeDrawer();
          emit('success');
        } finally {
          setDrawerProps({ confirmLoading: false });
        }
      }

      // 商品批次选项
      const batchOptions = [
        { value: 'batch1', label: '2024年第一批次', cost: 100 },
        { value: 'batch2', label: '2024年第二批次', cost: 120 },
        { value: 'batch3', label: '2023年末批次', cost: 95 },
      ];

      // 商品规格选项
      const specificationOptions = [
        { value: 'spec1', label: '标准规格 - 100x100cm' },
        { value: 'spec2', label: '加大规格 - 150x150cm' },
        { value: 'spec3', label: '特大规格 - 200x200cm' },
        { value: 'spec4', label: '迷你规格 - 50x50cm' },
        { value: 'spec5', label: '定制规格 - 按需定制' },
      ];

      // 供货商选项
      const supplierOptions = [
        { value: 'supplier1', label: '广州优质食品供应商' },
        { value: 'supplier2', label: '深圳生鲜配送中心' },
        { value: 'supplier3', label: '东莞农产品批发商' },
      ];

      // 商品分类选项
      const categoryOptions = [
        {
          value: 'food',
          label: '食品',
          children: [
            {
              value: 'fresh',
              label: '生鲜',
              children: [
                { value: 'vegetable', label: '蔬菜' },
                { value: 'fruit', label: '水果' },
              ],
            },
          ],
        },
        {
          value: 'electronics',
          label: '电子产品',
          children: [
            {
              value: 'computer',
              label: '电脑',
              children: [
                { value: 'laptop', label: '笔记本' },
                { value: 'desktop', label: '台式机' },
              ],
            },
          ],
        },
      ];

      // 订单标签选项
      const tagOptions = [
        { value: 'urgent', label: '加急订单' },
        { value: 'vip', label: 'VIP客户' },
        { value: 'special', label: '特殊处理' },
        { value: 'regular', label: '常规订单' },
      ];

      // 计算利润
      const calculateProfit = () => {
        // TODO: 实现利润计算逻辑
      };

      // 客户名称选项
      const customerOptions = [
        { value: 'customer1', label: '客户A' },
        { value: 'customer2', label: '客户B' },
        { value: 'customer3', label: '客户C' },
      ];

      return {
        registerDrawer,
        registerForm,
        getTitle,
        handleSubmit,
        supplierOptions,
        categoryOptions,
        batchOptions,
        specificationOptions,
        tagOptions,
        calculateProfit,
        customerOptions, // 新增客户选项
      };
    },
  });
</script>
