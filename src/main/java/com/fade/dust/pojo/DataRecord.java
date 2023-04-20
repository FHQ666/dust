package com.fade.dust.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("data")
public class DataRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "eCO2")
    private Integer eCO2;

    @TableField(value = "eCH20")
    private Integer eCH20;

    @TableField(value = "TVOC")
    private Integer TVOC;

    @TableField(value = "PM25")
    private Integer PM25;

    @TableField(value = "PM10")
    private Integer PM10;

    @TableField(value = "Temperature")
    private Double Temperature;

    @TableField(value = "Humidity")
    private Double Humidity;

    @TableField(value = "add_time")
    private String addTime;
}


