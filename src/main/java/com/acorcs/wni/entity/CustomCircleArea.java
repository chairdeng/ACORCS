package com.acorcs.wni.entity;

import com.acorcs.wni.web.serializer.GeometryDeserializer;
import com.acorcs.wni.web.serializer.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by dengc on 2017/2/18.
 */
@Data
public class CustomCircleArea {
    @NotBlank
    @Size(min = 1,max = 32)
    private String code;
    @Max(20)
    @Min(1)
    private Integer level = 1;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date basetime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date validtime;
    //圆心
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    @NotNull
    private Point center;
    //半径
    @Min(0)
    @NotNull
    private Double radius;
}
