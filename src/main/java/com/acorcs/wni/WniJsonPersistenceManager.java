package com.acorcs.wni;

import com.acorcs.wni.entity.Notice;
import com.acorcs.wni.entity.WniEntity;
import com.acorcs.wni.mybatis.mapper.NoticeMapper;
import com.acorcs.wni.mybatis.mapper.WniEntityMapper;
import com.acorcs.wni.resolver.IResolver;
import com.acorcs.wni.resolver.ResolverFactory;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by dengc on 2016/12/11.
 */
@Component
public class WniJsonPersistenceManager {
    @Autowired
    protected NoticeMapper noticeMapper;
    @Autowired
    private ResolverFactory resolverFactory;
    private GsonJsonParser parser = new GsonJsonParser();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson = gsonBuilder.create();
    private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    @Transactional
    public void transformAndSave(String json){
        JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
        String type = jsonObject.get("type").getAsString();
        String elem = jsonObject.get("elem").getAsString();
        String dataname = jsonObject.get("dataname").getAsString();
        Date updated = convert2Date(jsonObject.get("updated").getAsString());
        Date basetime = convert2Date(jsonObject.get("updated").getAsString());
        Date validtime = convert2Date(jsonObject.get("updated").getAsString());
        Notice notice = new Notice();
        notice.setType(type);
        notice.setElem(elem);
        notice.setDataname(dataname);
        notice.setUpdated(updated);
        notice.setBasetime(basetime);
        notice.setValidtime(validtime);
        notice.setJson(json);
        noticeMapper.save(notice);
        JsonObject body = jsonObject.get("body").getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : body.entrySet()){
            String handler = entry.getKey();
            JsonArray bodyArray = body.get(handler).getAsJsonObject().get("body").getAsJsonArray();
            for(JsonElement jsonElement:bodyArray){
                JsonObject item = jsonElement.getAsJsonObject();
                String contentsKind = item.get("contents_kind").getAsString();
                IResolver resolver = resolverFactory.getResolver(handler,contentsKind);
                if(resolver == null){
                    logger.error("暂不支持"+handler+":"+contentsKind+"类型的转换");
                }else {
                    WniEntityMapper mapper = resolver.getMapper();
                    WniEntity entity = resolver.resolve(item.toString());
                    entity.setHeader(handler);
                    entity.setNoticeId(notice.getId());
                    mapper.save(entity);
                }
            }
        }

    }
    private Date convert2Date(String dateString){
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
