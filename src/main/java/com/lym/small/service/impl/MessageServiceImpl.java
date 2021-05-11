package com.lym.small.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lym.small.mapper.MessageMapper;
import com.lym.small.model.dto.MessageBaseDTO;
import com.lym.small.model.dto.MessageListDTO;
import com.lym.small.model.pojo.Message;
import com.lym.small.model.pojo.MessageExample;
import com.lym.small.model.pojo.User;
import com.lym.small.service.MessageService;
import com.lym.small.utils.ReturnUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: Lian
 * @time: 2021/5/11 16:49
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    /**
     * 获取留言列表
     * @param dto
     * @return
     */
    @Override
    public PageInfo<Message> queryMessageList(MessageListDTO dto) {
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        if (null != dto.getUserId()) {
            criteria.andUserIdEqualTo(dto.getUserId());
        }
        if (null != dto.getPicId()) {
            criteria.andPicIdEqualTo(dto.getPicId());
        }
        if (StringUtils.isNotBlank(dto.getLabel())) {
            criteria.andLabelLike("%" + dto.getLabel() + "%");
        }
        if (StringUtils.isNotBlank(dto.getContent())) {
            criteria.andContentLike("%" + dto.getContent() + "%");
        }
        if (StringUtils.isNotBlank(dto.getType())) {
            criteria.andTypeEqualTo(dto.getType());
        }
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<Message> messages = messageMapper.selectByExample(example);
        PageInfo<Message> pageInfo = new PageInfo<Message>(messages);
        return pageInfo;
    }

    /**
     * 添加留言
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> addMessage(MessageBaseDTO dto) {
        Message message = new Message();
        BeanUtils.copyProperties(dto, message);
        // 此处添加当前登录用户id
        // message.setUserId();
        message.setUserId(1);
        messageMapper.insertSelective(message);
        return ReturnUtil.success(new HashMap<>(), "添加成功");
    }

    /**
     * 编辑留言
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> updateMessage(MessageBaseDTO dto) {
        Message message = new Message();
        BeanUtils.copyProperties(dto, message);
        // 此处添加当前登录用户id
        // message.setUserId();
        message.setUserId(1);
        Message mess = messageMapper.selectByPrimaryKey(message.getId());
        if (mess == null) {
            return ReturnUtil.error(new HashMap<>(), "该留言不存在");
        }
        messageMapper.updateByPrimaryKeySelective(message);
        return ReturnUtil.success(new HashMap<>(), "修改成功");
    }

    /**
     * 删除留言
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> deleteMessage(MessageBaseDTO dto) {
        Message mess = messageMapper.selectByPrimaryKey(dto.getId());
        if (mess == null) {
            return ReturnUtil.error(new HashMap<>(), "该留言不存在");
        }
        messageMapper.deleteByPrimaryKey(dto.getId());
        return ReturnUtil.success(new HashMap<>(), "删除成功");
    }
}
