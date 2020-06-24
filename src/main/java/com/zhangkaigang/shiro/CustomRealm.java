package com.zhangkaigang.shiro;

import com.zhangkaigang.system.pojo.dto.AuthDTO;
import com.zhangkaigang.system.pojo.dto.UserDTO;
import com.zhangkaigang.system.pojo.po.Auth;
import com.zhangkaigang.system.pojo.po.RoleAuth;
import com.zhangkaigang.system.pojo.po.UserRole;
import com.zhangkaigang.system.service.AuthService;
import com.zhangkaigang.system.service.RoleAuthService;
import com.zhangkaigang.system.service.UserRoleService;
import com.zhangkaigang.system.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/6/22
 * @Version:1.0
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    /**
     * 设置realm的名称
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // token是用户输入的用户名和密码
        // 第一步从token中取出用户名
        String loginName = (String) authenticationToken.getPrincipal();
        // 调用数据层
        UserDTO userDTO = userService.findByLoginName(loginName);
        // 用户不存在，直接返回null
        if (userDTO == null) {
            return null;
        }
        // 从数据库查询到密码
        String password = userDTO.getPassword();
        // 封装需要展示的内容

        // 根据用户id取出菜单集合，先找出角色，然后根据角色得到权限集合
        List<AuthDTO> menuList;
        if(userDTO.getUserId() == 1L) {
            // 超级管理员,拥有所有菜单
            menuList = authService.findAll(1, 0L);
        } else {
            // 其他用户
            menuList = authService.findByUserId(userDTO.getUserId(), 1, 0L);
        }
        // 将用户菜单设置到用户信息中
        userDTO.setMenuList(menuList);
        // 第一个参数 ，登陆后，需要在session保存数据
        // 第二个参数，查询到密码(加密规则要和自定义的HashedCredentialsMatcher中的HashAlgorithmName散列算法一致)
        // 第三个参数 ，realm名字
        return new SimpleAuthenticationInfo(userDTO, password, getName());

    }


    /**
     * 授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalCollection) {
        // 添加权限 和 角色信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型）
        UserDTO userDTO =  (UserDTO) principalCollection.getPrimaryPrincipal();
        List<AuthDTO> authList;
        if(userDTO.getUserId() == 1L) {
            // 超级管理员，添加所有角色、添加所有权限
            simpleAuthorizationInfo.addRole("*");
            simpleAuthorizationInfo.addStringPermission("*");
        } else {
            // 其他用户
            // 根据身份信息获取功能权限信息，从数据库获取到拥有的权限数据
            authList = authService.findByUserId(userDTO.getUserId(), 2, null);

            // 单独定一个集合对象，存放功能权限的编码
            List<String> authCodeList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(authList)){
                for(AuthDTO authDTO : authList){
                    // 将数据库中的权限标签 符放入集合
                    authCodeList.add(authDTO.getAuthCode());
                }
                userDTO.setAuthList(authList);
            }
            // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
            simpleAuthorizationInfo.addStringPermissions(authCodeList);
        }

        return simpleAuthorizationInfo;
    }

    /**
     * 清除缓存
     */
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
