package com.imooc.policy.controller.admin;

import com.imooc.policy.common.AdminPermission;
import com.imooc.policy.common.BusinessException;
import com.imooc.policy.common.EmBusinessError;
import com.imooc.policy.service.CategoryService;
import com.imooc.policy.service.SellerService;
import com.imooc.policy.service.ShopService;
import com.imooc.policy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Api(tags={"管理员接口"})
@Controller("/admin/admin")
@RequestMapping("/admin/admin")
public class AdminController {

    @Value("${admin.email}")
    private String email;


    @Value("${admin.encryptPassword}")
    private String encrptyPassord;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private SellerService sellerService;


    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";
    @ApiOperation(value="管理员首页")
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");

        modelAndView.addObject("userCount",userService.countAllUser());
        modelAndView.addObject("shopCount",shopService.countAllShop());
        modelAndView.addObject("categoryCount",categoryService.countAllCategory());
        modelAndView.addObject("sellerCount",sellerService.countAllSeller());
        modelAndView.addObject("CONTROLLER_NAME","admin");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @ApiOperation(value="管理员登录页面")
    @RequestMapping("/loginpage")
    public ModelAndView loginpage(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/login");
        return modelAndView;
    }
    @ApiOperation(value="管理员登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name="email")String email,
                              @RequestParam(name="password")String password ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名密码不能为空");
        }
        if(email.equals(this.email) && encodeByMd5(password).equals(this.encrptyPassord)){
            //登录成功
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION,email);
            return "redirect:/admin/admin/index";
        }else{
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名密码错误");
        }

    }
    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确认计算方法MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));

    }
}
