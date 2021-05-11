package com.lym.small.service;

import com.github.pagehelper.PageInfo;
import com.lym.small.model.dto.UserBaseDTO;
import com.lym.small.model.pojo.User;
import com.lym.small.model.dto.UserListDTO;

import java.util.Map;

/**
 * @desc: User Service
 * @author: Lian
 * @time: 2021/5/8 14:32
 */
public interface UserService {
    /**
     * 查用户列表
     * @param dto
     * @return
     */
    PageInfo<User> queryUserList(UserListDTO dto);

    /**
     * 添加用户
     * @param dto
     * @return
     */
    Map<String, Object> addUser(UserBaseDTO dto);

    /**
     * 修改用户
     * @param dto
     * @return
     */
    Map<String, Object> updateUser(UserBaseDTO dto);

    /**
     * 删除用户 (逻辑删除)
     * @param dto
     * @return
     */
    Map<String, Object> deleteUserByPrimaryKey(UserBaseDTO dto);

}
