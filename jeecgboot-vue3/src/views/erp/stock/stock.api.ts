import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/stock/list',
  save = '/erp/stock/save',
  edit = '/erp/stock/update',
  delete = '/erp/stock/delete',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * 删除库存
 */
export const deleteStock = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或更新库存
 * @param params
 */
export const saveOrUpdateStock = (params, isUpdate) => {
  const url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};
