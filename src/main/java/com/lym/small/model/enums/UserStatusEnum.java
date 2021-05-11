package com.lym.small.model.enums;

/**
 * @desc: 用户状态枚举
 * @author: Lian
 * @time: 2021/5/10 13:51
 */
public enum UserStatusEnum {

    /** 正常 */
    NORMAL("1", "正常"),
    /** 失效 */
    INVALID("0", "失效");

    private String code;
    private String name;

    UserStatusEnum(String code ,String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getName(String code) {
        for (UserStatusEnum ele : UserStatusEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele.getName();
            }
        }
        return null;
    }



}
