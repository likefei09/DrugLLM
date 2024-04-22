package com.rainng.coursesystem.exception;

import com.rainng.coursesystem.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ExceptionHandler(value = MyException.class) -- 注解类型
 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) -- 错误码
 * @ResponseBody --  返回json
 * @ControllerAdvice -- 顾名思义，这是一个增强的 Controller。使用这个 Controller ，可以实现三个方面的功能：
 * 1.全局异常处理
 * 2.全局数据绑定
 * 3.全局数据预处理
 */
//@Slf4j
@ControllerAdvice // @RestControllerAdvice = @ControllerAdvice + @ResponseBody
//@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 业务异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(ServiceException se) {
        return Result.error(se.getCode(),se.getMessage());
    }
}
