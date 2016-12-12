package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Turbulence;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

/**
 * Created by dengc on 2016/12/11.
 */
public interface TurbulenceMapper  extends WniEntityMapper<Turbulence> {
    @Insert("INSERT INTO wni_turbulence (notice_id,header,extended_degree,altitudes,geographic)" +
            " VALUES (#{noticeId},#{header},#{extendedDegree}," +
            "#{altitudes},GeomFromText(#{geographic},4326))")
    @SelectKey(keyProperty = "id",resultType = long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    public int save(Turbulence turbulence);
}
