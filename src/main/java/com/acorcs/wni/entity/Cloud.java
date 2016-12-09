package com.acorcs.wni.entity;

import com.vividsolutions.jts.geom.Polygon;
import lombok.Data;

/**
 * Created by 邓超 on 2016/12/9.
 */

public class Cloud {
    private long id;
    private String header;
    private long noticeId;
    private String cloudDistribution;
    private String cloudType;
    private int altitudes;
    private String airframeIcing;
    private int extendedDegree;
    private Polygon geographic;
    private String wkt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getCloudDistribution() {
        return cloudDistribution;
    }

    public void setCloudDistribution(String cloudDistribution) {
        this.cloudDistribution = cloudDistribution;
    }

    public String getCloudType() {
        return cloudType;
    }

    public void setCloudType(String cloudType) {
        this.cloudType = cloudType;
    }

    public int getAltitudes() {
        return altitudes;
    }

    public void setAltitudes(int altitudes) {
        this.altitudes = altitudes;
    }

    public String getAirframeIcing() {
        return airframeIcing;
    }

    public void setAirframeIcing(String airframeIcing) {
        this.airframeIcing = airframeIcing;
    }

    public int getExtendedDegree() {
        return extendedDegree;
    }

    public void setExtendedDegree(int extendedDegree) {
        this.extendedDegree = extendedDegree;
    }

    public Polygon getGeographic() {
        return geographic;
    }

    public void setGeographic(Polygon geographic) {
        this.geographic = geographic;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }


}
