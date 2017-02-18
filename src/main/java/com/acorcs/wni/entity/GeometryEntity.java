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
 * Created by dengchao5 on 2017/2/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class GeometryEntity<T extends Geometry> {
    @JsonIgnore
    private Long id;
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private T geographic;

    public abstract String getContentsKind();
}
