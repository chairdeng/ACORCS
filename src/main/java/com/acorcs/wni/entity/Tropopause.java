package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vividsolutions.jts.geom.MultiPoint;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public class Tropopause extends WniEntity<MultiPoint> implements Serializable {

    private String significance;

    private Integer[] altitudes;
}
