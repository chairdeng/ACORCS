package com.acorcs.wni.mybatis.mapper.typehandler;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.vividsolutions.jts.geom.*;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dengc on 2016/12/11.
 */
@MappedJdbcTypes(value={JdbcType.VARCHAR},includeNullJdbcType=true)
@MappedTypes({Integer[].class})
public class IntArrayTypeHandler implements TypeHandler<Integer[]> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Integer[] ints, JdbcType jdbcType) throws SQLException {
        String parameter = Joiner.on(",").skipNulls().join(ints);
        preparedStatement.setString(i,parameter);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, String s) throws SQLException {
        String parameter = resultSet.getString(s);
        return toIntArray(parameter);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int i) throws SQLException {
        String parameter = resultSet.getString(i);
        return toIntArray(parameter);
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String parameter = callableStatement.getString(i);
        return toIntArray(parameter);
    }
    private Integer[]  toIntArray(String parameter){
        List<String> splits = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(parameter);
        Integer[] result = new Integer[splits.size()];
        for (int i=0;i<splits.size();i++){
            result[i] = Integer.valueOf(splits.get(i));
        }
        return result;
    }
}
