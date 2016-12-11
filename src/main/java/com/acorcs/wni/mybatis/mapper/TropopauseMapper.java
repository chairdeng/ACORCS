package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Tropopause;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

/**
 * Created by dengc on 2016/12/11.
 */
public interface TropopauseMapper  extends WniEntityMapper<Tropopause> {
    @Insert("INSERT INTO wni_tropopause (notice_id,header,significance,geographic,original) " +
            "VALUES (#{noticeId},#{header},#{significance},GeomFromText(#{geographic},4326),#{original})")
    @SelectKey(keyProperty = "id",resultType = long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    public int save(Tropopause tropopause);
}
