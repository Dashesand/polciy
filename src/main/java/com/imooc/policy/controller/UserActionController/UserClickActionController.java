package com.imooc.policy.controller.UserActionController;


import com.imooc.policy.common.CommonRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: dianping
 * @description:
 * @author: Mr.Like
 * @create: 2023-02-21 21:37
 **/
@Api(tags={"用户行为"})
@Controller("/userAction")
@RequestMapping("/userAction")
public class UserClickActionController {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserClickActionController.class);
    @ApiOperation(value="热搜词")
    @PostMapping("/hotKey")
    @ResponseBody
    public CommonRes search(String hotkey){

        return null;

    }
}
