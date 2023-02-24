package com.imooc.dianping.controller.UserActionController;


import com.imooc.dianping.common.BusinessException;
import com.imooc.dianping.common.CommonRes;
import com.imooc.dianping.model.PolicyModel;
import com.imooc.dianping.request.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
