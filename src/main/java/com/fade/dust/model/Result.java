package com.fade.dust.model;

import lombok.Data;

@Data
public class Result {
    private Integer status;//状态 1成功 0失败
    private String massage;//提示信息

    public Result(Integer status, String massage) {
        this.status = status;
        this.massage = massage;
    }
}
