package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.JetStream;
import com.acorcs.wni.mybatis.mapper.JetStreamMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 邓超 on 2016/12/12.
 */
@Component
@Logger
public class JetStreamResolver implements IResolver<JetStream> {
    @Autowired
    private JetStreamMapper jetStreamMapper;
    @Override
    public JetStream resolve(String json) {
        return null;
    }

    @Override
    public WniEntityMapper<JetStream> getMapper() {
        return jetStreamMapper;
    }
}
