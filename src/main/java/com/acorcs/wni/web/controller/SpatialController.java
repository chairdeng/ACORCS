package com.acorcs.wni.web.controller;


import com.acorcs.wni.entity.CustomCircleArea;
import com.acorcs.wni.entity.CustomRestrictedArea;
import com.acorcs.wni.entity.WniEntity;
import com.acorcs.wni.service.AffectedService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/spatial")
public class SpatialController {
    @Autowired
    private AffectedService affectedService;
    @RequestMapping(path="/affected")
    public List<WniEntity> affected(Geometry geometry, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date queryTime){
        if(queryTime == null){
            queryTime = new Date();
        }
        return affectedService.findAffectWni(geometry,queryTime);
    }
    @RequestMapping(path = "/geometry/polygen",method = RequestMethod.POST)
    public String custom(@RequestBody CustomRestrictedArea customRestrictedArea){
        return null;
    }
    @RequestMapping(path = "/geometry/circle",method = RequestMethod.POST)
    public String custom(@RequestBody CustomCircleArea customCircleArea){
        return null;
    }
}
