package com.lym.small.controller.picture;

import com.github.pagehelper.PageInfo;
import com.lym.small.model.dto.PictureBaseDTO;
import com.lym.small.model.dto.PictureListDTO;
import com.lym.small.model.pojo.Picture;
import com.lym.small.model.req.PictureDeleteReq;
import com.lym.small.model.req.PictureListReq;
import com.lym.small.model.resp.ListResp;
import com.lym.small.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * @desc: 图片 Controller
 * @author: Lian
 * @time: 2021/5/11 15:55
 */
@Api(value="图片Controller")
@Slf4j
@RestController
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * 图片列表查询
     * @param reqModel
     * @return
     */
    @ApiOperation(value = "获取图片列表", notes = "通过图片名称、图片类型、UserId模糊查询所有图片")
    @PostMapping(value = "/picture/queryPictureList")
    public ListResp queryPictureList(@RequestBody @Validated PictureListReq reqModel){
        log.info("收到请求开始：[获取图片列表][/oicture/queryPictureList:"+reqModel.toString());
        PictureListDTO dto = new PictureListDTO();
        BeanUtils.copyProperties(reqModel, dto);
        PageInfo<Picture> pictures = pictureService.queryPictureList(dto);
        // 返回结果
        ListResp resp = new ListResp();
        resp.setList(new ArrayList<Picture>());
        resp.setTotalRows((int) pictures.getTotal());
        resp.setPageNum(pictures.getPageNum());
        resp.setPageSize(pictures.getPageSize());
        resp.setPages(pictures.getPages());
        for (Picture item: pictures.getList()) {
            Picture picture = new Picture();
            BeanUtils.copyProperties(item, picture);
            resp.getList().add(picture);
        }
        log.info("请求处理结束：[获取图片列表]" + resp);
        return resp;
    }

    /**
     * 删除图片
     * @param reqModel
     */
    @ApiOperation(value = "删除图片", notes = "")
    @PostMapping(value = "/picture/deletePicture")
    public Map<String, Object> deleteUser(@RequestBody @Validated PictureDeleteReq reqModel){
        log.info("收到请求开始：[删除图片][/picture/deletePicture:" + reqModel.toString());
        PictureBaseDTO dto = new PictureBaseDTO();
        dto.setId(reqModel.getId());
        Map<String, Object> resultMap = pictureService.deletePicture(dto);
        log.info("请求处理结束：[删除图片][/user/deleteUser:" + resultMap);
        return resultMap;
    }

}
