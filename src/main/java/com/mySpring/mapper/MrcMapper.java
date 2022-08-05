package com.mySpring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Menu;
import com.mySpring.boot.Mrc;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface MrcMapper extends BaseMapper<Mrc> {
    @Insert("insert into mrc (role_id,menu_id) values(#{role.id},#{menu.id})")
    void add(Mrc mrc);
    @Delete("delete from mrc where role_id=#{role.id} and menu_id=#{menu.id}")
    void dele(Mrc mrc);
    @Select("select * from mrc m,menu e where m.menu_id=e.id and m.role_id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "certificatify", property = "certificatify"),
            @Result(column = "href", property = "href"),
            @Result(column = "image", property = "image"),
            @Result(column = "famenu", property = "famenu")
    })
    List<Menu> seleMenuById(int id);
    @Delete("delete from mrc where role_id=#{id}")
    void deleByRole(int id);
}
