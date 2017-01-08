package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vividsolutions.jts.geom.Polygon;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public class Turbulence extends WniEntity<Polygon> implements Serializable {
    private int extendedDegree;
    private Integer[] altitudes;

}
