import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/customer/list',
  save = '/erp/customer/save',
  edit = '/erp/customer/update',
  delete = '/erp/customer/delete',
  getAllValidCustomer = '/erp/customer/getAllValidCustomer',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * selecet 专用接口
 */
export const getAllValidCustomer = () => {
  return defHttp.get({ url: Api.getAllValidCustomer });
};

/**
 * 删除客户
 */
export const deleteCustomer = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或更新客户
 * @param params
 */
export const saveOrUpdateCustomer = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};
