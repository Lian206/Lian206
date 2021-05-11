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
public class MessageAddReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 留言标签名 */
    @NotBlank(message = "留言标签名不能为空")
    private String label;
    /** 留言类型 */
    private String type;
    /** 留言内容 */
    @NotBlank(message = "留言内容不能为空")
    private String content;

}
