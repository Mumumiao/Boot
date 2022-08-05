package com.mySpring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mySpring.boot.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select * from menu where id=#{id}")
    void selectById(int id);

    @Update("update menu set status='不可见' where id=#{id}")
    void dele(int id);
    @Insert("insert into menu (name,certificatify,href,image,fa) values (#{name},#{certificatify},#{href},#{image},#{fa})")
    void add(Menu menu);
    @Select("select * from menu where status='可见'")
    List<Menu> selectall();
}
