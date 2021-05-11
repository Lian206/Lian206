package com.lym.small.controller.user;

import com.github.pagehelper.PageInfo;
import com.lym.small.model.dto.UserBaseDTO;
import com.lym.small.model.enums.UserStatusEnum;
import com.lym.small.model.pojo.User;
import com.lym.small.model.dto.UserListDTO;
import com.lym.small.model.req.UserAddReq;
import com.lym.small.model.req.UserDeleteReq;
import com.lym.small.model.req.UserListReq;
import com.lym.small.model.req.UserUpdateReq;
import com.lym.small.model.resp.ListResp;
import com.lym.small.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * @desc: User Controller
 * @author: Lian
 * @time: 2021/5/8 13:20
 */

@Api(value="用户Controller")
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表查询
     * @param reqModel
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "通过用户名、用户标签模糊查询用户列表")
    @PostMapping(value = "/user/queryUserList")
    public ListResp queryUserList(@RequestBody @Validated UserListReq reqModel){
        log.info("收到请求开始：[用户列表查询][/user/queryUserList:"+reqModel.toString());
        UserListDTO userDTO = new UserListDTO();
        BeanUtils.copyProperties(reqModel, userDTO);
        PageInfo<User> users = userService.queryUserList(userDTO);
        // 返回结果
        ListResp resp = new ListResp();
        resp.setList(new ArrayList<User>());
        resp.setTotalRows((int) users.getTotal());
        resp.setPageNum(users.getPageNum());
        resp.setPageSize(users.getPageSize());
        resp.setPages(users.getPages());
        for (User item: users.getList()) {
            User u = new User();
            BeanUtils.copyProperties(item, u);
            resp.getList().add(u);
        }
        log.info("请求处理结束：[用户列表查询]" + resp);
        return resp;
    }

    /**
     * 添加用户
     * @param reqModel
     */
    @ApiOperation(value = "添加用户", notes = "用户名、性别、密码为必填")
    @PostMapping(value = "/user/addUser")
    public Map<String, Object> addUser(@RequestBody @Validated UserAddReq reqModel){
        log.info("收到请求开始：[添加用户][/user/addUser:" + reqModel.toString());
        UserBaseDTO dto = new UserBaseDTO();
        BeanUtils.copyProperties(reqModel, dto);
        dto.setStatus(UserStatusEnum.NORMAL.getCode());
        Map<String, Object> resultMap = userService.addUser(dto);
        log.info("请求处理结束：[添加用户][/user/addUser:" + resultMap);
        return resultMap;
    }

    /**
     * 修改用户信息
     * @param reqModel
     */
    @ApiOperation(value = "修改用户信息", notes = "")
    @PostMapping(value = "/user/updateUser")
    public Map<String, Object> updateUser(@RequestBody @Validated UserUpdateReq reqModel){
        log.info("收到请求开始：[修改用户信息][/user/updateUser:" + reqModel.toString());
        UserBaseDTO dto = new UserBaseDTO();
        BeanUtils.copyProperties(reqModel, dto);
        Map<String, Object> resultMap = userService.updateUser(dto);
        log.info("请求处理结束：[修改用户信息][/user/updateUser:" + resultMap);
        return resultMap;
    }

    /**
     * 删除用户 (逻辑删除)
     * @param reqModel
     */
    @ApiOperation(value = "删除用户", notes = "")
    @PostMapping(value = "/user/deleteUser")
    public Map<String, Object> deleteUser(@RequestBody @Validated UserDeleteReq reqModel){
        log.info("收到请求开始：[删除用户][/user/deleteUser:" + reqModel.toString());
        UserBaseDTO dto = new UserBaseDTO();
        dto.setId(reqModel.getId());
        Map<String, Object> resultMap = userService.deleteUserByPrimaryKey(dto);
        log.info("请求处理结束：[删除用户][/user/deleteUser:" + resultMap);
        return resultMap;
    }

}
