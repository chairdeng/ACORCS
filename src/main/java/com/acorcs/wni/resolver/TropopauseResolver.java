package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Tropopause;
import com.acorcs.wni.mybatis.mapper.TropopauseMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class TropopauseResolver implements IResolver<Tropopause>{
    @Autowired
    private TropopauseMapper tropopauseMapper;
    @Override
    public Tropopause resolve(String json) {
        return null;
    }

    @Override
    public WniEntityMapper<Tropopause> getMapper() {
        return tropopauseMapper;
    }
}
