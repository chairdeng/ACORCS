package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.entity.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.Date;
import java.util.List;

/**
 * Created by dengc on 2016/12/10.
 */
public interface NoticeMapper {
    @Insert("insert into wni_notice (type,elem,dataname,updated,basetime,validtime,json) " +
            "values (#{type},#{elem},#{dataname},#{updated},#{basetime},#{validtime},#{json})")
    @SelectKey(keyProperty = "id",resultType = Long.class,before = false,statement = "SELECT LAST_INSERT_ID() AS id")
    Long save(Notice notice);

    @Select("SELECT id,type,elem,dataname,updated,basetime,validtime FROM wni_notice WHERE id=#{noticeId}")
    Notice getNotice(long noticdId);

    @Select("SELECT id,type,elem,dataname,updated,basetime,validtime FROM wni_notice WHERE #{queryTime} BETWEEN basetime AND validtime")
    List<Notice> getValidNotice(Date queryTime);
}
