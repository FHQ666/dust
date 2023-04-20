package com.fade.dust.controller;

import com.fade.dust.DustApplication;
import com.fade.dust.model.Echarts;
import com.fade.dust.model.Result;
import com.fade.dust.model.Series;
import com.fade.dust.pojo.DataRecord;
import com.fade.dust.service.DataRecordServiceImpl;
import com.fade.dust.utils.CommonUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @RequestMapping("/test")
    @ResponseBody
    public void test(Model model) {
        List<DataRecord> records = dataRecordService.getData("1", "", "");
        System.out.println(records);
    }


    @RequestMapping(value="/getData")
    @ResponseBody
    public Echarts lineData(String type, String datetime) {
        List<String> category = new ArrayList<String>();

        // 存储不同类型的数据
        Map<String, List<BigDecimal>> dataMap = new HashMap<>();

        // 将datetime字符串拆分为开始时间和结束时间
        String[] times = datetime.split(" - ");

        // 调用getData方法，传入所选类型和时间范围
        List<DataRecord> records = dataRecordService.getData(type, times[0], times[1]);

        // 遍历数据记录
        for (DataRecord record : records) {
            // 将记录的添加时间添加到category列表中
            category.add(record.getAddTime());

            // 获取所选类型的数据值
            String methodName = "get" + CommonUtil.captureName(DustApplication.typesField.get(type));
            BigDecimal value;

            try {
                Method method = DataRecord.class.getMethod(methodName);
                val invoke = method.invoke(record);
                value = new BigDecimal(invoke.toString());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("无法获取数据类型的值: " + type, e);
            }

            // 将数据值添加到对应类型的数据列表中
            dataMap.computeIfAbsent(type, k -> new ArrayList<>()).add(value);
        }

        // 创建图例，只包含所选类型的名称
        List<String> legend = new ArrayList<>(Collections.singletonList(DustApplication.types.get(type)));

        // 创建Series对象列表
        List<Series> series = new ArrayList<>();

        // 为所选数据类型创建一个Series对象
        String typeName = DustApplication.types.get(type);
        String unit = DustApplication.units.get(type);
        List<BigDecimal> data = dataMap.get(type);

        series.add(new Series(typeName, "line", unit, data));

        // 创建并返回Echarts对象
        Echarts echartsData = new Echarts(legend, category, series);
        return echartsData;
    }


    @RequestMapping(value="/addData")
    @ResponseBody
    public Result addData(int eCO2, int eCH20, int TVOC, int PM25, int PM10, double Temperature, double Humidity){
        boolean b = dataRecordService.addData(eCO2, eCH20, TVOC, PM25, PM10, Temperature, Humidity);
        return new Result(1,"ok");
    }

}
