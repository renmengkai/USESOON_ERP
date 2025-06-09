<!-- 代码已包含 CSS：使用 TailwindCSS , 安装 TailwindCSS 后方可看到布局样式效果 -->
<template>
  <div class="min-h-screen bg-gray-50 py-8">
    <div class="mx-auto max-w-5xl bg-white rounded-lg shadow-sm">
      <div class="p-8">
        <h1 class="text-2xl font-bold mb-8">订单信息登记</h1>
        <!-- 商品信息 -->
        <div class="mb-8">
          <h2 class="text-lg font-semibold mb-4 pb-2 border-b">商品信息</h2>
          <div class="space-y-4">
            <div class="grid grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium mb-2">供货商</label>
                <a-select v-model:value="supplier" show-search placeholder="请选择供货商" :options="supplierOptions" class="w-full" />
              </div>
              <div>
                <label class="block text-sm font-medium mb-2">商品分类</label>
                <a-cascader v-model:value="category" :options="categoryOptions" placeholder="请选择商品分类" class="w-full" />
              </div>
              <div>
                <label class="block text-sm font-medium mb-2">商品批次</label>
                <a-select v-model:value="batch" show-search placeholder="请选择商品批次" :options="batchOptions" class="w-full">
                  <template #option="{ label, value, cost }">
                    <span>{{ label }} (成本价: ¥{{ cost }})</span>
                  </template>
                </a-select>
                <div class="text-sm text-gray-500 mt-1" v-if="selectedBatchCost"> 成本价：¥{{ selectedBatchCost }} </div>
              </div>
              <div>
                <label class="block text-sm font-medium mb-2">商品售价</label>
                <div>
                  <a-input-number
                    v-model:value="sellingPrice"
                    :min="0"
                    :precision="2"
                    placeholder="请输入售价"
                    class="w-full"
                    @change="calculateProfit"
                  />
                </div>
              </div>
            </div>
            <div class="grid grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium mb-2">商品规格</label>
                <a-select v-model:value="specification" show-search placeholder="请选择商品规格" :options="specificationOptions" class="w-full" />
              </div>
              <div>
                <label class="block text-sm font-medium mb-2">商品数量</label>
                <a-input-number v-model:value="quantity" :min="1" placeholder="请输入商品数量" class="w-full" />
              </div>
              <div class="grid grid-cols-4 gap-6 mt-6 col-span-2">
                <div class="bg-gray-50 p-6 rounded-lg text-center">
                  <label class="block text-sm text-gray-500 mb-2">总成本金额</label>
                  <div class="text-xl font-semibold">¥{{ totalCost.toFixed(2) }}</div>
                </div>
                <div class="bg-gray-50 p-6 rounded-lg text-center">
                  <label class="block text-sm text-gray-500 mb-2">总销售金额</label>
                  <div class="text-xl font-semibold">¥{{ totalSales.toFixed(2) }}</div>
                </div>
                <div class="bg-gray-50 p-6 rounded-lg text-center">
                  <label class="block text-sm text-gray-500 mb-2">总利润</label>
                  <div class="text-xl font-semibold" :class="{ 'text-green-600': profit > 0, 'text-red-600': profit < 0 }">
                    ¥{{ profit.toFixed(2) }}
                  </div>
                </div>
                <div class="bg-gray-50 p-6 rounded-lg text-center">
                  <label class="block text-sm text-gray-500 mb-2">利润率</label>
                  <div class="text-xl font-semibold" :class="{ 'text-green-600': profitRate > 0, 'text-red-600': profitRate < 0 }">
                    {{ profitRate.toFixed(2) }}%
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 订单节点 -->
        <div class="mb-8">
          <h2 class="text-lg font-semibold mb-4 pb-2 border-b">订单节点</h2>
          <div class="mb-6">
            <a-steps :current="currentStep" size="small">
              <a-step title="订货" :description="orderTimeStr" />
              <a-step title="定金" :description="depositTimeStr" />
              <a-step title="收款" :description="paymentTimeStr" />
              <a-step title="交货" :description="deliveryTimeStr" />
              <a-step title="收货" :description="receiveTimeStr" />
            </a-steps>
          </div>
          <!-- 交付信息 -->
          <div class="mb-6">
            <h3 class="text-base font-medium mb-4">交付信息</h3>
            <div class="grid grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium mb-2">预估交付日期</label>
                <a-date-picker v-model:value="estimatedDeliveryDate" class="w-full" />
              </div>
              <div>
                <label class="block text-sm font-medium mb-2">实际交付日期</label>
                <a-date-picker v-model:value="actualDeliveryDate" class="w-full" />
              </div>
            </div>
            <div class="mt-4">
              <label class="block text-sm font-medium mb-2">交付备注信息</label>
              <a-textarea v-model:value="deliveryNotes" rows="3" placeholder="请输入交付相关备注信息" />
            </div>
          </div>
          <!-- 回款信息 -->
          <div>
            <h3 class="text-base font-medium mb-4">回款信息</h3>
            <div class="grid grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium mb-2">预估回款日期</label>
                <a-date-picker v-model:value="estimatedPaymentDate" class="w-full" />
              </div>
              <div>
                <label class="block text-sm font-medium mb-2">实际回款日期</label>
                <a-date-picker v-model:value="actualPaymentDate" class="w-full" />
              </div>
            </div>
            <div class="mt-4">
              <label class="block text-sm font-medium mb-2">回款方式</label>
              <a-radio-group v-model:value="paymentMethod">
                <a-radio value="cash">现金</a-radio>
                <a-radio value="transfer">转账</a-radio>
                <a-radio value="check">支票</a-radio>
                <a-radio value="other">其他</a-radio>
              </a-radio-group>
            </div>
            <div class="mt-4">
              <label class="block text-sm font-medium mb-2">回款备注信息</label>
              <a-textarea v-model:value="paymentNotes" rows="3" placeholder="请输入回款相关备注信息" />
            </div>
          </div>
        </div>
        <!-- 订单标签 -->
        <div class="mb-8">
          <h2 class="text-lg font-semibold mb-4 pb-2 border-b">订单标签</h2>
          <a-select
            v-model:value="selectedTags"
            mode="multiple"
            placeholder="请选择或输入标签"
            :options="tagOptions"
            :token-separators="[',']"
            @change="handleTagChange"
            class="w-full"
          />
        </div>
        <!-- 操作按钮 -->
        <div class="flex justify-end space-x-4 pt-6 border-t">
          <a-button class="!rounded-button whitespace-nowrap" @click="handleCancel">取消</a-button>
          <a-button class="!rounded-button whitespace-nowrap" @click="handleSaveAndNew" type="default">保存并新建 </a-button>
          <a-button class="!rounded-button whitespace-nowrap" @click="handleSave" type="primary">保存</a-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
  import { computed, ref } from 'vue';
  import type { SelectProps } from 'ant-design-vue';
  import dayjs from 'dayjs';

  const supplier = ref<string>('');
  const category = ref<string[]>([]);
  const specification = ref<string>('');
  const quantity = ref<number>(1);
  const currentStep = ref(2); // 当前订单进度
  const orderTime = ref(dayjs('2024-01-10 10:00:00'));
  const depositTime = ref(dayjs('2024-01-11 14:30:00'));
  const paymentTime = ref(dayjs('2024-01-12 16:45:00'));
  const deliveryTime = ref(null);
  const receiveTime = ref(null);
  const estimatedDeliveryDate = ref<string>('');
  const formatTimeStr = (time: any) => (time ? time.format('YYYY-MM-DD HH:mm') : '待完成');
  const orderTimeStr = computed(() => formatTimeStr(orderTime.value));
  const depositTimeStr = computed(() => formatTimeStr(depositTime.value));
  const paymentTimeStr = computed(() => formatTimeStr(paymentTime.value));
  const deliveryTimeStr = computed(() => formatTimeStr(deliveryTime.value));
  const receiveTimeStr = computed(() => formatTimeStr(receiveTime.value));
  const batch = ref<string>('');
  const sellingPrice = ref<number>(0);
  const profit = ref<number>(0);
  const totalCost = computed(() => (selectedBatchCost.value || 0) * (quantity.value || 0));
  const totalSales = computed(() => (sellingPrice.value || 0) * (quantity.value || 0));
  const profitRate = computed(() => (totalSales.value ? (profit.value / totalSales.value) * 100 : 0));
  const batchOptions = [
    { value: 'batch1', label: '2024年第一批次', cost: 100 },
    { value: 'batch2', label: '2024年第二批次', cost: 120 },
    { value: 'batch3', label: '2023年末批次', cost: 95 },
  ];
  const selectedBatchCost = computed(() => {
    const selectedBatch = batchOptions.find((item) => item.value === batch.value);
    return selectedBatch ? selectedBatch.cost : null;
  });
  const calculateProfit = () => {
    if (selectedBatchCost.value && sellingPrice.value) {
      profit.value = totalSales.value - totalCost.value;
    } else {
      profit.value = 0;
    }
  };
  const specificationOptions: SelectProps['options'] = [
    { value: 'spec1', label: '标准规格 - 100x100cm' },
    { value: 'spec2', label: '加大规格 - 150x150cm' },
    { value: 'spec3', label: '特大规格 - 200x200cm' },
    { value: 'spec4', label: '迷你规格 - 50x50cm' },
    { value: 'spec5', label: '定制规格 - 按需定制' },
  ];
  const actualDeliveryDate = ref<string>('');
  const deliveryNotes = ref<string>('');
  const estimatedPaymentDate = ref<string>('');
  const actualPaymentDate = ref<string>('');
  const paymentMethod = ref<string>('cash');
  const paymentNotes = ref<string>('');
  const selectedTags = ref<string[]>([]);
  const supplierOptions: SelectProps['options'] = [
    { value: 'supplier1', label: '广州优质食品供应商' },
    { value: 'supplier2', label: '深圳生鲜配送中心' },
    { value: 'supplier3', label: '东莞农产品批发商' },
  ];
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
  const tagOptions = [
    { value: 'urgent', label: '加急订单' },
    { value: 'vip', label: 'VIP客户' },
    { value: 'special', label: '特殊处理' },
    { value: 'regular', label: '常规订单' },
  ];
  const handleTagChange = (value: string[]) => {
    selectedTags.value = value;
  };
  const handleSave = () => {
    // 处理保存逻辑
  };
  const handleSaveAndNew = () => {
    // 处理保存并新建逻辑
  };
  const handleCancel = () => {
    // 处理取消逻辑
  };
</script>
<style scoped>
  .ant-select-selector {
    @apply border border-gray-300 !important;
  }

  .ant-input {
    @apply border border-gray-300 !important;
  }

  .ant-picker {
    @apply border border-gray-300 !important;
  }

  .ant-input-number {
    @apply border border-gray-300 !important;
  }
</style>
