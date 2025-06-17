<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" showFooter :title="getTitle" width="500px" @ok="handleSubmit">
    <BasicForm @register="registerForm"></BasicForm>
  </BasicDrawer>
</template>

<script lang="ts">
import { defineComponent, ref, computed, unref } from 'vue';
import { BasicForm, useForm } from '@/components/Form';
import { formSchema } from '@/views/erp/product/product.data';
import { BasicDrawer, useDrawerInner } from '@/components/Drawer';

export default defineComponent({
  name: 'ProductDrawer',
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
        });
      }
    });

    const getTitle = computed(() => (!unref(isUpdate) ? '新增商品' : '编辑商品'));

    async function handleSubmit() {
      try {
        const values = await validate();
        setDrawerProps({ confirmLoading: true });
        // TODO: 调用API保存或更新商品信息
        console.log(values);
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
