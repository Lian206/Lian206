package com.lym.small.model.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @desc: 图片删除请求
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PictureDeleteReq implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 图片id */
    private Integer id;

}
