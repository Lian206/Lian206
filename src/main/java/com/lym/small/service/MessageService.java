package com.lym.small.service;

import com.github.pagehelper.PageInfo;
import com.lym.small.model.dto.MessageBaseDTO;
import com.lym.small.model.dto.MessageListDTO;
import com.lym.small.model.pojo.Message;

import java.util.Map;

/**
 * @desc: message Service
 * @author: Lian
 * @time: 2021/5/11 16:44
 */
public interface MessageService {

    /**
     * 查留言列表
     * @param dto
     * @return
     */
    PageInfo<Message> queryMessageList(MessageListDTO dto);

    /**
     * 添加留言
     * @param dto
     * @return
     */
    Map<String, Object> addMessage(MessageBaseDTO dto);

    /**
     * 编辑留言
     * @param dto
     * @return
     */
    Map<String, Object> updateMessage(MessageBaseDTO dto);

    /**
     * 删除留言
     * @param dto
     * @return
     */
    Map<String, Object> deleteMessage(MessageBaseDTO dto);

}
