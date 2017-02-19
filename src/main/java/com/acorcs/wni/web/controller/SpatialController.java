package com.acorcs.wni.web.controller;


import com.acorcs.wni.entity.CustomCircleArea;
import com.acorcs.wni.entity.CustomRestrictedArea;
import com.acorcs.wni.entity.GeometryEntity;
import com.acorcs.wni.entity.WniEntity;
import com.acorcs.wni.service.AffectedService;
import com.acorcs.wni.service.CustomRestrictedAreaService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@Validated
@RequestMapping("/spatial")
public class SpatialController {
    @Autowired
    private AffectedService affectedService;
    @Autowired
    private CustomRestrictedAreaService customRestrictedAreaService;
    @RequestMapping(path="/affected")
    public List<GeometryEntity> affected(@NotNull(message = "{geographic.notnull.message}") Geometry geometry, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date queryTime){
        if(queryTime == null){
            queryTime = new Date();
        }
        return affectedService.findAffectWni(geometry,queryTime);
    }
    @RequestMapping(path = "/geometry/polygen",method = RequestMethod.POST)

    public String custom(@Validated @RequestBody CustomRestrictedArea customRestrictedArea){
        customRestrictedAreaService.save(customRestrictedArea);
        return "{message:\"ok\"}";
    }
    @RequestMapping(path = "/geometry/circle",method = RequestMethod.POST)
    public String custom(@Validated @RequestBody CustomCircleArea customCircleArea){
        customRestrictedAreaService.save(customCircleArea);
        return "{message:\"ok\"}";
    }
}
