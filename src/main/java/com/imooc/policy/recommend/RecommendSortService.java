package com.imooc.policy.recommend;

import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendSortService {

    private SparkSession spark;

    private LogisticRegressionModel lrModel;


    //@PostConstruct
    public void init(){
        //加载LR模型
        spark = SparkSession.builder().master("local").appName("DianpingApp").getOrCreate();

        lrModel = LogisticRegressionModel.load("D:\\user\\Desktop\\data\\lrmode");

    }

    public List<Integer> sort(List<Integer> policyIdList, Integer userId){
        //需要根据lrmode所需要11唯的x，生成特征，然后调用其预测方法
        List<PolicySortModel> list = new ArrayList<>();
        for(Integer polciyId : policyIdList){
            //造的假数据，可以从数据库或缓存中拿到对应的性别，年龄，评分，政策类型等做特征转化生成feture向量
            Vector v = Vectors.dense(1,0,0,0,0,1,0.6,0,0,1,0);
            Vector result = lrModel.predictProbability(v);
            double[] arr = result.toArray();
            //正样本概率
            double score = arr[1];
            PolicySortModel policySortModel = new PolicySortModel();
            policySortModel.setPolicyId(polciyId);
            policySortModel.setScore(score);
            list.add(policySortModel);
        }
        list.sort(new Comparator<PolicySortModel>() {
            @Override
            public int compare(PolicySortModel o1, PolicySortModel o2) {
                if(o1.getScore() < o2.getScore()){
                    return 1;
                }else if(o1.getScore() > o2.getScore()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        return list.stream().map(policySortModel -> policySortModel.getPolicyId()).collect(Collectors.toList());


    }

}
