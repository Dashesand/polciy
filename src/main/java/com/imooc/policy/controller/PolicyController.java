package com.imooc.policy.controller;

import com.imooc.policy.common.BusinessException;
import com.imooc.policy.common.CommonRes;
import com.imooc.policy.model.PolicyModel;
import com.imooc.policy.request.PageQuery;
import com.imooc.policy.service.PolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-09 11:54
 **/
@Api(tags={"政策操作接口"})
@Controller("/policy")
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    private PolicyService policyService;



    //搜索服务V1.0
    @ApiOperation(value="按照标题相关度查找")
    @PostMapping("/search")
    @ResponseBody
    public CommonRes search(PageQuery pageQuery,
                            @RequestParam(name="keyword",required = false)@ApiParam(name = "keyword",value = "关键字")String keyword,
                            @RequestParam(name="timeChose",required = false)@ApiParam(name = "timeChose",value = "时间筛选，非必须。规定为[1:最近一周时间" +
                                    "2:最近一个月，3:最近一年]") Integer timeChose,
                            @RequestParam(name="startTime",required = false)@ApiParam(name = "startTime",value = "时间筛选开始时间") Date startTime,
                            @RequestParam(name="endTime",required = false)@ApiParam(name = "endTime",value = "时间筛选结束时间") Date endTime,
                            @RequestParam(name="zone",required = false)@ApiParam(name = "zone",value = "地区") String zone,
                            @RequestParam(name="policySource",required = false)@ApiParam(name = "policySource",value = "政策来源") String policySource,
                            @RequestParam(name="policyGrade",required = false)@ApiParam(name = "policyGrade",value = "政策级别,[国家级,省级,区县级，市级]") String policyGrade,
                            @RequestParam(name="pubAgency",required = false)@ApiParam(name = "pubAgency",value = "发布机构名称") String pubAgency,
                            @RequestParam(name="policyType",required = false)@ApiParam(name = "policyType",value = "政策类型[办法,方案,概要，公报,公示,规定,规划,函,决定，名单" +
                                    "目录，批复，其他，说明，通报，通告，通知，意见]") String policyType,
                            @RequestParam(name="sort",required = false)@ApiParam(name = "sort",value = "排序规则，0相关程度排序，1发布时间排序") Integer sort,
                            @RequestParam(name="choseAggs",required = false)@ApiParam(name = "choseAggs",value = "分组规则，0查看地区，1政策来源，2政策级别，3发布机构名称，4政策类型") Integer choseAggs) throws BusinessException, IOException {

        Map<String,Object> result = policyService.searchES(pageQuery,keyword, pubAgency, startTime, endTime,
                zone, policySource, policyGrade, policyType,
                sort, timeChose,choseAggs);

        List<PolicyModel> PolicyModelList = (List<PolicyModel>) result.get("policy");


        List<Map<String,Object>> agencysAggregation = (List<Map<String, Object>>) result.get("agencys");
        List<Map<String,Object>> typesAggregation = (List<Map<String, Object>>) result.get("types");
        List<Map<String,Object>> sourcesAggregation = (List<Map<String, Object>>) result.get("sources");
        List<Map<String,Object>> gradesAggregation = (List<Map<String, Object>>) result.get("grades");

        Map<String,Object> resMap = new HashMap<>();

        resMap.put("data",PolicyModelList);
        resMap.put("agencys",agencysAggregation);
        resMap.put("types",typesAggregation);
        resMap.put("sources",sourcesAggregation);
        resMap.put("grades",gradesAggregation);
        resMap.put("total",result.get("total"));
        return CommonRes.create(resMap);

    }
    @ApiOperation(value="按照政策id查找")
    @PostMapping("/searchById")
    @ResponseBody
    public CommonRes searchById(@RequestParam(name="id")Integer id)
    {
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("policyModel",policyService.get(id));
        return CommonRes.create(resMap);
    }
}
