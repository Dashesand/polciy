package com.imooc.dianping.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.imooc.dianping.common.BusinessException;
import com.imooc.dianping.dal.PolicyModelMapper;
import com.imooc.dianping.model.PolicyModel;
import com.imooc.dianping.model.PolicyModelWithBLOBs;
import com.imooc.dianping.request.PageQuery;
import com.imooc.dianping.service.PolicyService;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-09 11:35
 **/
@Service
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private PolicyModelMapper policyModelMapper;

    @Autowired
    private RestHighLevelClient highLevelClient;



    @Override
    public PolicyModel create(PolicyModel PolicyModel) throws BusinessException {
        return null;
    }

    @Override
    public PolicyModel get(Integer id) {
    return null;
    }

    @Override
    public List<PolicyModel> selectAll() {
        return null;
    }

    @Override
    public List<PolicyModel> recommend(BigDecimal longitude, BigDecimal latitude) {
        return null;
    }

    @Override
    public List<Map<String, Object>> searchGroupByTags(String keyword, Integer categoryId, String tags) {
        return null;
    }

    @Override
    public Integer countAllPolicy() {
        return null;
    }



    @Override
    public Map<String, Object> searchES(PageQuery pageQuery,String keyword, String pubAgency, Date startTime, Date endTime,
                                        String zone, String policySource, String policyGrade, String policyType,
                                        Integer sort, Integer timeChose,Integer choseAggs ) throws IOException {
        System.out.println("进入es");
        Request request = new Request("GET","/policy/_search");

        Map<String, Object> result = new HashMap<>();

        //构建请求
        JSONObject jsonRequestObj = new JSONObject();
        //构建source部分
        jsonRequestObj.put("_source","*");
        //构建query
        jsonRequestObj.put("query",new JSONObject());
        //构建function_score部分
        jsonRequestObj.getJSONObject("query").put("function_score",new JSONObject());

        //构建function_score的query部分
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").put("query",new JSONObject());
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").put("bool",new JSONObject());
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool").put("must",new JSONArray());
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                .getJSONArray("must").add(new JSONObject());
        //构建match query
        int queryIndex = 0;
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                .getJSONArray("must").getJSONObject(queryIndex).put("match",new JSONObject());
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("match").put("policy_title", new JSONObject());
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("match").getJSONObject("policy_title").put("query",keyword);
        jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("match").getJSONObject("policy_title").put("boost",0.1);
        System.out.println(pubAgency);
        if(choseAggs!=null)
        {
            if(choseAggs==2)
            {
                queryIndex++;
                jsonRequestObj.put("aggs", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").put("group_by_policyGrade", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyGrade").put("terms", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyGrade").getJSONObject("terms").put("field", "policy_grade.keyword");
                List<Map> gradesList = new ArrayList<>();
                String reqJson = jsonRequestObj.toString();
                System.out.println(reqJson);
                request.setJsonEntity(reqJson);
                Response response = highLevelClient.getLowLevelClient().performRequest(request);
                String responseStr = EntityUtils.toString(response.getEntity());
                System.out.println(responseStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_policyGrade").getJSONArray("buckets");
                for(int i = 0; i < agencyJsonArray.size(); i++){
                    JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                    Map<String,Object> tagMap = new HashMap<>();
                    tagMap.put("grades",jsonObj.getString("key"));
                    tagMap.put("num",jsonObj.getInteger("doc_count"));
                    gradesList.add(tagMap);
                }
                result.put("grades", gradesList);
            }
            else if(choseAggs==1)
            {
                queryIndex++;
                jsonRequestObj.put("aggs", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").put("group_by_policySource", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policySource").put("terms", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policySource").getJSONObject("terms").put("field", "policy_source.keyword");
                List<Map> sourcesList = new ArrayList<>();
                String reqJson = jsonRequestObj.toString();
                System.out.println(reqJson);
                request.setJsonEntity(reqJson);
                Response response = highLevelClient.getLowLevelClient().performRequest(request);
                String responseStr = EntityUtils.toString(response.getEntity());
                System.out.println(responseStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_policySource").getJSONArray("buckets");
                for(int i = 0; i < agencyJsonArray.size(); i++){
                    JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                    Map<String,Object> tagMap = new HashMap<>();
                    tagMap.put("sources",jsonObj.getString("key"));
                    tagMap.put("num",jsonObj.getInteger("doc_count"));
                    sourcesList.add(tagMap);
                }
                result.put("sources", sourcesList);
            }
            else if(choseAggs==3)
            {
                queryIndex++;
                jsonRequestObj.put("aggs", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").put("group_by_pubAgency", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_pubAgency").put("terms", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_pubAgency").getJSONObject("terms").put("field", "pub_agency.keyword");
                List<Map> agencysList = new ArrayList<>();
                String reqJson = jsonRequestObj.toString();
                System.out.println(reqJson);
                request.setJsonEntity(reqJson);
                Response response = highLevelClient.getLowLevelClient().performRequest(request);
                String responseStr = EntityUtils.toString(response.getEntity());
                System.out.println(responseStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_pubAgency").getJSONArray("buckets");
                for(int i = 0; i < agencyJsonArray.size(); i++){
                    JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                    Map<String,Object> tagMap = new HashMap<>();
                    tagMap.put("agencys",jsonObj.getString("key"));
                    tagMap.put("num",jsonObj.getInteger("doc_count"));
                    agencysList.add(tagMap);
                }
                result.put("agencys", agencysList);
            }
            else if(choseAggs==4)
            {
                queryIndex++;
                jsonRequestObj.put("aggs", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").put("group_by_policyType", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyType").put("terms", new JSONObject());
                jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyType").getJSONObject("terms").put("field", "policy_type.keyword");
                List<Map> typesList = new ArrayList<>();
                String reqJson = jsonRequestObj.toString();
                System.out.println(reqJson);
                request.setJsonEntity(reqJson);
                Response response = highLevelClient.getLowLevelClient().performRequest(request);
                String responseStr = EntityUtils.toString(response.getEntity());
                System.out.println(responseStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStr);
                JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_policyType").getJSONArray("buckets");
                for(int i = 0; i < agencyJsonArray.size(); i++){
                    JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                    Map<String,Object> tagMap = new HashMap<>();
                    tagMap.put("types",jsonObj.getString("key"));
                    tagMap.put("num",jsonObj.getInteger("doc_count"));
                    typesList.add(tagMap);
                }
                result.put("types", typesList);
            }
            else if(choseAggs==0)
            {

            }
        }
        //构建第二个query机构+聚合的条件
        if(pubAgency != null){
            queryIndex++;
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").add(new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).put("term",new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("term").put("pub_agency.keyword",pubAgency);
            queryIndex++;
            jsonRequestObj.put("aggs", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").put("group_by_pubagency", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_pubagency").put("terms", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_pubagency").getJSONObject("terms").put("field", "pub_agency.keyword");
        }


        //按照发布政策类型聚合字段
        else if(policyType!=null){
            queryIndex++;
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").add(new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).put("term",new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("term").put("policy_type.keyword",policyType);
            queryIndex++;
            jsonRequestObj.put("aggs", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").put("group_by_policyType", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyType").put("terms", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyType").getJSONObject("terms").put("field", "policy_type.keyword");

        }
        //按照政策来源
        else if(policySource!=null)
        {
            queryIndex++;
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").add(new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).put("term",new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("term").put("policy_source.keyword",policySource);
            queryIndex++;
            jsonRequestObj.put("aggs", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").put("group_by_policySource", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policySource").put("terms", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policySource").getJSONObject("terms").put("field", "policy_source.keyword");
        }
        //按照政策级别聚合字段
        else if(policyGrade!=null)
        {
            queryIndex++;
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").add(new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).put("term",new JSONObject());
            jsonRequestObj.getJSONObject("query").getJSONObject("function_score").getJSONObject("query").getJSONObject("bool")
                    .getJSONArray("must").getJSONObject(queryIndex).getJSONObject("term").put("policy_grade.keyword",policyGrade);
            queryIndex++;
            jsonRequestObj.put("aggs", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").put("group_by_policyGrade", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyGrade").put("terms", new JSONObject());
            jsonRequestObj.getJSONObject("aggs").getJSONObject("group_by_policyGrade").getJSONObject("terms").put("field", "policy_grade.keyword");
        }

        //构建返回页面字段
        jsonRequestObj.put("from",pageQuery.getPage());
        jsonRequestObj.put("size",pageQuery.getSize());

        String reqJson = jsonRequestObj.toString();
        System.out.println(reqJson);
        request.setJsonEntity(reqJson);
        Response response = highLevelClient.getLowLevelClient().performRequest(request);
        String responseStr = EntityUtils.toString(response.getEntity());
        System.out.println(responseStr);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        JSONArray jsonArr = jsonObject.getJSONObject("hits").getJSONArray("hits");
        List<PolicyModel> policyModelList = new ArrayList<>();

        if(pubAgency!=null){
            List<Map> agencysList = new ArrayList<>();
            JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_pubagency").getJSONArray("buckets");
            for(int i = 0; i < agencyJsonArray.size(); i++){
                JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                Map<String,Object> tagMap = new HashMap<>();
                tagMap.put("agencys",jsonObj.getString("key"));
                tagMap.put("num",jsonObj.getInteger("doc_count"));
                agencysList.add(tagMap);
            }
            result.put("agencys", agencysList);
        }else if(policyGrade!=null)
        {
            List<Map> gradesList = new ArrayList<>();
            JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_policyGrade").getJSONArray("buckets");
            for(int i = 0; i < agencyJsonArray.size(); i++){
                JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                Map<String,Object> tagMap = new HashMap<>();
                tagMap.put("grades",jsonObj.getString("key"));
                tagMap.put("num",jsonObj.getInteger("doc_count"));
                gradesList.add(tagMap);
            }
            result.put("grades", gradesList);
        }else if(policyType!=null)
        {
            List<Map> typesList = new ArrayList<>();
            JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_policyType").getJSONArray("buckets");
            for(int i = 0; i < agencyJsonArray.size(); i++){
                JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                Map<String,Object> tagMap = new HashMap<>();
                tagMap.put("types",jsonObj.getString("key"));
                tagMap.put("num",jsonObj.getInteger("doc_count"));
                typesList.add(tagMap);
            }
            result.put("types", typesList);
        }else if(policySource!=null)
        {
            List<Map> sourcesList = new ArrayList<>();
            JSONArray agencyJsonArray = jsonObject.getJSONObject("aggregations").getJSONObject("group_by_policySource").getJSONArray("buckets");
            for(int i = 0; i < agencyJsonArray.size(); i++){
                JSONObject jsonObj = agencyJsonArray.getJSONObject(i);
                Map<String,Object> tagMap = new HashMap<>();
                tagMap.put("sources",jsonObj.getString("key"));
                tagMap.put("num",jsonObj.getInteger("doc_count"));
                sourcesList.add(tagMap);
            }
            result.put("sources", sourcesList);
        }


        for(int i = 0; i < jsonArr.size(); i++){
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            Integer id = new Integer(jsonObj.get("_id").toString());
            System.out.println(id);
            PolicyModelWithBLOBs policyModel = policyModelMapper.selectByPrimaryKey(id);
            System.out.println(policyModel.getPolicyBody());
            policyModelList.add(policyModel);
        }

        result.put("policy", policyModelList);
        result.put("total",jsonObject.getJSONObject("hits").getJSONObject("total").getString("value"));
        return result;
    }
}


