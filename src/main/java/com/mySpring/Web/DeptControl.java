package com.mySpring.Web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mySpring.boot.AddGroup;
import com.mySpring.boot.Config.Jwtutil;
import com.mySpring.boot.Dept;
import com.mySpring.boot.ResponseEntility;
import com.mySpring.boot.ResponseFactory;
import com.mySpring.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Boot")
@Transactional(rollbackFor =Exception.class)
public class DeptControl {
    @Autowired
    private Jwtutil jwtutil;
    @Autowired
    private DeptService deptService;

    @PostMapping("/sele")
    public ResponseEntility seleall(@RequestHeader String jwt) {
        System.out.println(jwt);
        if(jwtutil.verifyJWT(jwt)){
            List<Dept> list = deptService.list();
            list.forEach(i->{
                System.out.println(i.getName());
            });
            return ResponseFactory.getSuResponseEntility(list);
        }else{
            return ResponseFactory.getDeResponseEntility("令牌无效");
        }

    }
    @PostMapping("/add")
    public ResponseEntility add(@RequestBody @Validated(AddGroup.class)  Dept dept,@RequestHeader String jwt) {
        System.out.println(jwt);
        if(jwtutil.verifyJWT(jwt)){
            deptService.save(dept);
            return ResponseFactory.getSuResponseEntility(dept.getId());
        }else {
            return ResponseFactory.getDeResponseEntility("令牌无效");
        }

    }
    @PostMapping("/up")
    public ResponseEntility<String> up(@RequestBody @Valid Dept dept) {
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",dept.getId());
        deptService.update(dept,queryWrapper);
        return ResponseFactory.getSuResponseEntility("修改的部门的id为"+dept.getId());
    }
    @RequestMapping("/test")
    public String test(Dept dept) {
        return "测试成功";
    }
    @PostMapping("getbyid/{id}")
    public ResponseEntility get(@PathVariable("id")  int id,@RequestHeader String jwt) {
        if (jwtutil.verifyJWT(jwt)) {
            return ResponseFactory.getSuResponseEntility(deptService.getById(id));
        } else {
            return ResponseFactory.getDeResponseEntility("令牌无效");
        }


    }
    @PostMapping("dele")
    public ResponseEntility dele(@RequestBody Dept dept,@RequestHeader String jwt){
        if(jwtutil.verifyJWT(jwt)) {
            deptService.removeById(dept);
            return ResponseFactory.getSuResponseEntility("删除成功");
        }else{
            return ResponseFactory.getDeResponseEntility("令牌无效");
        }
    }
}
