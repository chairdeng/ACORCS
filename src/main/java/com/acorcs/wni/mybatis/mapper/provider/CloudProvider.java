package com.acorcs.wni.mybatis.mapper.provider;


import com.acorcs.wni.entity.Cloud;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 邓超 on 2016/12/9.
 */
public class CloudProvider {
    public String insertSql(final Cloud cloud){
        return new SQL(){{
            INSERT_INTO("wni_cloud_${cloud.header}");
            VALUES("notice_id,cloud_distribution","#{cloud.notice.id},#{cloud.cloudDistribution}");
            VALUES("cloud_type,altitudes","#{cloud.cloudType},#{cloud.altitudes}");
            if(cloud.getHeader().toLowerCase().equals("june00"))
                VALUES("airframe_icing,extended_degree","#{cloud.airframeIcing},#{cloud.extendedDegree}");
            VALUES("geographic,wkt","ST_GeomFromText('#{cloud.wkt}'),#{cloud.wkt}");

        }}.toString();
    }
}
