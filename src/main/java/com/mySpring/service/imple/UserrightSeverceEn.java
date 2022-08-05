package com.mySpring.service.imple;


import com.mySpring.boot.Userright;
import com.mySpring.mapper.UserrightMapper;
import com.mySpring.service.UserrightSeverce;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class UserrightSeverceEn implements UserrightSeverce {
    @Autowired
    private UserrightMapper userrightMapper;
    @Override
    public List<Userright> getAll(){

       return userrightMapper.seleAll();
    }

}
