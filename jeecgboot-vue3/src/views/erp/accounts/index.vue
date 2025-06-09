<script setup lang="ts" name="erp-order"></script>

<template>
  <div></div>
</template>

<style scoped lang="less"></style>

<!--<script setup lang="ts" name="erp-accounts">-->
<!--  import { ref, onMounted } from 'vue';-->
<!--  import { ElMessage, ElMessageBox } from 'element-plus';-->
<!--  import { getAccountList, addAccount, updateAccount, deleteAccount } from '@/api/account';-->

<!--  interface Account {-->
<!--    id: string;-->
<!--    accountType: string;-->
<!--    accountName: string;-->
<!--    accountNumber: string;-->
<!--    bankName?: string;-->
<!--    remark: string;-->
<!--  }-->

<!--  const accountList = ref<Account[]>([]);-->
<!--  const dialogVisible = ref(false);-->
<!--  const form = ref({-->
<!--    id: '',-->
<!--    accountType: '',-->
<!--    accountName: '',-->
<!--    accountNumber: '',-->
<!--    bankName: '',-->
<!--    remark: '',-->
<!--  });-->
<!--  const formRules = {-->
<!--    accountType: [{ required: true, message: '请选择账户类型', trigger: 'change' }],-->
<!--    accountName: [{ required: true, message: '请输入账户名称', trigger: 'blur' }],-->
<!--    accountNumber: [{ required: true, message: '请输入账户号', trigger: 'blur' }],-->
<!--    bankName: [{ required: true, message: '请输入开户行', trigger: 'blur' }],-->
<!--  };-->

<!--  onMounted(() => {-->
<!--    fetchAccountList();-->
<!--  });-->

<!--  const fetchAccountList = async () => {-->
<!--    try {-->
<!--      const res = await getAccountList();-->
<!--      accountList.value = res.data;-->
<!--    } catch (error) {-->
<!--      console.error('获取账户列表失败:', error);-->
<!--      ElMessage.error('获取账户列表失败');-->
<!--    }-->
<!--  };-->

<!--  const handleAdd = () => {-->
<!--    dialogVisible.value = true;-->
<!--    form.value = {-->
<!--      id: '',-->
<!--      accountType: '',-->
<!--      accountName: '',-->
<!--      accountNumber: '',-->
<!--      bankName: '',-->
<!--      remark: '',-->
<!--    };-->
<!--  };-->

<!--  const handleEdit = (row: Account) => {-->
<!--    dialogVisible.value = true;-->
<!--    form.value = { ...row };-->
<!--  };-->

<!--  const handleDelete = async (id: string) => {-->
<!--    try {-->
<!--      await ElMessageBox.confirm('确定要删除该账户吗？', '提示', {-->
<!--        confirmButtonText: '确定',-->
<!--        cancelButtonText: '取消',-->
<!--        type: 'warning',-->
<!--      });-->
<!--      await deleteAccount(id);-->
<!--      ElMessage.success('删除成功');-->
<!--      fetchAccountList();-->
<!--    } catch (error) {-->
<!--      console.error('删除账户失败:', error);-->
<!--      ElMessage.error('删除账户失败');-->
<!--    }-->
<!--  };-->

<!--  const handleSubmit = async () => {-->
<!--    try {-->
<!--      if (form.value.id) {-->
<!--        await updateAccount(form.value);-->
<!--        ElMessage.success('更新成功');-->
<!--      } else {-->
<!--        await addAccount(form.value);-->
<!--        ElMessage.success('添加成功');-->
<!--      }-->
<!--      dialogVisible.value = false;-->
<!--      fetchAccountList();-->
<!--    } catch (error) {-->
<!--      console.error('提交表单失败:', error);-->
<!--      ElMessage.error('提交表单失败');-->
<!--    }-->
<!--  };-->
<!--</script>-->

<!--<template>-->
<!--  <div>-->
<!--    <el-button type="primary" @click="handleAdd">新增账户</el-button>-->
<!--    <el-table :data="accountList" style="width: 100%; margin-top: 20px">-->
<!--      <el-table-column prop="accountType" label="账户类型" width="120" />-->
<!--      <el-table-column prop="accountName" label="账户名称" width="150" />-->
<!--      <el-table-column prop="accountNumber" label="账户号" width="180" />-->
<!--      <el-table-column prop="bankName" label="开户行" width="150" />-->
<!--      <el-table-column prop="remark" label="备注信息" />-->
<!--      <el-table-column label="操作" width="150">-->
<!--        <template #default="{ row }">-->
<!--          <el-button size="small" @click="handleEdit(row)">编辑</el-button>-->
<!--          <el-button size="small" type="danger" @click="handleDelete(row.id)">删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--    <el-dialog v-model="dialogVisible" :title="form.id ? '编辑账户' : '新增账户'" width="500px">-->
<!--      <el-form :model="form" :rules="formRules" ref="formRef" label-width="100px">-->
<!--        <el-form-item label="账户类型" prop="accountType">-->
<!--          <el-select v-model="form.accountType" placeholder="请选择账户类型">-->
<!--            <el-option label="银行账户" value="bank" />-->
<!--            <el-option label="支付宝" value="alipay" />-->
<!--            <el-option label="微信" value="wechat" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="账户名称" prop="accountName">-->
<!--          <el-input v-model="form.accountName" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="账户号" prop="accountNumber">-->
<!--          <el-input v-model="form.accountNumber" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="开户行" prop="bankName" v-if="form.accountType === 'bank'">-->
<!--          <el-input v-model="form.bankName" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注信息" prop="remark">-->
<!--          <el-input v-model="form.remark" type="textarea" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <template #footer>-->
<!--        <span class="dialog-footer">-->
<!--          <el-button @click="dialogVisible = false">取消</el-button>-->
<!--          <el-button type="primary" @click="handleSubmit">确定</el-button>-->
<!--        </span>-->
<!--      </template>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<style scoped lang="less"></style>-->
