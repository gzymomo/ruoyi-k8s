package com.ruoyi.auth.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.auth.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: Zhiyong.ge
 * @Date: 2022/06/06 14:09
 */
@Configuration
public class CodeWebConfigurer implements WebMvcConfigurer {

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private CaptchaProperties captchaProperties;

    private static final String CODE = "code";

    private static final String UUID = "uuid";


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {
                try
                {
                    String rspStr = resolveBodyFromRequest(request);
                    JSONObject obj = JSON.parseObject(rspStr);
                    validateCodeService.checkCaptcha(obj.getString(CODE), obj.getString(UUID));
                }
                catch (Exception e)
                {
                    return false;
                }
                return true;
            }
        }).addPathPatterns("/auth/login","/auth/register");
    }

    private String resolveBodyFromRequest(HttpServletRequest request)
    {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
        }

        return data.toString();
    }
}