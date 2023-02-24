package com.imooc.dianping.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PolicyModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.policy_id
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private Integer policyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.policy_title
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String policyTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.policy_grade
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String policyGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.PUB_NUMBER
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String pubNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.PUB_TIME
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pubTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.POLICY_TYPE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String policyType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.PROVINCE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.CITY
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.POLICY_SOURCE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String policySource;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.UPDATE_DATE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.remark_score
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private BigDecimal remarkScore;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.pub_agency
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String pubAgency;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column policy.pub_agency_fullname
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    private String pubAgencyFullname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.policy_id
     *
     * @return the value of policy.policy_id
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public Integer getPolicyId() {
        return policyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.policy_id
     *
     * @param policyId the value for policy.policy_id
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.policy_title
     *
     * @return the value of policy.policy_title
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPolicyTitle() {
        return policyTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.policy_title
     *
     * @param policyTitle the value for policy.policy_title
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle == null ? null : policyTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.policy_grade
     *
     * @return the value of policy.policy_grade
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPolicyGrade() {
        return policyGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.policy_grade
     *
     * @param policyGrade the value for policy.policy_grade
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPolicyGrade(String policyGrade) {
        this.policyGrade = policyGrade == null ? null : policyGrade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.PUB_NUMBER
     *
     * @return the value of policy.PUB_NUMBER
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPubNumber() {
        return pubNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.PUB_NUMBER
     *
     * @param pubNumber the value for policy.PUB_NUMBER
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPubNumber(String pubNumber) {
        this.pubNumber = pubNumber == null ? null : pubNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.PUB_TIME
     *
     * @return the value of policy.PUB_TIME
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public Date getPubTime() {
        return pubTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.PUB_TIME
     *
     * @param pubTime the value for policy.PUB_TIME
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.POLICY_TYPE
     *
     * @return the value of policy.POLICY_TYPE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPolicyType() {
        return policyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.POLICY_TYPE
     *
     * @param policyType the value for policy.POLICY_TYPE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPolicyType(String policyType) {
        this.policyType = policyType == null ? null : policyType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.PROVINCE
     *
     * @return the value of policy.PROVINCE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.PROVINCE
     *
     * @param province the value for policy.PROVINCE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.CITY
     *
     * @return the value of policy.CITY
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.CITY
     *
     * @param city the value for policy.CITY
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.POLICY_SOURCE
     *
     * @return the value of policy.POLICY_SOURCE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPolicySource() {
        return policySource;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.POLICY_SOURCE
     *
     * @param policySource the value for policy.POLICY_SOURCE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPolicySource(String policySource) {
        this.policySource = policySource == null ? null : policySource.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.UPDATE_DATE
     *
     * @return the value of policy.UPDATE_DATE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.UPDATE_DATE
     *
     * @param updateDate the value for policy.UPDATE_DATE
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.remark_score
     *
     * @return the value of policy.remark_score
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public BigDecimal getRemarkScore() {
        return remarkScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.remark_score
     *
     * @param remarkScore the value for policy.remark_score
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setRemarkScore(BigDecimal remarkScore) {
        this.remarkScore = remarkScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.pub_agency
     *
     * @return the value of policy.pub_agency
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPubAgency() {
        return pubAgency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.pub_agency
     *
     * @param pubAgency the value for policy.pub_agency
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPubAgency(String pubAgency) {
        this.pubAgency = pubAgency == null ? null : pubAgency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column policy.pub_agency_fullname
     *
     * @return the value of policy.pub_agency_fullname
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public String getPubAgencyFullname() {
        return pubAgencyFullname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column policy.pub_agency_fullname
     *
     * @param pubAgencyFullname the value for policy.pub_agency_fullname
     *
     * @mbg.generated Fri Feb 10 11:10:59 CST 2023
     */
    public void setPubAgencyFullname(String pubAgencyFullname) {
        this.pubAgencyFullname = pubAgencyFullname == null ? null : pubAgencyFullname.trim();
    }
}