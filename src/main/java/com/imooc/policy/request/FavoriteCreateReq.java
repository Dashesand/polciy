package com.imooc.policy.request;

import lombok.Data;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-22 09:52
 **/
@Data
public class FavoriteCreateReq {
    private Integer userId;
    private Integer policyId;
    private Integer status;
}
