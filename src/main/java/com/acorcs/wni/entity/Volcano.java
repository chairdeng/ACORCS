package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public class Volcano extends WniEntity<Point> implements Serializable {
    private String featureName;
    private String timeSignificance;
    private String specialClouds;

}
