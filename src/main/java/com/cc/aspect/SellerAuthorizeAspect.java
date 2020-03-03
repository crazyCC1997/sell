package com.cc.aspect;

import com.cc.constant.CookieConstant;
import com.cc.constant.RedisConstant;
import com.cc.exception.SellerAuthorizeException;
import com.cc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家授权登录切面
 *
 * @author cchen
 * @version 1.0
 * @date 2020/3/1 10:58
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.cc.controller.seller.Seller*.*(..))" +
    "&& !execution(public * com.cc.controller.seller.SellerUserController.*(..))")
    public void verify() {}


    /**
     * 实现
     */
    @Before("verify()")
    public void doVerify() {
        //获取cookie，首先要获得request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(null == cookie) {
            log.warn("【登录校验】，cookie中不存在token");
            throw  new SellerAuthorizeException();
        }

        //去redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】，redis中不存在token");
            throw new SellerAuthorizeException();
        }

    }
}
