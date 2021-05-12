package com.lym.small.config;

import com.lym.small.mapper.UserMapper;
import com.lym.small.model.pojo.User;
import com.lym.small.model.pojo.UserExample;
import com.lym.small.utils.Md5Utils;
import com.lym.small.utils.ReturnUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @desc: 自定义Realm
 * 需要继承AuthorizingRealm类
 * 会实现两个方法
 * @author: Lian
 * @time: 2021/5/12 13:40
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    /**
     * 执行授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取账号密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName= usernamePasswordToken.getUsername();
        String password =new String(usernamePasswordToken.getPassword());
        // 获取数据库用户信息
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(example);
        // MD5加密
        String pwdHash = Md5Utils.hash(password + Md5Utils.defaultSalt + users.get(0).getId());
        if (!pwdHash.equals((users.get(0).getPassword()))) {
            throw new IncorrectCredentialsException();
        }
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
        return authenticationInfo;
    }

}
