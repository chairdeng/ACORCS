package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.StormCenter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
@CacheConfig(cacheNames = "wni_storm_center")
public interface StormCenterMapper extends WniEntityMapper<StormCenter> {
    @Insert("INSERT INTO wni_storm_center (notice_id,header,storm_name,type,geographic) " +
            "VALUES (#{noticeId},#{header},#{stormName},#{type},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    public int save(StormCenter stormCenter);

    @Cacheable(key = "#noticeId")
    @Select("select id,notice_id,header,storm_name,type,AsBinary(geographic) as geographic from wni_storm_center where notice_id=#{noticeId}")
    @Results({
            @Result(property = "notice",column = "notice_id",one = @One(select = "com.acorcs.wni.mybatis.mapper.NoticeMapper.getNotice")),
            @Result(property = "noticeId",column = "notice_id")
    })
    public List<StormCenter> findByNoticeId(Long noticeId);
}
