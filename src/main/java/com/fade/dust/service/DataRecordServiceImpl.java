package com.fade.dust.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fade.dust.mapper.DataRecordMapper;
import com.fade.dust.pojo.DataRecord;
import com.fade.dust.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataRecordServiceImpl {

    @Autowired
    private DataRecordMapper dataRecordMapper;
    private QueryWrapper queryWrapper = new QueryWrapper();

    public List<DataRecord> getData(String type,String beginTime,String endTime){
        queryWrapper.clear();
        queryWrapper.eq("type",type);
        if(beginTime != null && beginTime != ""){
            queryWrapper.gt("add_time",beginTime);
        }
        if(endTime != null && endTime != ""){
            queryWrapper.lt("add_time",endTime);
        }
        queryWrapper.orderByAsc("add_time");
        return dataRecordMapper.selectList(queryWrapper);
    }

    public boolean addData(String type,String val){
        return BeanUtil.beanUtil.asyncService.addData(type,val);
    }
}
