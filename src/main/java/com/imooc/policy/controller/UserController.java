package com.imooc.policy.controller;

import com.imooc.policy.common.*;
import com.imooc.policy.model.UserModel;
import com.imooc.policy.request.LoginReq;
import com.imooc.policy.request.RegisterReq;
import com.imooc.policy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
@Api(tags={"用户模块"})
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;


    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    @ApiOperation(value="首页")
    @GetMapping("/index")
    public ModelAndView index(){
        String userName = "imooc";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name",userName);
        return modelAndView;
    }

    @ApiOperation(value="获取用户信息")
    @GetMapping("/get")
    @ResponseBody
    public CommonRes getUser(@ApiParam(name = "id",value = "需要获得用户信息的id")@RequestParam(name="id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUser(id);
        if(userModel == null){
            //return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"fail");
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        }else{
            return CommonRes.create(userModel);
        }

    }

    @ApiOperation(value="注册")
    @PostMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel registerUser = new UserModel();
        registerUser.setTelphone(registerReq.getTelphone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setNickName(registerReq.getNickName());
        registerUser.setGender(registerReq.getGender());
        registerUser.setProvince(registerReq.getProvince());
        registerUser.setCity(registerReq.getCity());
        registerUser.setCounty(registerReq.getCounty());
        registerUser.setAttention(registerReq.getAttention());


        UserModel resUserModel = userService.register(registerUser);

        return CommonRes.create(resUserModel);
    }
    @ApiOperation(value="登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginReq loginReq,BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(loginReq.getPassword());
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelphone(),loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);

        return CommonRes.create(userModel);
    }
    @ApiOperation(value="登出")
    @GetMapping("/logout")
    @ResponseBody
    public CommonRes logout() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    //获取当前用户信息
    @ApiOperation(value="获取当前用户信息")
    @GetMapping("/getcurrentuser")
    @ResponseBody
    public CommonRes getCurrentUser(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }
    @ApiOperation(value="修改用户信息")
    @PostMapping("/update")
    @ResponseBody
    public CommonRes update(@Valid @RequestBody UserModel userModel, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel updateUser = new UserModel();
        updateUser.setTelphone(userModel.getTelphone());
        updateUser.setPassword(userModel.getPassword());
        updateUser.setNickName(userModel.getNickName());
        updateUser.setGender(userModel.getGender());
        updateUser.setProvince(userModel.getProvince());
        updateUser.setCity(userModel.getCity());
        updateUser.setCounty(userModel.getCounty());
        updateUser.setAttention(userModel.getAttention());


        int update = userService.update(userModel);

        return CommonRes.create(update);
    }
}
