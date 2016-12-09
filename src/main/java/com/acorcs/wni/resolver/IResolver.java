package com.acorcs.wni.resolver;

/**
 * Created by 邓超 on 2016/12/9.
 */
public interface IResolver<T> {
    public T resolve(Long noticeId,String json);
}
