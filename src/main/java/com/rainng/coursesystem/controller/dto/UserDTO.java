package com.rainng.coursesystem.controller.dto;

//用于接收参数的类

import lombok.Data;

/***
 * 接收前端登录请求的参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;

    //
    private String nickname;
    //头像
    private String avatar;

    private String token;
}
