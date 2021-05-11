package com.lym.small.utils;

import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 返回响应工具类
 * @author: Lian
 * @time: 2021/5/8 14:50
 */
@Data
public class ResultUtil<T> {
    private String msg;
    private String code;

    public static Map<String, String> resultResp( String code, String msg) {
        Map map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        return map;
    }

}
