<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" showFooter :title="getTitle" width="800px" @ok="handleSubmit">
    <BasicForm @register="registerForm">
      <!-- 选择商品 -->
      <template #productInfo="{ model, field }">
        <a-cascader v-model:value="model[field]" :options="categoryOptions" placeholder="请选择商品" class="w-full" />
      </template>
      <template #salePrice="{ model, field }">
        <a-input-number
          v-model:value="model[field]"
          :min="0"
          :precision="2"
          addon-after="元"
          placeholder="请输入售价"
          class="w-full"
          @change="calculateProfit"
        />
      </template>
      <template #quantity="{ model, field }">
        <a-input-number v-model:value="model[field]" :min="1" placeholder="请输入商品数量" class="w-full" @change="calculateProfitAndStock" />
        <!--        <div style="margin-top: 20px; display: flex; justify-content: space-evenly">-->
        <!--          <div style="flex: 1; height: 70px; background: #f8f8f8; display: inline-block; margin-right: 20px; border-radius: 5px"></div>-->
        <!--          <div style="flex: 1; height: 70px; background: #f8f8f8; display: inline-block; margin-right: 20px; border-radius: 5px"></div>-->
        <!--          <div style="flex: 1; height: 70px; background: #f8f8f8; display: inline-block; margin-right: 20px; border-radius: 5px"></div>-->
        <!--          <div style="flex: 1; height: 70px; background: #f8f8f8; display: inline-block; margin-right: 20px; border-radius: 5px"></div>-->
        <!--        </div>-->
      </template>
      <!-- 订单节点 -->
      <template #estimatedDeliveryDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" :format="dateFormat" />
      </template>
      <template #actualDeliveryDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" :format="dateFormat" />
      </template>
      <template #deliveryRemark="{ model, field }">
        <a-textarea v-model:value="model[field]" rows="3" placeholder="请输入交付相关备注信息" />
      </template>
      <template #estimatedPaybackDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" />
      </template>
      <template #actualPaybackDate="{ model, field }">
        <a-date-picker v-model:value="model[field]" class="w-full" />
      </template>
      <template #paybackRemark="{ model, field }">
        <a-textarea v-model:value="model[field]" rows="3" placeholder="请输入回款相关备注信息" />
      </template>

      <!-- 订单标签 -->
      <template #tags="{ model, field }">
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
  import { getAllValidCustomer } from '@/views/erp/customer/customer.api';
  import { useMessage } from '@/hooks/web/useMessage';
  import { getAllProductTree } from '/@/views/erp/product/product.api';
  import { formSchema } from '../order.data';
  import { saveOrUpdateOrder } from '@/views/erp/order/order.api';
  import { useUserStoreWithOut } from '@/store/modules/user';

  export default defineComponent({
    name: 'OrderDrawer',
    components: { BasicDrawer, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const isUpdate = ref(true);

      const { createMessage } = useMessage();

      const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
        labelWidth: 120,
        schemas: formSchema,
        showActionButtonGroup: false,
      });

      const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
        resetFields();
        setDrawerProps({ confirmLoading: false });
        isUpdate.value = !!data?.isUpdate;
        data.record.productInfo =
          data.record.productInfo && typeof data.record.productInfo === 'string' ? data.record.productInfo.split(',') : data.record.productInfo;
        data.record.tags = data.record.tags && typeof data.record.tags === 'string' ? data.record.tags.split(',') : (data.record.tags ?? []);
        if (unref(isUpdate)) {
          setFieldsValue({
            ...data.record,
          });
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '创建订单' : '编辑订单'));

      // 客户名称选项
      let customerOptions = ref([]);
      // 商品级联选择
      const categoryOptions = ref([]);
      // 回款方式选项
      const paybackOptions = ref([]);

      function initOption() {
        // 初始化商品级联选择
        getAllProductTree().then((res) => {
          categoryOptions.value = res;
        });
        // 初始化客户名称选项
        getAllValidCustomer()
          .then((res) => {
            customerOptions.value = res.map((item) => ({
              value: item.id,
              label: item.name,
            }));
          })
          .catch(() => {
            createMessage.error('获取客户列表失败');
          });
      }
      initOption();

      async function handleSubmit() {
        try {
          const userStore = useUserStoreWithOut();
          let userInfo = userStore.getUserInfo;
          if (!userInfo) {
            createMessage.error('获取用户信息失败');
            return;
          }
          const values = await validate();
          if(userInfo['username'] != values.crter){
            createMessage.error('仅能修改自己的订单信息');
            return;
          }
          setDrawerProps({ confirmLoading: true });
          const productInfo = values.productInfo.split(',');
          values.productId = productInfo[2];
          // 调用API保存或更新订单信息
          await saveOrUpdateOrder(values, isUpdate.value);
          closeDrawer();
          emit('success');
        } finally {
          setDrawerProps({ confirmLoading: false });
        }
      }

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

      // 计算利润并检查库存
      const calculateProfitAndStock = () => {
        // TODO: 检查库存逻辑
        // TODO: 实现利润计算逻辑
      };
      const dateFormat = 'YYYY-MM-DD';
      return {
        registerDrawer,
        registerForm,
        getTitle,
        handleSubmit,
        categoryOptions,
        paybackOptions,
        tagOptions,
        calculateProfit,
        calculateProfitAndStock,
        customerOptions, // 新增客户选项
        dateFormat,
      };
    },
  });
</script>
