package com.acorcs.wni.resolver;

import com.acorcs.wni.AcorcsWniApplication;
import com.acorcs.wni.entity.Cloud;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 邓超 on 2016/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcorcsWniApplication.class)
public class TestCloudResolver {
    @Autowired
    private CloudResolver cloudResolver;
    @Test
    public void resolve(){
        Cloud cloud = cloudResolver.resolve("{\n" +
                "\t\t\t\t\"contents_kind\":\"CLOUD\",\n" +
                "\t\t\t\t\"draw_type\":\"AREA\",\n" +
                "\t\t\t\t\"cloud_distribution\":\"OCNL/EMBED\",\n" +
                "\t\t\t\t\"cloud_type\":\"CB\",\n" +
                "\t\t\t\t\"altitudes\":[14330],\n" +
                "\t\t\t\t\"points\":[[1.73, -62.72], [3.64, -60.38], [5.47, -61.30], [6.31, -64.77], [7.58, -65.90], [6.24, -71.48], [4.48, -71.34], [1.87, -67.31], [1.73, -62.72]]\n" +
                "\t\t\t}");
        System.out.println(cloud.getCloudType());
    }
}
