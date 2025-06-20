package org.jeecg.modules.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.langchain4j.data.document.Metadata;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.erp.entity.Product;
import org.jeecg.modules.erp.entity.Supplier;
import org.jeecg.modules.erp.mapper.ProductMapper;
import org.jeecg.modules.erp.service.IProductService;
import org.jeecg.modules.erp.service.ISupplierService;
import org.jeecg.modules.system.mapper.SysDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品服务实现类
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private SysDictMapper sysDictMapper;

    public ProductServiceImpl(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public List<Map<String, Object>> getAllProductTree() {

        // 查询所有供应商
        List<Supplier> supplierList = supplierService.list();
        // 查询所有商品类型字典
        List<DictModel> productTypeList = sysDictMapper.queryDictItemsByCode("product_type");
        // 设置结果集
        List<Map<String, Object>> treeObject = new ArrayList<>();
        // 查询供应商下的所有商品
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.eq("is_valid", "1");
        List<Product> productList = list(productQueryWrapper);

        // 按照 supplierNumber 将Product分组
        Map<String, List<Product>> productMap = productList.stream().collect(Collectors.groupingBy(Product::getSupplierNumber));
        productMap.forEach((supplierNumber, products) -> {
            Supplier supplier = supplierList.stream().filter(item -> item.getId().equals(supplierNumber)).findFirst().orElse(null);
            if (supplier != null) {
                HashMap<String, Object> supplierLevelMap = new HashMap<>();
                supplierLevelMap.put("value", supplierNumber);
                supplierLevelMap.put("label", supplier.getName());

                ArrayList<Map<String, Object>> supplierLevelList = new ArrayList<>();
                // 按照 type 分组
                Map<String, List<Product>> productTypeMap = products.stream().collect(Collectors.groupingBy(Product::getType));

                productTypeMap.forEach((type, productsBase) -> {
                    DictModel dictModel = productTypeList.stream().filter(productType -> productType.getValue().equals(type)).findFirst().orElse(null);
                    if (dictModel != null) {
                        HashMap<String, Object> productTypeLevelMap = new HashMap<>();
                        productTypeLevelMap.put("value", type);
                        productTypeLevelMap.put("label", dictModel.getText());

                        ArrayList<Map<String, Object>> productLevelList = new ArrayList<>();
                        productsBase.forEach(product -> {
                            HashMap<String, Object> productLevelMap = new HashMap<>();
                            productLevelMap.put("value", product.getId());
                            productLevelMap.put("label", product.getName()+"（"+product.getSpecification()+"）");
                            productLevelList.add(productLevelMap);
                        });
                        productTypeLevelMap.put("children", productLevelList);

                        supplierLevelList.add(productTypeLevelMap);
                    }
                });

                supplierLevelMap.put("children", supplierLevelList);
                // 添加到结果集
                treeObject.add(supplierLevelMap);
            }
        });
        return treeObject;
    }
}