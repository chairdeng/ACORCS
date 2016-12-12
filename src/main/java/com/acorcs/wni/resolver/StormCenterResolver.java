package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.StormCenter;
import com.acorcs.wni.mybatis.mapper.StormCenterMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 邓超 on 2016/12/12.
 */
@Component
public class StormCenterResolver implements IResolver<StormCenter> {
    @Autowired
    private StormCenterMapper stormCenterMapper;
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private GeometryFactory geometryFactory = new GeometryFactory();
    @Override
    public StormCenter resolve(String json) {
        StormCenter stormCenter = new StormCenter();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        String stormName = jsonObject.get("storm_name").getAsString();
        stormCenter.setStormName(stormName);
        String type = jsonObject.get("type").getAsString();
        stormCenter.setType(type);
        JsonArray points = jsonObject.get("points").getAsJsonArray();
//        stormCenter.setOriginal(points.toString());
        JsonArray point = points.get(0).getAsJsonArray();
        stormCenter.setGeographic(geometryFactory.createPoint(new Coordinate(point.get(0).getAsDouble(),point.get(1).getAsDouble())));
        return stormCenter;
    }

    @Override
    public WniEntityMapper<StormCenter> getMapper() {
        return stormCenterMapper;
    }
}
