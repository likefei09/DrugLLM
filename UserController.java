package com.rainng.coursesystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.coursesystem.controller.dto.UserDTO;
import com.rainng.coursesystem.entity.User;
import com.rainng.coursesystem.mapper.UserMapper;
import com.rainng.coursesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //新增和修改
    @PostMapping   //save既有新增又有更新的功能，如何在一个接口里面既做到新增又做到更新，需要service
    public boolean save(@RequestBody User user){   //当前台传入一个json对象时，@RequestBody可以将json对象转化为user对象插入到数据库中
        //新增或更新
        return userService.saveUser(user);
    }

    //登录
//    @PostMapping
//    public boolean login(@RequestBody UserDTO userDTO){    //@RequestBody将前端传过来的json转化为Java对象
        //新增或更新
 //       return userService.login(userDTO);
 //   }

    //查询所有数据
    @GetMapping
    public List<User> findAll(){
       // return userMapper.findAll();
        return userService.list();   //自带方法
    }

    //删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){  //@PathVariable表示url参数
       // return userMapper.deleteById(id);
        return userService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeByIds(ids);
    }

//    //分页查询
//    //@RequestParam负责将接口路径中的参数转化为方法中的两个参数pageNum和pageSize
//    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum,
//                                       @RequestParam Integer pageSize,
//                                       @RequestParam String username){
//        pageNum = (pageNum-1) * pageSize;
//        username = "%" + username + "%";   //拼接实现模糊查询
//        List<User> data = userMapper.selectPage(pageNum,pageSize,username);
//        Integer total = userMapper.selelctTotal(username);  //查询总条数
//        Map<String,Object> res = new HashMap<>();
//        res.put("data",data);
//        res.put("total",total);
//        return res;
//    }


    //分页查询  -mybatis-plus的方式
    //@RequestParam负责将接口路径中的参数转化为方法中的两个参数pageNum和pageSize
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String username,
                                       @RequestParam(defaultValue = "") String email,
                                       @RequestParam(defaultValue = "") String address){     //(defaultValue = "")表示不传参数时设置为空
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){    //不为空时再进行查询拼接
            queryWrapper.like("username",username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }
        queryWrapper.orderByDesc("id");
        return userService.page(page,queryWrapper);
    }




}
