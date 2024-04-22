package com.rainng.coursesystem.exception;

import lombok.Getter;

/***
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException{   //RuntimeException是Java提供的一种运行异常
    private String code;

    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }

}
