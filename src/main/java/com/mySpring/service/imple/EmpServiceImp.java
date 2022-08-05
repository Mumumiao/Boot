package com.mySpring.service.imple;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mySpring.boot.Emp;
import com.mySpring.boot.PageBean;
import com.mySpring.mapper.EmpMapper;
import com.mySpring.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImp extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean<Emp> getBycon(Emp emp,int page,int size){
        PageBean<Emp> pageBean=new PageBean<>();
        Page<Object> p = PageHelper.startPage(page, size);
        List<Emp> emps=empMapper.seleByCon(emp);
        pageBean.setData(emps);
        pageBean.setCurrPage(page);
        pageBean.setPageSize(emps.size());
        pageBean.setTotalPages(p.getPages());
        pageBean.setTotalNums((int) p.getTotal());
        return pageBean;
    }
    @Override
    public  void up(Emp emp){
        empMapper.up(emp);
 }


}
