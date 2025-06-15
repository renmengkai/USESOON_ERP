import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/product/list',
  save = '/erp/product/add',
  edit = '/erp/product/edit',
  delete = '/erp/product/delete',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * 删除商品
 */
export const deleteProduct = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.delete, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或更新商品
 * @param params
 */
export const saveOrUpdateProduct = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};