package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Volcano;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
@CacheConfig(cacheNames = "wni_volcano")
public interface VolcanoMapper  extends WniEntityMapper<Volcano> {
    @Insert("INSERT INTO wni_volcano (notice_id,header,feature_name,time_significance,special_clouds,geographic) " +
            "VALUES (#{noticeId},#{header},#{featureName},#{timeSignificance},#{specialClouds},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = Long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    Long save(Volcano volcano);

    @Cacheable(key = "#noticeId")
    @Select("SELECT id,notice_id,header,feature_name,time_significance,special_clouds,AsBinary(geographic) as geographic FROM wni_volcano WHERE notice_id=#{noticeId}")
    @Results({
            @Result(property = "notice",column = "notice_id",one = @One(select = "com.acorcs.wni.mybatis.mapper.NoticeMapper.getNotice")),
            @Result(property = "noticeId",column = "notice_id")
    })
    List<Volcano> findByNoticeId(Long noticeId);
}
