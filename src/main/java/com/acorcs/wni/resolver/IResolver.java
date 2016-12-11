package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.WniEntity;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;

/**
 * Created by 邓超 on 2016/12/9.
 */
public interface IResolver<T extends WniEntity> {
    public T resolve(String json);
    public WniEntityMapper<T> getMapper();
}
