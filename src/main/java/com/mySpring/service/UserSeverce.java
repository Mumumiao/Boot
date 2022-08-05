package com.mySpring.service;


import com.mySpring.boot.Menu;
import com.mySpring.boot.User;

import java.util.List;

public interface UserSeverce {

    public User login(String account, String password);
    public List<Menu> getMenu(int id);
    public void Add(User user);
}
