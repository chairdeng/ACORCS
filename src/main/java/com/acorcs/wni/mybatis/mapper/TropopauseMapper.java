package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Tropopause;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
@CacheConfig(cacheNames = "wni_tropopause")
public interface TropopauseMapper  extends WniEntityMapper<Tropopause> {
    @Insert("INSERT INTO wni_tropopause (notice_id,header,significance,altitudes,geographic) " +
            "VALUES (#{noticeId},#{header},#{significance},#{altitudes},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = Long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    Long save(Tropopause tropopause);

    @Cacheable(key = "#noticeId")
    @Select("SELECT id,notice_id,header,significance,altitudes,AsBinary(geographic) as geographic FROM wni_tropopause WHERE notice_id=#{noticeId}")
    @Results({
            @Result(property = "notice",column = "notice_id",one = @One(select = "com.acorcs.wni.mybatis.mapper.NoticeMapper.getNotice")),
            @Result(property = "noticeId",column = "notice_id")
    })
    List<Tropopause> findByNoticeId(Long noticeId);
}
