package com.imooc.policy.service;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.model.SellerModel;

import java.util.List;

public interface SellerService {

    SellerModel create(SellerModel sellerModel);
    SellerModel get(Integer id);
    List<SellerModel> selectAll();
    SellerModel changeStatus(Integer id,Integer disabledFlag) throws BusinessException;

    Integer countAllSeller();
}
