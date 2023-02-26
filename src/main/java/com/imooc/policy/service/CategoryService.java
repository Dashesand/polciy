package com.imooc.policy.service;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;
    CategoryModel get(Integer id);
    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
