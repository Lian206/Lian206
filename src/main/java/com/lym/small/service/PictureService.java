package com.lym.small.service;

import com.github.pagehelper.PageInfo;
import com.lym.small.model.dto.PictureBaseDTO;
import com.lym.small.model.dto.PictureListDTO;
import com.lym.small.model.pojo.Picture;

import java.util.Map;

/**
 * @desc: User Service
 * @author: Lian
 * @time: 2021/5/8 14:32
 */
public interface PictureService {

    /**
     * 查图片列表
     * @param dto
     * @return
     */
    PageInfo<Picture> queryPictureList(PictureListDTO dto);

    /**
     * 删除图片
     * @param dto
     * @return
     */
    Map<String, Object> deletePicture(PictureBaseDTO dto);

}
