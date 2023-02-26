package com.imooc.policy.recommend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.policy.dal.RecommendDOMapper;
import com.imooc.policy.model.RecommendDO;

@Service
public class RecommendService {

    @Autowired
    private RecommendDOMapper recommendDOMapper;


    //召回数据，根据userid 召回policyidList
    public List<Integer> recall(Integer userId){

        RecommendDO recommendDO = recommendDOMapper.selectByPrimaryKey(userId);
        if(recommendDO == null){
            //默认推荐，非活跃用户，根据地址信息进行推荐
            //省级地址推荐,实例为四川省推荐
            recommendDO = recommendDOMapper.selectByPrimaryKey(9999999);
        }
        String[] policyIdArr = recommendDO.getRecommend().split(",");
        List<Integer> shopIdList = new ArrayList<>();
        for(int i = 0; i < policyIdArr.length; i++) {
            shopIdList.add(Integer.valueOf(policyIdArr[i]));
        }
        return shopIdList;
    }




}
