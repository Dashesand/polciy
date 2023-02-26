package com.imooc.policy.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;


public class LoginReq {
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String telphone;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
