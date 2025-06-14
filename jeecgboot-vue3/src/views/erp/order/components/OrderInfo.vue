<!-- 代码已包含 CSS：使用 TailwindCSS , 安装 TailwindCSS 后方可看到布局样式效果 -->
<template>
  <div style="min-height: 100vh; background-color: #f9fafb; padding: 2rem">
    <div
      style="
        margin: 0 auto;
        max-width: 1280px;
        background-color: #ffffff;
        border-radius: 0.5rem;
        box-shadow:
          0 1px 3px 0 rgba(0, 0, 0, 0.1),
          0 1px 2px 0 rgba(0, 0, 0, 0.06);
      "
    >
      <div style="padding: 2rem">
        <h1 style="font-size: 1.5rem; font-weight: bold; margin-bottom: 2rem">订单信息登记</h1>
        <!-- 商品信息 -->
        <div style="margin-bottom: 2rem">
          <h2 style="font-size: 1.125rem; font-weight: bold; margin-bottom: 1rem; padding-bottom: 0.5rem; border-bottom: 1px solid #e5e7eb"
            >商品信息</h2
          >
          <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.5rem">
            <div>
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">供货商</label>
              <a-select v-model:value="supplier" show-search placeholder="请选择供货商" :options="supplierOptions" class="w-full" />
            </div>
            <div>
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">商品分类</label>
              <a-cascader v-model:value="category" :options="categoryOptions" placeholder="请选择商品分类" class="w-full" />
            </div>
            <div>
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">商品批次</label>
              <a-select v-model:value="batch" show-search placeholder="请选择商品批次" :options="batchOptions" class="w-full">
                <template #option="{ label, value, cost }">
                  <span>{{ label }} (成本价: ¥{{ cost }})</span>
                </template>
              </a-select>
              <div style="font-size: 0.875rem; color: #6b7280; margin-top: 0.25rem" v-if="selectedBatchCost"> 成本价：¥{{ selectedBatchCost }} </div>
            </div>
            <div>
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">商品售价</label>
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
          <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.5rem">
            <div>
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">商品规格</label>
              <a-select v-model:value="specification" show-search placeholder="请选择商品规格" :options="specificationOptions" class="w-full" />
            </div>
            <div>
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">商品数量</label>
              <a-input-number v-model:value="quantity" :min="1" placeholder="请输入商品数量" class="w-full" />
            </div>
            <div style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 1.5rem; margin-top: 1.5rem; column-span: 2">
              <div style="background-color: #f9fafb; padding: 1.5rem; border-radius: 0.5rem; text-align: center">
                <label style="display: block; font-size: 0.875rem; color: #6b7280; margin-bottom: 0.5rem">总成本金额</label>
                <div style="font-size: 1.25rem; font-weight: bold">¥{{ totalCost.toFixed(2) }}</div>
              </div>
              <div style="background-color: #f9fafb; padding: 1.5rem; border-radius: 0.5rem; text-align: center">
                <label style="display: block; font-size: 0.875rem; color: #6b7280; margin-bottom: 0.5rem">总销售金额</label>
                <div style="font-size: 1.25rem; font-weight: bold">¥{{ totalSales.toFixed(2) }}</div>
              </div>
              <div style="background-color: #f9fafb; padding: 1.5rem; border-radius: 0.5rem; text-align: center">
                <label style="display: block; font-size: 0.875rem; color: #6b7280; margin-bottom: 0.5rem">总利润</label>
                <div style="font-size: 1.25rem; font-weight: bold" :style="{ color: profit > 0 ? '#16a34a' : '#dc2626' }">
                  ¥{{ profit.toFixed(2) }}
                </div>
              </div>
              <div style="background-color: #f9fafb; padding: 1.5rem; border-radius: 0.5rem; text-align: center">
                <label style="display: block; font-size: 0.875rem; color: #6b7280; margin-bottom: 0.5rem">利润率</label>
                <div style="font-size: 1.25rem; font-weight: bold" :style="{ color: profitRate > 0 ? '#16a34a' : '#dc2626' }">
                  {{ profitRate.toFixed(2) }}%
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 订单节点 -->
        <div style="margin-bottom: 2rem">
          <h2 style="font-size: 1.125rem; font-weight: bold; margin-bottom: 1rem; padding-bottom: 0.5rem; border-bottom: 1px solid #e5e7eb"
            >订单节点</h2
          >
          <div style="margin-bottom: 1.5rem">
            <a-steps :current="currentStep" size="small">
              <a-step title="订货" :description="orderTimeStr" />
              <a-step title="定金" :description="depositTimeStr" />
              <a-step title="收款" :description="paymentTimeStr" />
              <a-step title="交货" :description="deliveryTimeStr" />
              <a-step title="收货" :description="receiveTimeStr" />
            </a-steps>
          </div>
          <!-- 交付信息 -->
          <div style="margin-bottom: 1.5rem">
            <h3 style="font-size: 1rem; font-weight: bold; margin-bottom: 1rem">交付信息</h3>
            <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.5rem">
              <div>
                <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">预估交付日期</label>
                <a-date-picker v-model:value="estimatedDeliveryDate" class="w-full" />
              </div>
              <div>
                <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">实际交付日期</label>
                <a-date-picker v-model:value="actualDeliveryDate" class="w-full" />
              </div>
            </div>
            <div style="margin-top: 1rem">
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">交付备注信息</label>
              <a-textarea v-model:value="deliveryNotes" rows="3" placeholder="请输入交付相关备注信息" />
            </div>
          </div>
          <!-- 回款信息 -->
          <div>
            <h3 style="font-size: 1rem; font-weight: bold; margin-bottom: 1rem">回款信息</h3>
            <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.5rem">
              <div>
                <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">预估回款日期</label>
                <a-date-picker v-model:value="estimatedPaymentDate" class="w-full" />
              </div>
              <div>
                <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">实际回款日期</label>
                <a-date-picker v-model:value="actualPaymentDate" class="w-full" />
              </div>
            </div>
            <div style="margin-top: 1rem">
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">回款方式</label>
              <a-radio-group v-model:value="paymentMethod">
                <a-radio value="cash">现金</a-radio>
                <a-radio value="transfer">转账</a-radio>
                <a-radio value="check">支票</a-radio>
                <a-radio value="other">其他</a-radio>
              </a-radio-group>
            </div>
            <div style="margin-top: 1rem">
              <label style="display: block; font-size: 0.875rem; font-weight: bold; margin-bottom: 0.5rem">回款备注信息</label>
              <a-textarea v-model:value="paymentNotes" rows="3" placeholder="请输入回款相关备注信息" />
            </div>
          </div>
        </div>
        <!-- 订单标签 -->
        <div style="margin-bottom: 2rem">
          <h2 style="font-size: 1.125rem; font-weight: bold; margin-bottom: 1rem; padding-bottom: 0.5rem; border-bottom: 1px solid #e5e7eb"
            >订单标签</h2
          >
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
        <div style="display: flex; justify-content: flex-end; gap: 1rem; padding-top: 1.5rem; border-top: 1px solid #e5e7eb">
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
