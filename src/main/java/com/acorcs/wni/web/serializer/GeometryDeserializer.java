package com.acorcs.wni.web.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vividsolutions.jts.geom.Geometry;
import org.geotools.geojson.geom.GeometryJSON;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by dengc on 2017/1/8.
 */
public class GeometryDeserializer extends JsonObjectDeserializer<Geometry> {

    @Override
    protected Geometry deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {
        String json = jsonNode.toString();
        GeometryJSON geoJson = new GeometryJSON();
        Reader reader = new StringReader(json);
        Geometry geometry = geoJson.read(reader);
        geometry.setSRID(4326);
        return geometry;
    }
}
