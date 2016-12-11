package com.acorcs.wni.entity;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public class Volcano extends WniEntity implements Serializable {
    private String featureName;
    private String timeSignificance;
    private String specialClouds;
    private Point geographic;

}
