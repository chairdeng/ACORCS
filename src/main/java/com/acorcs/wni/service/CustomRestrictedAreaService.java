package com.acorcs.wni.service;

import com.acorcs.wni.common.utils.GeometryUtils;
import com.acorcs.wni.entity.CustomCircleArea;
import com.acorcs.wni.entity.CustomRestrictedArea;
import com.acorcs.wni.mybatis.mapper.CustomRestrictedAreaMapper;
import com.vividsolutions.jts.geom.*;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dengc on 2017/2/18.
 */
@Service
public class CustomRestrictedAreaService {
    @Autowired
    private CustomRestrictedAreaMapper customRestrictedAreaMapper;

    public void save(CustomRestrictedArea customRestrictedArea){
        customRestrictedAreaMapper.save(customRestrictedArea);
    }
    public void save(CustomCircleArea customCircleArea){
        CoordinateReferenceSystem crsSource = null;
        CoordinateReferenceSystem crsTarget = null;
        MathTransform transformWGS84 = null;
        MathTransform transformWebMercator = null;
        Point pointTarget = null;
        Polygon polygonTarget = null;
        try {
            crsSource = CRS.decode("EPSG:4326");
            crsTarget = CRS.decode("EPSG:3857");
            transformWGS84 = CRS.findMathTransform(crsSource, crsTarget);
            transformWebMercator = CRS.findMathTransform(crsTarget,crsSource);
            pointTarget = (Point) JTS.transform(customCircleArea.getCenter(), transformWGS84);
            Polygon polygon = GeometryUtils.createCircle(pointTarget, customCircleArea.getRadius());
            polygonTarget = (Polygon) JTS.transform(polygon,transformWebMercator);
        } catch (FactoryException e) {
            e.printStackTrace();
        } catch (TransformException e) {
            e.printStackTrace();
        }
        CustomRestrictedArea customRestrictedArea = new CustomRestrictedArea();
        customRestrictedArea.setGeographic(polygonTarget);
        customRestrictedArea.setCode(customCircleArea.getCode());
        customRestrictedArea.setLevel(customCircleArea.getLevel());
        customRestrictedArea.setBasetime(customCircleArea.getBasetime());
        customRestrictedArea.setValidtime(customCircleArea.getValidtime());
        customRestrictedAreaMapper.save(customRestrictedArea);
    }

}
