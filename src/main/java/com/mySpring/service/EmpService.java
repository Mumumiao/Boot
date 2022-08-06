package com.mySpring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mySpring.boot.Emp;
import com.mySpring.boot.PageBean;

import java.util.List;

public interface EmpService extends IService<Emp> {
    public PageBean<Emp> getBycon(Emp emp, int page, int size);
    public  void up(Emp emp);
    public List<Emp> getByDp(int id);
}
