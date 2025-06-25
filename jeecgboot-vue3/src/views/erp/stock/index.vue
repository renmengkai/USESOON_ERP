<template>
  <div>
    <BasicTable @register="registerTable">
      <template #tableTitle>
        <a-button type="primary" @click="handleCreate">库存登记</a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :actions="[
            {
              label: '修改库存',
              icon: 'clarity:note-edit-line',
              onClick: handleEdit.bind(null, record),
            },
            {
              label: '删除',
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
    <StockDrawer @register="registerDrawer" @success="handleSuccess" width="35%" />
  </div>
</template>

<script lang="ts">
  import { defineComponent, onMounted } from 'vue';
  import { BasicTable, TableAction, useTable } from '/@/components/Table';
  import { deleteStock, list } from './stock.api';
  import { useDrawer } from '/@/components/Drawer';
  import StockDrawer from './components/StockDrawer.vue';
  import { initOrderData, columns, searchFormSchema } from './stock.data';

  export default defineComponent({
    name: 'ErpStock',
    components: { BasicTable, StockDrawer, TableAction },
    setup() {
      onMounted(async () => {
        await initOrderData();
      });
      const [registerDrawer, { openDrawer }] = useDrawer();
      const [registerTable, { reload }] = useTable({
        title: '库存列表',
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
          width: 90,
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
        // 调用API删除库存
        deleteStock({ id: record.id }, reload());
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
