package com.fade.dust.controller;

import com.fade.dust.DustApplication;
import com.fade.dust.model.Echarts;
import com.fade.dust.model.Result;
import com.fade.dust.model.Series;
import com.fade.dust.pojo.DataRecord;
import com.fade.dust.service.DataRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HelloController {

    @Autowired
    private DataRecordServiceImpl dataRecordService;
    @RequestMapping("/index")
    public String hello(Model model) {
        Date date = new Date(System.currentTimeMillis());
        Date date1 = new Date(System.currentTimeMillis()+86399000);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        model.addAttribute("types", DustApplication.types);
        model.addAttribute("datetime",dateFormat.format(date) + " - " + dateFormat.format(date1));
        return "hello";
    }

    /**
     * 获取数据
     * @param type 类型
     * @param datetime 时间范围"2023-02-16 00:00:00 - 2023-02-16 11:52:45"
     * @return
     */
    @RequestMapping(value="/getData")
    @ResponseBody
    public Echarts lineData(String type,String datetime) {
        List<String> category = new ArrayList<String>();
        List<BigDecimal> index = new ArrayList<BigDecimal>();
        String[] times = datetime.split(" - ");
        List<DataRecord> records = dataRecordService.getData(type, times[0], times[1]);
        for(DataRecord record : records){//循环获取数据
            category.add(record.getAddTime());
            index.add(new BigDecimal(record.getVal()));
        }
        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{DustApplication.types.get(type)}));//数据分组
        List<Series> series = new ArrayList<Series>();
        series.add(new Series(DustApplication.types.get(type), "line", DustApplication.units.get(type), index));
        Echarts data=new Echarts(legend, category, series);
        return data;
    }

    /**
     * 添加数据
     * @param type
     * @param val
     * @return
     */
    @RequestMapping(value="/addData")
    @ResponseBody
    public Result addData(String type, String val){
        boolean b = dataRecordService.addData(type, val);
        return new Result(1,"ok");
    }
}
