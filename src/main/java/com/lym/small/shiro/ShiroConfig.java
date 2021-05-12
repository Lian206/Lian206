package com.lym.small.shiro;

import com.lym.small.config.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @desc: shiro配置
 * @author: Lian
 * @time: 2021/5/12 13:43
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的有：
         * 	 anon：无需认证就能访问
         * 	 authc：必须认证才能访问
         *   user：必须拥有 “记住我” 功能才能使用
         *   perms：拥有对某个资源的权限才能访问
         *   role： 拥有对某个角色权限才能访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<String,String>();
        //放行的用户放在前面
        filterMap.put("/user/login", "anon");
        //放行Swagger
        filterMap.put("/swagger-ui.html","anon");
        filterMap.put("/swagger/**","anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**","anon");
        filterMap.put("/v2/**","anon");
        //拦截所有请求
        filterMap.put("/**", "authc");
        //修改登录页面
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        //Shiro自带加密
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法使用md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数，1表示md5加密1次
        credentialsMatcher.setHashIterations(1);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("MyRealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /**
     * 创建Realm对象（第一步）
     * @Bean 将方法返回的对象放入Spring环境进行管理
     */
    @Bean(name="MyRealm")
    public MyRealm myRealm() {
        return new MyRealm();
    }

}
