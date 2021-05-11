package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @desc: 用户列表请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserListReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String userName;
    /** 用户标签 */
    private String label;

    /** 当前页码 */
    @NotNull(message = "页码不能为空")
    private Integer pageNum;
    /** 每页条数 */
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
