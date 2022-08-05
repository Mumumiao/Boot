package com.mySpring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Insert("insert into role(id,scripe,name,status) values(#{id},#{scripe},#{name},#{status})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void add(Role role);

    List<Role> seleByCon(Role role);

    @UpdateProvider(type =RoleSqlprovide.class, method = "updateSql")
    void update(Role role);

    @Select("select * from role where id =#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "scripe", property = "scripe")
    })
    Role getById(int id);

    @Update("update  role set status='不可见' where id =#{id} ")
    void dele(int id);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "userrights", many = @Many(select = "com.woniu.mapper.UrcMapper.seleRightById"))
    })
    Role getRightById(int id);
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "menus", many = @Many(select = "com.woniu.mapper.MrcMapper.seleMenuById"))
    })
    Role getMenuById(int id);
}
