package com.lym.small.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @desc: MessageBaseDTO
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 留言id */
    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 图片id */
    private Integer picId;
    /** 留言标签 */
    private String label;
    /** 留言内容 */
    private String content;
    /** 留言类型 */
    private String type;


}