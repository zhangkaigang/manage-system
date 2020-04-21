package com.zhangkaigang.index.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zhangkaigang.base.constant.IndexConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Description:验证码
 * @Author:zhang.kaigang
 * @Date:2020/4/10
 * @Version:1.0
 */
@Controller
@Api(tags = "验证码接口")
public class CaptchaController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * 生成验证码图片
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/kaptcha")
    @ApiOperation(value = "获取验证码", httpMethod = "GET")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] captcha = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            // 将生成的验证码保存在session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(IndexConstants.KAPTCHA_CODE, createText);
            BufferedImage bi = defaultKaptcha.createImage(createText);
            ImageIO.write(bi, "jpg", out);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        captcha = out.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(captcha);
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
