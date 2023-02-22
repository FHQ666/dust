package com.fade.dust.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("data_record")
public class DataRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "type")
    private Integer type;

    @TableField(value = "add_time")
    private String addTime;

    @TableField(value = "val")
    private String val;

}


