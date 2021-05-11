package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @desc: 获取所有图片 请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PictureListReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 图片名称 */
    private String picName;
    /** 图片类型 */
    private String type;
    /** 用户id */
    private Integer userId;

    /** 当前页码 */
    @NotNull(message = "页码不能为空")
    private Integer pageNum;
    /** 每页条数 */
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
