package com.lym.small.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * @desc: PictureBaseDTO
 * @author: Lian
 * @time: 2021/5/10 15:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PictureBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 图片id */
    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 图片名称 */
    private String picName;
    /** 图片地址 */
    private String address;
    /** 图片类型 */
    private String type;
    /** 图片热度 */
    private Integer hot;


}