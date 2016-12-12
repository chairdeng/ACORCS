package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.StormCenter;
import com.acorcs.wni.mybatis.mapper.StormCenterMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class StormCenterResolver implements IResolver<StormCenter> {
    @Autowired
    private StormCenterMapper stormCenterMapper;
    @Override
    public StormCenter resolve(String json) {
        return null;
    }

    @Override
    public WniEntityMapper<StormCenter> getMapper() {
        return stormCenterMapper;
    }
}
