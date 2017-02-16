package com.acorcs.wni.resolver;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by 邓超 on 2016/12/12.
 */
@Component
@Logger
@CacheConfig(cacheNames="resolvers")
public class ResolverConfig {
    @Value("classpath:config/resolver_config.xml")
    private Resource resolverConfigFile;

    private Element root;
    @PostConstruct
    public void init() throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(resolverConfigFile.getInputStream());
        root = doc.getRootElement();
    }
    @Cacheable(key="#header+'@'+#contentsKind")
    public Class getResolverClass(String header,String contentsKind){
        List<Element> resolvers = root.elements("resolver");
        for(Element resolver:resolvers){
            String _header = resolver.elementText("header");
            String _contentsKind = resolver.elementText("contentsKind");
            if(header.equals(_header) && contentsKind.equals(_contentsKind)){
                String clazz = resolver.elementText("class");
                if(clazz!=null){
                    try {
                        return Class.forName(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
