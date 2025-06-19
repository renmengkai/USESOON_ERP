<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" showFooter :title="getTitle" width="500px" @ok="handleSubmit">
    <BasicForm @register="registerForm" />
  </BasicDrawer>
</template>

<script lang="ts">
  import { defineComponent, ref, computed, unref } from 'vue';
  import { BasicForm, useForm } from '/@/components/Form';
  import { formSchema } from '@/views/erp/stock/stock.data';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { saveOrUpdateStock } from '@/views/erp/stock/stock.api';

  export default defineComponent({
    name: 'StockDrawer',
    components: { BasicDrawer, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const isUpdate = ref(true);

      const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
        labelWidth: 90,
        schemas: formSchema,
        showActionButtonGroup: false,
      });

      const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
        resetFields();
        setDrawerProps({ confirmLoading: false });
        isUpdate.value = !!data?.isUpdate;

        if (unref(isUpdate)) {
          setFieldsValue({
            ...data.record,
            productInfo: [data.record.supplierNumber, data.record.type, data.record.productId],
          });
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '入库登记' : '修改库存'));

      async function handleSubmit() {
        try {
          const values = await validate();
          setDrawerProps({ confirmLoading: true });
          const productInfo = values.productInfo.split(',');
          values.supplierNumber = productInfo[0];
          values.type = productInfo[1];
          values.productId = productInfo[2];
          // 调用API保存或更新库存信息
          await saveOrUpdateStock(values, isUpdate.value);
          closeDrawer();
          emit('success');
        } finally {
          setDrawerProps({ confirmLoading: false });
        }
      }

      return {
        registerDrawer,
        registerForm,
        getTitle,
        handleSubmit,
      };
    },
  });
</script>
