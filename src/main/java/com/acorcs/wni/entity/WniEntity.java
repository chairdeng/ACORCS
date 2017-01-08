package com.acorcs.wni.entity;

import com.acorcs.wni.web.serializer.GeometryDeserializer;
import com.acorcs.wni.web.serializer.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;

/**
 * Created by dengc on 2016/12/11.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class WniEntity<T extends Geometry> {
    @JsonIgnore
    private long id;
    private String header;
    @JsonIgnore
    private long noticeId;
    private Notice notice;

    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private T geographic;

    public abstract String getContentsKind();
}
