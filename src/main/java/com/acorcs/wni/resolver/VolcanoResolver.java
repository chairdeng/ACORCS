package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Volcano;
import com.acorcs.wni.mybatis.mapper.VolcanoMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class VolcanoResolver implements IResolver<Volcano> {
    @Autowired
    private VolcanoMapper volcanoMapper;
    @Override
    public Volcano resolve(String json) {
        return null;
    }

    @Override
    public WniEntityMapper<Volcano> getMapper() {
        return volcanoMapper;
    }
}
