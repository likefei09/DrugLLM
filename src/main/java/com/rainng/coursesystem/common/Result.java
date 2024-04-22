package com.rainng.coursesystem.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * 接口统一返回包装类
 */
//封装类，将接口的返回结果进行统一
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    //状态码在constants中

    //告诉前端本次请求是失败还是成功，前端和后台可以进行约定，如code为0失败这种情况
    private String code;
    //msg表示请求失败的原因
    private String msg;
    //后台需要携带的数据
    private Object data;

    //没有数据之后成功之后的统一返回结果
    public static Result success() {
        return new Result(Constants.CODE_200,"",null);
    }

    //有数据之后成功之后的统一返回结果
    public static Result success(Object data) {
        return new Result(Constants.CODE_200,"",data);
    }

    //错误的统一返回结果
    public static Result error(String code, String msg) {
        return new Result(code,msg,null);
    }

    //错误的统一返回结果
    public static Result error() {
        return new Result(Constants.CODE_500,"系统错误",null);
    }

}
