package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Turbulence;
import com.acorcs.wni.mybatis.mapper.TurbulenceMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class TurbulenceResolver implements IResolver<Turbulence> {
    @Autowired
    private TurbulenceMapper turbulenceMapper;
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private GeometryFactory geometryFactory = new GeometryFactory();
    @Override
    public Turbulence resolve(String json) {
        Turbulence turbulence = new Turbulence();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        int extendedDegree = jsonObject.get("extended_degree").getAsInt();
        turbulence.setExtendedDegree(extendedDegree);
        JsonArray altitudes = jsonObject.get("altitudes").getAsJsonArray();
        Integer[] altitudeArr = new Integer[altitudes.size()];
        for (int i = 0;i<altitudes.size();i++){
            altitudeArr[i] = altitudes.get(i).getAsInt();
        }
        turbulence.setAltitudes(altitudeArr);
        JsonArray points = jsonObject.get("points").getAsJsonArray();
        Coordinate[] coordinates = new Coordinate[points.size()];
        for(int i=0;i<points.size();i++){
            JsonElement point = points.get(i);
            Coordinate coordinate = new Coordinate(point.getAsJsonArray().get(0).getAsDouble(),point.getAsJsonArray().get(1).getAsDouble());
            coordinates[i] = coordinate;
        }
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        turbulence.setGeographic(polygon);
        turbulence.setOriginal(points.toString());
        return turbulence;
    }

    @Override
    public WniEntityMapper<Turbulence> getMapper() {
        return turbulenceMapper;
    }
}
