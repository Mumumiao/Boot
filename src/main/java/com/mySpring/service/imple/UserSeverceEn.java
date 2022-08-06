package com.mySpring.service.imple;

import com.mySpring.boot.Menu;
import com.mySpring.boot.Role;
import com.mySpring.boot.User;
import com.mySpring.mapper.RoleMapper;
import com.mySpring.mapper.UserMapper;
import com.mySpring.service.UserSeverce;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class UserSeverceEn implements UserSeverce {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User login(String account, String password) {
        User user = userMapper.getByap(account, password);
        return user;
    }

    @Override
    public List<Menu> getMenu(int id) {
        User user = userMapper.getById(id);
        Role role = roleMapper.getMenuById(user.getRole().getId());
        return role.getMenus();
    }

    @Override
    public void Add(User user) {
        userMapper.add(user);
    }
    @Override
    public List<User> seleAll(){
       return userMapper.seleAll();
    }
}
