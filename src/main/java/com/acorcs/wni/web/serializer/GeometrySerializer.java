package com.acorcs.wni.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Geometry;
import org.geotools.geojson.geom.GeometryJSON;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengc on 2017/1/8.
 */
public class GeometrySerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        GeometryJSON geoJson = new GeometryJSON();
        StringWriter writer = new StringWriter();
        geoJson.write(geometry,writer);
        String json = writer.toString();
        ObjectMapper mapper = new ObjectMapper();
        HashMap geo = mapper.readValue(json, HashMap.class);

        jsonGenerator.writeObject(geo);
    }
}
