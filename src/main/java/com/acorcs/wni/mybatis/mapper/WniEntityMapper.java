package com.acorcs.wni.mybatis.mapper;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
public interface WniEntityMapper<T> {
    public int save(T t);
    public List<T> findByNoticeId(long noticeId);
}
