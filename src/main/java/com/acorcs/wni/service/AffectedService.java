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

        for(Notice notice:notices){
            List<Class> childrenMapper = ClassUtil.getAllClassByInterface(WniEntityMapper.class);

            for(Class mapper:childrenMapper){
                WniEntityMapper wniEntityMapper = (WniEntityMapper)applicationContext.getBean(mapper);
                List<GeometryEntity> entities = wniEntityMapper.findByNoticeId(notice.getId());


                Future<List<GeometryEntity>> future = geometryTask.findCross(geometry,entities);
                futures.add(future);
            }
        }

        List<CustomRestrictedArea> entities =  customRestrictedAreaMapper.findValid(queryTime);
        Future<List<GeometryEntity>> f = geometryTask.findCross(geometry,entities);
        futures.add(f);
        try {

            for(Future<List<GeometryEntity>> future :futures){

                result.addAll(future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            log.error("异步任务异常：",e);
            e.printStackTrace();
        }

        return result;
    }


}
