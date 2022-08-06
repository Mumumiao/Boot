package com.mySpring.Web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mySpring.boot.AddGroup;
import com.mySpring.boot.Dept;
import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Boot")
@Transactional(rollbackFor =Exception.class)
public class DeptControl {

    @Autowired
    private DeptService deptService;

    @PostMapping("/sele")
    public ResponseEntility<List<Dept>> seleall() {
        List<Dept> list = deptService.list();
        list.forEach(i->{
            System.out.println(i.getName());
        });
        return ResponseFactory.getSuResponseEntility(list);
    }
    @PostMapping("/add")
    public ResponseEntility<String> add(@RequestBody @Valid Dept dept) {
       deptService.save(dept);
        return ResponseFactory.getSuResponseEntility("添加的部门的id为"+dept.getId());
    }
    @PostMapping("/up")
    public ResponseEntility<String> up(@RequestBody @Validated(AddGroup.class) Dept dept) {
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",dept.getId());
        deptService.update(dept,queryWrapper);
        return ResponseFactory.getSuResponseEntility("修改的部门的id为"+dept.getId());
    }
    @RequestMapping("/test")
    public String test(Dept dept) {
        return "测试成功";
    }



}
