package com.imooc.policy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.policy.common.AdminPermission;
import com.imooc.policy.common.BusinessException;
import com.imooc.policy.common.CommonUtil;
import com.imooc.policy.common.EmBusinessError;
import com.imooc.policy.model.ShopModel;
import com.imooc.policy.request.PageQuery;
import com.imooc.policy.request.ShopCreateReq;
import com.imooc.policy.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
@Api(tags={"政策接口"})
@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    //门店列表
    @GetMapping("/index")
    @AdminPermission
    @ApiOperation(value="政策列表页面")
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<ShopModel> shopModelList = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);
        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");
        modelAndView.addObject("data",shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @GetMapping("/createpage")
    @AdminPermission
    @ApiOperation(value="政策创建页面")
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }
    @ApiOperation(value="政策创建")
    @PostMapping(value = "/create")
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setIconUrl(shopCreateReq.getIconUrl());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setCategoryId(shopCreateReq.getCategoryId());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setLongitude(shopCreateReq.getLongitude());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setSellerId(shopCreateReq.getSellerId());

        shopService.create(shopModel);


        return "redirect:/admin/shop/index";


    }

}