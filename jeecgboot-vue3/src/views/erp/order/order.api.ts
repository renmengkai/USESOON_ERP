import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/order/list',
  save = '/erp/order/save',
  edit = '/erp/order/update',
  delete = '/erp/order/delete',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * 删除订单
 */
export const deleteOrder = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或更新订单
 * @param params
 */
export const saveOrUpdateOrder = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};
