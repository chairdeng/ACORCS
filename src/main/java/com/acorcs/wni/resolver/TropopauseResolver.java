package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Tropopause;
import com.acorcs.wni.mybatis.mapper.TropopauseMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.*;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 邓超 on 2016/12/12.
 */
public class TropopauseResolver implements IResolver<Tropopause>{
    @Autowired
    private TropopauseMapper tropopauseMapper;
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private GeometryFactory geometryFactory = new GeometryFactory();
    @Override
    public Tropopause resolve(String json) {
        Tropopause tropopause = new Tropopause();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        if(jsonObject.get("significance")!=null){
            String significance = jsonObject.get("significance").getAsString();
            tropopause.setSignificance(significance);
        }
        JsonArray points = jsonObject.get("points").getAsJsonArray();
        Coordinate[] coordinates = new Coordinate[points.size()];
        for(int i=0;i<points.size();i++){
            JsonElement point = points.get(i);
            Coordinate coordinate = new Coordinate(point.getAsJsonArray().get(0).getAsDouble(),point.getAsJsonArray().get(1).getAsDouble());
            coordinates[i] = coordinate;
        }
        tropopause.setGeographic(geometryFactory.createMultiPoint(coordinates));
        tropopause.setOriginal(points.toString());
        return tropopause;
    }

    @Override
    public WniEntityMapper<Tropopause> getMapper() {
        return tropopauseMapper;
    }
}
