package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.JetStream;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

/**
 * Created by dengc on 2016/12/11.
 */
public interface JetStreamMapper  extends WniEntityMapper<JetStream> {
    @Insert("INSERT INTO wni_jet_stream (notice_id,header,geographic) " +
            "VALUES (#{noticeId},#{header},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    public int save(JetStream jetStream);
}
