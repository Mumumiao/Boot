package com.mySpring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<UserMapper> {
    @InsertProvider(type =UserSqlprovide.class, method = "addSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void add(User user);
    @Select("select * from user")
    List<User> seleAll();

    /* @Select("select * ,u.id ud ,r.id rd from user u,role r where u.role=r.id and u.id=#{id}")
     @Results({
             @Result(column = "ud", property = "id"),
             @Result(column = "account", property = "account"),
             @Result(column = "password", property = "password"),
             @Result(column = "role", property = "role.id"),
             @Result(column = "name", property = "role.name")
   })
     User getById(int id);*/
    @Select("select * from user where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "account", property = "account"),
            @Result(column = "password", property = "password"),
            @Result(column = "role", property = "role", one = @One(select = "com.mySpring.mapper.RoleMapper.getById", fetchType = FetchType.LAZY)),
    })
    User getById(int id);

    @UpdateProvider(type = UserSqlprovide.class, method = "updateSql")
    void update(User user);

    @Delete("delete from user where id=#{id}")
    void dele(int id);
    @Select("select * from user where account=#{account} and password=#{password}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "account", property = "account"),
            @Result(column = "password", property = "password"),
            @Result(column = "role", property = "role.id"),
    })
    User getByap(@Param("account") String account,@Param("password")String password);


}
