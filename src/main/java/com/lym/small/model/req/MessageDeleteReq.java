package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @desc: 添加留言 请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageDeleteReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 留言id */
    @NotNull(message = "留言id不能为空")
    private Integer id;

}
