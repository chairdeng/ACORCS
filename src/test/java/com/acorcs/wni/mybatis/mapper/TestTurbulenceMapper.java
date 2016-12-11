package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.AcorcsWniApplication;
import com.acorcs.wni.entity.Turbulence;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dengc on 2016/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcorcsWniApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTurbulenceMapper {
    @Autowired
    private TurbulenceMapper turbulenceMapper;
    private WKTReader reader = new WKTReader();
    @Test
    public void save(){
        Turbulence turbulence = new Turbulence();
        Polygon polygon = null;
        try {
            polygon = (Polygon)reader.read("Polygon((44.22 7.00,44.63 10.70,45.71 12.28,46.00 15.00,46.62 16.54,44.22 7.00))");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        turbulence.setGeographic(polygon);
        turbulence.setNoticeId(4);
        turbulence.setHeader("JUCE00");
        turbulence.setAltitudes(new Integer[]{1,2,3,4});
        turbulenceMapper.save(turbulence);
        assertThat(turbulence.getId()).as("校验主键").isGreaterThan(0);
    }

}
