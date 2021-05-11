package com.lym.small.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.small.mapper.UserMapper;
import com.lym.small.model.dto.UserBaseDTO;
import com.lym.small.model.enums.UserStatusEnum;
import com.lym.small.model.pojo.User;
import com.lym.small.model.pojo.UserExample;
import com.lym.small.model.dto.UserListDTO;
import com.lym.small.service.UserService;
import com.lym.small.utils.Md5Utils;
import com.lym.small.utils.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc: User Service 业务层
 * @author: Lian
 * @time: 2021/5/8 14:32
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查用户列表
     * @param dto
     * @return
     */
    @Override
    public PageInfo<User> queryUserList(UserListDTO dto) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        // 有效用户
        criteria.andStatusEqualTo(UserStatusEnum.NORMAL.getCode());
        if (StringUtils.isNotBlank(dto.getUserName())) {
            criteria.andUserNameLike("%" + dto.getUserName() + "%");
        }
        if (StringUtils.isNotBlank(dto.getLabel())) {
            criteria.andLabelLike("%" + dto.getLabel() + "%");
        }
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<User> users = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return pageInfo;
    }

    /**
     * 添加用户
     * @param dto
     */
    @Override
    public Map<String, Object> addUser(UserBaseDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return ReturnUtil.error(new HashMap<>(), "用户名已存在");
        }
        userMapper.insertSelective(user);
        // 密码加密
        user.setPassword(Md5Utils.hash(dto.getPassword() + Md5Utils.defaultSalt + user.getId()));
        userMapper.updateByPrimaryKeySelective(user);
        return ReturnUtil.success(new HashMap<>(), "添加成功");
    }

    /**
     * 修改用户信息
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> updateUser(UserBaseDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        User userSe = userMapper.selectByPrimaryKey(user.getId());
        if (userSe == null) {
            return ReturnUtil.error(new HashMap<>(), "该用户不存在");
        }
        UserExample example = new UserExample();
        example.createCriteria().andIdNotEqualTo(user.getId())
                .andUserNameEqualTo(user.getUserName());
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return ReturnUtil.error(new HashMap<>(), "用户名已存在");
        }
        userMapper.updateByPrimaryKeySelective(user);
        return ReturnUtil.success(new HashMap<>(), "修改成功");
    }

    /**
     * 删除用户 (逻辑删除)
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> deleteUserByPrimaryKey(UserBaseDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setStatus(UserStatusEnum.INVALID.getCode());  // 状态失效
        User userSe = userMapper.selectByPrimaryKey(user.getId());
        if (userSe == null) {
            return ReturnUtil.error(new HashMap<>(), "该用户不存在");
        }
        userMapper.updateByPrimaryKeySelective(user);
        return ReturnUtil.success(new HashMap<>(), "删除成功");
    }
}
