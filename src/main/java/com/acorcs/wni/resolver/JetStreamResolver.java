package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.JetStream;
import com.acorcs.wni.mybatis.mapper.JetStreamMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 邓超 on 2016/12/12.
 */
@Component
@Logger
public class JetStreamResolver implements IResolver<JetStream> {
    @Autowired
    private JetStreamMapper jetStreamMapper;
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private GeometryFactory geometryFactory = new GeometryFactory();
    @Override
    public JetStream resolve(String json) {
        JetStream jetStream = new JetStream();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        JsonArray points = jsonObject.get("points").getAsJsonArray();
        jetStream.setOriginal(points.toString());
        Coordinate[] coordinates = new Coordinate[points.size()];
        for(int i=0;i<points.size();i++){
            JsonElement point = points.get(i);
            Coordinate coordinate = new Coordinate(point.getAsJsonArray().get(0).getAsDouble(),point.getAsJsonArray().get(1).getAsDouble());
            coordinates[i] = coordinate;
        }
        LineString lineString = geometryFactory.createLineString(coordinates);
        jetStream.setGeographic(lineString);
        return jetStream;
    }

    @Override
    public WniEntityMapper<JetStream> getMapper() {
        return jetStreamMapper;
    }
}
