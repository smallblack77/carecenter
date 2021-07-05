package org.csu.carecenter.common;

import lombok.Getter;

//生成Get方法
@Getter
public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    //不合法的参数
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    //枚举，使项目写起来更加方便，可维护性提高
    private final int code;
    private final String description;

    ResponseCode(int code,String description)
    {
        this.code = code;
        this.description = description;
    }
}
