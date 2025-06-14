<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" showFooter :title="getTitle" width="500px" @ok="handleSubmit">
    <BasicForm @register="registerForm">
      <template #accountType="{ model, field }">
        <a-select v-model:value="model[field]" placeholder="请选择账户类型">
          <a-select-option value="bank">银行账户</a-select-option>
          <a-select-option value="cash">现金账户</a-select-option>
        </a-select>
      </template>
    </BasicForm>
  </BasicDrawer>
</template>

<script lang="ts">
import { defineComponent, ref, computed, unref } from 'vue';
import { BasicForm, useForm } from '/@/components/Form/index';
import { formSchema } from '../accounts.data';
import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';

export default defineComponent({
  name: 'AccountDrawer',
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

    const getTitle = computed(() => (!unref(isUpdate) ? '新增账户' : '编辑账户'));

    async function handleSubmit() {
      try {
        const values = await validate();
        setDrawerProps({ confirmLoading: true });
        // TODO: 调用API保存或更新账户信息
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