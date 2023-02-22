package com.fade.dust.model;

import com.fade.dust.controller.HelloController;

import java.math.BigDecimal;
import java.util.List;

public class Series {
    public String name;
    public String unit;
    public String type;

    public List<BigDecimal> data;
    public Series( String name, String type,String unit, List<BigDecimal> data) {
        super();
        this.name = name;
        this.unit = unit;
        this.type = type;
        this.data = data;
    }
}
