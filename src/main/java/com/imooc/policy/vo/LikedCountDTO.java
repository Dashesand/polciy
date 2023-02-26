package com.imooc.policy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-20 20:34
 **/
@Data
@AllArgsConstructor
public class LikedCountDTO {
    private Integer policyId;
    private Integer Count;


}
