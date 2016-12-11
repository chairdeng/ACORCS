package com.acorcs.wni.resolver;

/**
 * Created by 邓超 on 2016/12/9.
 */
public class ResolverFactory {
    public static IResolver getResolver(String header,String contentsKind){
        return new CloudResolver();
    }
}
