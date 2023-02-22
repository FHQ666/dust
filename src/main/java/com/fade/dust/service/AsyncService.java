package com.fade.dust.service;

import com.fade.dust.mapper.DataRecordMapper;
import com.fade.dust.pojo.DataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AsyncService {
    @Autowired
    private DataRecordMapper dataRecordMapper;

    //添加设备状态信息
    @Async //告诉spring这是一个异步任务
    public boolean addData(String type,String val){
        Date date = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DataRecord dataRecord = new DataRecord();
        dataRecord.setType(Integer.parseInt(type));
        dataRecord.setAddTime(dateFormat.format(date));
        dataRecord.setVal(val);
        int insert = dataRecordMapper.insert(dataRecord);
        return insert>0 ? true : false;
    }


}
