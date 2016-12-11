package com.acorcs.wni.entity;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public class StormCenter extends WniEntity implements Serializable {

    private String stormName;
    private String type;
    private Point geographic;

}
