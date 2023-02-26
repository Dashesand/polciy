package com.imooc.policy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.policy.common.*;
import com.imooc.policy.model. FavoriteModel;
import com.imooc.policy.request.FavoriteCreateReq;
import com.imooc.policy.request.PageQuery;
import com.imooc.policy.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-21 21:42
 **/
@Api(tags={"收藏接口"})
@Controller("/favorite")
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    //收藏列表
    @ApiOperation(value="收藏列表")
    @GetMapping( "/list")
    @ResponseBody
    public CommonRes list( PageQuery pageQuery,Integer userId){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<FavoriteModel>  FavoriteModelList =  favoriteService.selectAll(userId);
        PageInfo<FavoriteModel>  FavoriteModelPageInfo = new PageInfo<>(FavoriteModelList);
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("data",FavoriteModelPageInfo);
        return CommonRes.create(resMap);
    }



    @ApiOperation(value="创建收藏信息")
    @PostMapping(value ="/create")
    @ResponseBody
    public CommonRes create(@Valid FavoriteCreateReq favoriteCreateReq, BindingResult bindingResult)throws BusinessException{
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        FavoriteModel favoriteModel = new FavoriteModel();
        favoriteModel.setUserId(favoriteCreateReq.getUserId());
        favoriteModel.setPolicyId(favoriteCreateReq.getPolicyId());
        favoriteModel.setStatus(1);
        favoriteModel.setCreateTime(new Date());
        favoriteModel.setUpdateTime(new Date());

        favoriteService.create(favoriteModel);
        return CommonRes.create(favoriteModel);
    }
    @ApiOperation(value="添加收藏信息")
    @PostMapping(value="down")
    @ResponseBody
    public CommonRes down(@ApiParam(name = "id",value = "需要修改状态的id")Integer id) throws BusinessException {
        FavoriteModel favoriteModel = favoriteService.changeStatus(id,1);
        return CommonRes.create(favoriteModel);
    }
    @ApiOperation(value="取消收藏信息")
    @PostMapping(value="up")
    @ResponseBody
    public CommonRes up(@ApiParam(name = "id",value = "需要修改状态的id")@RequestParam(value="id")Integer id) throws BusinessException {
        FavoriteModel favoriteModel = favoriteService.changeStatus(id,0);
        return CommonRes.create(favoriteModel);
    }


}
