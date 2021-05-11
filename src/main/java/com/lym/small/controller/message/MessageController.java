package com.lym.small.controller.message;

import com.github.pagehelper.PageInfo;
import com.lym.small.model.dto.MessageBaseDTO;
import com.lym.small.model.dto.MessageListDTO;
import com.lym.small.model.pojo.Message;
import com.lym.small.model.pojo.Picture;
import com.lym.small.model.req.*;
import com.lym.small.model.resp.ListResp;
import com.lym.small.service.MessageService;
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
 * @desc: 留言 Controller
 * @author: Lian
 * @time: 2021/5/11 16:57
 */
@Api(value="留言Controller")
@Slf4j
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 留言列表查询
     * @param reqModel
     * @return
     */
    @ApiOperation(value = "获取留言列表", notes = "通过userId、picId、留言标签名、留言类型、留言内容模糊查询留言列表")
    @PostMapping(value = "/message/queryMessageList")
    public ListResp queryMessageList(@RequestBody @Validated MessageListReq reqModel){
        log.info("收到请求开始：[获取留言列表][/message/queryMessageList:"+reqModel.toString());
        MessageListDTO dto = new MessageListDTO();
        BeanUtils.copyProperties(reqModel, dto);
        PageInfo<Message> messages = messageService.queryMessageList(dto);
        // 返回结果
        ListResp resp = new ListResp();
        resp.setList(new ArrayList<Picture>());
        resp.setTotalRows((int) messages.getTotal());
        resp.setPageNum(messages.getPageNum());
        resp.setPageSize(messages.getPageSize());
        resp.setPages(messages.getPages());
        for (Message item: messages.getList()) {
            Message picture = new Message();
            BeanUtils.copyProperties(item, picture);
            resp.getList().add(picture);
        }
        log.info("请求处理结束：[获取留言列表]" + resp);
        return resp;
    }

    /**
     * 添加留言
     * @param reqModel
     */
    @ApiOperation(value = "添加留言", notes = "留言标签、留言内容为必填")
    @PostMapping(value = "/message/addMessage")
    public Map<String, Object> addMessage(@RequestBody @Validated MessageAddReq reqModel){
        log.info("收到请求开始：[添加留言][/message/addMessage:" + reqModel.toString());
        MessageBaseDTO dto = new MessageBaseDTO();
        BeanUtils.copyProperties(reqModel, dto);
        Map<String, Object> resultMap = messageService.addMessage(dto);
        log.info("请求处理结束：[添加留言][/user/addUser:" + resultMap);
        return resultMap;
    }

    /**
     * 修改留言
     * @param reqModel
     */
    @ApiOperation(value = "修改留言", notes = "")
    @PostMapping(value = "/message/updateMessage")
    public Map<String, Object> updateMessage(@RequestBody @Validated MessageUpdateReq reqModel){
        log.info("收到请求开始：[修改留言][/message/addMessage:" + reqModel.toString());
        MessageBaseDTO dto = new MessageBaseDTO();
        BeanUtils.copyProperties(reqModel, dto);
        Map<String, Object> resultMap = messageService.updateMessage(dto);
        log.info("请求处理结束：[修改留言][/user/addUser:" + resultMap);
        return resultMap;
    }

    /**
     * 删除留言
     * @param reqModel
     */
    @ApiOperation(value = "删除留言", notes = "")
    @PostMapping(value = "/message/deleteMessage")
    public Map<String, Object> deleteMessage(@RequestBody @Validated MessageDeleteReq reqModel){
        log.info("收到请求开始：[删除留言][/message/deleteMessage:" + reqModel.toString());
        MessageBaseDTO dto = new MessageBaseDTO();
        BeanUtils.copyProperties(reqModel, dto);
        Map<String, Object> resultMap = messageService.deleteMessage(dto);
        log.info("请求处理结束：[删除留言][/user/addUser:" + resultMap);
        return resultMap;
    }

}
