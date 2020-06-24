package com.zhangkaigang.index.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/10
 * @Version:1.0
 */
@Controller
public class LoginController {

    @RequestMapping("loginPage")
    public String index () {
        return "login";
    }

    private static final String SHIRO_LOGIN_FAILURE = "shiroLoginFailure";
    private static final String RANDOM_CODE_ERROR = "randomCodeError";


    /**
     * 登陆提交地址，和applicationContext-shiro.xml中配置的loginurl一致
     * 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request)throws Exception{
        ModelAndView modelAndView = new ModelAndView("login");
        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute(SHIRO_LOGIN_FAILURE);
        // 根据shiro返回的异常类路径判断，抛出指定异常信息
        if(exceptionClassName!=null){
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                modelAndView.addObject("msg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(
                    exceptionClassName)) {
                modelAndView.addObject("msg", "用户名/密码错误");
            } else if(RANDOM_CODE_ERROR.equals(exceptionClassName)){
                modelAndView.addObject("msg", "验证码错误");
            }else {
                modelAndView.addObject("msg", "用户名/密码错误");
            }
        }
        // 登陆失败还到login页面
        return modelAndView;
    }
}
