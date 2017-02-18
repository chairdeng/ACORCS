package com.acorcs.wni.mybatis.mapper;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
public interface WniEntityMapper<T> {
    Long save(T t);

    List<T> findByNoticeId(long noticeId);
}
