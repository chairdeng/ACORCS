package com.acorcs.wni.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 邓超 on 2016/12/9.
 */
@Data
public class Notice implements Serializable {
    private long id;
    private String type;
    private String elem;
    private String dataname;
    private Date updated;
    private Date basetime;
    private Date validtime;
    private String json;
}
