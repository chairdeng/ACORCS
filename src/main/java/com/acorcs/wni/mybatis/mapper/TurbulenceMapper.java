package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Tropopause;
import com.acorcs.wni.entity.Turbulence;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
@CacheConfig(cacheNames = "wni_turbulence")
public interface TurbulenceMapper  extends WniEntityMapper<Turbulence> {
    @Insert("INSERT INTO wni_turbulence (notice_id,header,extended_degree,altitudes,geographic)" +
            " VALUES (#{noticeId},#{header},#{extendedDegree}," +
            "#{altitudes},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = Long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    Long save(Turbulence turbulence);

    @Cacheable(key = "#noticeId")
    @Select("SELECT id,notice_id,header,extended_degree,altitudes,AsBinary(geographic) as geographic FROM wni_turbulence WHERE notice_id=#{noticeId}")
    @Results({
            @Result(property = "notice",column = "notice_id",one = @One(select = "com.acorcs.wni.mybatis.mapper.NoticeMapper.getNotice")),
            @Result(property = "noticeId",column = "notice_id"),
            @Result(property = "extendedDegree",column = "extended_degree")
    })
    List<Turbulence> findByNoticeId(Long noticeId);
}
