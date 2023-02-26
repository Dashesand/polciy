package com.imooc.policy.service.impl;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.common.EmBusinessError;
import com.imooc.policy.dal.FavoriteModelMapper;
import com.imooc.policy.model.FavoriteModel;
import com.imooc.policy.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-21 21:47
 **/
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteModelMapper favoriteModelMapper;

    @Override
    @Transactional
    public FavoriteModel create(FavoriteModel FavoriteModel) {
        FavoriteModel.setCreateTime(new Date());
        FavoriteModel.setUpdateTime(new Date());
        FavoriteModel.setUserId(FavoriteModel.getUserId());
        FavoriteModel.setPolicyId(FavoriteModel.getPolicyId());
        FavoriteModel.setStatus(1);
        favoriteModelMapper.insertSelective(FavoriteModel);
        return get(FavoriteModel.getId());
    }

    @Override
    public FavoriteModel get(Integer id) {
        return favoriteModelMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<FavoriteModel> selectAll(Integer user_id) {
        return favoriteModelMapper.selectAll(user_id);
    }

    @Override
    public FavoriteModel changeStatus(Integer id,Integer status) throws BusinessException {
        FavoriteModel FavoriteModel = get(id);
        if(FavoriteModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        FavoriteModel.setStatus(status);
        favoriteModelMapper.updateByPrimaryKeySelective(FavoriteModel);
        return FavoriteModel;
    }
}
