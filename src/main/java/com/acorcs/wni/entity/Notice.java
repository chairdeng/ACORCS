package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 邓超 on 2016/12/9.
 */
@Data
public class Notice implements Serializable {
    @JsonIgnore
    private long id;
    private String type;
    private String elem;
    private String dataname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date basetime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validtime;
    @JsonIgnore
    private String json;
}
