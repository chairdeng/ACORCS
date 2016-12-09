package com.acorcs.wni.resolver;

import com.acorcs.wni.entity.Cloud;
import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;

import java.util.List;
import java.util.Map;

/**
 * Created by 邓超 on 2016/12/9.
 */
public class CloudResolver implements IResolver<Cloud>{
    private GsonJsonParser parser = new GsonJsonParser();
    @Override
    public Cloud resolve(Long noticeId,String json) {
        Cloud cloud = new Cloud();
        cloud.setNoticeId(noticeId);
        Map<String,Object> attributes = parser.parseMap(json);
        cloud.setCloudDistribution((String) attributes.get("cloud_distribution"));
        cloud.setCloudType((String) attributes.get("cloud_type"));
        cloud.setAirframeIcing((String)attributes.get("airframe_icing"));
        if(attributes.get("extended_degree") != null) {
            cloud.setExtendedDegree(Integer.valueOf((String) attributes.get("extended_degree")));
        }
        if(attributes.get("altitudes") != null) {
            List<Integer> altitudes = (List<Integer>) attributes.get("altitudes");
            cloud.setAltitudes(altitudes.get(0));
        }
        return cloud;
    }
}
