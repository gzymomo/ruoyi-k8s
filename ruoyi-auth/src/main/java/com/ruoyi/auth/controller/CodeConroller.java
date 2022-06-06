package com.ruoyi.auth.controller;

import com.ruoyi.auth.service.ValidateCodeService;
import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author: Zhiyong.ge
 * @Date: 2022/06/06 13:22
 */
@RestController
public class CodeConroller {

    @Autowired
    private ValidateCodeService validateCodeService;

    @RequestMapping("code")
    public AjaxResult code(){
        AjaxResult ajax;
        try
        {
            ajax = validateCodeService.createCaptcha();
        }
        catch (CaptchaException | IOException e)
        {
            return AjaxResult.error("获取code失败");
        }
        return ajax;
    }
}
