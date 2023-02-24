package com.imooc.dianping.service;

import com.imooc.dianping.common.BusinessException;
import com.imooc.dianping.model.FavoriteModel;
import com.imooc.dianping.model.FavoriteModel;
import com.imooc.dianping.model.SellerModel;

import java.util.List;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-21 21:43
 **/

public interface FavoriteService {
    FavoriteModel create(FavoriteModel FavoriteModel);
    FavoriteModel get(Integer id);
    List<FavoriteModel> selectAll();
    FavoriteModel changeStatus(Integer id,Integer status) throws BusinessException;
}
