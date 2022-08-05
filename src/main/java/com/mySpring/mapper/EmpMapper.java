package com.mySpring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
    @SelectProvider(type = EmpSqlProvide.class,method = "seleS")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "gender",property = "gender"),
            @Result(column = "photo",property = "photo"),
            @Result(column = "entry_date",property = "entry_date"),
            @Result(column = "salary",property = "salary"),
            @Result(column = "dept_id",property = "dept.id")
    })
    List<Emp> seleByCon(Emp emp);
    void up(Emp emp);

}
