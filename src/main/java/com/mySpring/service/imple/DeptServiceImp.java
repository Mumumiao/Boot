package com.mySpring.service.imple;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mySpring.boot.Dept;
import com.mySpring.mapper.DeptMapper;
import com.mySpring.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImp extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Autowired
    private DeptMapper oaDeptMapper;
    @Override
    public Dept getById(int id){
       return oaDeptMapper.seleById(id);
    }
}
