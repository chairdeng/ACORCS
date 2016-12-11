package com.acorcs.wni.entity;

import com.vividsolutions.jts.geom.MultiPoint;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public class Tropopause extends WniEntity implements Serializable {

    private String significance;
    private MultiPoint geographic;
}
