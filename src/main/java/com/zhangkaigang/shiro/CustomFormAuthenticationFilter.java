package com.zhangkaigang.shiro;

import com.zhangkaigang.system.pojo.dto.UserDTO;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/6/22
 * @Version:1.0
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 重写认证方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        // 在这里进行验证码的校验
        // 从session获取正确验证码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session =httpServletRequest.getSession();
        // 取出session的验证码（正确的验证码）
        String validateCode = (String) session.getAttribute("KAPTCHA_SESSION_KEY");

        // 取出页面的验证码
        // 输入的验证和session中的验证进行对比
        String code = httpServletRequest.getParameter("verifyCode");
        if(code != null && validateCode != null && !code.equals(validateCode)){
            // 如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("shiroLoginFailure", "randomCodeError");
            // 拒绝访问，不再校验账号和密码
            return true;
        }
        return super.onAccessDenied(request, response);
    }

    /**
     * 重写onLoginSuccess方法，将用户信息存入session
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        // 获取已登录的用户信息
        UserDTO userDTO = (UserDTO)subject.getPrincipal();
        // 获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        // 把用户信息保存到session
        session.setAttribute("user", userDTO);

        // 登录成功-写入登录日志
//        String loginName = userDTO.getLoginName();
//        String ip = request.getRemoteHost();
//        LoginLog loginLog = LogFactory.createLoginLog("登录日志", loginName, ip);
//        LogManager.me().executeLog(LogTaskFactory.addLoginLog(loginLog));

        return super.onLoginSuccess(token, subject, request, response);
    }
}
