package com.acorcs.wni.mybatis.mapper;

import com.acorcs.wni.AcorcsWniApplication;
import com.acorcs.wni.entity.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by dengc on 2016/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcorcsWniApplication.class)
public class TestNoticeMapper {
    @Autowired
    private NoticeMapper noticeMapper;
    @Test
    public void testSave(){
        Notice notice = new Notice();
        notice.setType("SIGWX");
        notice.setDataname("SIGWX");
        notice.setElem("EGRB LONDON");
        notice.setBasetime(new Date());
        notice.setUpdated(new Date());
        notice.setValidtime(new Date());
        System.out.println(noticeMapper.save(notice));
        System.out.println(notice.getId());
    }
}
