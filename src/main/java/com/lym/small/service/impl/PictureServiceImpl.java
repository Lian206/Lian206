package com.lym.small.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.small.mapper.PictureMapper;
import com.lym.small.model.dto.PictureBaseDTO;
import com.lym.small.model.dto.PictureListDTO;
import com.lym.small.model.pojo.Picture;
import com.lym.small.model.pojo.PictureExample;
import com.lym.small.service.PictureService;
import com.lym.small.utils.ReturnUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc: picture Service 业务层
 * @author: Lian
 * @time: 2021/5/11 16:01
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Resource
    private PictureMapper pictureMapper;

    /**
     * 获取所有图片
     * @param dto
     * @return
     */
    @Override
    public PageInfo<Picture> queryPictureList(PictureListDTO dto) {
        PictureExample example = new PictureExample();
        PictureExample.Criteria criteria = example.createCriteria();
        if (null != dto.getUserId()) {
            criteria.andUserIdEqualTo(dto.getUserId());
        }
        if (StringUtils.isNotBlank(dto.getType())) {
            criteria.andTypeEqualTo(dto.getType());
        }
        if (StringUtils.isNotBlank(dto.getPicName())) {
            criteria.andPicNameLike("%" + dto.getPicName() + "%");
        }
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Picture> pictures = pictureMapper.selectByExample(example);
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(pictures);
        return pageInfo;
    }

    /**
     * 删除图片
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> deletePicture(PictureBaseDTO dto) {
        Picture picture = pictureMapper.selectByPrimaryKey(dto.getId());
        if (picture == null) {
            return ReturnUtil.error(new HashMap<>(), "未找到该图片");
        }
        pictureMapper.deleteByPrimaryKey(dto.getId());
        return ReturnUtil.success(new HashMap<>(), "删除成功");
    }
}
