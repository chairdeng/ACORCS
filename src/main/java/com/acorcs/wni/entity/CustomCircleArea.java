package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import java.util.Date;

/**
 * Created by dengc on 2017/2/18.
 */
@Data
public class CustomCircleArea {

    private String code;
    private Short level;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date basetime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validtime;
    //圆心
    private Point center;
    //半径
    private Double radius;
}
