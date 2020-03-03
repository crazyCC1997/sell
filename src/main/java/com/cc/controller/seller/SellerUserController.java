package com.cc.controller.seller;

import com.cc.config.ProjectUrlProperties;
import com.cc.constant.CookieConstant;
import com.cc.constant.RedisConstant;
import com.cc.enums.ResultEnum;
import com.cc.pojo.SellerInfo;
import com.cc.service.SellerService;
import com.cc.utils.CookieUtil;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户controller
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 18:38
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    /**
     * 卖家登录功能
     *
     * @param openid
     * @param model
     * @param response
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam("openid") String openid, Model model, HttpServletResponse response) {

        //openid匹配数据库信息
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(null == sellerInfo) {
            model.addAttribute("msg", ResultEnum.LOGIN_FAIL.getMessage());
            model.addAttribute("url", "/sell/seller/order/list");
            return "/common/error";
        }
        //设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);
        //设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        //重定向,建议用绝对路劲
        return "redirect:" + projectUrlProperties.getSell() +"/sell/seller/order/list";
    }

    /**
     * 卖家登出功能
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
        //从cookie中查询token
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(null != cookie) {
            //清除redis中的数据
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清除cookie中的token
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        model.addAttribute("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        model.addAttribute("url", "/sell/seller/order/list");

        return "/common/success";
    }
}
