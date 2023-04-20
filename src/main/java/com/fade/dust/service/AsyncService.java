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
    public boolean addData(int eCO2, int eCH20, int TVOC, int PM25, int PM10, double Temperature, double Humidity) {
        DataRecord dataRecord = new DataRecord();
        dataRecord.setECO2(eCO2);
        dataRecord.setECH20(eCH20);
        dataRecord.setTVOC(TVOC);
        dataRecord.setPM25(PM25);
        dataRecord.setPM10(PM10);
        dataRecord.setTemperature(Temperature);
        dataRecord.setHumidity(Humidity);

        int insert = dataRecordMapper.insert(dataRecord);
        return insert > 0 ? true : false;
    }
}
