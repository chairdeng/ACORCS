package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.Polygon;
import lombok.Data;

import java.util.Date;

/**
 * Created by dengc on 2017/2/18.
 */
@Data
public class CustomRestrictedArea extends GeometryEntity<Polygon> {
    private String code;
    private Short level;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date basetime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validtime;

    @Override
    public String getContentsKind() {
        return "CUSTOM";
    }
}
