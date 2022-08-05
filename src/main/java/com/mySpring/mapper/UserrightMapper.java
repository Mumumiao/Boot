package com.mySpring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Userright;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface UserrightMapper extends BaseMapper<UserrightMapper> {
    @Select("select * from userright where id=#{id}")
    Userright selectById(int id);
    @Update("update userright set status='不可见' where id=#{id}")
    void dele(int id);
    @Insert("insert into userright (name,certificatify,fa,url) values (#{name},#{certificatify},#{fa},#{url})")
    void add(Userright userright);
    @Select("select * from userright where status='可见'")
    List<Userright> seleAll();
}
