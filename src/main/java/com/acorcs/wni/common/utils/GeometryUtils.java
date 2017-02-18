package com.acorcs.wni.common.utils;

import com.vividsolutions.jts.geom.*;
import org.geotools.geometry.jts.JTSFactoryFinder;

/**
 * Created by dengc on 2017/2/19.
 */
public final class GeometryUtils {
    public static Polygon createCircle(Point center, double radius){
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        final int SIDES = 32;//圆上面的点个数
        Coordinate coords[] = new Coordinate[SIDES+1];
        for(int i = 0; i < SIDES; i++){
            double angle = ((double) i / (double) SIDES) * Math.PI * 2.0;
            double dx = Math.cos(angle) * radius;
            double dy = Math.sin(angle) * radius;
            coords[i] = new Coordinate(center.getX() + dx,center.getY() + dy);
        }
        coords[SIDES] = coords[0];
        LinearRing ring = geometryFactory.createLinearRing(coords);
        Polygon polygon = geometryFactory.createPolygon(ring,null);
        return polygon;
    }
}
