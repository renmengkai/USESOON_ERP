import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/accounts/list',
  save = '/erp/accounts/add',
  edit = '/erp/accounts/edit',
  delete = '/erp/accounts/delete',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * 删除账户
 */
export const deleteAccount = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或更新账户
 * @param params
 */
export const saveOrUpdateAccount = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};