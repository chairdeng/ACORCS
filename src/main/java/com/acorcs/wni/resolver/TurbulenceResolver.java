package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Turbulence;
import com.acorcs.wni.mybatis.mapper.TurbulenceMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class TurbulenceResolver implements IResolver<Turbulence> {
    @Autowired
    private TurbulenceMapper turbulenceMapper;
    @Override
    public Turbulence resolve(String json) {
        return null;
    }

    @Override
    public WniEntityMapper<Turbulence> getMapper() {
        return turbulenceMapper;
    }
}
