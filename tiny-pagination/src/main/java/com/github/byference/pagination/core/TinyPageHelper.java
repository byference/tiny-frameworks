package com.github.byference.pagination.core;

/**
 * TinyPageHelper
 *
 * @author byference
 * @since 2019-08-24
 */
public class TinyPageHelper {


    static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<>();


    /**
     * 开始分页
     */
    public static <E> Page<E> startPage(int pageNum, int pageSize) {

        Page<E> page = new Page<>(pageNum, pageSize);
        LOCAL_PAGE.set(page);
        return page;
    }


    /**
     * 获取 {@link Page} 参数
     */
    static Page getLocalPage() {
        return LOCAL_PAGE.get();
    }


    /**
     * 移除本地变量
     */
    public static void clearPage() {
        LOCAL_PAGE.remove();
    }

}