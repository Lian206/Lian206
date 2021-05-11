package com.lym.small.utils;

import java.util.Map;

/**
 * @desc: 返回通用工具类
 * @author: Lian
 * @time: 2021/5/11 11:16
 */
public class ReturnUtil {

    private ReturnUtil() {}

    /**
     *
     * @param map 容器
     * @param result 数据集
     * @param code MSG消息
     * @return
     */
    public static Map<String, Object> success(Map<String, Object> map, Object result, String  code) {
        //初始化Map
        {
            map.clear();
        }
        map.put(CommonRest.MSG,code);
        map.put(CommonRest.SUCCESS, Boolean.TRUE);
        map.put(CommonRest.DATA,result);
        return map;
    }

    /**
     *
     * @param map
     * @param code
     * @return
     */
    public static Map<String, Object> success(Map<String, Object> map, String  code) {
        //初始化Map
        {
            map.clear();
        }
        map.put(CommonRest.MSG,code);
        map.put(CommonRest.SUCCESS, Boolean.TRUE);
        return map;
    }

    /**
     *
     * @param map
     * @param code MSG消息
     * @return
     */
    public static Map<String, Object> error(Map<String, Object> map, String code){

        //初始化Map
        {
            map.clear();
        }
        map.put(CommonRest.MSG,code);
        map.put(CommonRest.SUCCESS, Boolean.FALSE);
        return map;

    }

}
