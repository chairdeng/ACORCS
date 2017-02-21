package com.acorcs.wni.rabbit;

import com.acorcs.wni.WniJsonPersistenceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.text.ParseException;


/**
 * Created by dengc on 2016/12/4.
 */
@Service
@Slf4j
public class ReceiveListener {
    @Autowired
    private WniJsonPersistenceManager jsonPersistenceManager;
    @RabbitListener(queues = "${wni.rabbitmq.queue}")
    @RabbitHandler
    public void process(@Payload Message payload) throws ParseException {
        String context = new String(payload.getBody());
        log.debug(context);
        try {
            jsonPersistenceManager.transformAndSave(context);
        }catch (Exception e){
            log.error(e.getMessage(), e.getCause());
            log.error(context);
        }
    }
}
