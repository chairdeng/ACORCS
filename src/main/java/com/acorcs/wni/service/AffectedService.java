package com.acorcs.wni.service;

import com.acorcs.wni.entity.CustomRestrictedArea;
import com.acorcs.wni.entity.GeometryEntity;
import com.acorcs.wni.entity.Notice;
import com.acorcs.wni.mybatis.mapper.*;
import com.acorcs.wni.common.utils.ClassUtil;
import com.acorcs.wni.service.task.GeometryTask;
import com.vividsolutions.jts.geom.Geometry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by dengc on 2017/1/2.
 */
@Component
@Slf4j
public class AffectedService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private CustomRestrictedAreaMapper customRestrictedAreaMapper;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private GeometryTask geometryTask;


    public List<GeometryEntity> findAffectWni(Geometry geometry, Date queryTime){
        List<GeometryEntity> result = new ArrayList<>();
        List<Notice> notices = noticeMapper.getValidNotice(queryTime);
        List<Future<List<GeometryEntity>>> futures = new ArrayList<>();
        log.debug("find {} notices",notices.size());
        for(Notice notice:notices){
            List<Class> childrenMapper = ClassUtil.getAllClassByInterface(WniEntityMapper.class);
            for(Class mapper:childrenMapper){
                WniEntityMapper wniEntityMapper = (WniEntityMapper)applicationContext.getBean(mapper);
                List<GeometryEntity> entities = wniEntityMapper.findByNoticeId(notice.getId());

                log.debug("find {} entities by notice {} mapper: {}",entities.size(),notice.getId(),mapper.getSimpleName());

                Future<List<GeometryEntity>> future = geometryTask.findCross(geometry,entities);
                futures.add(future);
            }
        }
//        Callable<List<GeometryEntity>> task = () -> {
//            List<CustomRestrictedArea> entities =  customRestrictedAreaMapper.findValid(queryTime);
//            return findCross(geometry,entities);
//        };
        List<CustomRestrictedArea> entities =  customRestrictedAreaMapper.findValid(queryTime);
        Future<List<GeometryEntity>> f = geometryTask.findCross(geometry,entities);
        futures.add(f);
        try {
            for(Future<List<GeometryEntity>> future :futures){

                result.addAll(future.get());
            }
            log.debug("count {} entitis",result.size());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }
//    private <T extends GeometryEntity> List<GeometryEntity> findCross(Geometry geometry,List<T> entities){
//        List<GeometryEntity> taskResult = new ArrayList<>();
//        for(GeometryEntity entity:entities){
//
//            if(entity.getGeographic()==null){
//                break;
//            }
//            if(geometry instanceof Point || geometry instanceof MultiPoint){
//                if(entity.getGeographic().contains(geometry)){
//                    log.debug("确定一个匹配entity[{}]",entity.getId());
//                    taskResult.add(entity);
//                }
//            }else if(geometry instanceof LineString){
//                if(entity.getGeographic().intersects(geometry)){
//                    log.debug("确定一个匹配entity[{}]",entity.getId());
//                    taskResult.add(entity);
//                }
//            }
//
//        }
//        return taskResult;
//    }

}
