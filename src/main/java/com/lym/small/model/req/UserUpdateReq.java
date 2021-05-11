package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @desc: 用户信息修改请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Integer id;
    /** 用户名 */
    private String userName;
    /** 性别 */
    private String sex;
    /** 手机号 */
    private String phone;
    /** 地址 */
    private String address;
    /** 用户标签 */
    private String label;


}
