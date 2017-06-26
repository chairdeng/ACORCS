package com.acorcs.wni.service.task;

import com.acorcs.wni.entity.GeometryEntity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by dengchao5 on 2017/2/23.
 */
@Service
@Slf4j
public class GeometryTask {
    @Async("asyncExecutor")
    public  <T extends GeometryEntity> Future<List<GeometryEntity>> findCross(Geometry geometry, List<T> entities){
        List<GeometryEntity> taskResult = new ArrayList<>();
        for(GeometryEntity entity:entities){

            if(entity.getGeographic()==null){
                break;
            }
            if(geometry instanceof Point || geometry instanceof MultiPoint){
                if(entity.getGeographic().contains(geometry)){

                    taskResult.add(entity);
                }
            }else if(geometry instanceof LineString){
                if(entity.getGeographic().intersects(geometry)){

                    taskResult.add(entity);
                }
            }

        }
//        log.debug("geometry count:{}",taskResult.size());
        return new AsyncResult<>(taskResult);
    }
}
