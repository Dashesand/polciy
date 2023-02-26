package com.imooc.policy.service;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.model.FavoriteModel;

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
    List<FavoriteModel> selectAll(Integer user_id);
    FavoriteModel changeStatus(Integer id,Integer status) throws BusinessException;
}
