package com.imooc.policy.service;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.model.PolicyModel;
import com.imooc.policy.request.PageQuery;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-09 11:35
 **/

public interface PolicyService {
    PolicyModel create(PolicyModel PolicyModel) throws BusinessException;
    PolicyModel get(Integer id);
    List<PolicyModel> selectAll();
    List<PolicyModel> recommend(BigDecimal longitude, BigDecimal latitude);

    List<Map<String,Object>> searchGroupByTags(String keyword, Integer categoryId, String tags);

    Integer countAllPolicy();



    Map<String,Object> searchES(PageQuery pageQuery,String keyword, String pubAgency, Date startTime, Date endTime,
                                String zone, String policySource, String policyGrade, String policyType,
                                Integer sort, Integer timeChose,Integer choseAggs) throws IOException;
}
