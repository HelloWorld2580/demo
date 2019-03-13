/*
 * <p>版权所有: 版权所有 (C) 2014-2024</p>
 * <p>公    司: 北京帮付宝网络科技有限公司</p>
 *
 * ListRespDto.java
 *
 * 创建时间: 2015年8月5日 下午3:29:13
 *
 * 作者: 黄祖真 (工号: AB045870)
 *
 * 功能描述：
 * 翻页列表DTO
 *
 * 版本：1.0.0
 */
package com.dhcc.ms.utils.dto;

import java.util.List;

public class ListRespDto<T> {

    private List<T> list;// 数据列表
    private long totalCount;// 记录总条数

    public ListRespDto () {
    }
    public ListRespDto (List<T> list,long total) {
    	this.list = list;
    	this.totalCount = total;
    }
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
