package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Cloud;
import com.acorcs.wni.mybatis.mapper.CloudMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 邓超 on 2016/12/9.
 */
@Component
public class CloudResolver implements IResolver<Cloud>{
    @Autowired
    private CloudMapper cloudMapper;
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson = gsonBuilder.create();
    @Override
    public Cloud resolve(String json) {
        Cloud cloud = new Cloud();
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        cloud.setCloudDistribution(jsonObject.get("cloud_distribution").getAsString());
        cloud.setCloudType(jsonObject.get("cloud_type").getAsString());
        cloud.setAirframeIcing(jsonObject.get("airframe_icing").getAsString());
        if(jsonObject.get("extended_degree") != null) {
            cloud.setExtendedDegree(jsonObject.get("extended_degree").getAsInt());
        }
        if(jsonObject.get("altitudes") != null) {
            JsonArray altitudes = jsonObject.get("altitudes").getAsJsonArray();
            cloud.setAltitudes(altitudes.get(0).getAsInt());
        }
        cloud.setOriginal(jsonObject.get("points").toString());
        JsonArray points = jsonObject.get("points").getAsJsonArray();
        /**
         * TODO
         * 创建Polygon
         */
        return cloud;
    }

    @Override
    public WniEntityMapper<Cloud> getMapper() {
        return cloudMapper;
    }
}
