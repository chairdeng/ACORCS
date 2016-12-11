package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.AcorcsWniApplication;
import com.acorcs.wni.entity.Cloud;
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

import java.util.List;
import static org.assertj.core.api.Assertions.*;


/**
 * Created by dengc on 2016/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcorcsWniApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCloudMapper {
    @Autowired
    private CloudMapper cloudMapper;
    private WKTReader reader = new WKTReader();
    @Test
    public void testFirstSave(){
        Cloud cloud = new Cloud();
        cloud.setNoticeId(4L);
        cloud.setAltitudes(5000);
        cloud.setAirframeIcing("MOD  (CLOUD)");
        cloud.setHeader("JUNE00");
        Polygon polygon = null;
        try {
            polygon = (Polygon)reader.read("Polygon((44.22 7.00,44.63 10.70,45.71 12.28,46.00 15.00,46.62 16.54,44.22 7.00))");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cloud.setGeographic(polygon);
        cloud.setOriginal("Polygon((44.22 7.00,44.63 10.70,45.71 12.28,46.00 15.00,46.62 16.54,44.22 7.00))");
        cloudMapper.save(cloud);
        assertThat(cloud.getId()).as("校验主键").isGreaterThan(0);

    }
    @Test
    public void testSecondFindByNoticeId(){
        List<Cloud> clouds = cloudMapper.findByNoticeId(4L);
        assertThat(clouds).as("断言查询结果").size().isGreaterThan(0);
    }
}
