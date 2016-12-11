package com.acorcs.wni.mybatis.mapper.typehandler;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dengc on 2016/12/10.
 */
@MappedJdbcTypes(value={JdbcType.BLOB},includeNullJdbcType=true)
@MappedTypes({Geometry.class, Point.class, MultiPoint.class,Polygon.class,LineString.class})
public class GeoTypeHandler implements TypeHandler<Geometry>{
    private GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),4326);
    private WKBReader wkbReader = new WKBReader(geometryFactory);
    private WKTWriter wktWriter = new WKTWriter();
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Geometry geometry, JdbcType jdbcType) throws SQLException {
        String wkt = wktWriter.write(geometry);
        preparedStatement.setString(i,wkt);
    }

    @Override
    public Geometry getResult(ResultSet resultSet, String s) throws SQLException {

        try {
            return wkbReader.read(resultSet.getBytes(s));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Geometry getResult(ResultSet resultSet, int i) throws SQLException {
        try {
            return wkbReader.read(resultSet.getBytes(i));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Geometry getResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            return wkbReader.read(callableStatement.getBytes(i));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
