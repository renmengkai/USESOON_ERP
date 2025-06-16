import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/supplier/list',
  save = '/erp/supplier/save',
  edit = '/erp/supplier/update',
  delete = '/erp/supplier/delete',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * 删除供应商
 */
export const deleteSupplier = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或更新供应商
 * @param params
 */
export const saveOrUpdateSupplier = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};
