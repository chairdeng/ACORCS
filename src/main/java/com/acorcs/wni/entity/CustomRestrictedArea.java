package com.acorcs.wni.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.Polygon;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by dengc on 2017/2/18.
 */
@Data
public class CustomRestrictedArea extends GeometryEntity<Polygon> {
    @NotBlank
    private String code;
    @Max(20)
    private Integer level = 1;
    @NotNull(message="basetime.notnull.message")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date basetime;
    @NotNull(message="{validtime.notnull.message}")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validtime;

    @Override
    public String getContentsKind() {
        return "CUSTOM";
    }
}
