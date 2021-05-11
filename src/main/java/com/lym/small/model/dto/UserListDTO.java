package com.lym.small.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @desc: UserListDTO
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserListDTO implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Integer id;
    /** 用户名 */
    private String userName;
    /** 性别 */
    private String sex;
    /** 密码 */
    private String password;
    /** 手机号 */
    private String phone;
    /** 地址 */
    private String address;
    /** 用户标签 */
    private String label;
    /** 热度 */
    private String rank;
    /** 用户状态 */
    private String status;

    /** 当前页码 */
    private Integer pageNum;
    /** 每页条数 */
    private Integer pageSize;

}
