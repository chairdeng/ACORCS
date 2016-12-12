package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Volcano;
import com.acorcs.wni.mybatis.mapper.VolcanoMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class VolcanoResolver implements IResolver<Volcano> {
    @Autowired
    private VolcanoMapper volcanoMapper;
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private GeometryFactory geometryFactory = new GeometryFactory();
    @Override
    public Volcano resolve(String json) {
        Volcano volcano = new Volcano();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        String featureName = jsonObject.get("feature_name").getAsString();
        volcano.setFeatureName(featureName);
        String timeSignificance = jsonObject.get("time_significance").getAsString();
        volcano.setTimeSignificance(timeSignificance);
        String specialClouds = jsonObject.get("special_clouds").getAsString();
        volcano.setSpecialClouds(specialClouds);
        JsonArray points = jsonObject.get("points").getAsJsonArray();
        volcano.setOriginal(points.toString());
        JsonArray point = points.get(0).getAsJsonArray();
        volcano.setGeographic(geometryFactory.createPoint(new Coordinate(point.get(0).getAsDouble(),point.get(1).getAsDouble())));
        return volcano;
    }

    @Override
    public WniEntityMapper<Volcano> getMapper() {
        return volcanoMapper;
    }
}
