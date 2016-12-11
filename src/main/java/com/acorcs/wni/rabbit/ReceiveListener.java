package com.acorcs.wni.rabbit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dengc on 2016/12/4.
 */
@Service
public class ReceiveListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson = gsonBuilder.create();
    private static JsonParser jsonParser = new JsonParser();
    @RabbitListener(queues = "${wni.rabbitmq.queue}")
    @RabbitHandler
    public void process(@Payload Message payload) throws ParseException {
        String context = new String(payload.getBody());
//        logger.info(context);
        JsonObject jsonObject = gson.fromJson(context,JsonObject.class);
//        JsonObject jsonObject = jsonParser.parse(context).getAsJsonObject();
        JsonObject header = jsonObject.getAsJsonObject("header");
        JsonObject body = jsonObject.getAsJsonObject("body");
        String type = jsonObject.get("type").getAsString();
        String elem = jsonObject.get("elem").getAsString();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Date updated = dateFormat.parse(jsonObject.get("updated").getAsString());
//        Date updated = new Date(jsonObject.get("updated").getAsInt() * 1000L);
        System.out.println(body);
    }
}
