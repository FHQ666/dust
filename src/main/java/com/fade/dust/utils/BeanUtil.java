package com.fade.dust.utils;



import com.fade.dust.service.AsyncService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;

@Component
public class BeanUtil implements Serializable {

    @Resource
    public AsyncService asyncService;


    public static BeanUtil beanUtil;

    @PostConstruct
    public void  init(){
        beanUtil=this;
    }

}