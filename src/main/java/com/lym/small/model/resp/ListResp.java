package com.lym.small.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: 数据 响应结果
 * @author: Lian
 * @time: 2021/5/10 15:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListResp<T> implements Serializable {

    /** 序列ID */
    private static final long serialVersionUID = 1L;

    /** 数据列表 */
    private List<T> list;
    /** 当前页码 */
    private int pageNum;
    /** 每页条数 */
    private int pageSize;
    /** 总条数 */
    private int totalRows;
    /** 总页数 */
    private int pages;

}
