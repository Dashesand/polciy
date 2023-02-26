package com.imooc.policy.controller;

import io.swagger.annotations.Api;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-20 10:59
 **/
@Api(tags={"kafka接口"})
@Controller("/kafka")
public class kafkaController {
//    @Autowired
//    //引入kafka的template
//    private KafkaTemplate<String,String> kafkaTemplate;
//    //如果是配置类，就注入配置类就可以
//    //在controller中直接进行消息的发布(为了简便，不建议)
//    @ApiOperation(value="按照标题相关度查找")
//    @GetMapping("/kafka")
//    @ResponseBody
//    public boolean kafka(@RequestParam("keyword") String keyword){
//        kafkaTemplate.send("keyword", keyword);
//        return true;
//    }
}
