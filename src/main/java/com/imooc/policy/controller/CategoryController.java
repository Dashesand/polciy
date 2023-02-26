package com.imooc.policy.controller;

import com.imooc.policy.common.CommonRes;
import com.imooc.policy.model.CategoryModel;
import com.imooc.policy.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Api(tags={"用户政策类型"})
@Controller("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @ApiOperation(value="政策类型收藏列表")
    @ResponseBody
    @RequestMapping("/list")
    public CommonRes list(){
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        return CommonRes.create(categoryModelList);

    }

}
