package com.rainng.coursesystem.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rainng.coursesystem.common.Constants;
import com.rainng.coursesystem.config.AuthAccess;
import com.rainng.coursesystem.entity.User;
import com.rainng.coursesystem.exception.ServiceException;
import com.rainng.coursesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");  //前端发来的token

        //如果不是映射到方法直接通过，即没有请求具体的方法
        if(!(handler instanceof HandlerMethod)){
            return true;
        }else{  //通过判断其注解是否是我们自定义的注解，若是，那么拦截器放行
            HandlerMethod h = (HandlerMethod) handler;
            AuthAccess authAccess = h.getMethodAnnotation(AuthAccess.class);
            if(authAccess != null){
                return true;
            }
        }
        //执行认证
        if(StrUtil.isBlank(token)){  //判断是否是空字符串
            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");  //自定义异常，权限验证错误
        }
        //获取token中的userId
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);   //将载荷取出，验证是否合法
        }catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401,"token验证失败，请重新登录");
        }
        //根据token中的userId查询数据库
        User user = userService.getById(userId);  //查询响应数据
        if(user == null) {
            throw new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        //用户密码加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);  //验证token
        }catch(JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        return true;
    }
}
