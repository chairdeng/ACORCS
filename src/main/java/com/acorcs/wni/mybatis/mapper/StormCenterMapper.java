package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.StormCenter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
public interface StormCenterMapper extends WniEntityMapper<StormCenter> {
    @Insert("INSERT INTO wni_storm_center (notice_id,header,storm_name,type,geographic,original) " +
            "VALUES (#{noticeId},#{header},#{stormName},#{type},GeomFromText(#{geographic},4326),#{original})")
    @SelectKey(keyProperty = "id",resultType = long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    public int save(StormCenter stormCenter);
    @Select("select id,notice_id,header,storm_name,AsBinary(geographic) as geographic,original from wni_storm_name where notice_id=#{noticeId}")
    @Results({
            @Result(property = "geographic",column = "geographic",jdbcType = JdbcType.BLOB,javaType = com.vividsolutions.jts.geom.Point.class),
            @Result(property = "notice",column = "notice_id",one = @One(select = "com.acorcs.wni.mybatis.mapper.NoticeMapper.getNotice")),
            @Result(property = "noticeId",column = "notice_id")
    })
    public List<StormCenter> findByNoticeId(@Param("noticeId") Long noticeId);
}
