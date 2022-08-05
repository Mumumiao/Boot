package com.mySpring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Urc;
import com.mySpring.boot.Userright;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UrcMapper extends BaseMapper<Urc> {
    @Insert("insert into urc (role_id,right_id) values(#{role.id},#{userright.id})")
    void add(Urc urc);
    @Delete("delete from urc where role_id=#{role.id} and right_id=#{userright.id}")
    void dele(Urc urc);
    @Select("select * from urc u,userright r where u.right_id=r.id and u.role_id=#{id}")
    @Results({
            @Result(column = "name", property = "name"),
            @Result(column = "certificatify", property = "certificatify"),
            @Result(column = "faright", property = "faright"),
            @Result(column = "url",property = "url")
    })
    List<Userright> seleRightById(int id);
}
