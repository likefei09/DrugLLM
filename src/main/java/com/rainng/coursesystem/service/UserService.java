package com.rainng.coursesystem.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.rainng.coursesystem.controller.dto.UserDTO;
import com.rainng.coursesystem.common.Constants;
import com.rainng.coursesystem.common.Result;
import com.rainng.coursesystem.controller.dto.UserDTO;
import com.rainng.coursesystem.controller.dto.UserPasswordDTO;
import com.rainng.coursesystem.entity.User;
import com.rainng.coursesystem.exception.ServiceException;
import com.rainng.coursesystem.mapper.UserMapper;
import com.rainng.coursesystem.utils.TokenUtils;
import io.netty.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ServerCloneException;

import static com.rainng.coursesystem.common.Constants.CODE_600;

@Service   //表示把service这个类注入到springboot容器中
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;
    public boolean saveUser(User user) {
//        if(user.getId() == null){
//            return save(user);   //mybatis-plus中自带的插入方法
//        }else{
//            return updateById(user);  //mybatis-plus中自带的新增方法
//        }

        return saveOrUpdate(user);   //直接可以根据id判断是否新增还是插入
    }




//    @Autowired
//    private UserMapper userMapper;



//    public int save(User user){
//        if(user.getId() == null){   //user没有id，则表示是新增
//            return userMapper.insert(user);
//        }else{   //否则为更新
//            return userMapper.update(user);
//        }
//    }
    private static final Log LOG = Log.get();

    //登录
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one != null) {
            BeanUtil.copyProperties(one,userDTO,true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    //注册
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);  //把copy完之后的用户对象存储到数据库
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名已存在");

        }
        return one;
    }

    //由于注册和登录方法体有很大的相同，故封装一个方法
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try{
            one = getOne(queryWrapper); //从数据库查询用户信息
        }catch(Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }

    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if(update < 1) {
            throw new ServiceException(Constants.CODE_600,"密码错误");
        }
    }
}
