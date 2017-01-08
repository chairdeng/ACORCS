package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.JetStream;
import com.acorcs.wni.entity.StormCenter;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
@CacheConfig(cacheNames = "wni_jet_stream")
public interface JetStreamMapper  extends WniEntityMapper<JetStream> {
    @Insert("INSERT INTO wni_jet_stream (notice_id,header,geographic) " +
            "VALUES (#{noticeId},#{header},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    public int save(JetStream jetStream);

    @Cacheable(key = "#noticeId")
    @Select("select id,notice_id,header,AsBinary(geographic) as geographic from wni_jet_stream where notice_id=#{noticeId}")
    @Results({
            @Result(property = "notice",column = "notice_id",one = @One(select = "com.acorcs.wni.mybatis.mapper.NoticeMapper.getNotice")),
            @Result(property = "noticeId",column = "notice_id")
    })
    public List<JetStream> findByNoticeId(Long noticeId);
}
