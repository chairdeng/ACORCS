package com.acorcs.wni.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by 邓超 on 2016/12/9.
 */
@Component
public class ResolverFactory {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ResolverConfig resolverConfig;
    public IResolver getResolver(String header,String contentsKind){
        Class clazz = resolverConfig.getResolverClass(header,contentsKind);
        if(clazz == null){
            return null;
        }
        return (IResolver)applicationContext.getBean(clazz);

    }
}
