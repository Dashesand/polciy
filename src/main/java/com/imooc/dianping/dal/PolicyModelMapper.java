package com.imooc.dianping.dal;

import com.imooc.dianping.model.PolicyModel;
import com.imooc.dianping.model.PolicyModelWithBLOBs;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PolicyModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    int deleteByPrimaryKey(Integer policyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    int insert(PolicyModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    int insertSelective(PolicyModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    PolicyModelWithBLOBs selectByPrimaryKey(Integer policyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    int updateByPrimaryKeySelective(PolicyModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    int updateByPrimaryKeyWithBLOBs(PolicyModelWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table policy
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    int updateByPrimaryKey(PolicyModel record);

}