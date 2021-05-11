package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @desc: 用户新增请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAddReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /** 性别 */
    @NotBlank(message = "性别不能为空")
    private String sex;
    /** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;
    /** 手机号 */
    private String phone;
    /** 地址 */
    private String address;
    /** 用户标签 */
    private String label;


}
