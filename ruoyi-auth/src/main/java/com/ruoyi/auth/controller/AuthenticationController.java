package com.ruoyi.auth.controller;

import com.ruoyi.auth.config.IgnoreWhiteProperties;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: Zhiyong.ge
 * @Date: 2022/06/08 9:33
 */
@RestController
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisService redisService;

    @RequestMapping("authentication")
    public AjaxResult authentication(){

        String authentication = ServletUtils.getRequest().getHeader("Authorization");
        HttpServletResponse response = ServletUtils.getResponse();
        response.setHeader("status","200");

        String url = ServletUtils.getRequest().getRequestURL().toString();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites()))
        {
            return AjaxResult.success();
        }
        String token = getToken(authentication);
        if (StringUtils.isEmpty(token))
        {
            response.setHeader("status","401");
            return AjaxResult.error(401, "令牌不能为空");
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null)
        {
            response.setHeader("status","401");
            return AjaxResult.error(401,"令牌已过期或验证不正确！");
        }
        String userkey = JwtUtils.getUserKey(claims);
        boolean islogin = redisService.hasKey(getTokenKey(userkey));
        if (!islogin)
        {
            response.setHeader("status","401");
            return AjaxResult.error(401,"登录状态已过期");
        }
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username))
        {
            response.setHeader("status","401");
            return AjaxResult.error(401,"令牌验证失败");
        }
        // 设置用户信息到请求
        response.setHeader(SecurityConstants.USER_KEY, userkey);
        response.setHeader(SecurityConstants.DETAILS_USER_ID, userid);
        response.setHeader(SecurityConstants.DETAILS_USERNAME, username);

        // 内部请求来源参数清除
        // removeHeader.put(SecurityConstants.FROM_SOURCE,"");
        System.out.println(String.format("验证通过 %s %s %s ",userkey,userid,username));
        return AjaxResult.success();
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token)
    {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(String token)
    {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }
}
