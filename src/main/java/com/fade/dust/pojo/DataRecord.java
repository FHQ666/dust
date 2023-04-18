package com.fade.dust.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//-------------------------------------------------------------
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
    private Integer eCO2;
    private Integer eCH20;
    private Integer TVOC;
    private Integer PM25;
    private Integer PM10;
    private Double Temperature;
    private Double Humidity;
    private Timestamp timestamp;

    // 省略 getter 和 setter 方法
}
//----------------------------------------------------------

//@TableName("data_record")
//public class DataRecord {
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
//
//    @TableField(value = "type")
//    private Integer type;
//
//    @TableField(value = "add_time")
//    private String addTime;
//
//    @TableField(value = "val")
//    private String val;
//
//}


