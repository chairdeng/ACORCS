package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Cloud;
import com.acorcs.wni.mybatis.mapper.CloudMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 邓超 on 2016/12/9.
 */
@Component
public class CloudResolver implements IResolver<Cloud>{

    @Autowired
    private CloudMapper cloudMapper;

    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public Cloud resolve(String json) {
        Cloud cloud = new Cloud();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        cloud.setCloudDistribution(jsonObject.get("cloud_distribution").getAsString());
        cloud.setCloudType(jsonObject.get("cloud_type").getAsString());
        if(jsonObject.get("airframe_icing")!=null) {
            cloud.setAirframeIcing(jsonObject.get("airframe_icing").getAsString());
        }
        if(jsonObject.get("extended_degree") != null) {
            cloud.setExtendedDegree(jsonObject.get("extended_degree").getAsInt());
        }
        if(jsonObject.get("altitudes") != null) {
            JsonArray altitudes = jsonObject.get("altitudes").getAsJsonArray();
            cloud.setAltitudes(altitudes.get(0).getAsInt());
        }
        cloud.setOriginal(jsonObject.get("points").toString());
        JsonArray points = jsonObject.get("points").getAsJsonArray();
        Coordinate[] coordinates = new Coordinate[points.size()];
        for(int i=0;i<points.size();i++){
            JsonElement point = points.get(i);
            Coordinate coordinate = new Coordinate(point.getAsJsonArray().get(0).getAsDouble(),point.getAsJsonArray().get(1).getAsDouble());
            coordinates[i] = coordinate;
        }
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        cloud.setGeographic(polygon);

        return cloud;
    }

    @Override
    public WniEntityMapper<Cloud> getMapper() {
        return cloudMapper;
    }
}
