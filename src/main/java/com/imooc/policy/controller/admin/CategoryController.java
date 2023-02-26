package com.imooc.policy.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.policy.common.AdminPermission;
import com.imooc.policy.common.BusinessException;
import com.imooc.policy.common.CommonUtil;
import com.imooc.policy.common.EmBusinessError;
import com.imooc.policy.model.CategoryModel;
import com.imooc.policy.request.CategoryCreateReq;
import com.imooc.policy.request.PageQuery;
import com.imooc.policy.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
@Api(tags={"管理员政策类型"})
@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //品类列表
    @ApiOperation(value="政策类型列表页面")
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModelList);
        ModelAndView modelAndView = new ModelAndView("/admin/category/index.html");
        modelAndView.addObject("data",categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }
    @ApiOperation(value="政策类型创建页面")
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/create.html");
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }
    @ApiOperation(value="政策类型列表")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryCreateReq.getName());
        categoryModel.setIconUrl(categoryCreateReq.getIconUrl());
        categoryModel.setSort(categoryCreateReq.getSort());

        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";


    }

}
