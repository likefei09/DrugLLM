package com.rainng.coursesystem.config;

import java.lang.annotation.*;

//当将注解放在接口上之后，当拦截器看到接口上面有这个注解之后就放行，不拦截，标识一些特殊的接口是可以被看见的
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {

}
