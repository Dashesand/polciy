package com.imooc.policy.service;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


public interface UserService {

    UserModel getUser(Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telphone,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

    Integer countAllUser();

    int update(UserModel userModel);
}
