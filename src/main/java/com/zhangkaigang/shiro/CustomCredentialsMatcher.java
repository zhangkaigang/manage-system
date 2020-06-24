package com.zhangkaigang.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @Description:自定义密码凭证器
 * @Author:zhang.kaigang
 * @Date:2020/6/22
 * @Version:1.0
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 要验证的明文密码
        String plaintext = new String(userToken.getPassword());
        // 数据库中的加密后的密文
        String hashed = info.getCredentials().toString();
        return BCrypt.checkpw(plaintext, hashed);
    }
}
