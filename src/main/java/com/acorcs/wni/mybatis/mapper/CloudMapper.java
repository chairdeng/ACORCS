package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Cloud;
import com.acorcs.wni.mybatis.mapper.provider.CloudProvider;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 邓超 on 2016/12/9.
 */
@CacheNamespace(size = 2048)
public interface CloudMapper {
    @InsertProvider(type = CloudProvider.class,method = "insertSql")
    public int save(@Param("cloud") Cloud cloud);
}
