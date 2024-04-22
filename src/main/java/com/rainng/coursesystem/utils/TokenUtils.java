package com.rainng.coursesystem.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rainng.coursesystem.entity.User;
import com.rainng.coursesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    //首先将TokenUtils注册为springboot的一个bean
    private static UserService staticUserService;
    //通过resource的方式把userService对象引入
    @Resource
    private UserService userService;

    //在项目启动时将userService赋给静态对象staticUserService
    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }


    /***
     * 生成token
     */

    public static String genToken(String userId,String sign) {
        return JWT.create()
                .withAudience(userId) // 将userId保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 使用 HMAC256 签名算法和密钥进行签名，以password作为token的密钥

    }

    /***
     * 获取当前登录的用户信息
     */
    public static User getCurrentUser() {
        try{
            //RequestContextHolder获取当前请求的request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");  //拿到token
            if(StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);  //不为空再去获取解析
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch(Exception e){
            return null;
        }

        return null;

    }

}
