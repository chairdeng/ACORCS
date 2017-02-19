package com.acorcs.wni.web.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.vividsolutions.jts.geom.Geometry;
import org.geotools.geojson.geom.GeometryJSON;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by dengc on 2017/1/8.
 */
public class GeometryDeserializer extends JsonDeserializer<Geometry> {
    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        StringBuffer json = new StringBuffer();
        while (currentToken != JsonToken.END_OBJECT){
            json.append(jsonParser.getText());
            currentToken = jsonParser.nextToken();
        }
        System.out.println(json);
        GeometryJSON geoJson = new GeometryJSON();
        Reader reader = new StringReader(json.toString());
        Geometry geometry = geoJson.read(reader);
        geometry.setSRID(4326);
        jsonParser.nextToken();
        return geometry;
    }
}
