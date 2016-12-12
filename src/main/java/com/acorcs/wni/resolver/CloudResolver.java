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

import java.util.ArrayList;
import java.util.List;

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
        if(jsonObject.get("cloud_distribution")!=null) {
            cloud.setCloudDistribution(jsonObject.get("cloud_distribution").getAsString());
        }
        if (jsonObject.get("cloud_type")!=null){
            cloud.setCloudType(jsonObject.get("cloud_type").getAsString());
        }
        if(jsonObject.get("airframe_icing")!=null) {
            cloud.setAirframeIcing(jsonObject.get("airframe_icing").getAsString());
        }
        if(jsonObject.get("extended_degree") != null) {
            cloud.setExtendedDegree(jsonObject.get("extended_degree").getAsInt());
        }
        if(jsonObject.get("altitudes") != null) {
            JsonArray altitudes = jsonObject.get("altitudes").getAsJsonArray();
            if(altitudes.get(0).getAsInt()<0 && altitudes.size()>1) {
                cloud.setAltitudes(altitudes.get(1).getAsInt());
            }else {
                cloud.setAltitudes(altitudes.get(0).getAsInt());
            }
        }
        JsonArray points = jsonObject.get("points").getAsJsonArray();
//        cloud.setOriginal(points.toString());

        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        for(JsonElement point:points){

            Coordinate coordinate = new Coordinate(point.getAsJsonArray().get(0).getAsDouble(),point.getAsJsonArray().get(1).getAsDouble());
            coordinates.add(coordinate);
        }
        if(!coordinates.get(0).equals(coordinates.get(coordinates.size()-1))){
            coordinates.add((Coordinate)coordinates.get(0).clone());
        }
        Polygon polygon = null;
        try {
            polygon = geometryFactory.createPolygon(coordinates.toArray(new Coordinate[coordinates.size()]));
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
        cloud.setGeographic(polygon);

        return cloud;
    }

    @Override
    public WniEntityMapper<Cloud> getMapper() {
        return cloudMapper;
    }
}
