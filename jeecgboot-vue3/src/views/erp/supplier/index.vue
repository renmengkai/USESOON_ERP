<template>
  <div>
    <BasicTable @register="registerTable">
      <template #tableTitle>
        <a-button type="primary" @click="handleCreate"> 新增供应商</a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :actions="[
            {
              icon: 'clarity:note-edit-line',
              onClick: handleEdit.bind(null, record),
            },
            {
              icon: 'ant-design:delete-outlined',
              color: 'error',
              popConfirm: {
                title: '是否确认删除',
                confirm: handleDelete.bind(null, record),
              },
            },
          ]"
        />
      </template>
    </BasicTable>
    <SupplierDrawer @register="registerDrawer" @success="handleSuccess" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { BasicTable, TableAction, useTable } from '/@/components/Table';
import { list } from './supplier.api';
import { useDrawer } from '/@/components/Drawer';
import SupplierDrawer from './components/SupplierDrawer.vue';
import { columns, searchFormSchema } from './supplier.data';

export default defineComponent({
  name: 'ErpSupplier',
  components: { BasicTable, SupplierDrawer, TableAction },
  setup() {
    const [registerDrawer, { openDrawer }] = useDrawer();
    const [registerTable, { reload }] = useTable({
      title: '供应商列表',
      api: list,
      columns,
      formConfig: {
        labelWidth: 120,
        schemas: searchFormSchema,
      },
      useSearchForm: true,
      showTableSetting: true,
      bordered: true,
      showIndexColumn: false,
      actionColumn: {
        width: 80,
        title: '操作',
        dataIndex: 'action',
        slots: { customRender: 'action' },
        fixed: undefined,
      },
    });

    function handleCreate() {
      openDrawer(true, {
        isUpdate: false,
      });
    }

    function handleEdit(record: Recordable) {
      openDrawer(true, {
        record,
        isUpdate: true,
      });
    }

    function handleDelete(record: Recordable) {
      console.log(record);
      // TODO: 调用API删除供应商
    }

    function handleSuccess() {
      reload();
    }

    return {
      registerTable,
      registerDrawer,
      handleCreate,
      handleEdit,
      handleDelete,
      handleSuccess,
    };
  },
});
</script>