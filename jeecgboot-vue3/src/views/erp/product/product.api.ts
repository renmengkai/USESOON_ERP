import { defHttp } from '/@/utils/http/axios';

enum Api {
  list = '/erp/product/list',
  save = '/erp/product/save',
  edit = '/erp/product/update',
  delete = '/erp/product/delete',
  getAllProductList = '/erp/product/getAllProductList',
  getAllProductTree = '/erp/product/getAllProductTree',
  getValidProductBatch = '/erp/product/getValidProductBatch',
}

/**
 * 列表接口
 * @param params
 */
export const list = (params) => {
  return defHttp.get({ url: Api.list, params });
};

/**
 * select 专用接口
 */
export const getAllProductList = () => {
  return defHttp.get({ url: Api.getAllProductList });
};

/**
 * Cascader 专用接口
 */
export const getAllProductTree = () => {
  return defHttp.get({ url: Api.getAllProductTree });
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
  const url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};
