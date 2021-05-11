package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @desc: 获取留言列表 请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageListReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Integer userId;
    /** 图片id */
    private Integer picId;
    /** 留言标签名 */
    private String label;
    /** 留言类型 */
    private String type;
    /** 留言内容 */
    private String content;

    /** 当前页码 */
    @NotNull(message = "页码不能为空")
    private Integer pageNum;
    /** 每页条数 */
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
