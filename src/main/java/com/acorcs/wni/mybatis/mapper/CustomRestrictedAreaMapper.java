package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.CustomRestrictedArea;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.Date;
import java.util.List;

/**
 * Created by dengc on 2017/2/18.
 */
public interface CustomRestrictedAreaMapper {
    @Insert("INSERT INTO custom_restricted_area (code,level,geographic,basetime,validtime) " +
            "VALUES (#{code},#{level},GeomFromText(#{geographic},4326),#{basetime},#{validtime})")
    @SelectKey(keyProperty = "id",resultType = Long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    Long save(CustomRestrictedArea customRestrictedArea);
    @Select("SELECT id,code,level,AsBinary(geographic) as geographic,basetime,validtime FROM custom_restricted_area WHERE #{queryTime} BETWEEN basetime AND validtime")
    List<CustomRestrictedArea> findValid(Date queryTime);
}
