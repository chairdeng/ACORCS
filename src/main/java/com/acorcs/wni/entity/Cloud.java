package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vividsolutions.jts.geom.Polygon;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by 邓超 on 2016/12/9.
 */
@Data
public class Cloud extends WniEntity<Polygon> implements Serializable {

    private String cloudDistribution;
    private String cloudType;
    private int altitudes;
    private String airframeIcing;
    private int extendedDegree;

    @Override
    public String getContentsKind() {
        return "CLOUD";
    }
}
