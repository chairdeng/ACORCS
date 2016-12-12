package com.acorcs.wni.rabbit;

import com.acorcs.wni.WniJsonPersistenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private WniJsonPersistenceManager jsonPersistenceManager;
    @RabbitListener(queues = "${wni.rabbitmq.queue}")
    @RabbitHandler
    public void process(@Payload Message payload) throws ParseException {
        String context = new String(payload.getBody());
        jsonPersistenceManager.transformAndSave(context);
    }
}
