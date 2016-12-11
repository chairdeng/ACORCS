package com.acorcs.wni.entity;

import lombok.Data;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
public abstract class WniEntity {
    private long id;
    private String header;
    private long noticeId;
    private Notice notice;
    private String original;
}
