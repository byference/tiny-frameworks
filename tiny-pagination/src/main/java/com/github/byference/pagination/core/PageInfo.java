package com.github.byference.pagination.core;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PageInfo
 *
 * @author byference
 * @since 2019-08-24
 */
@Data
public class PageInfo<E> implements Serializable {

    private static final long serialVersionUID = 4775409201177752808L;

    /**
     * 页码，从1开始
     */
    private int pageNum;


    /**
     * 页面大小
     */
    private int pageSize;


    /**
     * 总数
     */
    private long total;


    /**
     * 结果集
     */
    private List<E> list;

    public PageInfo() {
    }

    public PageInfo(List<E> list) {

        this.list = list;
        if (list instanceof Page) {

            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
        } else {

            this.total = list.size();
        }
    }


    public static <E> PageInfo<E> of(List<E> list) {
        return new PageInfo<>(list);
    }

}
