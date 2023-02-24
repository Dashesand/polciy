package com.imooc.dianping.recomend;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-20 10:53
 **/

public class kafkaTest {
//    @Autowired
//    //引入kafka的template
//    private KafkaTemplate<String,String> kafkaTemplate;
//    //如果是配置类，就注入配置类就可以
//
//    //在controller中直接进行消息的发布(为了简便，不建议)
//    @GetMapping("/kafka")
//    public boolean kafka(@RequestParam("msg") String msg){
//        kafkaTemplate.send("test", msg);
//        return true;
//    }
}
