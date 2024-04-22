package com.rainng.coursesystem.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data  //get和set方法
@TableName(value = "sys_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore    //前端不展示密码
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private String avatar;


}
