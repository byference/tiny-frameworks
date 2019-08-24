package com.github.byference.pagination.core;

import java.util.ArrayList;

/**
 * Page
 *
 * @author byference
 * @since 2019-08-24
 */
public class Page<E> extends ArrayList<E> {

    private static final long serialVersionUID = 8683452581122892189L;

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


    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Page(int pageNum, int pageSize, long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                '}';
    }
}
