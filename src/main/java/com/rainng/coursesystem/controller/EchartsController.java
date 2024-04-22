package com.rainng.coursesystem.controller;

import cn.hutool.core.collection.CollUtil;
import com.rainng.coursesystem.common.Result;
import com.rainng.coursesystem.config.AuthAccess;
import com.rainng.coursesystem.entity.User;
import com.rainng.coursesystem.mapper.FileMapper;
import com.rainng.coursesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private UserService userService;

    @Resource
    private FileMapper fileMapper;

    @GetMapping("/example")
    public Result get() {
        //传递坐标
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    @GetMapping("/members")
    public Result members() {
        List<User> list = userService.list();   //将数据库的内容查出
        int a1 = 0;  //山东
        int a2 = 0;  //河南
        int a3 = 0;  //贵州
        int a4 = 0;  //陕西
        for(User user : list){
            String address = user.getAddress();
            switch (address) {
                case "山东": a1 += 1;break;
                case "河南": a2 += 1;break;
                case "贵州": a3 += 1;break;
                case "陕西": a4 += 1;break;
            }

        }
        return Result.success(CollUtil.newArrayList(a1, a2, a3, a4));

    }


    //该接口用于测试前台， @AuthAccess为我们自定义的授权
    @AuthAccess
    @GetMapping("/file/front/all")
    public Result frontAll() {
        return Result.success(fileMapper.selectList(null));
    }
}
