package com.mySpring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
 @Select("select * from oa_dept")
public List<Dept> seleAll(Dept dept);
 @Select("select * from oa_dept where id=#{id}")
 public Dept seleById(int id);
}
