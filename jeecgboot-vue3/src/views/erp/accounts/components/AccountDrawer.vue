<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" showFooter :title="getTitle" width="500px" @ok="handleSubmit">
    <BasicForm @register="registerForm">
      <template #accountType="{ model, field }">
        <a-form-item label="账户类型">
          <JDictSelectTag type="radio" v-model:value="model[field]" dictCode="sex" placeholder="请选择账户类型" />
        </a-form-item>
      </template>
    </BasicForm>
  </BasicDrawer>
</template>

<script lang="ts">
  import { defineComponent, ref, computed, onMounted, unref } from 'vue';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { formSchema } from '../accounts.data';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import JDictSelectTag from '../../../../components/Form/src/jeecg/components/JDictSelectTag.vue';
  import { saveOrUpdateAccount } from '@/views/erp/accounts/accounts.api';

  export default defineComponent({
    name: 'AccountDrawer',
    components: { JDictSelectTag, BasicDrawer, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      // 加载初始化字典数据
      onMounted(() => {});
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
          // 调用API保存或更新账户信息
          await saveOrUpdateAccount(values, isUpdate.value);
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
