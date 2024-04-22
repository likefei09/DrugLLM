package com.rainng.coursesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.controller.dto.UserPasswordDTO;
import com.rainng.coursesystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// @Mapper  //加入该注解将mapper注入到springboot中
//数据库查询的接口，实现与数据库的交互
public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from sys_user")
//    List<User> findAll();
//
//    @Insert("insert into sys_user(username,password,nickname,email,phone,address) values (#{username},#{password}," +
//            "#{nickname},#{email},#{phone},#{address})")
//    int insert(User user);
//
//
//    int update(User user);
//
//    @Delete("delete from sys_user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like #{username} limit #{pageNum}, #{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize, String username);
//
//    @Select("select count(*) from sys_user where username like #{username}")
//    Integer selelctTotal(String username);

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

}
