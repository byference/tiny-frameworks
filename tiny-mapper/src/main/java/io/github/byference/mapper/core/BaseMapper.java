package io.github.byference.mapper.core;

import java.io.Serializable;

/**
 * BaseMapper
 *
 * @author byference
 * @since 2020-08-29
 */
public interface BaseMapper<T> {

    int insert(T entity);

    int updateById(T entity);

    int deleteById(Serializable id);

    T findById(Serializable id);
}
