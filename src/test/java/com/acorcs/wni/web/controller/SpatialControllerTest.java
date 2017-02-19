package com.acorcs.wni.web.controller;

import com.acorcs.wni.AcorcsWniApplication;
import com.acorcs.wni.entity.CustomRestrictedArea;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dengc on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AcorcsWniApplication.class)
@WebAppConfiguration
public class SpatialControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Autowired
    private SpatialController spatialController;
    @Test
    public void affected() throws Exception {

    }

    @Test
    public void custom() throws Exception {
        CustomRestrictedArea customRestrictedArea = new CustomRestrictedArea();
        customRestrictedArea.setCode("123123123");
        customRestrictedArea.setLevel(1);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/spatial/geometry/polygen")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(customRestrictedArea)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{message:\"ok\"}"))
        ;


    }

    @Test
    public void custom1() throws Exception {

    }

}